package datos;

import domain.PersonaDTO;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PersonaDaoJDBC implements PersonaDao {
    
    private Connection conexionTransaccional;
    
    private static final String SQL_SELECT = "SELECT * FROM PERSONA";
    private static final String SQL_INSERT = "INSERT INTO PERSONA(personacol, apellido, email, telefono) VALUES(?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE persona SET personacol = ?, apellido = ?, email = ?, telefono = ? WHERE idpersona = ?";
    private static final String SQL_DELETE = "DELETE FROM persona WHERE idpersona = ?";
    
    public PersonaDaoJDBC(){
        
    }
    
    public PersonaDaoJDBC(Connection conexionTransaccioanl){
        this.conexionTransaccional = conexionTransaccioanl;
    }
    
    
    public List<PersonaDTO> select() throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PersonaDTO persona = null;
        List<PersonaDTO> personas = new ArrayList<>();
        
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional: Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){
                int idpersona = rs.getInt("idpersona");
                String nombre = rs.getString("personacol");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                
                persona = new PersonaDTO(idpersona,nombre,apellido,email,telefono);
                
                personas.add(persona);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            Conexion.close(rs);
            Conexion.close(stmt);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }
        }
        
        return personas;
    }
    
    public int insert(PersonaDTO persona) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional: Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PersonaDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            
            Conexion.close(stmt);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }
        }
        return registros;
    }
    
    public int update(PersonaDTO persona) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional: Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getApellido());
            stmt.setString(3, persona.getEmail());
            stmt.setString(4, persona.getTelefono());
            stmt.setInt(5, persona.getIdpersona());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PersonaDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            
            Conexion.close(stmt);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }
        }
        return registros;
    }
    
    public int delete(PersonaDTO persona) throws SQLException{
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;
        
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional: Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, persona.getIdpersona());
            registros = stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PersonaDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally{
            
            Conexion.close(stmt);
            if (this.conexionTransaccional == null) {
                Conexion.close(conn);
            }
        }
        return registros;
    }
    
}
