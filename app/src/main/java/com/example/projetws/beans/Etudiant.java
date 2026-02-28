package com.example.projetws.beans;

public class Etudiant {
    private int id;
    private String nom, prenom, ville, sexe;

    public Etudiant() {}

    public int getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getVille() { return ville; }
    public String getSexe() { return sexe; }

    @Override
    public String toString() {
        return "Etudiant{id=" + id + ", nom='" + nom + "', prenom='" + prenom +
                "', ville='" + ville + "', sexe='" + sexe + "'}";
    }
}