package com.example.betterwayfinal.Activity.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static int VERSION = 4;
    public static String NOME_DB = "BETTERWAY";
    public static String TABELA_COORDENADAS = "COORDENADAS";
    public static String TABELA_USUARIO = "USUARIO";

    public DBHelper(@Nullable Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA_COORDENADAS
                + " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " coordenadaX DOUBLE NOT NULL," +
                " coordenadaY DOUBLE NOT NULL," +
                " coordenadaZ DOUBLE NOT NULL," +
                " latitude DOUBLE NOT NULL," +
                " longitude DOUBLE NOT NULL); ";
        try {
            db.execSQL( sql );
            Log.i("INFO DB", "Sucesso ao criar a tabela: " + TABELA_COORDENADAS);
        }catch (Exception e){
            Log.i("INFO DB", "Erro ao criar a tabela" + e.getMessage() );
        }

        sql = "CREATE TABLE IF NOT EXISTS " + TABELA_USUARIO +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome VARCHAR(200) NOT NULL, " +
                "email VARCHAR(200) NOT NULL," +
                "senha VARCHAR(200) NOT NULL," +
                "idade INT NOT NULL, " +
                "sexo VARCHAR(20) NOT NULL, " +
                "num_celular INT (15), " +
                "endereco VARCHAR(200), " +
                "bairro VARCHAR(90), " +
                "estado VARCHAR (40));";

        try {
            db.execSQL( sql );
            Log.i("INFO DB", "Sucesso ao criar a tabela: " + TABELA_USUARIO);
        }catch (Exception e){
            Log.i("INFO DB", "Erro ao criar a tabela" + e.getMessage() );
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABELA_COORDENADAS + " ;" ;

        try {
            db.execSQL( sql );
            onCreate(db);
            Log.i("INFO DB", "Sucesso ao atualizar App" );
        }catch (Exception e){
            Log.i("INFO DB", "Erro ao atualizar App" + e.getMessage() );
        }

        sql = "DROP TABLE IF EXISTS " + TABELA_USUARIO + " ;" ;

        try {
            db.execSQL( sql );
            onCreate(db);
            Log.i("INFO DB", "Sucesso ao atualizar App" );
        }catch (Exception e){
            Log.i("INFO DB", "Erro ao atualizar App" + e.getMessage() );
        }
    }

    public Long cadastrarUsuarioBase(String nome, String email, String senha, int idade, String sexo){
        SQLiteDatabase db = getWritableDatabase();

            ContentValues cv = new ContentValues();

            cv.put("nome", nome);
            cv.put("email", email);
            cv.put("senha", senha);
            cv.put("idade", idade);
            cv.put("sexo", sexo);

            return db.insert(TABELA_USUARIO, null, cv);

    }

    public boolean validarUsuario(String email, String senha){
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABELA_USUARIO + " WHERE email = ? AND senha = ?", new String[]{email, senha});

        return cursor.getCount() > 0;

    }

    public Long cadastrarCoordenadas(double coordenadaX, double coordenadaY, double coordenadaZ, double latitude, double longitude){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("coordenadaX", coordenadaX);
        cv.put("coordenadaY", coordenadaY);
        cv.put("coordenadaZ", coordenadaZ);
        cv.put("latitude", latitude);
        cv.put("longitude", longitude);

        return db.insert(TABELA_COORDENADAS, null, cv);

    }
}
