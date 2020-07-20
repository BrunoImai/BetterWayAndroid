package com.example.betterwayfinal.Activity.Activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.betterwayfinal.Activity.Helper.DBHelper;
import com.example.betterwayfinal.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class CadastroActivity extends AppCompatActivity {
    private String[] permissoes = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };

    private CircleImageView circleImageViewUsuario;

    private EditText idboxEmail, idboxSenha, idboxConfirmarSenha, idboxIdadeString, idboxNome;
    private RadioButton radioButtonMasc, radioButtonFem;

    DBHelper dbHelper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        ImageButton cameraButton = findViewById(R.id.cameraButton);
        ImageButton galeriaButton = findViewById(R.id.galeriaButton);
        circleImageViewUsuario = findViewById(R.id.imagemPerfil);

        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (i.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(i, 100);
                }
            }
        });

        galeriaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                if (getIntent().resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(i, 200);
                }
            }
        });

        Button confirmarButton = findViewById(R.id.buttonLogin);
        idboxConfirmarSenha = findViewById(R.id.boxConfirmarSenha);
        idboxEmail = findViewById(R.id.boxEmailCadastro);
        idboxSenha = findViewById(R.id.boxSenhaCadastro);
        idboxIdadeString = findViewById(R.id.boxIdade);
        idboxNome = findViewById(R.id.boxNomeCadastro);

        radioButtonFem = findViewById(R.id.radioButtonFeminino);
        radioButtonMasc = findViewById(R.id.radioButtonMasculino);

        confirmarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String boxEmail = idboxEmail.getText().toString();
                String boxSenha = idboxSenha.getText().toString();
                String boxConfirmarSenha = idboxConfirmarSenha.getText().toString();
                String boxNome = idboxNome.getText().toString();

                String boxIdadeString = idboxIdadeString.getText().toString();

                String sexo = "";

                if (radioButtonMasc.isChecked()) {
                    sexo = "Masculino";
                } else if (radioButtonFem.isChecked()) {
                    sexo = "Feminino";
                }

                if (boxEmail.equals("")) {
                    Toast.makeText(getApplicationContext(), " Preencha o campo 'Email' ", Toast.LENGTH_LONG).show();

                } else if (sexo.equals("")) {
                    Toast.makeText(getApplicationContext(), " Preencha o campo 'Sexo' ", Toast.LENGTH_LONG).show();

                } else if (boxIdadeString.equals("")) {
                    Toast.makeText(getApplicationContext(), " Preencha o campo 'Idade' ", Toast.LENGTH_LONG).show();

                } else if (boxNome.equals("")) {
                    Toast.makeText(getApplicationContext(), " Preencha o campo 'Nome' ", Toast.LENGTH_LONG).show();

                } else if (!boxSenha.equals(boxConfirmarSenha)) {
                    Toast.makeText(getApplicationContext(), " Senhas não são correspondentes ", Toast.LENGTH_LONG).show();

                } else if (boxSenha.equals(boxConfirmarSenha)) {
                    int boxIdade = Integer.parseInt(boxIdadeString);
                    dbHelper.cadastrarUsuarioBase(boxNome, boxEmail, boxSenha, boxIdade, sexo);
                    Toast.makeText(getApplicationContext(), " Cadastro realizado com sucesso ", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), MapaActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), " Erro ao completar o cadastro ", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ( resultCode == RESULT_OK) {
            Bitmap imagem = null;

            try {

                switch (requestCode) {
                    case 100:
                        imagem = (Bitmap) data.getExtras().get("data");
                        break;
                    case 200:
                        Uri localImagemSelecionada = data.getData();
                        imagem = MediaStore.Images.Media.getBitmap(getContentResolver(), localImagemSelecionada);
                        break;
                }

                if ( imagem != null){

                    circleImageViewUsuario.setImageBitmap(imagem);

                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}