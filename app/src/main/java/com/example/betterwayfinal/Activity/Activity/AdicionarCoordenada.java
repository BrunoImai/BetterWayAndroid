package com.example.betterwayfinal.Activity.Activity;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.betterwayfinal.Activity.Helper.DBHelper;
import com.example.betterwayfinal.R;
import com.getbase.floatingactionbutton.FloatingActionButton;

public class AdicionarCoordenada extends AppCompatActivity implements SensorEventListener {
    private static final String TAG = "DadosFragment";

    private TextView ViewX,ViewY,ViewZ;

    private RadioButton radioButtonLombada, radioButtonBuraco;

    float sensorX,sensorY,sensorZ;

    DBHelper db = new DBHelper(this);

    MapaActivity mp = new MapaActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_coordenada);

        ViewX = findViewById(R.id.textViewCordX);
        ViewY = findViewById(R.id.textViewCordY);
        ViewZ = findViewById(R.id.textViewCordZ);

        radioButtonLombada = findViewById(R.id.radioButtonLombada);
        radioButtonBuraco = findViewById(R.id.radioButtonBuraco);

        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

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
                
                String tipoDeDesnivel = "";
                if( radioButtonBuraco.isChecked() ){
                    tipoDeDesnivel = "Buraco";
                }else if(radioButtonLombada.isChecked()){
                    tipoDeDesnivel = "Lombada";
                }
                
                db.cadastrarCoordenadas(sensorX,sensorY,sensorZ, tipoDeDesnivel, mp.latitude, mp.longitude);
            }
        });
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        sensorX = event.values[0];
        sensorY = event.values[1];
        sensorZ = event.values[2];

        String  x = String.valueOf(event.values[0]);
        String  y = String.valueOf(event.values[1]);
        String  z = String.valueOf(event.values[2]);

        ViewX.setText(x);
        ViewY.setText(y);
        ViewZ.setText(z);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


}
