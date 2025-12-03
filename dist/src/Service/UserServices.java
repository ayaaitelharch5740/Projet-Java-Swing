/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import util.Connexion;
import dao.IDao;
import entities.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ORIGINAL SHOP
 */
public class UserServices implements IDao<User> {

    // ===========================
    // HASH PASSWORD SHA-256
    // ===========================
    public String hashPassword(String pwd) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashed = md.digest(pwd.getBytes());

            StringBuilder sb = new StringBuilder();
            for (byte b : hashed) sb.append(String.format("%02x", b));

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    // ===========================
    // LOGIN
    // ===========================
    public User login(String email, String password) {

    String hashed = hashPassword(password);

    System.out.println("Email entré : " + email);
    System.out.println("Mot de passe entré : " + password);
    System.out.println("Mot de passe hashé : " + hashed);

    try {
        String req = "SELECT * FROM user WHERE email=? AND password=?";
        PreparedStatement ps = Connexion.getInstance().getConn().prepareStatement(req);
        ps.setString(1, email.trim());
        ps.setString(2, hashed.trim());
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            System.out.println("Login réussi pour : " + rs.getString("username"));
            return new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email")
            );
        } else {
            System.out.println("Login échoué : email ou mot de passe incorrect.");
        }

    } catch (SQLException ex) {
        Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
    }
    return null;
}



    // ===========================
    // CHECK USERNAME EXISTS
    // ===========================
    public boolean usernameExists(String username) {
        try {
            String req = "SELECT id FROM user WHERE username=?";
            PreparedStatement ps = Connexion.getInstance().getConn().prepareStatement(req);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            return false;
        }
    }

    // ===========================
    // SIGNUP / CREATE ACCOUNT
    // ===========================
    public boolean signup(User u) {

        if (usernameExists(u.getUsername())) {
            return false; // username déjà utilisé
        }

        try {
            String req = "INSERT INTO user(username, password, email) VALUES(?,?,?)";
            PreparedStatement ps = Connexion.getInstance().getConn().prepareStatement(req);
            ps.setString(1, u.getUsername());
            ps.setString(2, hashPassword(u.getPassword()));
            ps.setString(3, u.getEmail());

            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    // ===========================
    // FIND USER BY EMAIL (pour forgot password)
    // ===========================
    public User findByEmail(String email) {
        try {
            String req = "SELECT * FROM user WHERE email=?";
            PreparedStatement ps = Connexion.getInstance().getConn().prepareStatement(req);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email")
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    // ===========================
    // UPDATE PASSWORD (Forgot password)
    // ===========================
    public boolean updatePassword(User u) {

        try {
            String req = "UPDATE user SET password=? WHERE id=?";
            PreparedStatement ps = Connexion.getInstance().getConn().prepareStatement(req);

            ps.setString(1, hashPassword(u.getPassword()));
            ps.setInt(2, u.getId());

            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    // ===========================
    // IDAO METHODS
    // ===========================
    @Override
    public User findById(int id) {
        try {
            String req = "SELECT * FROM user WHERE id=?";
            PreparedStatement ps = Connexion.getInstance().getConn().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email")
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        List<User> list = new ArrayList<>();

        try {
            String req = "SELECT * FROM user";
            PreparedStatement ps = Connexion.getInstance().getConn().prepareStatement(req);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                list.add(new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email")
                ));
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    @Override
    public boolean insert(User u) {
        return signup(u);
    }

    @Override
    public boolean update(User u) {
        try {
            String req = "UPDATE user SET username=?, email=? WHERE id=?";
            PreparedStatement ps = Connexion.getInstance().getConn().prepareStatement(req);

            ps.setString(1, u.getUsername());
            ps.setString(2, u.getEmail());
            ps.setInt(3, u.getId());

            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(UserServices.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean delete(User u) {
        try {
            String req = "DELETE FROM user WHERE id=?";
            PreparedStatement ps = Connexion.getInstance().getConn().prepareStatement(req);

            ps.setInt(1, u.getId());
            ps.executeUpdate();

            return true;

        } catch (SQLException ex) {
            return false;
        }
    }
}
