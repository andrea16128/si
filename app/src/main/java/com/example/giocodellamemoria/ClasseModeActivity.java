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

public class ClasseModeActivity extends AppCompatActivity {
    private ImageView mezza,serghe,wen,mere,tony,cuco,vieri,megna,loris,duli,fasullo,ricatti,wu,abou,fantoz,togne,miottz,steven;
    private ArrayList<Integer> ids;
    private int cont = 0;
    private Actions actions;
    private int firstClick, secondClick;
    private int positionFirstClick = -1,positionSecondClick = -1;
    private int tentativi = 25;
    private FrameLayout transparentLayout;
    private boolean trovato = false;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classe_mode);
        mezza = new ImageView(this);
        serghe = new ImageView(this);
        wen = new ImageView(this);
        mere = new ImageView(this);
        tony = new ImageView(this);
        cuco = new ImageView(this);
        vieri = new ImageView(this);
        megna = new ImageView(this);
        loris = new ImageView(this);
        duli = new ImageView(this);
        fasullo = new ImageView(this);
        ricatti = new ImageView(this);
        wu = new ImageView(this);
        abou = new ImageView(this);
        fantoz = new ImageView(this);
        togne = new ImageView(this);
        miottz = new ImageView(this);
        steven = new ImageView(this);

        mezza.setImageResource(R.drawable.mezza);
        serghe.setImageResource(R.drawable.serghe);
        wen.setImageResource(R.drawable.wen);
        mere.setImageResource(R.drawable.mere);
        tony.setImageResource(R.drawable.tony);
        cuco.setImageResource(R.drawable.cuco);
        vieri.setImageResource(R.drawable.vieri);
        megna.setImageResource(R.drawable.megna);
        loris.setImageResource(R.drawable.loris);
        duli.setImageResource(R.drawable.duli);
        fasullo.setImageResource(R.drawable.fasullo);
        ricatti.setImageResource(R.drawable.ricatti);
        wu.setImageResource(R.drawable.wu);
        abou.setImageResource(R.drawable.abou);
        fantoz.setImageResource(R.drawable.fantoz);
        togne.setImageResource(R.drawable.togne);
        miottz.setImageResource(R.drawable.miottz);
        steven.setImageResource(R.drawable.steven);

        transparentLayout = findViewById(R.id.transparentLayout);

        ids = new ArrayList<>();
        for(int i = 2131230825; i<=2131230860; i++){
            ids.add(i);
        }

        Collections.shuffle(ids);
        actions = new Actions(ids);

    }
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
                imageView.setImageResource(R.drawable.mezza);

            } else if (id == ids.get(2) || id == ids.get(3)) {
                ImageView imageView = findViewById(id);
                imageView.setImageResource(R.drawable.serghe);

            } else if (id == ids.get(4) || id == ids.get(5)) {
                ImageView imageView = findViewById(id);
                imageView.setImageResource(R.drawable.wen);

            } else if (id == ids.get(6) || id == ids.get(7)) {
                ImageView imageView = findViewById(id);
                imageView.setImageResource(R.drawable.mere);

            } else if (id == ids.get(8) || id == ids.get(9)) {
                ImageView imageView = findViewById(id);
                imageView.setImageResource(R.drawable.tony);

            } else if (id == ids.get(10) || id == ids.get(11)) {
                ImageView imageView = findViewById(id);
                imageView.setImageResource(R.drawable.cuco);

            }else if (id == ids.get(12) || id == ids.get(13)) {
                ImageView imageView = findViewById(id);
                imageView.setImageResource(R.drawable.vieri);

            }else if (id == ids.get(14) || id == ids.get(15)) {
                ImageView imageView = findViewById(id);
                imageView.setImageResource(R.drawable.megna);

            }else if (id == ids.get(16) || id == ids.get(17)) {
                ImageView imageView = findViewById(id);
                imageView.setImageResource(R.drawable.loris);

            }else if (id == ids.get(18) || id == ids.get(19)) {
                ImageView imageView = findViewById(id);
                imageView.setImageResource(R.drawable.duli);

            }else if (id == ids.get(20) || id == ids.get(21)) {
                ImageView imageView = findViewById(id);
                imageView.setImageResource(R.drawable.fasullo);

            }else if (id == ids.get(22) || id == ids.get(23)) {
                ImageView imageView = findViewById(id);
                imageView.setImageResource(R.drawable.ricatti);

            }else if (id == ids.get(24) || id == ids.get(25)) {
                ImageView imageView = findViewById(id);
                imageView.setImageResource(R.drawable.wu);

            }else if (id == ids.get(26) || id == ids.get(27)) {
                ImageView imageView = findViewById(id);
                imageView.setImageResource(R.drawable.abou);

            }else if (id == ids.get(28) || id == ids.get(29)) {
                ImageView imageView = findViewById(id);
                imageView.setImageResource(R.drawable.fantoz);

            }else if (id == ids.get(30) || id == ids.get(31)) {
                ImageView imageView = findViewById(id);
                imageView.setImageResource(R.drawable.togne);

            }else if (id == ids.get(32) || id == ids.get(33)) {
                ImageView imageView = findViewById(id);
                imageView.setImageResource(R.drawable.miottz);

            }else if (id == ids.get(34) || id == ids.get(35)) {
                ImageView imageView = findViewById(id);
                imageView.setImageResource(R.drawable.steven);

            }
            //

        }
        if(firstClick != 0 && secondClick != 0){
            if(ids.contains(firstClick) && ids.contains(secondClick)) {

                //match
                positionFirstClick = actions.positionAtClick(firstClick);
                positionSecondClick = actions.positionAtClick(secondClick);

                for(int i = 0; i<36; i++) {
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
                                        Intent loseActivity = new Intent(ClasseModeActivity.this, LoseActivity.class);
                                        startActivity(loseActivity);
                                        Actions.setId_activity(4);
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
            Intent winActivity = new Intent(ClasseModeActivity.this, WinActivity.class);
            startActivity(winActivity);
            Actions.setId_activity(4);
            finish();
        }

    }
    private void resetAll(int cont, int firstClick, int secondClick){
        this.cont = 0;
        this.firstClick = 0;
        this.secondClick = 0;
    }
}