/*
 * Cet activité affiche la fenêtre qui permet d'afficher les informations par rapport
 * au comptes chèques des clients.
 */
package com.example.guichet_automatique;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Hashtable;

import Controller.Cheque;
import Controller.ChequeAdapter;
import Controller.Client;
import Controller.InteretAdapter;
import Controller.Compte;

public class ChequeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_cheque);

        ChequeAdapter adapter = new ChequeAdapter(this,R.layout.activity_cheque, getCompteCheques());

        final ListView lvCheques = findViewById(R.id.lvCheques);
        final TextView nombreCheques = findViewById(R.id.txtvNombreCheque);

        nombreCheques.setText(nombreCheques.getText().toString() + adapter.getCount());
        lvCheques.setAdapter(adapter);
    }

    //Obtention de la liste des comptes cheques des clients qui est crée dans le MainActivity
    public ArrayList<Cheque> getCompteCheques() {
        ArrayList<Cheque> chequeList = new ArrayList<>();
        Hashtable<Integer, Cheque> listeClient = new MainActivity().comptesCheque();
        for (Cheque cheque : listeClient.values()) {
            chequeList.add(cheque);
        }

        return chequeList;
    }
}