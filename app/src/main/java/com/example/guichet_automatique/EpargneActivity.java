/*
 * Cet activité affiche la fenêtre qui permet d'afficher les informations par rapport
 * au comptes épargnes des clients.
 *
 */
package com.example.guichet_automatique;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Hashtable;

import Controller.Epargne;
import Controller.EpargneAdapter;

public class EpargneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_epargne);

        //Creation de l'objet ArrayAdapter et le lié au listView des Epargnes
        EpargneAdapter adapter = new EpargneAdapter(this, R.layout.listview_epargne, getCompteEpargnes());

        //Calculer le nombre du client dans la liste
        final TextView txtvNombre = findViewById(R.id.txtvNombreEpargne);
        txtvNombre.setText(txtvNombre.getText().toString() + adapter.getCount());

        final ListView list = (ListView) findViewById(R.id.malisteEpargne);

        //final ListView lvClients = findViewById(R.id.lvClients);
        list.setAdapter(adapter);
    }

    //Obtention de la liste des comptes épargnes crée dans le MainActivity
    public ArrayList<Epargne> getCompteEpargnes() {
        ArrayList<Epargne> epargneList = new ArrayList<>();
        Hashtable<Integer, Epargne> listeEpargne = new MainActivity().comptesEpargne();
        for (Epargne epargne : listeEpargne.values()) {
            epargneList.add(epargne);
        }
        return epargneList;
    }

}