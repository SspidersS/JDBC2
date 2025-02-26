package org.example;

import java.io.*;
import java.sql.*;
import java.util.*;

public class DatabaseQueryService {
    public List<MaxProjectCountClient> findMaxProjectsClient() throws SQLException {
        List<MaxProjectCountClient> maxProjectCountClientList = new ArrayList<>();

        Connection connection = Database.getInstance().getConnection();
        
        String sqlQuery2 = "SELECT MAX(PROJECT_NUMBER) AS PROJECT_NUMBER FROM ( " +
                "  SELECT CLIENT_ID, COUNT(CLIENT_ID) AS PROJECT_NUMBER " +
                "  FROM PUBLIC.project GROUP BY CLIENT_ID " +
                ") t";

        try (PreparedStatement psMaxProjectCount = connection.prepareStatement(sqlQuery2);
             ResultSet rsMaxProjectCount = psMaxProjectCount.executeQuery()) {

            if (rsMaxProjectCount.next()) {
                int maxProjectCount = rsMaxProjectCount.getInt("PROJECT_NUMBER");

                String sqlQuery1 = "WITH Project_Count AS ( " +
                        "  SELECT CLIENT_ID, COUNT(CLIENT_ID) AS PROJECT_NUMBER " +
                        "  FROM PUBLIC.project GROUP BY CLIENT_ID " +
                        ") " +
                        "SELECT c.NAME, pc.PROJECT_NUMBER " +
                        "FROM PUBLIC.client c " +
                        "JOIN Project_Count pc ON c.ID = pc.CLIENT_ID " +
                        "WHERE pc.PROJECT_NUMBER = ?";

                try (PreparedStatement psClient = connection.prepareStatement(sqlQuery1)) {
                    psClient.setInt(1, maxProjectCount);

                    try (ResultSet rsClient = psClient.executeQuery()) {
                        while (rsClient.next()) {
                            String name = rsClient.getString("NAME");
                            Long projectNumber = rsClient.getLong("PROJECT_NUMBER");
                            maxProjectCountClientList.add(new MaxProjectCountClient(name, projectNumber));
                        }
                    }
                }
            }
        }

        return maxProjectCountClientList;
    }



    public List<WorkerSalaryDetails> findWorkerSalaryDetails() throws SQLException {
        List<WorkerSalaryDetails> workerSalaryDetailsList = new ArrayList<>();

        Connection connection = Database.getInstance().getConnection();

        String sqlQuery2 = "SELECT MAX(SALARY) AS SALARY FROM PUBLIC.worker";

        try (PreparedStatement psMaxSalary = connection.prepareStatement(sqlQuery2);
             ResultSet rsMaxSalary = psMaxSalary.executeQuery()) {

            if (rsMaxSalary.next()) {
                int maxSalary = rsMaxSalary.getInt("SALARY");

                String sqlQuery1 = "SELECT NAME, SALARY FROM PUBLIC.worker WHERE SALARY = ?";
                try (PreparedStatement psWorker = connection.prepareStatement(sqlQuery1)) {
                    psWorker.setInt(1, maxSalary);

                    try (ResultSet rsWorker = psWorker.executeQuery()) {
                        while (rsWorker.next()) {
                            String name = rsWorker.getString("NAME");
                            int salary = rsWorker.getInt("SALARY");
                            workerSalaryDetailsList.add(new WorkerSalaryDetails(name, salary));
                        }
                    }
                }
            }
        }

        return workerSalaryDetailsList;
    }
}