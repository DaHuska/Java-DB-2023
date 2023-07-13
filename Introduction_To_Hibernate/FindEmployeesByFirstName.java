package Exercises;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class FindEmployeesByFirstName {

    private static final String PRINT_EMPLOYEE_INFO = "%s %s - %s - ($%.2f)%n";

    public static void main(String[] args) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni_db");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        final Scanner scan = new Scanner(System.in);
        final String pattern = scan.nextLine();

        final List<Employee> employees = entityManager
                .createQuery("FROM Employee WHERE first_name LIKE :pattern", Employee.class)
                .setParameter("pattern", pattern + "%")
                .getResultList();

        employees
                .forEach(e -> System.out.printf(PRINT_EMPLOYEE_INFO, e.getFirstName(), e.getLastName(), e.getJobTitle(), e.getSalary()));

        entityManager.close();
        scan.close();
    }
}
