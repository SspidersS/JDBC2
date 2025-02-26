package org.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        DatabaseQueryService service = new DatabaseQueryService();

        List<MaxProjectCountClient> maxProjectCountClients = service.findMaxProjectsClient();
        for (MaxProjectCountClient client : maxProjectCountClients) {
            System.out.println(client.getName() + " has " + client.getProjectCount() + " projects.");
        }

        List<WorkerSalaryDetails> workerSalaryDetails = service.findWorkerSalaryDetails();
        for (WorkerSalaryDetails worker : workerSalaryDetails) {
            System.out.println(worker.getName() + " earns " + worker.getSalary() + " salary.");
        }
}
}
