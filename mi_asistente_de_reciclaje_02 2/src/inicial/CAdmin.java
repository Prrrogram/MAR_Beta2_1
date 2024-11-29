
package inicial;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import javax.swing.JPasswordField;

/**
 *
 * @author danielortanapoleon
 */
public class CAdmin {
    
    public void validarAdmin (JTextField usuario, JPasswordField contrasenia){
        
        try{
            
            ResultSet rs = null;
            PreparedStatement ps = null;
            
            CConexion objetoConexion = new CConexion();
            
            String consulta = "SELECT * FROM Administradores WHERE Administradores.ingresoUsuario = ? and Administradores.ingresoContrasenia = ? ;"; 
            ps=objetoConexion.estableceConexion().prepareStatement(consulta);
            
            String contra = String.valueOf(contrasenia.getPassword());
            
            ps.setString(1, usuario.getText());
            ps.setString(2, contra);
            
            rs = ps.executeQuery();
            
            if (rs.next()){
                
                JOptionPane.showMessageDialog(null, "El usuario es correcto...");
                
                FormAdmin objetoAdmin = new FormAdmin();
                objetoAdmin.setVisible(true);
                
            }
            else{
                
                JOptionPane.showMessageDialog(null, "El usuario es incorrecto...");
                FormMenuAdmin objetoFormulario = new FormMenuAdmin();
                objetoFormulario.setVisible(true);
                
            }
            
            
        }catch (Exception ex){
            
            JOptionPane.showMessageDialog(null, "Error: "+ ex.toString());
            
        }
        
    }
    
}
