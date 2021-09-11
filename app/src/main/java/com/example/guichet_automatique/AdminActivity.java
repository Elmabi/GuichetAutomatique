package com.example.guichet_automatique;

/*
* Cet activité affiche la fenêtre destinée à l'administrateur du guichet quand il se connecte.
*
 */
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
    }

    public void onClickEpargne(View view) {
        //Creation de l'objet Intent pour appeler le Epargne Activity
        Intent intent = new Intent(this, EpargneActivity.class);
        startActivity(intent);
    }

    public void onClickClient(View view) {
        //Creation de l'objet Intent pour appeler le Client Activity
        Intent intent = new Intent(this, ClientsActivity.class);
        startActivity(intent);
    }

    public void onClickCheque(View view) {
        //Creation de l'objet Intent pour appeler le Cheque Activity
        Intent intent = new Intent(this, ChequeActivity.class);
        startActivity(intent);
    }

    public void onClickPaiementInteret(View view) {
        //Creation de l'objet Intent pour appeler le Interet Activity
        Intent intent = new Intent(this, InteretActivity.class);
        startActivity(intent);

    }
}