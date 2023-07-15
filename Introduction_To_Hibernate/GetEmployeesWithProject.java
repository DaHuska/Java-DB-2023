package Exercises;

import entities.Employee;
import jdk.jfr.Percentage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class GetEmployeesWithProject {

    private static final String PRINT_EMPLOYEE_NAME_AND_JOB_TITLE = "%s %s - %s%n";
    private static final String PRINT_PROJECT_NAME = "      %s%n";

    public static void main(String[] args) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni_db");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        final Scanner scan = new Scanner(System.in);
        final int employeeID = Integer.parseInt(scan.nextLine());

        Employee employee = entityManager
                .createQuery("FROM Employee WHERE employee_id = :employeeID", Employee.class)
                .setParameter("employeeID", employeeID)
                .getSingleResult();

        System.out.printf(PRINT_EMPLOYEE_NAME_AND_JOB_TITLE, employee.getFirstName(), employee.getLastName(), employee.getJobTitle());
        employee.getProjects()
                .stream()
                .sorted((a, b) -> a.getName().compareTo(b.getName()))
                .forEach(p -> System.out.printf(PRINT_PROJECT_NAME, p.getName()));

        entityManager.close();
        scan.close();
    }
}
