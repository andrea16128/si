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

public class HardModeActivity extends AppCompatActivity {
    private ImageView abella,adriana,brandi,lana,malkova,mia,nicole,rae,riley,sasha;
    private ArrayList<Integer> ids;
    private int cont = 0;
    private Actions actions;
    private int firstClick, secondClick;
    private int positionFirstClick = -1,positionSecondClick = -1;
    private int tentativi = 15;
    private FrameLayout transparentLayout;
    private boolean trovato = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard_mode);
        abella = new ImageView(this);
        adriana = new ImageView(this);
        brandi = new ImageView(this);
        lana = new ImageView(this);
        malkova = new ImageView(this);
        mia = new ImageView(this);
        nicole = new ImageView(this);
        rae = new ImageView(this);
        riley = new ImageView(this);
        sasha = new ImageView(this);

        abella.setImageResource(R.drawable.abella);
        adriana.setImageResource(R.drawable.adriana);
        brandi.setImageResource(R.drawable.brandi);
        lana.setImageResource(R.drawable.lana);
        malkova.setImageResource(R.drawable.malkova);
        mia.setImageResource(R.drawable.mia);
        nicole.setImageResource(R.drawable.nicole);
        rae.setImageResource(R.drawable.rae);
        riley.setImageResource(R.drawable.riley);
        sasha.setImageResource(R.drawable.sasha);

        transparentLayout = findViewById(R.id.transparentLayout);

        ids = new ArrayList<>();
        for(int i = 2131230976; i<=2131230995; i++){
            ids.add(i);
        }

        Collections.shuffle(ids);
        actions = new Actions(ids);

    }

    public void ock_carta(View view) {
        int id = view.getId();
        cont ++;
        System.out.println(id);

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
                imageView.setImageResource(R.drawable.abella);

            } else if (id == ids.get(2) || id == ids.get(3)) {
                ImageView imageView = findViewById(id);
                imageView.setImageResource(R.drawable.adriana);

            } else if (id == ids.get(4) || id == ids.get(5)) {
                ImageView imageView = findViewById(id);
                imageView.setImageResource(R.drawable.brandi);

            } else if (id == ids.get(6) || id == ids.get(7)) {
                ImageView imageView = findViewById(id);
                imageView.setImageResource(R.drawable.lana);

            } else if (id == ids.get(8) || id == ids.get(9)) {
                ImageView imageView = findViewById(id);
                imageView.setImageResource(R.drawable.malkova);

            } else if (id == ids.get(10) || id == ids.get(11)) {
                ImageView imageView = findViewById(id);
                imageView.setImageResource(R.drawable.mia);

            }else if (id == ids.get(12) || id == ids.get(13)) {
                ImageView imageView = findViewById(id);
                imageView.setImageResource(R.drawable.nicole);

            }else if (id == ids.get(14) || id == ids.get(15)) {
                ImageView imageView = findViewById(id);
                imageView.setImageResource(R.drawable.rae);

            }else if (id == ids.get(16) || id == ids.get(17)) {
                ImageView imageView = findViewById(id);
                imageView.setImageResource(R.drawable.riley);

            }else if (id == ids.get(18) || id == ids.get(19)) {
                ImageView imageView = findViewById(id);
                imageView.setImageResource(R.drawable.sasha);

            }
            //

        }
        if(firstClick != 0 && secondClick != 0){
            if(ids.contains(firstClick) && ids.contains(secondClick)) {

                //match
                positionFirstClick = actions.positionAtClick(firstClick);
                positionSecondClick = actions.positionAtClick(secondClick);

                for(int i = 0; i<20; i++) {
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

                //no match
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
                                    resetAll(cont, firstClick, secondClick);
                                    tentativi--;
                                    TextView textView = findViewById(R.id.txtv_tentativi);
                                    textView.setText(String.valueOf(tentativi));
                                    transparentLayout.setVisibility(View.GONE);
                                    //fare il robo in caso di perdita
                                    if (tentativi == 0) {
                                        Intent loseActivity = new Intent(HardModeActivity.this, LoseActivity.class);
                                        startActivity(loseActivity);
                                        Actions.setId_activity(3);
                                        finish();
                                    }
                                }
                            }, 1000);
                        }
                    });


                }
            }else {
                this.resetAll(cont, firstClick, secondClick);
            }
        }
        //in caso di vittoria
        if(actions.vittoria(ids)){
            Intent winActivity = new Intent(HardModeActivity.this, WinActivity.class);
            startActivity(winActivity);
            Actions.setId_activity(3);
            finish();
        }
    }
    private void resetAll(int cont, int firstClick, int secondClick){
        this.cont = 0;
        this.firstClick = 0;
        this.secondClick = 0;
    }
}