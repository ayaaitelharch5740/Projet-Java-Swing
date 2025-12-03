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
import Service.ModuleService;
import entities.Module;

public class TestModule {
    public static void main(String[] args) {

        ModuleService ms = new ModuleService();

        System.out.println("=== AJOUT DES MODULES  ===");

        Module[] modules = {
            new Module(0, "Analyse Mathématique", 3),
            new Module(0, "Algèbre Linéaire", 2),
            new Module(0, "Algorithmique", 4),
            new Module(0, "Statistiques & Probabilités", 2),
            new Module(0, "Physique Générale", 3),
            new Module(0, "Chimie Générale", 2),
            new Module(0, "Méthodologie de Recherche", 1),
            new Module(0, "Bases de données", 3),
            new Module(0, "Réseaux informatiques", 3),
            new Module(0, "Physique appliquée", 2),
            new Module(0, "Chimie organique", 3),
            new Module(0, "Analyse numérique", 2),
            new Module(0, "Intelligence artificielle", 4),
            new Module(0, "Algorithmique avancée", 3)
        };

        for (Module m : modules) {
            ms.insert(m);
        }

        System.out.println("=== LISTE DES MODULES ===");
        ms.findAll().forEach(System.out::println);
    }
}