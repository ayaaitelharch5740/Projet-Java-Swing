/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import util.Connexion;
import dao.IDao;
import entities.Etudiant;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EtudiantService implements IDao<Etudiant> {

    @Override
    public Etudiant findById(int id) {
        try {
            String req = "SELECT * FROM etudiant WHERE id=?";
            PreparedStatement ps = Connexion.getInstance().getConn().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new Etudiant(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),rs.getString("filiere"), rs.getString("niveau"));
            }
        } catch(SQLException ex){
            Logger.getLogger(EtudiantService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Etudiant> findAll() {
        List<Etudiant> etudiants = new ArrayList<>();
        try {
            String req = "SELECT * FROM etudiant";
            PreparedStatement ps = Connexion.getInstance().getConn().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                etudiants.add(new Etudiant(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("filiere"), rs.getString("niveau")));
            }
        } catch(SQLException ex){
            Logger.getLogger(EtudiantService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return etudiants;
    }

    @Override
    public boolean insert(Etudiant o) {
        try {
            String req = "INSERT INTO etudiant(nom, prenom, filiere, niveau) VALUES(?,?,?,?)";
            PreparedStatement ps = Connexion.getInstance().getConn().prepareStatement(req);
            ps.setString(1, o.getNom());
            ps.setString(2, o.getPrenom());
            ps.setString(3, o.getFiliere());
            ps.setString(4, o.getNiveau());
            ps.executeUpdate();
            return true;
        } catch(SQLException ex){
            Logger.getLogger(EtudiantService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean update(Etudiant o) {
        try {
            String req = "UPDATE etudiant SET nom=?, prenom=?, filiere=?, niveau=? WHERE id=?";
            PreparedStatement ps = Connexion.getInstance().getConn().prepareStatement(req);
            ps.setString(1, o.getNom());
            ps.setString(2, o.getPrenom());
            ps.setString(3, o.getFiliere());
            ps.setString(4, o.getNiveau());
            ps.setInt(5, o.getId());
            ps.executeUpdate();
            return true;
        } catch(SQLException ex){
            Logger.getLogger(EtudiantService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean delete(Etudiant o) {
        try {
            String req = "DELETE FROM etudiant WHERE id=?";
            PreparedStatement ps = Connexion.getInstance().getConn().prepareStatement(req);
            ps.setInt(1, o.getId());
            ps.executeUpdate();
            return true;
        } catch(SQLException ex){
            Logger.getLogger(EtudiantService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public Etudiant findByNomPrenom(String nom, String prenom){
    Etudiant e = null;
    try {
        String req = "SELECT id, nom, prenom, filiere, niveau FROM etudiant WHERE nom=? AND prenom=?";
        PreparedStatement ps = Connexion.getInstance().getConn().prepareStatement(req);
        ps.setString(1, nom);
        ps.setString(2, prenom);

        ResultSet rs = ps.executeQuery();

        if(rs.next()){
            e = new Etudiant(
                rs.getInt("id"),
                rs.getString("nom"),
                rs.getString("prenom"),
                rs.getString("filiere"),
                rs.getString("niveau")
            );
        }

    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return e;
}

}

