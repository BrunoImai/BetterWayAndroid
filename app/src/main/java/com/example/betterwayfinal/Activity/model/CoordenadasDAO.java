package com.example.betterwayfinal.Activity.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.betterwayfinal.Activity.Helper.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class CoordenadasDAO implements ICordenadasDAO{

    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public CoordenadasDAO(Context context) {
        DBHelper db = new DBHelper( context );
        escreve = db.getWritableDatabase();
        le = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Recycler recycler) {

        ContentValues cv = new ContentValues();
        cv.put("tipoDeDesnivel", recycler.gettipoDeDesnivel());
        cv.put("cordenadaX", recycler.getCordX());
        cv.put("cordenadaY", recycler.getCordY());
        cv.put("cordenadaZ", recycler.getCordZ());

        try {
            escreve.insert(DBHelper.TABELA_CORDENADAS, null, cv );
            Log.i("INFO", "Tarefa salva com sucesso!");
        }catch (Exception e){
            Log.e("INFO", "Erro ao salvar tarefa " + e.getMessage() );
            return false;
        }

        return true;
    }

    @Override
    public boolean atualizar(Recycler recycler) {
        return false;
    }

    @Override
    public boolean deletar(Recycler recycler) {
        return false;
    }

    @Override
    public List<Recycler> listar() {

        List<Recycler> dados = new ArrayList<>();

        String sql = "SELECT * FROM " + DBHelper.TABELA_CORDENADAS;
        
        Cursor c = le.rawQuery(sql, null);

        while (c.moveToNext()){
            Recycler recycler = new Recycler();

            long id = c.getLong( c.getColumnIndex("id"));
            String CordX = c.getString( c.getColumnIndex("cordenadaX"));
            String CordY = c.getString( c.getColumnIndex("cordenadaY"));
            String CordZ = c.getString( c.getColumnIndex("cordenadaZ"));
            String tipoDeDesnivel = c.getString( c.getColumnIndex("tipoDeDesnivel"));

            recycler.setId( id );
            recycler.setCordX(CordX);
            recycler.setCordY(CordY);
            recycler.setCordZ(CordZ);
            recycler.settipoDeDesnivel(tipoDeDesnivel);

            dados.add( recycler );
        }
        c.close();

        return dados;
    }
}
