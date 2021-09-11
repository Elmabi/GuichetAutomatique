/*
 * Cet activité affiche la fenêtre qui permet d'afficher les informations par rapport
 * au clients.
 */
package com.example.guichet_automatique;

import androidx.appcompat.app.AppCompatActivity;

import Controller.*;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Hashtable;

public class ClientsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_clients);

        //Creation de l'objet de type ArrayAdapter et le lié au listView des clients
        ClientAdapter adapter = new ClientAdapter(this, R.layout.activity_clients, getClients());

        //Calculer le nombre du client dans la liste
        final TextView txtvNombre = findViewById(R.id.txtvNombre);
        txtvNombre.setText(txtvNombre.getText().toString() + adapter.getCount());

        final ListView lvClients = findViewById(R.id.lvClients);
        lvClients.setAdapter(adapter);
    }

    //Obtention de la liste  des Clients qui est crée dans le MainActivity
    public ArrayList<Client> getClients() {
        ArrayList<Client> clientList = new ArrayList<>();
        Hashtable<Integer, Client> listeClient = new MainActivity().clients();
        for (Client client : listeClient.values()) {
            clientList.add(client);
        }
        return clientList;
    }

}