package org.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabasePopulateService {

    public static void main(String[] args) {
        String sqlFilePath = "sql/populate_db.sql";

        try (Connection connection = Database.getInstance().getConnection()) {
            String sqlQueries = readSqlFile(sqlFilePath);

            executeSqlQueries(connection, sqlQueries);

            System.out.println("База даних успішно заповнена!");
        } catch (SQLException | IOException e) {
            System.err.println("Помилка при виконанні SQL запитів: " + e.getMessage());
        }
    }

    private static String readSqlFile(String filePath) throws IOException {
        StringBuilder sqlQueries = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.replaceAll("\\\\000a", "");
                sqlQueries.append(line.trim()).append(" ");
            }
        }
        return sqlQueries.toString();
    }

    private static void executeSqlQueries(Connection connection, String sqlQueries) throws SQLException {
        String[] queries = sqlQueries.split(";");
        try (Statement statement = connection.createStatement()) {
            for (String query : queries) {
                if (!query.trim().isEmpty()) {
                    statement.executeUpdate(query.trim());
                }
            }
        }
    }
}
