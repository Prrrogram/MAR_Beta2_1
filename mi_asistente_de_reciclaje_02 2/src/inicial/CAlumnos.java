
package inicial;

import java.awt.HeadlessException;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
        
public class CAlumnos {
    
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
    
    public void InsertarAlumno(JTextField paramNombres, javax.swing.JTextField paramApellidos){
        
        setNombreAlumnos(paramNombres.getText());
        setApellidosAlumnos(paramApellidos.getText());
        
        CConexion objetoConexion = new CConexion();
        
        String consulta =("Insert into Alumnos (nombres,apellidos) values (?,?);");
        
        try {
            
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            
            cs.setString(1, getNombreAlumnos());
            cs.setString(2, getApellidosAlumnos());
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se inserto correctamente");

            
            
            
        }catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, "No se inserto correctamente, error: "+ ex.toString());

        }

    }
    
    public void MostrarAlumnos(JTable paramTablaTotalAlumnos){
        
        CConexion objetoConexion = new CConexion();
        
        DefaultTableModel modelo = new DefaultTableModel();
        
        TableRowSorter<TableModel> OrdenarTabla = new TableRowSorter<TableModel>(modelo);
        paramTablaTotalAlumnos.setRowSorter(OrdenarTabla);
        
        String sql = "";
        
        modelo.addColumn("id");
        modelo.addColumn("Tipo");
        modelo.addColumn("Cantidad");
        
        paramTablaTotalAlumnos.setModel(modelo);
        
        sql = "Select * From Alumnos WHERE id > 0;";
        
        String[] datos = new String[3];
        Statement st;
        
        try{
            
            st = objetoConexion.estableceConexion().createStatement();
            
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()) {
                
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                
                modelo.addRow(datos);
                
            }
            
            paramTablaTotalAlumnos.setModel(modelo);
                    
        }catch (SQLException ex){
            JOptionPane.showMessageDialog(null, "No se pudieron mostrar los registros"+ ex.toString());
        }
        
        
        
    }
    
    public void SeleccionarAlumno(JTable paramTablaAlumnos, JTextField paramId, JTextField paramNombres, javax.swing.JTextField paramApellidos){
        
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
    
    
    public void ModificarAlumnos(JTextField paramCodigo, JTextField paramNombres, javax.swing.JTextField paramApellidos){
        
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        setNombreAlumnos (paramNombres.getText());
        setApellidosAlumnos (paramApellidos.getText());
        
        CConexion objetoConexion = new CConexion();
        
        String consulta = "UPDATE Alumnos SET alumnos.nombres = ?, alumnos.apellidos = ? WHERE alumnos.id = ?;";
        
        try{
            
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            
            cs.setString(1, getNombreAlumnos());
            cs.setString(2, getApellidosAlumnos());
            cs.setInt(3, getCodigo());
            
            cs.execute();
            
            
            JOptionPane.showMessageDialog(null, "Modificacion exitosa");
            
            
            
        }catch (SQLException ex){
            
            JOptionPane.showMessageDialog(null, "Modificacion fallida, error"+ex.toString());
        }
             
    }
    
    public void EliminarAlumnos(JTextField paramCodigo){
        
        setCodigo(Integer.parseInt(paramCodigo.getText()));
        
        CConexion objetoConexion = new CConexion();
        
        String consulta = "DELETE FROM Alumnos WHERE alumnos.id=?";
        
        try{
            
            CallableStatement cs = objetoConexion.estableceConexion().prepareCall(consulta);
            
            cs.setInt(1, getCodigo());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Dato borrado con exito");
            
        }catch (HeadlessException | SQLException ex){
            
            JOptionPane.showMessageDialog(null, "Dato borrado fallido, error: "+ ex.toString());
            
        }
        
    }
    
}
