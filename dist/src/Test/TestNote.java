/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

/**
 *
 * @author ORIGINAL SHOP
 */

import Service.NoteService;
import Service.EtudiantService;
import Service.ModuleService;
import entities.Etudiant;
import entities.Module;
import entities.Note;
import java.util.List;

public class TestNote {
    public static void main(String[] args) {

        NoteService ns = new NoteService();
        EtudiantService es = new EtudiantService();
        ModuleService ms = new ModuleService();

        
        List<Module> tousModules = ms.findAll();
      System.out.println("Nombre de modules : " + tousModules.size());

      List<Module> anciensModules = tousModules.subList(0, Math.min(7, tousModules.size()));
      List<Module> nouveauxModules = tousModules.subList(
        Math.min(7, tousModules.size()), 
        Math.min(14, tousModules.size())
        );

        System.out.println("=== AJOUT DES NOTES POUR CHAQUE ETUDIANT ===");

        for (Etudiant e : es.findAll()) {

            // --------------------------
            // Session Automne 
            // --------------------------
            for (Module m : anciensModules) {
                double valeur = 10 + Math.random() * 10; // note aléatoire 10-20
                valeur = Math.round(valeur * 100.0) / 100.0; 
                Note n = new Note(0, e, m, "Automne", valeur);
                ns.insert(n);
            }

            // --------------------------
            // Session Printemps 
            // --------------------------
           for (Module m : nouveauxModules) {
           double valeur = 10 + Math.random() * 10;
           valeur = Math.round(valeur * 100.0) / 100.0; 
           Note n = new Note(0, e, m, "Printemps", valeur);
           ns.insert(n);
    }
        }

        System.out.println("=== TOUTES LES NOTES ===");
        ns.findAll().forEach(System.out::println);

        System.out.println("\n=== NOTES PAR SESSION (Automne) ===");
        ns.ResultatSession("Automne").forEach(System.out::println);

        System.out.println("\n=== NOTES PAR SESSION (Printemps) ===");
        ns.ResultatSession("Printemps").forEach(System.out::println);

        System.out.println("\n=== MOYENNE DE CHAQUE ETUDIANT ===");
        for (Etudiant e : es.findAll()) {
            double moyenne = ns.CalculerMoyenne(e);
            System.out.println(e.getNom() + " " + e.getPrenom() + " -> Moyenne = " + String.format("%.2f", moyenne));
        }
    }
}
