package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {


    public static final String TABELA_CLIENTE = "TABELA_CLIENTE";
    public static final String COLUNA_ID = "ID";
    public static final String COLUNA_NOME_CLIENTE = "NOME_CLIENTE";
    public static final String COLUNA_IDADE_CLIENTE = "IDADE_CLIENTE";
    public static final String COLUNA_CLIENTE_ATIVO = "CLIENTE_ATIVO";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "db.clientes", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String criarTabela = "CREATE TABLE " + TABELA_CLIENTE + " (" + COLUNA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUNA_NOME_CLIENTE + " TEXT, " + COLUNA_IDADE_CLIENTE + " INT, " + COLUNA_CLIENTE_ATIVO + " BOOL) ";
        db.execSQL(criarTabela);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean adicionarCliente(ClasseCliente classeCliente){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUNA_NOME_CLIENTE, classeCliente.getNome());
        cv.put(COLUNA_IDADE_CLIENTE, classeCliente.getIdade());
        cv.put(COLUNA_CLIENTE_ATIVO, classeCliente.isClienteAtivo());

        long insert = db.insert(TABELA_CLIENTE, null, cv);
        if(insert == -1){
            return false;
        } else {
            return true;
        }
    }

    public boolean atualizarCliente(String id, ClasseCliente classeCliente){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUNA_NOME_CLIENTE, classeCliente.getNome());
        cv.put(COLUNA_IDADE_CLIENTE, classeCliente.getIdade());
        cv.put(COLUNA_CLIENTE_ATIVO, classeCliente.isClienteAtivo());

        int count = db.update(TABELA_CLIENTE, cv, "ID = ?", new String[] {id} );

        if (count == 1) {
            return true;
        } else {
            return false;
        }

    }

    public boolean deletarCliente(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryDelete = "DELETE FROM " + TABELA_CLIENTE + " WHERE " + COLUNA_ID + " = " + id;

        Cursor cursor = db.rawQuery(queryDelete, null);

        if(cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }

    }


    public ArrayList<ClasseCliente> listaClientes(){

        ArrayList<ClasseCliente> listaRetorno = new ArrayList<>();

        String querySelect = "SELECT * FROM "+ TABELA_CLIENTE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor response = db.rawQuery(querySelect, null);

        if(response.moveToFirst()){
            do{
                int idCliente = response.getInt(0);
                String nomeCliente = response.getString(1);
                int idadeCliente = response.getInt(2);
                boolean clienteAtivo = response.getInt(3) == 1 ? true : false;

                ClasseCliente classeCliente = new ClasseCliente(idCliente, nomeCliente, idadeCliente, clienteAtivo);
                listaRetorno.add(classeCliente);

            } while(response.moveToNext());
        } else {

        }

        response.close();
        db.close();

        return listaRetorno;
    }

}
