package com.example.marvejuegos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Administrador extends AppCompatActivity {
    ListView lv_admin;
    ArrayList<String>userList=new ArrayList<>();
    ClaseBBDD connection;
    EditText ed_borrado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrador);

        connection=new ClaseBBDD(this,"users",null,1);
        lv_admin=findViewById(R.id.lv_usuarios);
        ed_borrado=findViewById(R.id.ed_borrado);

        consulta();
    }

    public void borrado(View v) {
        ClaseBBDD admin=new ClaseBBDD(this,"users",null,1);
        SQLiteDatabase borrado=admin.getReadableDatabase();
        String username=ed_borrado.getText().toString();
        if(!username.isEmpty()){
            borrado.delete("users","nombre_usuario='"+username+ "'",null);
            Toast.makeText(this, "Se ha borrado el usuario", Toast.LENGTH_SHORT).show();
            borrado.close();
        }else{
            Toast.makeText(this, "Introduce un username", Toast.LENGTH_SHORT).show();
        }

    }

    public void refresco(View v){
        Intent refrescar = new Intent(this, Administrador.class);
        startActivity(refrescar);
    }
    private void consulta() {
        ClaseBBDD admin=new ClaseBBDD(this,"users",null,1);
        SQLiteDatabase consulta=admin.getReadableDatabase();
        Cursor fila=consulta.rawQuery("SELECT email,nombre_usuario FROM users",null);
        if(fila.moveToFirst()){
            do{
                userList.add("Email: "+fila.getString(0)+ "     |       Username: "+ fila.getString(1));
            }while(fila.moveToNext());
        }
        consulta.close();
        ArrayAdapter<String> adaptador= new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,userList);
        lv_admin.setAdapter(adaptador);
    }


    public void cerrar_sesion(View v){
        Intent cerrar=new Intent(this,MainActivity.class);
        startActivity(cerrar);
    }


}
