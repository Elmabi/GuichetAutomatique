package Controller;

import java.util.Objects;

public class Epargne extends Compte {

    private final double TAUX_INTERET = 0.0125;


    public Epargne(int numeroNIP, String numeroCompte, float soldeCompte) {
        super(numeroNIP, numeroCompte, soldeCompte);
    }

    public Epargne(Compte autre) {
        super(autre);
    }

    public float paiementInterets() {
        float interet = (float) (getSoldeCompte()*TAUX_INTERET);

        return interet;
    }

    @Override
    public void setSoldeCompte(float soldeCompte) {
        super.setSoldeCompte(soldeCompte);
    }

    @Override
    public float getSoldeCompte() {
        return super.getSoldeCompte();
    }

    public double getTauxInteret() {
        return TAUX_INTERET;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Epargne epargne = (Epargne) o;
        return Double.compare(epargne.TAUX_INTERET, TAUX_INTERET) == 0;
    }


    @Override
    public String toString() {
        return super.toString() + "Epargne{" +
                "TAUX_INTERET=" + TAUX_INTERET +
                '}';
    }
}




