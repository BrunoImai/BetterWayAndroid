package com.example.betterwayfinal.Activity.Activity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.betterwayfinal.Activity.Helper.DBHelper;
import com.example.betterwayfinal.R;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.concurrent.ExecutionException;

public class AdicionarCoordenada extends AppCompatActivity implements SensorEventListener {
    private static final String TAG = "DadosFragment";

    private TextView ViewX,ViewY,ViewZ,teste;

    float sensorX,sensorY,sensorZ, ultimoX, ultimoY, ultimoZ;

    private float diferencaX, diferncaY, diferencaZ;

    private float limiarDeAgitacao = 5f;

    private boolean primeiraVez = true;

    private Vibrator vibrator;

    DBHelper db = new DBHelper(this);

    MapaActivity mp = new MapaActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_coordenada);

        ViewX = findViewById(R.id.textViewCordX);
        ViewY = findViewById(R.id.textViewCordY);
        ViewZ = findViewById(R.id.textViewCordZ);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        teste = findViewById(R.id.textViewTESTE);
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        assert sensorManager != null;
        Sensor acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, acelerometro, SensorManager.SENSOR_DELAY_NORMAL);
        Log.d(TAG, "onCreate: Registrando listener do Acelerometro ");

        FloatingActionButton fabUsuario = findViewById(R.id.fab_usuario);
        fabUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), UsuarioActivity.class);
                startActivity(intent);

            }
        });
        FloatingActionButton fabNotific = findViewById(R.id.fab_notific);
        fabNotific.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NotificacaoActivity.class);
                startActivity(intent);

            }
        });
        FloatingActionButton fabMapa = findViewById(R.id.fab_mapa);
        fabMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MapaActivity.class);
                startActivity(intent);
            }
        });

        Button buttonEnviarCoordenada = findViewById(R.id.buttonSalvarCoordenada);
        buttonEnviarCoordenada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                http service = new http(teste.getText().toString());
                try {
                    com.example.betterwayfinal.Activity.Helper.teste retorno = service.execute().get();
                    teste.setText(retorno.toString());
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        sensorX = event.values[0];
        sensorY = event.values[1];
        sensorZ = event.values[2];

        ViewX.setText(String.valueOf(event.values[0]));
        ViewY.setText(String.valueOf(event.values[1]));
        ViewZ.setText(String.valueOf(event.values[2]));

        if (!primeiraVez){

            float diferencaX = Math.abs(ultimoX - sensorX);
            float diferencaY = Math.abs(ultimoX - sensorY);
            float diferencaZ = Math.abs(ultimoX - sensorZ);

            if ((diferencaX > limiarDeAgitacao && diferencaY > limiarDeAgitacao) ||
                (diferencaX > limiarDeAgitacao && diferencaZ > limiarDeAgitacao) ||
                    (diferencaY > limiarDeAgitacao && diferencaZ > limiarDeAgitacao))
            {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                    db.cadastrarCoordenadas(sensorX,sensorY,sensorZ, mp.latitude, mp.longitude);
                }else {
                    vibrator.vibrate(500);
                }
            }
        }

        ultimoX = sensorX;
        ultimoY = sensorY;
        ultimoZ = sensorZ;

        primeiraVez = false;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
