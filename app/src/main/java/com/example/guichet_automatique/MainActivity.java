
        //region DOCUMENTATION
/*
 ****************************************************************************************************
 ****************************************BIENVENUE***************************************************
 * Ce programme est un prototype simulant le fonctionnement d'un guichet automatique. Ça simule le
 * virement d'un compte cheque vers un compte d'épargne et vice versa, le dépot et le retrait dans
 * un compte (Cheque ou epargne) et ça contient une page Admin qui permet à l'administrateur du
 * guichet de pourvoir se connecter et voir les comptes clients qui ont été créés.
 * À l'ouverture vous trouverez une page qui permet de se connecter au guichet. Trois comptes clients
 * ont été crées par défaut.
 *
 * AUTHEURS: ARMEL FRANCK DJIONGO
 * VERSION : 5.0
 * **************************************************************************************************
 */
//endregion

package com.example.guichet_automatique;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Hashtable;

import Controller.*;

public class MainActivity extends AppCompatActivity {

    //region "DONNÉES ADMIN POUR SE CONNECTER"
    private final String USER_NAME_SUPERVISEUR = "Admin";
    private final String NIP_SUPERVISEUR = "D001";
    //endregion

    // si le client rate ses coordoonées pour se connecter 3 fois de suite le guichet se ferme.
    private int compteurEssais = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickSignIn(View view) {
        EditText edtUsername = findViewById(R.id.edtNom);
        EditText edtNip = findViewById(R.id.edtNip);
        // Si c'est le l'admin qui se connecte l'activité AdminActivity s'ouvre au lieu du guichet.
        if (isSuperviseur(edtUsername, edtNip)) return;

        if (isBonUsernameEtNip(edtUsername, edtNip)) {
            compteurEssais = 0;
            ouvrirLeGuichet(edtUsername, edtNip);
        }
        compteurEssais++;
        fermerApresTroisEssais();
    }

    // Quand les coordoonées de connexion sont bonne, le guichet s'ouvre et les informations du
    // client sont envoyées au GuichetActivity
    private void ouvrirLeGuichet(EditText edtUsername, EditText edtNip) {
        Intent intent = new Intent(this, GuichetActivity.class);
        Bundle bundle = new Bundle();
        int nipClient = Integer.parseInt(edtNip.getText().toString());
        String prenomClient = getPrenomNomClient(nipClient)[0];
        String nomClient = getPrenomNomClient(nipClient)[1];
        String userName = edtUsername.getText().toString();
        bundle.putInt("NIP", nipClient);
        bundle.putString("nomClient", nomClient );
        bundle.putString("prenomClient",prenomClient );
        bundle.putString("userName",userName);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private boolean isSuperviseur(EditText edtUsername, EditText edtNip) {
        boolean isSuperviseurNom = edtUsername.getText().toString().equals(USER_NAME_SUPERVISEUR);
        boolean isSuperviseurNip = edtNip.getText().toString().equals(NIP_SUPERVISEUR);
        if (isSuperviseurNom && isSuperviseurNip) {
            Intent intent = new Intent(this, AdminActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }

    private boolean isBonUsernameEtNip(EditText edtUsername, EditText edtNip) {
        for (Client value : clients().values()) {
            boolean isValidName = edtUsername.getText().toString().equals(value.getUserName());
            String NIP = String.valueOf(value.getNumeroNIP());
            boolean isValidNip = edtNip.getText().toString().equals(NIP);
            if (isValidName && isValidNip) {
                guichet(value);
                return true;
            }
        }
        String message = "Entrée incorrecte";
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        return false;
    }

    // Retourne le guichet et le client connecté. Ce qui sera utilisé dans la classe
    // GuichetActivity
    public Guichet guichet(Client client) {
        Cheque cheque = comptesCheque().get(client.getNumeroNIP());
        Epargne epargne = comptesEpargne().get(client.getNumeroNIP());
        Guichet guichet = new Guichet(client,cheque,epargne);
        return guichet;
    }

    private void fermerApresTroisEssais() {
        if (compteurEssais > 3) {
            String message = "Veuillez réutiliser le guichet plus tard.";
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            this.finishAndRemoveTask();
        }
    }

    //region LA LISTE DES CLIENTS ET COMPTES
    public Hashtable<Integer, Client> clients() {
        Hashtable<Integer, Client> clients = new Hashtable();
        Client client1234 = new Client("Trudaud", "Leoanard", "tru1234",
                1234);
        Client client789 = new Client("Harakate", "Fatima", "fat789",
                789);
        Client client852 = new Client("Djiongo", "Franck", "fra852",
                852);
        clients.put(789, client789);
        clients.put(852, client852);
        clients.put(1234, client1234);
        return clients;
    }

    public Hashtable<Integer, Epargne> comptesEpargne() {

        Hashtable<Integer, Epargne> comptesEpargne = new Hashtable<>();

        comptesEpargne.put(1234, new Epargne(1234, "1234", 1234));
        comptesEpargne.put(789, new Epargne(789, "789", 789));
        comptesEpargne.put(852, new Epargne(852, "852", 852));

        return comptesEpargne;
    }

    public Hashtable<Integer, Cheque> comptesCheque() {
        Hashtable<Integer, Cheque> comptesCheque = new Hashtable<>();

        comptesCheque.put(1234, new Cheque(1234, "1234", 1000));
        comptesCheque.put(789, new Cheque(789, "789", 1500));
        comptesCheque.put(852, new Cheque(852, "852", 1800));

        return comptesCheque;
    }
    //endregion

    // Retourne un array qui contient le nom et prenom du client qui se connecte  en fonction de
    // son nip. Ces coordonées seront envoyés à la classe GuichetActivity
    public  String[] getPrenomNomClient(int nip){

        String [] tableau = new String[]{};
        Hashtable<Integer, Client> listeClient = clients();
        for (Client client : listeClient.values()) {
           if(client.getNumeroNIP() == nip){
               tableau = new String[] { client.getPrenomClient(), client.getNomClient()};
           }
        }

        return tableau;
    }

}