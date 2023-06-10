package com.example.donachiquinhakitandav1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ClienteDB extends SQLiteOpenHelper {

    private static int DATABASE_VERSION = 1;
    private static String DATABASE_NAME = "clientes_db";
    private static String TABLE_NAME = "clientes";

    public static final String ID = "_id";
    public static final String NOME = "nome";
    public static final String NOMERESPONSAVEL = "nomeresponsavel";
    public static final String TELEFONE = "telefone";
    public static final String CREDITO = "CREDITO";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "( " +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOME + " TEXT NOT NULL, " +
            NOMERESPONSAVEL + " TEXT NOT NULL, " +
            TELEFONE + " TEXT NOT NULL," + CREDITO + " REAL NOT NULL" + ");";

    private SQLiteDatabase sqLiteDatabase;


    public ClienteDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void inserirCliente(Cliente cliente){
        ContentValues cv = new ContentValues();
        cv.put(ClienteDB.NOME, cliente.getNome());
        cv.put(ClienteDB.NOMERESPONSAVEL, cliente.getNomeResponsavel());
        cv.put(ClienteDB.TELEFONE, cliente.getTelefone());
        cv.put(ClienteDB.CREDITO, cliente.getCreditoInicial());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(ClienteDB.TABLE_NAME, null, cv);
    }

    public List<Cliente> buscarClientes(){
        String sql = "SELECT * FROM " + TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();

        List<Cliente> lista = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        if (cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String nome = cursor.getString(1);
                String nomeResponsavel = cursor.getString(1);
                String telefone = cursor.getString(2);
                double credito = Double.parseDouble(cursor.getString(3));

                lista.add(new Cliente(id, nome, nomeResponsavel, telefone, credito));
            }while (cursor.moveToNext());
        }

        cursor.close();
        return lista;
    }
}
