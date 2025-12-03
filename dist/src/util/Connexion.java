/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author ORIGINAL SHOP
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Connexion {
    
    private static Connexion instance ;
    public static Object getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private final Connection conn ;
    
    private static final String URL ="jdbc:mysql://localhost:3306/db?serverTimezone=UTC";
    private static final String USER = "root";   
    private static final String PASS= "";

    public Connexion() throws SQLException { 
        try {
            // Chargement explicite du driver MySQL
            Class.forName("com.mysql.cj.jdbc.Driver"); // charge le driver MySQL dans classpath
        } catch (ClassNotFoundException e) {
            throw new SQLException(" Driver MySQL non trouvé dans le classpath", e);
        }

        // Connexion à la base
        conn = DriverManager.getConnection(URL, USER, PASS); // connection vers la base
        conn.setAutoCommit(true); // active auto-commit (INSERT/UPDATE/DELETE valide auto )
        System.out.println(" Connexion établie avec succès à la base de données.");
    }
    //Acces au Singleton
    public static synchronized Connexion getInstance() throws SQLException {   //Synchronized (évite que deux threads créent 2 instances simultanément)
        if(instance == null || instance.conn.isClosed()){
            instance = new Connexion();
            
        }
        return instance ;
        
        
    }
    
    //Getter pour la connection
    public Connection getConn() {
        return conn;
    }

    
     public static void main(String[] args) {
        getConnection();
    }    

    public void setVisible(boolean b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    
}
