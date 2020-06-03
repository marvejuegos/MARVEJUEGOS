package com.example.marvejuegos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DuckHunt extends AppCompatActivity {
    Button btnStart,btnVuelve;
    TextView tv_username;
    EditText edNick;
    String nick;
    Bundle nombre_usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duck_hunt);

        edNick=findViewById(R.id.editTextNick);
        btnStart=findViewById(R.id.buttonStart);
        btnVuelve=findViewById(R.id.btn_back);
        tv_username=findViewById(R.id.tv_nombre);

        nombre_usuario=getIntent().getExtras();
        tv_username.setText(nombre_usuario.getString("nombre_usuario"));


    }

    public void botonStart (View view){
        nick=edNick.getText().toString();
        if(nick.isEmpty()){
            edNick.setError("El nombre es obligatorio");
        }else if(nick.length()<3){
            edNick.setError("El nombre debe tener al menos 3 caracteres");
        }else{
            Intent intent=new Intent(this,GameActivity.class);
            intent.putExtra("nick",nick);
            startActivity(intent);
        }
    }
    public void volver(View v){
        String username=tv_username.getText().toString();
        Intent intent_vuelta=new Intent(this,Usuarios.class);
        intent_vuelta.putExtra("nombre_usuario",username);
        startActivity(intent_vuelta);
    }
}
