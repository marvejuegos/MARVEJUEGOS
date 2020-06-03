package com.example.marvejuegos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.graphics.Point;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    TextView tvCounterDucks,tvTimer,tvNick;
    ImageView ivDuck;
    int counter=0;
    int anchoPantalla;
    int altoPantalla;
    Random aleatorio;
    boolean gameOver=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initViewComponents();
        eventos();
        initPantalla();
        initCuentaAtras();
        moveDucks();

    }
    private void initCuentaAtras() {
        new CountDownTimer(31000, 1000) {

            public void onTick(long millisUntilFinished) {
                long segundosRestantes=millisUntilFinished/1000;
                tvTimer.setText(segundosRestantes+"s");
            }

            public void onFinish() {
                tvTimer.setText("0s");
                gameOver=true;
                mostrarDialogoGameOver();
            }
        }.start();
    }
    private void mostrarDialogoGameOver() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Has cazado "+counter+" patos").setTitle("Game over");
        builder.setPositiveButton("Reiniciar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                counter=0;
                tvCounterDucks.setText("0");
                gameOver=false;
                initCuentaAtras();
                moveDucks();
            }
        });
        builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.dismiss();
                finish();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void initPantalla() {
        Display display =getWindowManager().getDefaultDisplay();
        Point size =new Point();
        display.getSize(size);
        anchoPantalla=size.x;
        altoPantalla=size.y;

        aleatorio=new Random();
    }

    private void initViewComponents() {
        tvCounterDucks=findViewById(R.id.textViewCounter);
        tvTimer=findViewById(R.id.textViewTimer);
        tvNick=findViewById(R.id.textViewNick);
        ivDuck=findViewById(R.id.imageViewDuck);


        Bundle extras=getIntent().getExtras();
        String nick=extras.getString("nick");
        tvNick.setText(nick);
    }

    private void eventos() {
        ivDuck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                tvCounterDucks.setText(String.valueOf(counter));
                ivDuck.setEnabled(false);
                ivDuck.setImageResource(R.drawable.duck_clicked);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ivDuck.setImageResource(R.drawable.duck);
                        moveDucks();
                    }
                }, 500);
            }
        });
    }

    private void moveDucks() {
        int minimo=0;

        int maxX=anchoPantalla - ivDuck.getWidth();
        int maxY=altoPantalla - ivDuck.getHeight();

        int randomX=aleatorio.nextInt(maxX-minimo+1);
        int randomY=aleatorio.nextInt(maxY-minimo+1);

        ivDuck.setX(randomX);
        ivDuck.setY(randomY);
        ivDuck.setEnabled(true);
    }

}
