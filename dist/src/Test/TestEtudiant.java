/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Service.EtudiantService;
import entities.Etudiant;

/**
 *
 * @author ORIGINAL SHOP
 */
public class TestEtudiant {
 
    public static void main(String[] args) {

        EtudiantService es = new EtudiantService();

        System.out.println("=== AJOUT DES 10 ETUDIANTS ===");

        Etudiant[] etudiants = {
            new Etudiant(0, "Karimi", "Aya", "Informatique", "L3"),
            new Etudiant(0, "El Amrani", "Mohamed", "Math", "L2"),
            new Etudiant(0, "Benali", "Sara", "Physique", "L1"),
            new Etudiant(0, "Berrada", "Youssef", "Informatique", "M1"),
            new Etudiant(0, "Saidi", "Imane", "Chimie", "L3"),
            new Etudiant(0, "Mansouri", "Hicham", "Informatique", "M2"),
            new Etudiant(0, "Boufakir", "Nadia", "Math", "L1"),
            new Etudiant(0, "Zerhouni", "Khalid", "Physique", "L3"),
            new Etudiant(0, "Hassani", "Rania", "Informatique", "L2"),
            new Etudiant(0, "Chakir", "Omar", "Chimie", "L2")
        };

        for (Etudiant e : etudiants) {
            es.insert(e);
        }

        System.out.println("=== LISTE DES ETUDIANTS ===");
        es.findAll().forEach(System.out::println);
    }
}


