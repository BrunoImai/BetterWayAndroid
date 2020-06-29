package com.example.betterwayfinal.Activity.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String NOME_DB = "BETTERWAY";
    public static String TABELA_CORDENADAS = "cordenadas";
    public static String TABELA_USUARIO = "USUARIO";

    public DBHelper(@Nullable Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABELA_CORDENADAS
                + " (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " cordenadaX DOUBLE NOT NULL," +
                " cordenadaY DOUBLE NOT NULL," +
                " cordenadaZ DOUBLE NOT NULL," +
                " tipoDeDesnivel VARCHAR(20) NOT NULL); ";
        try {
            db.execSQL( sql );
            Log.i("INFO DB", "Sucesso ao criar a tabela: " + TABELA_CORDENADAS);
        }catch (Exception e){
            Log.i("INFO DB", "Erro ao criar a tabela" + e.getMessage() );
        }

        sql = "CREATE TABLE IF NOT EXISTS " + TABELA_USUARIO +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome VARCHAR(200) NOT NULL, " +
                "email VARCHAR(200) NOT NULL," +
                "senha VARCHAR(200) NOT NULL," +
                "data_nasc DATETIME NOT NULL, " +
                "sexo CHAR(1) NOT NULL, " +
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
        String sql = "DROP TABLE IF EXISTS " + TABELA_CORDENADAS + " ;" ;

        try {
            db.execSQL( sql );
            onCreate(db);
            Log.i("INFO DB", "Sucesso ao atualizar App" );
        }catch (Exception e){
            Log.i("INFO DB", "Erro ao atualizar App" + e.getMessage() );
        }
    }
}
