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
public class Note {
    private int id ;
    private Etudiant etudiant ;
    private Module module ;
    private String session ;
    private double valeur ;

    public Note(int id ,Etudiant etudiant, Module module, String session, double valeur) {
        this.id = id ;
        this.etudiant = etudiant;
        this.module = module;
        this.session = session;
        this.valeur = valeur;
    }

    

    public int getId() {
        return id;
    }
    

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public Module getModule() {
        return module;
    }

    public String getSession() {
        return session;
    }

    public double getValeur() {
        return valeur;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public void setModule(Module module) {
        this.module = module;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }
    
    
    
    
}
