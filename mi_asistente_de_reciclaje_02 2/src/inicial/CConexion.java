package inicial;

import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class CConexion {
    
    Connection conectar = null;
    
    String usuario = "root";
    String contrasenia = "2208Tugo";
    String bd = "bdescuela";
    String ip = "localhost";
    String puerto = "3306";
    
    
    String cadena = "jdbc:mysql://" + ip + ":" + puerto + "/" + bd;
    
    public Connection estableceConexion(){
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conectar = DriverManager.getConnection(cadena, usuario, contrasenia);
            
        } catch (HeadlessException | ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al conectar con la bas de datos, error: "+ ex.toString());
        }
        return conectar;
    }
    
} 
