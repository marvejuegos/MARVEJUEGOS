package com.example.marvejuegos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {
    EditText et_name,et_email,et_password,et_repasword;
    Button btn_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        et_name=findViewById(R.id.et_name);
        et_email=findViewById(R.id.et_email_login);
        et_password=findViewById(R.id.et_password_login);
        et_repasword=findViewById(R.id.et_repassword);
        btn_register=findViewById(R.id.btn_register);
    }

    public void login(View v){
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);

    }
    public void insertar(View view){
        ClaseBBDD admin=new ClaseBBDD(this,"users",null,1);
        SQLiteDatabase Database=admin.getWritableDatabase();
        String email=et_email.getText().toString();
        String nombre=et_name.getText().toString();
        String contrasena=et_password.getText().toString();
        String recontrasena=et_repasword.getText().toString();
        if(email.length()<=0){
            Toast.makeText(this, "Introduce un email", Toast.LENGTH_SHORT).show();
        }else if(nombre.length()<=0){
            Toast.makeText(this, "Introduce un nombre de usuario", Toast.LENGTH_SHORT).show();
        }else if(contrasena.length()<=0){
            Toast.makeText(this, "Introduce una contraseña", Toast.LENGTH_SHORT).show();
        }else if(recontrasena.length()<=0){
            Toast.makeText(this, "Reintroduce la contraseña", Toast.LENGTH_SHORT).show();
        }else{
            if (contrasena.equals(recontrasena)==true){
                ContentValues contenido=new ContentValues();
                contenido.put("email",email);
                contenido.put("nombre_usuario",nombre);
                contenido.put("contrasena",contrasena);

                Database.insert("users",null,contenido);
                Database.close();
                Intent intent_username=new Intent(this,Usuarios.class);
                intent_username.putExtra("nombre_usuario", nombre);
                startActivity(intent_username);

            }else{
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
