package Controller;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.guichet_automatique.R;

import java.util.Objects;

public class Compte extends AppCompatActivity {

    private int numeroNIP;
    private String numeroCompte;
    private float soldeCompte;


    public Compte(int numeroNIP, String numeroCompte, float soldeCompte) {
        this.numeroNIP = numeroNIP;
        this.numeroCompte = numeroCompte;
        this.soldeCompte = soldeCompte;
    }

    public Compte(Compte autre) {
        this.numeroNIP = autre.numeroNIP;
        this.numeroCompte = autre.numeroCompte;
        this.soldeCompte = autre.soldeCompte;
    }


    public void retrait(float montant) {
        soldeCompte -= montant;
    }

    public void depot(float montant) {
        setSoldeCompte(this.soldeCompte + montant);
    }

    public void setNumeroNIP(int numeroNIP) {
        this.numeroNIP = numeroNIP;
    }

    public void setNumeroCompte(String numeroCompte) {
        this.numeroCompte = numeroCompte;
    }

    public void setSoldeCompte(float soldeCompte) {
        this.soldeCompte = soldeCompte;
    }

    public int getNumeroNIP() {
        return numeroNIP;
    }

    public String getNumeroCompte() {
        return numeroCompte;
    }

    public float getSoldeCompte() {
        return soldeCompte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Compte compte = (Compte) o;
        return numeroNIP == compte.numeroNIP &&
                Float.compare(compte.soldeCompte, soldeCompte) == 0 &&
                numeroCompte.equals(compte.numeroCompte);
    }

    @Override
    public String toString() {
        return "Compte{" +
                "numeroNIP=" + numeroNIP +
                ", numeroCompte='" + numeroCompte + '\'' +
                ", soldeCompte=" + soldeCompte +
                '}';
    }

}
