/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;


import util.Connexion;
import dao.IDao;
import entities.Module;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ModuleService implements IDao<Module> {

    @Override
    public Module findById(int id) {
        try {
            String req = "SELECT * FROM module WHERE id=?";
            PreparedStatement ps = Connexion.getInstance().getConn().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new Module(rs.getInt("id"), rs.getString("nom") , rs.getInt("coefficient"));
            }
        } catch(SQLException ex){
            Logger.getLogger(ModuleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Module> findAll() {
        List<Module> modules = new ArrayList<>();
        try {
            String req = "SELECT * FROM module";
            PreparedStatement ps = Connexion.getInstance().getConn().prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                modules.add(new Module(rs.getInt("id"), rs.getString("nom"), rs.getInt("coefficient")));
            }
        } catch(SQLException ex){
            Logger.getLogger(ModuleService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return modules;
    }

    @Override
    public boolean insert(Module o) {
        try {
            String req = "INSERT INTO module(nom, coefficient) VALUES(?,?)";
            PreparedStatement ps = Connexion.getInstance().getConn().prepareStatement(req);
            ps.setString(1, o.getNom());
            ps.setInt(2, o.getCoefficient());
            ps.executeUpdate();
            return true;
        } catch(SQLException ex){
            Logger.getLogger(ModuleService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean update(Module o) {
        try {
            String req = "UPDATE module SET nom=?, coefficient=? WHERE id=?";
            PreparedStatement ps = Connexion.getInstance().getConn().prepareStatement(req);
            ps.setString(1, o.getNom());
            ps.setInt(2, o.getCoefficient());
            ps.setInt(3, o.getId());
            ps.executeUpdate();
            return true;
        } catch(SQLException ex){
            Logger.getLogger(ModuleService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean delete(Module o) {
    try {
        String req = "DELETE FROM module WHERE id=?";
        PreparedStatement ps = Connexion.getInstance().getConn().prepareStatement(req);
        ps.setInt(1, o.getId());
        int rows = ps.executeUpdate(); 
        return rows > 0; 
    } catch(SQLException ex){
        Logger.getLogger(ModuleService.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    }
}
}

