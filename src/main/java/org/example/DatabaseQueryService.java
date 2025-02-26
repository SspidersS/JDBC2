package org.example;
import java.io.*;
import java.sql.*;
import java.util.*;

public class DatabaseQueryService {

    private String readSqlFile(String filePath) {
        StringBuilder sqlQuery = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                sqlQuery.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sqlQuery.toString();
    }

    private List<Map<String, Object>> executeQuery(String sqlQuery) {
        List<Map<String, Object>> result = new ArrayList<>();
        try (Connection connection = Database.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sqlQuery)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object value = resultSet.getObject(i);
                    row.put(columnName, value);
                }
                result.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<MaxProjectCountClient> findMaxProjectsClient() {
        String sqlQuery = readSqlFile("sql/find_max_projects_client.sql");
        List<Map<String, Object>> results = executeQuery(sqlQuery);

        List<MaxProjectCountClient> maxProjectCountClients = new ArrayList<>();
        for (Map<String, Object> row : results) {
            String name = (String) row.get("NAME");
            Long projectCount = (Long) row.get("PROJECT_NUMBER");
            if (projectCount == null) {
                projectCount = 0L;
            }
            maxProjectCountClients.add(new MaxProjectCountClient(name, projectCount));
        }
        return maxProjectCountClients;
    }

    public List<WorkerSalaryDetails> findWorkerSalaryDetails() {
        String sqlQuery = readSqlFile("sql/find_max_salary_worker.sql");
        List<Map<String, Object>> results = executeQuery(sqlQuery);

        List<WorkerSalaryDetails> workerSalaryDetailsList = new ArrayList<>();
        for (Map<String, Object> row : results) {
            String name = (String) row.get("NAME");
            Integer salary = (Integer) row.get("SALARY");
            if (salary == null) {
                salary = 0;
            }
            workerSalaryDetailsList.add(new WorkerSalaryDetails(name, salary));
        }
        return workerSalaryDetailsList;
    }
}
