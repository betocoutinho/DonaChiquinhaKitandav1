package com.example.donachiquinhakitandav1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDB extends SQLiteOpenHelper {

    private static int DATABASE_VERSION = 1;
    private static String DATABASE_NAME = "produtos_db";
    private static String TABLE_NAME = "produtos";

    public static final String ID = "_id";
    public static final String NOMEPRODUTO = "nomeproduto";
    public static final String PRECO = "PRECO";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "( " +
            ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOMEPRODUTO + " TEXT NOT NULL, "
            + PRECO + " REAL NOT NULL" + ");";

    private SQLiteDatabase sqLiteDatabase;

    public ProdutoDB(@Nullable Context context) {
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

    public void inserirProduto(Produto produto){
        ContentValues cv = new ContentValues();
        cv.put(ProdutoDB.NOMEPRODUTO, produto.getNomeProduto());
        cv.put(ProdutoDB.PRECO, produto.getPreco());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(ProdutoDB.TABLE_NAME, null, cv);
    }

    public List<Produto> buscarProdutos(){
        String sql = "SELECT * FROM " + TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();

        List<Produto> lista = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);

        if (cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));
                String nomeProduto = cursor.getString(1);
                double preco = Double.parseDouble(cursor.getString(2));

                lista.add(new Produto(id, nomeProduto, preco));
            }while (cursor.moveToNext());
        }

        cursor.close();
        return lista;
    }
}
