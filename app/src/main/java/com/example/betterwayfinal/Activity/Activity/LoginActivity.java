package com.example.betterwayfinal.Activity.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.betterwayfinal.R;

public class LoginActivity extends AppCompatActivity {

    TextView textViewCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textViewCadastro = findViewById(R.id.textViewCadastro);


        textViewCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CadastroActivity.class);
                startActivity(intent);
            }
        });
    }
    public void confirmarLogin(View view) {

        Intent telaInicial = new Intent(this, MapaActivity.class);
        startActivity(telaInicial);
    }
}
