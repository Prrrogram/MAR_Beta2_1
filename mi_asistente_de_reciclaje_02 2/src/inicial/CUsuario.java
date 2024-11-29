package inicial;

import java.awt.HeadlessException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.CallableStatement;



import javax.swing.JPasswordField;
import javax.swing.JTable;

/**
 *
 * @author danielortanapoleon
 */
public class CUsuario {
    
        int codigo;
    String nombreAlumnos;
    String apellidosAlumnos;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombreAlumnos() {
        return nombreAlumnos;
    }

    public void setNombreAlumnos(String nombreAlumnos) {
        this.nombreAlumnos = nombreAlumnos;
    }

    public String getApellidosAlumnos() {
        return apellidosAlumnos;
    }

    public void setApellidosAlumnos(String apellidosAlumnos) {
        this.apellidosAlumnos = apellidosAlumnos;
    }
    
    
    
    
    public void RegistrarUsuario(JTextField paramNombres, JPasswordField paramApellidos){
        
        String contras = String.valueOf(paramApellidos.getPassword());
        
        setNombreAlumnos(paramNombres.getText());
        setApellidosAlumnos(contras);
        
        CConexion objetoConexion = new CConexion();
        
        String consulta =("Insert into Administradores (ingresoUsuario,ingresoContrasenia) values (?,?);");
        
        try {
            
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            cs.setString(1, getNombreAlumnos());
            cs.setString(2, contras);
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se creo el usuario, inicia sesion.");
            FormMenuAdmin objetoFormulario = new FormMenuAdmin();
            objetoFormulario.setVisible(true);

            
            
            
        }catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, "No se inserto correctamente el alumno, error: "+ ex.toString());
            
            FormCrear objetoCrear = new FormCrear();
            objetoCrear.setVisible(true);
        }

    }
    
    public void SeleccionarUsuario(JTable paramTablaAlumnos, JTextField paramId, JTextField paramNombres, JPasswordField paramApellidos ){
        
         String contras = String.valueOf(paramApellidos.getPassword());
        
        setNombreAlumnos(paramNombres.getText());
        setApellidosAlumnos(contras);
        
        try{
            
            int fila = paramTablaAlumnos.getSelectedRow();
            
            if (fila>=0){
                paramId.setText ((String) (paramTablaAlumnos.getValueAt(fila, 0)));
                paramNombres.setText ((String) (paramTablaAlumnos.getValueAt(fila, 0)));
                paramApellidos.setText ((String) (paramTablaAlumnos.getValueAt(fila, 0)));
            }
            else{
                JOptionPane.showMessageDialog(null, "No se pudieron seleccionar los datos");
            }
            
        }catch (HeadlessException ex){
            JOptionPane.showMessageDialog(null, "No se pudieron seleccionar los datos"+ex.toString());
        }
        
    }
    
    
    
    
}