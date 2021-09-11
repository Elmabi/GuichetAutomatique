package Controller;

import java.util.Objects;

public class Client {

    private String nomClient;
    private String prenomClient;
    private String userName;
    private int numeroNIP;

    public Client(String nomClient, String prenomClient, String userName, int numeroNIP) {
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
        this.userName = userName;
        this.numeroNIP = numeroNIP;
    }

    public Client(Client autre) {
        this.nomClient    = autre.nomClient;
        this.prenomClient = autre.prenomClient;
        this.userName   = autre.userName;
        this.numeroNIP = autre.numeroNIP;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
    }

    public void setUserName(String userName) {
        userName = userName;
    }

    public void setNumeroNIP(int numeroNIP) {
        this.numeroNIP = numeroNIP;
    }

    public String getNomClient() {
        return nomClient;
    }

    public String getPrenomClient() {
        return prenomClient;
    }

    public String getUserName() {
        return userName;
    }

    public int getNumeroNIP() {
        return numeroNIP;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return numeroNIP == client.numeroNIP &&
                nomClient.equals(client.nomClient) &&
                prenomClient.equals(client.prenomClient) &&
                userName.equals(client.userName);
    }


    @Override
    public String toString() {
        return "Client{" +
                "nomClient='" + nomClient + '\'' +
                ", prenomClient='" + prenomClient + '\'' +
                ", userName='" + userName + '\'' +
                ", numeroNIP=" + numeroNIP +
                '}';
    }
}
