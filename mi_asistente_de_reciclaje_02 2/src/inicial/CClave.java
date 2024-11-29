
package inicial;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author danielortanapoleon
 */
public class CClave {
    public void validarClave (JPasswordField contrasenia){
        
        try{
            
            ResultSet rs = null;
            PreparedStatement ps = null;
            
            CConexion objetoConexion = new CConexion();
            
            String consulta = "SELECT * FROM Claves WHERE Claves.ingresoContrasenia = ? ;"; 
            ps=objetoConexion.estableceConexion().prepareStatement(consulta);
            
            String contra = String.valueOf(contrasenia.getPassword());
            
            ps.setString(1, contra);
            
            rs = ps.executeQuery();
            
            if (rs.next()){
                
                JOptionPane.showMessageDialog(null, "El usuario es correcto...");
                
                FormCrear objetoCrear = new FormCrear();
                objetoCrear.setVisible(true);
                
            }
            else{
                
                JOptionPane.showMessageDialog(null, "El usuario es incorrecto...");
                FormClave objetoClave = new FormClave();
                objetoClave.setVisible(true);
                
            }
            
            
        }catch (Exception ex){
            
            JOptionPane.showMessageDialog(null, "Error: "+ ex.toString());
            
        }
        
    }
}
