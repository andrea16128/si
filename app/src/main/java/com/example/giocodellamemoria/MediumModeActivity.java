package com.example.giocodellamemoria;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

public class MediumModeActivity extends AppCompatActivity {
    private ImageView noyz,erCicoria,fibra,madman,kidYugi,lazza;
    private ArrayList<Integer> ids;
    private Vector<ImageView> carte;
    private int cont = 0;
    private Actions actions;
    private int firstClick, secondClick;
    private int positionFirstClick = -1,positionSecondClick = -1;
    private int tentativi = 6;
    private FrameLayout transparentLayout;
    private boolean trovato = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium_mode);
        noyz = new ImageView(this);
        erCicoria = new ImageView(this);
        fibra = new ImageView(this);
        madman = new ImageView(this);
        kidYugi = new ImageView(this);
        lazza = new ImageView(this);

        noyz.setImageResource(R.drawable.noyz);
        erCicoria.setImageResource(R.drawable.er_cicoria);
        fibra.setImageResource(R.drawable.fibra);
        madman.setImageResource(R.drawable.madman);
        kidYugi.setImageResource(R.drawable.kid_yugi);
        lazza.setImageResource(R.drawable.lazza);

        transparentLayout = findViewById(R.id.transparentLayout);

        ids = new ArrayList<>();
        for(int i = 2131231035; i<=2131231046; i++){
            ids.add(i);
        }

        Collections.shuffle(ids);
        actions = new Actions(ids);

        carte = new Vector<>();
        carte.add(findViewById(R.id.m_carta1));
        carte.add(findViewById(R.id.m_carta2));
        carte.add(findViewById(R.id.m_carta3));
        carte.add(findViewById(R.id.m_carta4));
        carte.add(findViewById(R.id.m_carta5));
        carte.add(findViewById(R.id.m_carta6));
        carte.add(findViewById(R.id.m_carta7));
        carte.add(findViewById(R.id.m_carta8));
        carte.add(findViewById(R.id.m_carta9));
        carte.add(findViewById(R.id.m_carta10));
        carte.add(findViewById(R.id.m_carta11));
        carte.add(findViewById(R.id.m_carta12));

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
                imageView.setImageResource(R.drawable.noyz);

            } else if (id == ids.get(2) || id == ids.get(3)) {
                ImageView imageView = findViewById(id);
                imageView.setImageResource(R.drawable.er_cicoria);

            } else if (id == ids.get(4) || id == ids.get(5)) {
                ImageView imageView = findViewById(id);
                imageView.setImageResource(R.drawable.fibra);

            } else if (id == ids.get(6) || id == ids.get(7)) {
                ImageView imageView = findViewById(id);
                imageView.setImageResource(R.drawable.madman);

            } else if (id == ids.get(8) || id == ids.get(9)) {
                ImageView imageView = findViewById(id);
                imageView.setImageResource(R.drawable.kid_yugi);

            } else if (id == ids.get(10) || id == ids.get(11)) {
                ImageView imageView = findViewById(id);
                imageView.setImageResource(R.drawable.lazza);
            }
            //

        }
        if(firstClick != 0 && secondClick != 0){
            if(ids.contains(firstClick) && ids.contains(secondClick)) {
                positionFirstClick = actions.positionAtClick(firstClick);
                positionSecondClick = actions.positionAtClick(secondClick);

                for(int i = 0; i<12; i++) {
                    if(positionFirstClick == i && positionSecondClick == (i+1) || positionFirstClick == (i+1) && positionSecondClick == i){
                        trovato = true;
                    }
                }
                if (trovato) {
                    ids.set(ids.indexOf(firstClick),0);
                    ids.set(ids.indexOf(secondClick),0);
                    this.resetAll(cont, firstClick, secondClick);
                    trovato = false;
                }

                else {

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
                                    resetAll(cont, firstClick, secondClick);;
                                    tentativi--;
                                    TextView textView = findViewById(R.id.txtv_tentativi);
                                    textView.setText(String.valueOf(tentativi));
                                    transparentLayout.setVisibility(View.GONE);
                                    //fare il robo in caso di perdita
                                    if (tentativi == 0) {
                                        Intent loseActivity = new Intent(MediumModeActivity.this, LoseActivity.class);
                                        startActivity(loseActivity);
                                        Actions.setId_activity(2);
                                        finish();
                                    }
                                }
                            }, 1000); // Ritardo di 2000 millisecondi (2 secondi)
                        }
                    });
                }

            }else{
                    this.resetAll(cont, firstClick, secondClick);
            }
        }
        //in caso di vittoria
        if(actions.vittoria(ids)){
            Intent winActivity = new Intent(MediumModeActivity.this, WinActivity.class);
            startActivity(winActivity);
            Actions.setId_activity(2);
            finish();
        }
    }
        private void resetAll(int cont, int firstClick, int secondClick){
            this.cont = 0;
            this.firstClick = 0;
            this.secondClick = 0;
        }
}
