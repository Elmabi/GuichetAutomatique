package Controller;

public class Guichet {
    private Client client;
    private Cheque comptesCheque;
    private Epargne comptesEpargne;

    public Guichet(Client client, Cheque comptesCheque, Epargne comptesEpargne) {
        this.client = client;
        this.comptesCheque = comptesCheque;
        this.comptesEpargne = comptesEpargne;
    }

    public Guichet(Guichet autre ) {
        this.client = autre.client;
        this.comptesCheque = autre.comptesCheque;
        this.comptesEpargne = autre.comptesEpargne;
    }

    public Boolean ValiderUtilisateur(String nomClient, int nip) {

        boolean isValidNom = client.getNomClient().equals(nomClient);
        boolean isValidNip = client.getNumeroNIP() == nip;
        if (isValidNom && isValidNip) return true;

        return  false;
    }

    public float RetraitCheque(int nip, float montant) {

        if(comptesCheque.getNumeroNIP() == nip ) {
            comptesCheque.retrait(montant);
        }
        return comptesCheque.getSoldeCompte();
    }

    public float RetraitEpargne(int nip, float montant) {
        if (comptesEpargne.getNumeroNIP() == nip ) {
            comptesEpargne.retrait(montant);
        }
        return comptesEpargne.getSoldeCompte();
    }

    public float DepotCheque(int nip, float montant) {

        if (comptesCheque.getNumeroNIP() == nip) {
            comptesCheque.depot(montant);
        }
        return comptesCheque.getSoldeCompte();
    }

    public float DepotEpargne(int nip, float montant) {
        if (comptesEpargne.getNumeroNIP() == nip) {
            comptesEpargne.depot(montant);
        }

        return comptesEpargne.getSoldeCompte();
    }

    public Client getClient() {
        return client;
    }

    public Cheque getComptesCheque() {
        return comptesCheque;
    }

    public Epargne getComptesEpargne() {
        return comptesEpargne;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setComptesCheque(Cheque comptesCheque) {
        this.comptesCheque = comptesCheque;
    }

    public void setComptesEpargne(Epargne comptesEpargne) {
        this.comptesEpargne = comptesEpargne;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guichet guichet = (Guichet) o;
        return client.equals(guichet.client) &&
                comptesCheque.equals(guichet.comptesCheque) &&
                comptesEpargne.equals(guichet.comptesEpargne);
    }

    @Override
    public String toString() {
        return "Guichet{" +
                "clients=" + client +
                ", comptesCheque=" + comptesCheque +
                ", comptesEpargne=" + comptesEpargne +
                '}';
    }
}
