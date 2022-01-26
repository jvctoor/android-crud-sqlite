package com.example.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

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
}
