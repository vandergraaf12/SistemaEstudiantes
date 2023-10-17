package himt.datos;

import himt.dominio.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import static himt.conexion.Conexion.getConexion;

// DAO - Data Access Object
public class EstudianteDAO {
    public List<Estudiante> listarEstudiantes(){
        List<Estudiante> estudiantes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = getConexion();
        String sql = "SELECT * FROM  estudiante ORDER BY id_estudiante";
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                var estudiante = new Estudiante();
                estudiante.setIdEstudiante(rs.getInt("id_estudiante"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiante.setEmail(rs.getString("email"));
                estudiantes.add(estudiante);
            }
        }catch (Exception e){
            System.out.println("Ocurrio un error al seleccionar datos: " + e.getMessage());
        }
        finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Ocurrio un error al cerrar conexión: " + e.getMessage());
            }
        }
        return estudiantes;
    }

    public static void main(String[] args) {
        var estudoanteDao = new EstudianteDAO();
        // Listar estudiantes
        System.out.println("Listado estudiantes: ");
        List<Estudiante> estudiantes = estudoanteDao.listarEstudiantes();
        estudiantes.forEach(System.out::println);
    }
}
