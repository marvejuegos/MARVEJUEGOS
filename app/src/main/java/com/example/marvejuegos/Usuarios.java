package com.example.marvejuegos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Usuarios extends AppCompatActivity {
    Button btn_game1,btn_game2,btn_volver;
    TextView tv_username;
    ImageView iv_duckhunt,iv_pokemon;
    Bundle nombre_usuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);


        btn_game1=findViewById(R.id.btn_game1);
        btn_game2=findViewById(R.id.btn_game2);
        btn_volver=findViewById(R.id.btn_volver_usuario);
        iv_duckhunt=findViewById(R.id.iv_duckhunt);
        iv_pokemon=findViewById(R.id.iv_pokemon);
        tv_username=findViewById(R.id.tv_username);

        nombre_usuario=getIntent().getExtras();
        tv_username.setText(nombre_usuario.getString("nombre_usuario"));

    }

    public void cierra_sesion(View v){
        Intent intent_cerrar=new Intent(this,MainActivity.class);
        startActivity(intent_cerrar);
    }

    public void duck_hunt(View v){
        String username=tv_username.getText().toString();
        Intent intentduckhunt=new Intent(this, DuckHunt.class);
        intentduckhunt.putExtra("nombre_usuario",username);
        startActivity(intentduckhunt);
    }

    public void showdown(View v){
        Intent intent_showdown = new Intent(Intent.ACTION_VIEW, Uri.parse("https://pokemonshowdown.com/"));
        startActivity(intent_showdown);
    }
}
