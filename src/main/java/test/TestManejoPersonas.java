package test;

import datos.Conexion;
import datos.PersonaDao;
import datos.PersonaDaoJDBC;
import domain.PersonaDTO;
import java.sql.*;
import java.sql.SQLException;
import java.util.List;

public class TestManejoPersonas {

    public static void main(String[] args) throws SQLException {

        Connection conexion = Conexion.getConnection();
        try {
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);
            }

            PersonaDao personaDao = new PersonaDaoJDBC(conexion);
            
            List<PersonaDTO> personas = personaDao.select();
            
            for(PersonaDTO persona: personas) {
                System.out.println("Persona DTO:" + persona);
            }
            
            conexion.commit();
        } catch (Exception e) 
        {
            e.printStackTrace(System.out);
            System.out.println("Entramos al rolback");
            conexion.rollback();
        } 

    }
}
