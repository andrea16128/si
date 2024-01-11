package com.example.giocodellamemoria;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.Vector;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class EasyModeActivity extends AppCompatActivity {
    private ImageView square,triangle,circle;
    private ArrayList<Integer> ids, idsAfterClick;
    private int cont = 0;
    private Actions actions;
    private int firstClick = 0, secondClick = 0;
    private int positionFirstClick = -1,positionSecondClick = -1;
    private int tentativi = 3;
    private FrameLayout transparentLayout;
    private boolean trovato = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_mode);

        square = new ImageView(this);
        triangle = new ImageView(this);
        circle = new ImageView(this);

        square.setImageResource(R.drawable.quadrato);
        triangle.setImageResource(R.drawable.triangolo);
        circle.setImageResource(R.drawable.cerchio);

        transparentLayout = findViewById(R.id.transparentLayout);

        ids = new ArrayList<>();
        for(int i = 2131230865; i<=2131230870; i++){
            ids.add(i);
        }
        idsAfterClick = new ArrayList<>();

        Collections.shuffle(ids);
        actions = new Actions(ids);



    }

    @SuppressLint("ResourceType")
    public void ock_carta(View view) {

       int id = view.getId();
       System.out.println(id);

       cont ++;

       if(cont == 1){
           firstClick = id;
       }
       if(cont == 2){
           secondClick = id;
       }
       if(secondClick == firstClick){
           secondClick = 0;
           cont--;
       }

       //il contatore deve essere minore di 2 perchè posso girare solo 2 immagini alla volta
       if(cont <= 2) {

           //prendo l'id dell'imageviews cliccata dall'utente
           //lo confronto con quello che ho salvato nell'arraylista che poi ho mischiato
           //assegno all'id della imageviews coperta la figura corrispettiva (quadrato, triangolo, cerchio)
           if (id == ids.get(0) || id == ids.get(1)) {
               ImageView imageView = findViewById(id);
               imageView.setImageResource(R.drawable.quadrato);

           } else if (id == ids.get(2) || id == ids.get(3)) {
               ImageView imageView = findViewById(id);
               imageView.setImageResource(R.drawable.triangolo);

           } else if (id == ids.get(4) || id == ids.get(5)) {
               ImageView imageView = findViewById(id);
               imageView.setImageResource(R.drawable.cerchio);
           }
           //

       }

       if(firstClick != 0 && secondClick != 0){
           if(ids.contains(firstClick) && ids.contains(secondClick)) {
               //match
               positionFirstClick = actions.positionAtClick(firstClick);
               positionSecondClick = actions.positionAtClick(secondClick);

               for(int i = 0; i<6; i++) {
                    if(positionFirstClick == i && positionSecondClick == (i+1) || positionFirstClick == (i+1) && positionSecondClick == i){
                        trovato = true;
                    }
               }
               if (trovato) {
                   ids.set(ids.indexOf(firstClick),0);
                   ids.set(ids.indexOf(secondClick),0);
                   idsAfterClick.add(firstClick);
                   idsAfterClick.add(secondClick);
                   this.resetAll(cont, firstClick, secondClick);
                   trovato = false;
               }

               //no match
               else{

                   //con runOnUiThread posso isolare il thread che gira le carte dal thread principale cosi posso aspettare 1 secondo prima che le carte si girino
                   // di nuovo
                   runOnUiThread(new Runnable() {
                       @Override
                       public void run() {
                           //tolgo la possibilita all'utente di cliccare altri tasti cosi da evitare il crash dell'applicazione
                           transparentLayout.setVisibility(View.VISIBLE);
                           // con android.os.Handler().postDelayed attendo che passi 1 secondo prima di eseguire il codice dentro il run
                           new android.os.Handler().postDelayed(new Runnable() {
                               @Override
                               public void run() {
                                   ImageView imageView1 = findViewById(firstClick);
                                   imageView1.setImageResource(R.drawable.carta_coperta);
                                   ImageView imageView2 = findViewById(secondClick);
                                   imageView2.setImageResource(R.drawable.carta_coperta);
                                   resetAll(cont, firstClick,secondClick);
                                   tentativi--;
                                   TextView textView = findViewById(R.id.txtv_tentativi);
                                   textView.setText(String.valueOf(tentativi));
                                   transparentLayout.setVisibility(View.GONE);
                                   //fare il robo in caso di perdita
                                   if(tentativi == 0){
                                       Intent loseActivity = new Intent(EasyModeActivity.this, LoseActivity.class);
                                       startActivity(loseActivity);
                                       Actions.setId_activity(1);
                                       finish();
                                   }
                               }
                           }, 1000);
                       }
                   });
               }
           }else{
               this.resetAll(cont, firstClick, secondClick);
           }
       }
       //in caso di vittoria
       if(actions.vittoria(ids)){
           Intent winActivity = new Intent(EasyModeActivity.this, WinActivity.class);
           startActivity(winActivity);
           Actions.setId_activity(1);
           finish();
       }
    }

    private void resetAll(int cont, int firstClick, int secondClick){
        this.cont = 0;
        this.firstClick = 0;
        this.secondClick = 0;
    }
}