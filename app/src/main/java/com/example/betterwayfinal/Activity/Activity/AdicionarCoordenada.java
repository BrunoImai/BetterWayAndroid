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
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.betterwayfinal.R;
import com.getbase.floatingactionbutton.FloatingActionButton;

public class AdicionarCoordenada extends AppCompatActivity implements SensorEventListener {
    private static final String TAG = "DadosFragment";

    private SensorManager sensorManager;

    private Sensor acelerometro;

    private String X,Y,Z;

    private TextView ViewX,ViewY,ViewZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_coordenada);

        ViewX = findViewById(R.id.textViewCordX);
        ViewY = findViewById(R.id.textViewCordY);
        ViewZ = findViewById(R.id.textViewCordZ);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        acelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
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
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        X = String.valueOf(event.values[0]);
        Y = String.valueOf(event.values[1]);
        Z = String.valueOf(event.values[2]);

        ViewX.setText(X);
        ViewY.setText(Y);
        ViewZ.setText(Z);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
