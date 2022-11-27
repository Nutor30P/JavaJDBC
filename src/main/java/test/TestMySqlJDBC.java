
package test;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestMySqlJDBC {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/test?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(url, "root","ADMIN");
            Statement instruccion = conexion.createStatement();
            var sql = "SELECT * FROM PERSONA";
            ResultSet resultado = instruccion.executeQuery(sql);
            while(resultado.next()){
                System.out.print("Id Persona: " + resultado.getInt("idpersona"));
                System.out.print(" nombre: " + resultado.getString("personacol"));
                System.out.print(" apellido: " + resultado.getString("apellido"));
                System.out.println(" telefono: " + resultado.getString("telefono"));
                System.out.println("");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TestMySqlJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TestMySqlJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
}
