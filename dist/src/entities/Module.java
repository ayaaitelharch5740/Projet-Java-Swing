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
public class Module {
    private int id ;
    private String nom ;
    private int coefficient ;

    public Module(int id ,String nom, int coefficient) {
        this.id = id ;
        this.nom = nom;
        this.coefficient = coefficient;
    }

    public Module(int id ) {
        this.id = id ; 
    }

    

    public int getId() {
        return id;
    }


    public String getNom() {
        return nom;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }
    
    
    @Override
   public String toString() {
    return nom;
}
    
}
