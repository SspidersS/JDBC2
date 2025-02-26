package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DbInitService {
    public static void main(String[] args) {
        String sqlFilePath = "sql/init_db.sql";

        try {

            String sqlQueries = new String(Files.readAllBytes(Paths.get(sqlFilePath)));

            Connection conn = Database.getInstance().getConnection();

            Statement stmt = conn.createStatement();

            stmt.executeUpdate(sqlQueries);
            System.out.println("База даних успішно ініціалізована.");


            stmt.close();
            Database.getInstance().closeConnection();

        } catch (IOException e) {
            System.err.println("Помилка при зчитуванні SQL файлу: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Помилка при виконанні запитів до бази даних: " + e.getMessage());
            e.printStackTrace();
        }
    }
}