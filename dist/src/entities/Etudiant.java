/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author ORIGINAL SHOP
 */
public class Etudiant {
    private int id;
    private String prenom ;
    private String nom ;
    private String filiere ;
    private String niveau ;

   
    
    
    public Etudiant(int id, String nom, String prenom, String filiere, String niveau){
        this.id = id ;
        this.nom = nom;
        this.prenom = prenom;
        this.filiere = filiere;
        this.niveau = niveau ;
     }

    

     
    
    public int getId() {
        return id;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getFiliere() {
        return filiere;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }
    @Override
    public String toString() {
    return nom;  
}
}
    
    
 