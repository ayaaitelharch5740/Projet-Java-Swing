package Service;

import util.Connexion;
import dao.IDao;
import entities.Etudiant;
import entities.Module;
import entities.Note;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NoteService implements IDao<Note> {

    private EtudiantService Es;
    private ModuleService Ms;

    public NoteService() {
        Es = new EtudiantService();
        Ms = new ModuleService();
    }

    @Override
    public Note findById(int id) {
        try {
            String req = "SELECT * FROM note WHERE id=?";
            PreparedStatement ps = Connexion.getInstance().getConn().prepareStatement(req);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Note(
                    rs.getInt("id"),
                    Es.findById(rs.getInt("id_etudiant")),
                    Ms.findById(rs.getInt("id_module")),
                    rs.getString("session"),
                    rs.getDouble("valeur")
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(NoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Note> findAll() {
        List<Note> notes = new ArrayList<>();
        try {
            String req = "SELECT * FROM note";
            PreparedStatement ps = Connexion.getInstance().getConn().prepareStatement(req);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                notes.add(
                    new Note(
                        rs.getInt("id"),
                        Es.findById(rs.getInt("id_etudiant")),
                        Ms.findById(rs.getInt("id_module")),
                        rs.getString("session"),
                        rs.getDouble("valeur")
                    )
                );
            }

        } catch (SQLException ex) {
            Logger.getLogger(NoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return notes;
    }

    @Override
    public boolean insert(Note o) {
        try {
            String req = "INSERT INTO note(id_etudiant, id_module, session, valeur) VALUES(?,?,?,?)";
            PreparedStatement ps = Connexion.getInstance().getConn().prepareStatement(req);

            ps.setInt(1, o.getEtudiant().getId());
            ps.setInt(2, o.getModule().getId());
            ps.setString(3, o.getSession());
            ps.setDouble(4, o.getValeur());

            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(NoteService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean update(Note o) {
        try {
            String req = "UPDATE note SET id_etudiant=?, id_module=?, session=?, valeur=? WHERE id=?";
            PreparedStatement ps = Connexion.getInstance().getConn().prepareStatement(req);

            ps.setInt(1, o.getEtudiant().getId());
            ps.setInt(2, o.getModule().getId());
            ps.setString(3, o.getSession());
            ps.setDouble(4, o.getValeur());
            ps.setInt(5, o.getId());

            ps.executeUpdate();
            return true;

        } catch (SQLException ex) {
            Logger.getLogger(NoteService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean delete(Note o) {
        try {
            String req = "DELETE FROM note WHERE id=?";
            PreparedStatement ps = Connexion.getInstance().getConn().prepareStatement(req);

            ps.setInt(1, o.getId());
            ps.executeUpdate();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(NoteService.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    // ------------------------------------------
    // CALCULER MOYENNE D'UN ETUDIANT
    // ------------------------------------------
    public double CalculerMoyenne(Etudiant etudiant) {
        double moyenne = 0;
        int id = etudiant.getId();

        try {
            String req = "SELECT AVG(valeur) AS moyenne FROM note WHERE id_etudiant = ?";
            PreparedStatement ps = Connexion.getInstance().getConn().prepareStatement(req);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                moyenne = rs.getDouble("moyenne");
            }

        } catch (SQLException e) {
            Logger.getLogger(EtudiantService.class.getName()).log(Level.SEVERE, null, e);
        }

        return moyenne;
    }

    // ------------------------------------------
    // RESULTATS PAR SESSION
    // ------------------------------------------
    public List<Note> ResultatSession(String session) {
        List<Note> notes = new ArrayList<>();

        try {
            String req = "SELECT * FROM note WHERE session = ?";
            PreparedStatement ps = Connexion.getInstance().getConn().prepareStatement(req);
            ps.setString(1, session);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Note n = new Note(
                    rs.getInt("id"),
                    Es.findById(rs.getInt("id_etudiant")),
                    Ms.findById(rs.getInt("id_module")),
                    rs.getString("session"),
                    rs.getDouble("valeur")
                );
                notes.add(n);
            }

        } catch (SQLException ex) {
            Logger.getLogger(NoteService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return notes;
    }
    
    public List<Object[]> getMoyenneParModule() {
    List<Object[]> list = new ArrayList<>();

    String req = "SELECT m.nom AS module, AVG(n.valeur) AS moyenne "
               + "FROM note n "
               + "JOIN module m ON m.id = n.id_module "
               + "GROUP BY m.nom";

    try {
        PreparedStatement ps = Connexion.getInstance().getConn().prepareStatement(req);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            list.add(new Object[]{
                rs.getString("module"),
                rs.getDouble("moyenne")
            });
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return list;
}


}
