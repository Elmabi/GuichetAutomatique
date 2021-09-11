package Controller;

public class Cheque extends Compte {

    public Cheque(int numeroNIP, String numeroCompte, float soldeCompte) {
        super(numeroNIP, numeroCompte, soldeCompte);
    }

    public Cheque(Compte autre) {
        super(autre);
    }

}
