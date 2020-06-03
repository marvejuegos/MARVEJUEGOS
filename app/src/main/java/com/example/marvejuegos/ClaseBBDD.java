package com.example.marvejuegos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ClaseBBDD extends SQLiteOpenHelper {

    private static final String TABLA_USUARIOS = "users";

    // nombres de las columnas de la tabla
    private static final String COLUMNA_EMAIL = "email";
    private static final String COLUMNA_NOMBRE = "nombre_usuario";
    private static final String COLUMNA_PASSWORD = "contrasena";

    public ClaseBBDD(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // consulta de creacion de la tabla
        db.execSQL("CREATE TABLE " + TABLA_USUARIOS + "("
                + COLUMNA_EMAIL + " varchar(40) PRIMARY KEY ," + COLUMNA_NOMBRE + " varchar(15),"
                + COLUMNA_PASSWORD + " varchar(20)" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
