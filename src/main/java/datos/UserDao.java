package datos;

import domain.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private static final String SQL_SELECT = "SELECT id_usuario, usuario, password  FROM usuarios";
    private static final String SQL_INSERT = "INSERT INTO usuarios (usuario, password) VALUES (?,?)";
    private static final String SQL_UPDATE = "UPDATE usuarios SET usuario = ?, password = ? WHERE id_usuario = ?";
    private static final String SQL_DELETE = "DELETE FROM usuarios WHERE id_usuario = ?";

    public List<User> select() {

        Connection conn = null;
        ResultSet resultS = null;
        PreparedStatement statement = null;
        User user = null;
        List<User> users = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            statement = conn.prepareStatement(SQL_SELECT);
            resultS = statement.executeQuery();
            while (resultS.next()) {
                int idUsuario = resultS.getInt("id_usuario");
                String username = resultS.getNString("usuario");
                String pass = resultS.getNString("password");

                user = new User(idUsuario, username, pass);

                users.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                Conexion.close(resultS);
                Conexion.close(statement);
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return users;
    }

    public int insert(User user) {

        Connection conn = null;
        PreparedStatement statement = null;
        int registros = 0;
        try {
            conn = Conexion.getConnection();
            statement = conn.prepareStatement(SQL_INSERT);

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());

            registros = statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                Conexion.close(statement);
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }

    public int update(User user) {

        int registros = 0;
        Connection conn = null;
        PreparedStatement statement = null;
        try {
            conn = Conexion.getConnection();
            statement = conn.prepareStatement(SQL_UPDATE);

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getUserId());

            registros = statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                Conexion.close(statement);
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }

        }
        return registros;
    }

    public int delete(User user) {
        Connection conn = null;
        PreparedStatement statement = null;
        int registro = 0;

        try {
            conn = Conexion.getConnection();
            statement = conn.prepareStatement(SQL_DELETE);
            statement.setInt(1, user.getUserId());
            registro = statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                Conexion.close(statement);
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registro;
    }

}
