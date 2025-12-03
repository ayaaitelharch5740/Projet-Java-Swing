package Test;

import Service.UserServices;
import entities.User;

public class TestUser {

    public static void main(String[] args) {

        UserServices us = new UserServices();

        System.out.println("=== AJOUT USER ===");
        User u1 = new User("admin", "1234", "admin@gmail.com"); 
        us.insert(u1);

        User u2 = new User("aya", "pass", "ayaaitelharch3@gmail.com"); 
        us.insert(u2);

        System.out.println("=== TOUS LES USERS ===");
        for(User u : us.findAll()) {
            System.out.println(u.getId() + " - " + u.getUsername() + " - " + u.getEmail());
        }

        System.out.println("=== FIND BY ID ===");
        User uFind = us.findById(1);
        if(uFind != null)
            System.out.println("User trouvé : " + uFind.getUsername());
        else
            System.out.println("User non trouvé");

        System.out.println("=== UPDATE USER ===");
        if(uFind != null){
            uFind.setPassword("newpass");
            uFind.setEmail("updated@gmail.com");
            us.update(uFind);
        }

        System.out.println("=== LOGIN TEST ===");
        User loginUser = us.login("admin", "1234");
        if(loginUser != null)
            System.out.println("Login réussi : " + loginUser.getUsername());
        else
            System.out.println("Login incorrect");

        System.out.println("=== SUPPRESSION ===");
        if(uFind != null) {
            us.delete(uFind);
        }

        System.out.println("=== LISTE APRÈS SUPPRESSION ===");
        for(User u : us.findAll()) {
            System.out.println(u.getId() + " - " + u.getUsername() + " - " + u.getEmail());
        }
    }
}

    

