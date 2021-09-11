/*
 * Cet activité affiche la fenêtre qui permet d'afficher les informations par rapport au intérêts
 * appliqué par l'administrateur du guichet à chaque compte épargne des clients.
 */
package com.example.guichet_automatique;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Hashtable;

import Controller.Compte;
import Controller.InteretAdapter;
import Controller.Epargne;

public class InteretActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_interets);

        ArrayList<Epargne> epargneList = new EpargneActivity().getCompteEpargnes();

        InteretAdapter adapter = new InteretAdapter(this, R.layout.activity_interet, epargneList);
        final ListView lvEpargnes = findViewById(R.id.lvInteret);
        final TextView nombreEpargnes = findViewById(R.id.txtvNombreInteret);
        nombreEpargnes.setText(nombreEpargnes.getText().toString() + adapter.getCount());
        lvEpargnes.setAdapter(adapter);
    }

}