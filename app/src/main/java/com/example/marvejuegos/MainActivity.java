package com.example.marvejuegos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActivityChooserView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText et_email_login,et_password_login;
    Button btn_login;
    String[]datosAdmin={"romarvedam@gmail.com","marvejuegos","admin"};
    String[]datosIniciales={"alvarobf98@gmail.com","alvaropls","1234"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_email_login=findViewById(R.id.et_email_login);
        et_password_login=findViewById(R.id.et_password_login);
        btn_login=findViewById(R.id.btn_login);

        inicial(datosAdmin);
        inicial(datosIniciales);
    }

    public void iniSesion(View v){
        Intent inicio;
        ClaseBBDD admin=new ClaseBBDD(this,"users",null,1);
        SQLiteDatabase Database=admin.getWritableDatabase();
        String email=et_email_login.getText().toString();
        String contrasena=et_password_login.getText().toString();
        Cursor fila = Database.rawQuery("SELECT contrasena,nombre_usuario FROM users WHERE email = '" + email + "'", null);
        String nombreUsuario="";
        String contraUsuario="";
        if(fila.moveToFirst()) {
            contraUsuario = fila.getString(0);
            nombreUsuario = fila.getString(1);
        }
        if(email.equals("romarvedam@gmail.com") && contrasena.equals(contraUsuario)){
            inicio = new Intent(this, Administrador.class);
            startActivity(inicio);
        }
        if(!email.equals("romarvedam@gmail.com") && !email.equals("") && contrasena.equals(contraUsuario)) {
            inicio = new Intent(this, Usuarios.class);
            inicio.putExtra("nombre_usuario", nombreUsuario);//pasarlo cada vez que se pida
            startActivity(inicio);
        }
    }

    public void registro(View v){
        Intent intent_registro=new Intent(this,RegistroActivity.class);
        startActivity(intent_registro);

    }
    public void inicial(String[]datos){
        ClaseBBDD admin=new ClaseBBDD(this,"users",null,1);
        SQLiteDatabase Database=admin.getWritableDatabase();
        ContentValues contenido=new ContentValues();
        contenido.put("email",datos[0]);
        contenido.put("nombre_usuario",datos[1]);
        contenido.put("contrasena",datos[2]);

        Database.insert("users",null,contenido);
        Database.close();


    }

}
