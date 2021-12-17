package datos;

import java.sql.*;

public class Conexion {

    public static final String JDBC_URL = "jdbc:mysql://localhost:3306/pruebas?useSSL=false&serverTimezone=UTC";
    public static final String JDBC_USER = "newuser";
    public static final String JDBC_PASS = "password";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASS);
    }

    public static void close(ResultSet rs) throws SQLException {
        rs.close();
    }

    public static void close(Statement statement) throws SQLException {
        statement.close();
    }

    public static void close(PreparedStatement statement) throws SQLException {
        statement.close();
    }

    public static void close(Connection conn) throws SQLException {
        conn.close();
    }

}
