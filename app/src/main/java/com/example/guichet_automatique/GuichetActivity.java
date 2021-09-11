/*
 * Cet activité affiche la fenêtre qui permet de naviguer dans le guichet en question. Cet ici
 * que se fait les transactions et l'interaction de l'utilisateur avec le guichet
 */
package com.example.guichet_automatique;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import Controller.*;

public class GuichetActivity extends AppCompatActivity {


    //<editor-fold desc="MES CONSTANTES">
    private final String MESSAGE_SAISIE_INCORRECTE_ENTREZ_UN_MONTANT_VALIDE = "Saisie incorrecte, Entrez un montant valide!";
    private final String MESSAGE_SOLDE_EPARGNE_NEGATIF = "Solde du compte d'epargne est insuffisant !";
    private final String MESSAGE_VIREMENT_INVALIDE = "Transaction non autorisée! vous avez depassé 100.000 $";
    private final String MESSAGE_MONTANT_PAS_MULTIPLE_10 = "Montant saisi est invalide! il n'est pas un multiple de 10";
    private final String MESSAGE_MONTANT_DEPASSE_1000 = "Montant saisi est invalide! Il depasse 1000 $";
    private final String MESSAGE_SOLDE_CHEQUE_INSUFFISANT = "Votre solde du compte cheque est insuffisant";
    //</editor-fold>

    //<editor-fold desc="MES VARIABLES">
    private Guichet guichet;
    //</editor-fold>

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guichet);

        //Récupération des données de Bundle (informations du client envoyer depuis le MainActivity)
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int numeroNIP = bundle.getInt("NIP");
        String nomClient = bundle.getString("nomClient");
        String prenomClient = bundle.getString("prenomClient");
        String userName = bundle.getString("userName");

        //Recupération du client et ses coordonées qui c'est connecté à travers le MainActivity
        // et on l'accepte dans le guichet. (Rappel: La classe MainActivity renvoi à cette classe
        // le client qui s'est connecté et ses différents comptes bancaires grace à la fonction
        // guichet() qui retourne un guichet.)
        Client client = new Client(nomClient, prenomClient, userName, numeroNIP);
        guichet = new MainActivity().guichet(client);

        //Affichage du nom de l'utilisateur
        TextView txtvBienvenue = (TextView) findViewById(R.id.txtvBienvenue);
        txtvBienvenue.setText(txtvBienvenue.getText().toString() + " " + prenomClient);

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        //Déclaration des variables
        float soldeCheque = guichet.getComptesCheque().getSoldeCompte();
        float soldeEpargne = guichet.getComptesEpargne().getSoldeCompte();
        float montant = getMontantSaisi();
        //Sauvegarde des valeurs du montant, solde cheque et solde d'epargne
        //pour le changement d'orientation
        savedInstanceState.putFloat("MONTANT", montant);
        savedInstanceState.putFloat("CHEQUE", soldeCheque);
        savedInstanceState.putFloat("EPARGNE", soldeEpargne);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        TextView txtMontant = (TextView) findViewById(R.id.txtvMontant);
        if (savedInstanceState != null) {
            //Récuperation des valeurs sauvegardées du montant, solde cheque et solde d'epargne
            float montant = savedInstanceState.getFloat("MONTANT");
            float soldeCheque = savedInstanceState.getFloat("CHEQUE");
            float soldeEpargne = savedInstanceState.getFloat("EPARGNE");

            //Mise à jour du compte Cheque et epargne par les valeurs récupérées
            guichet.getComptesEpargne().setSoldeCompte(soldeEpargne);
            guichet.getComptesCheque().setSoldeCompte(soldeCheque);

            //Affichage du montant recupérer
            if (montant == 0) {
                txtMontant.setHint("Montant");
                return;
            }
            txtMontant.setText(Float.toString(montant));
        }
    }

    //<editor-fold desc="METHODES POUR SAISIR LES CHIFFRES">
    public void onClickZero(View view) {
        entrerChiffre("0");
    }

    public void onClickUn(View view) {
        entrerChiffre("1");
    }

    public void onClickDeux(View view) {
        entrerChiffre("2");
    }

    public void onClickTrois(View view) {
        entrerChiffre("3");
    }

    public void onClickQuatre(View view) {
        entrerChiffre("4");
    }

    public void onClickCinq(View view) {
        entrerChiffre("5");
    }

    public void onClickSix(View view) {
        entrerChiffre("6");
    }

    public void onClickSept(View view) {
        entrerChiffre("7");
    }

    public void onClickHuit(View view) {
        entrerChiffre("8");
    }

    public void onClickNeuf(View view) {
        entrerChiffre("9");
    }

    public void onClickPoint(View view) {
        entrerChiffre(".");
    }

    private void entrerChiffre(String chiffre) {
        TextView txtMontant = findViewById(R.id.txtvMontant);
        String ancienMontant = String.valueOf(txtMontant.getText());
        txtMontant.setText(ancienMontant.concat(chiffre));
    }

    //Effacer le montant saisi par l'utilisateur
    public void onClickEffacer(View view) {
        TextView txtMontant = findViewById(R.id.txtvMontant);
        txtMontant.setText("");
    }

    //</editor-fold>

    // Permet l'obtentation du montant saisi par le client
    public float getMontantSaisi() {
        float montant;
        try {
            TextView txtMontant = findViewById(R.id.txtvMontant);
            montant = Float.parseFloat(txtMontant.getText().toString());
        } catch (NumberFormatException e) {
            return 0;
        }
        return montant;
    }

    //permet les differentes Transactions : Retrait, Depot ET Virement
    public void onClickSoumettre(View view) {

        //get NIP number and username
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int numeroNIP = bundle.getInt("NIP");
        String nomClient = bundle.getString("nomClient");

        //get Radio Button id
        RadioButton rbDepot = (RadioButton) findViewById(R.id.rbDepot);
        RadioButton rbCheque = (RadioButton) findViewById(R.id.rbCheque);
        RadioButton rbRetrait = (RadioButton) findViewById(R.id.rbRetrait);
        RadioButton rbVirement = (RadioButton) findViewById(R.id.rbVirement);
        RadioButton rbEpargne = (RadioButton) findViewById(R.id.rbEpargne);

        //get le montant
        float montant = getMontantSaisi();
        if(montant == 0){
            Toast.makeText(this, MESSAGE_SAISIE_INCORRECTE_ENTREZ_UN_MONTANT_VALIDE,
                    Toast.LENGTH_SHORT).show();
            return;
        }

        //Rendre l'affichage de l'etat du compte Cheque et Epargne invisible
        visibiliteEtatCompte(view.INVISIBLE);

        //Vérification du type de transaction et type du compte choisi par l'utilisateur
        boolean isDepotDansCheque = rbCheque.isChecked() && rbDepot.isChecked();
        boolean isRetraitDansCheque = rbCheque.isChecked() && rbRetrait.isChecked();
        boolean isDepotDansEpargne = rbEpargne.isChecked() && rbDepot.isChecked();
        boolean isRetraitDansEpargne = rbEpargne.isChecked() && rbRetrait.isChecked();
        boolean isVirementVersEpargne = rbVirement.isChecked() && rbEpargne.isChecked();
        boolean isVirementVersCheque = rbVirement.isChecked() && rbCheque.isChecked();

        if (isDepotDansCheque) depotDansCheque(numeroNIP, nomClient, montant);
        if (isRetraitDansCheque) RetraitDansCheque(numeroNIP, nomClient, montant);
        if (isDepotDansEpargne) depotDansEpargne(numeroNIP, nomClient, montant);
        if (isRetraitDansEpargne) retraitDansEpargne(numeroNIP, nomClient, montant);
        if (isVirementVersEpargne) virementVersEpargne(numeroNIP, nomClient, montant);
        if (isVirementVersCheque) virementVersCheque(numeroNIP, nomClient, montant);

    }

    //Validation du depot dans le compte Epargne
    private void depotDansEpargne(int numeroNIP, String nomClient, float montant) {
        String message = "Dépot de " + montant + " sur votre compte Epargne";

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        if (guichet.ValiderUtilisateur(nomClient, numeroNIP)) {
            guichet.DepotEpargne(numeroNIP, montant);
        }
    }

    //Validation du Virement du compte Epargne ==> Compte Cheque
    private void virementVersCheque(int numeroNIP, String nomClient, float montant) {

        //Verifier si l'utilisateur existe
        if (guichet.ValiderUtilisateur(nomClient, numeroNIP)) {
            virementChequeValide(numeroNIP, montant);
        }
    }

    //Verifier si le solde Epargne est suffisant && Montant est valide
    private void virementChequeValide(int numeroNIP, float montant) {

        boolean isSoldeSuffisant = guichet.getComptesEpargne().getSoldeCompte() >= montant;
        boolean isMontantValide = montant <= 100000;

        if (!isSoldeSuffisant) {
            Toast.makeText(this, MESSAGE_SOLDE_EPARGNE_NEGATIF, Toast.LENGTH_LONG).show();
            return;
        }
        if (!isMontantValide) {
            Toast.makeText(this, MESSAGE_VIREMENT_INVALIDE, Toast.LENGTH_LONG).show();
            return;
        }
        guichet.DepotCheque(numeroNIP, montant);
        guichet.RetraitEpargne(numeroNIP, montant);
        String messageValide = messageVirementValide(montant, "Epargne", "Cheque");
        Toast.makeText(this, messageValide, Toast.LENGTH_LONG).show();
    }

    //Validation du Virement du compte Cheque ==> Compte Epargne
    private void virementVersEpargne(int numeroNIP, String nomClient, float montant) {
        if (guichet.ValiderUtilisateur(nomClient, numeroNIP)) {
            virementEpargneValide(numeroNIP, montant);
        }
    }

    //Verifier si le solde Cheque est suffisant && Montant est valide pour le virement
    private void virementEpargneValide(int numeroNIP, float montant) {

        boolean isSoldeSuffisant = guichet.getComptesCheque().getSoldeCompte() >= montant;
        boolean isMontantValide = montant <= 100000;

        if (!isSoldeSuffisant) {
            Toast.makeText(this, MESSAGE_SOLDE_CHEQUE_INSUFFISANT, Toast.LENGTH_LONG).show();
            return;
        }

        if (!isMontantValide) {
            Toast.makeText(this, MESSAGE_VIREMENT_INVALIDE, Toast.LENGTH_LONG).show();
            return;
        }
        guichet.DepotEpargne(numeroNIP, montant);
        guichet.RetraitCheque(numeroNIP, montant);
        String messageValide = messageVirementValide(montant, "Cheque", "Epargne");
        Toast.makeText(this, messageValide, Toast.LENGTH_LONG).show();
    }

    //Validation du Retrait du Compte Epargne
    private void retraitDansEpargne(int numeroNIP, String nomClient, float montant) {
        if (guichet.ValiderUtilisateur(nomClient, numeroNIP)) {
            retraitEpargneValide(numeroNIP, montant);
        }
    }

    //Verifier si le solde est suffisant && Montant est valide && multiple de 10 avant le retrait
    private void retraitEpargneValide(int numeroNIP, float montant) {

        boolean isMontantValide = montant <= 1000;
        boolean isMontantMultipl10 = montant % 10 == 0;
        boolean isSoldeSuffisant = guichet.getComptesEpargne().getSoldeCompte() >= montant;

        if (!isSoldeSuffisant) {
            Toast.makeText(this, MESSAGE_SOLDE_EPARGNE_NEGATIF, Toast.LENGTH_LONG).show();
            return;
        }

        if (!isMontantValide) {
            Toast.makeText(this, MESSAGE_MONTANT_DEPASSE_1000, Toast.LENGTH_LONG).show();
            return;
        }

        if (!isMontantMultipl10) {
            Toast.makeText(this, MESSAGE_MONTANT_PAS_MULTIPLE_10, Toast.LENGTH_LONG).show();
            return;
        }

        guichet.RetraitEpargne(numeroNIP, montant);
        String messageValide = "Retrait de " + montant + " sur votre compte Epargne";
        Toast.makeText(this, messageValide, Toast.LENGTH_LONG).show();
    }

    //Validation du Retrait du Compte Cheque
    private void RetraitDansCheque(int numeroNIP, String nomClient, float montant) {
        if (guichet.ValiderUtilisateur(nomClient, numeroNIP)) {
            retraitChequeValide(numeroNIP, montant);
        }
    }

    //Verifier si le solde est suffisant && Montant est valide && multiple de 10 avant le retrait
    private void retraitChequeValide(int numeroNIP, float montant) {

        boolean isMontantValide = montant <= 1000;
        boolean isMontantMultipl10 = montant % 10 == 0;
        boolean isSoldeSuffisant = guichet.getComptesCheque().getSoldeCompte() >= montant;

        if (!isSoldeSuffisant) {
            Toast.makeText(this, MESSAGE_SOLDE_CHEQUE_INSUFFISANT, Toast.LENGTH_LONG).show();
            return;
        }

        if (!isMontantValide) {
            Toast.makeText(this, MESSAGE_MONTANT_DEPASSE_1000, Toast.LENGTH_LONG).show();
            return;
        }

        if (!isMontantMultipl10) {
            Toast.makeText(this, MESSAGE_MONTANT_PAS_MULTIPLE_10, Toast.LENGTH_LONG).show();
            return;
        }

        guichet.RetraitCheque(numeroNIP, montant);
        String messageValide = "Retrait de " + montant + " sur votre compte Cheque";
        Toast.makeText(this, messageValide, Toast.LENGTH_LONG).show();
    }

    private void depotDansCheque(int numeroNIP, String nomClient, float montant) {

        String message = "Dépot de " + montant + " sur votre compte Cheque";
        if (guichet.ValiderUtilisateur(nomClient, numeroNIP)) {
            guichet.DepotCheque(numeroNIP, montant);
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    //Changer la visibilité de l'etat du compte Cheque et Epargne
    private void visibiliteEtatCompte(int visibility) {
        LinearLayout layoutEtatCompte = findViewById(R.id.layoutEtatCompte);
        layoutEtatCompte.setVisibility(visibility);
    }

    //Recuperation du solde  du compte Cheque et Epargne du client du client connécté
    public void onClickEtat(View view) {

        TextView txtvMontantCheque = (TextView) findViewById(R.id.txtvMontantCheque);
        TextView txtvMontantEpargne = (TextView) findViewById(R.id.txtvMontantEpargne);
        visibiliteEtatCompte(view.VISIBLE);
        txtvMontantCheque.setText(String.valueOf(guichet.getComptesCheque().getSoldeCompte()));
        txtvMontantEpargne.setText(String.valueOf(guichet.getComptesEpargne().getSoldeCompte()));
    }

    //Message de validation du Virment entre les compte Cheque et Epargne
    private String messageVirementValide(float montant, String compte, String compte2) {
        String chaine = "Virement de " + montant + " de votre compte " + compte
                + " vers compte " + compte2;
        return chaine;
    }

    public void onClickQuitter(View view) {
        finish();
        System.exit(0);
    }
}
