package com.example.betterwayfinal.Activity.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.betterwayfinal.Activity.Helper.DBHelper;
import com.example.betterwayfinal.R;

public class LoginActivity extends AppCompatActivity {

    TextView textViewCadastro;

    DBHelper db = new DBHelper(this);

    EditText idboxemail, idboxsenha;
    Button buttonlogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        idboxemail = findViewById(R.id.boxEmailLogin);
        idboxsenha= findViewById(R.id.boxSenhaLogin);

        textViewCadastro = findViewById(R.id.textViewCadastro);

        textViewCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CadastroActivity.class);
                startActivity(intent);
            }
        });

        buttonlogin = findViewById(R.id.buttonLogin);

        buttonlogin.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                final String boxEmail = idboxemail.getText().toString();
                final String boxSenha = idboxsenha.getText().toString();

                if (db.validarUsuario(boxEmail, boxSenha)){
                    Intent intent = new Intent(getApplicationContext(), MapaActivity.class);
                    startActivity(intent);

                    Toast.makeText(LoginActivity.this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(LoginActivity.this, "Login ou senha incorretos", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
