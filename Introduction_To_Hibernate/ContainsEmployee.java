package Exercises;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class ContainsEmployee {
    public static void main(String[] args) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni_db");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        final Scanner scan = new Scanner(System.in);
        final String employeeToCheck = scan.nextLine();

        final List<Employee> employees = entityManager.createQuery("FROM Employee", Employee.class).getResultList();
        boolean doesEmployeeExist = false;

        for (Employee employee : employees) {
            StringBuilder employeeFullName = new StringBuilder();

            employeeFullName.append(employee.getFirstName()).append(" ").append(employee.getLastName());

            if (employeeFullName.toString().equals(employeeToCheck)) {
                doesEmployeeExist = true;
                break;
            }
        }

        if (doesEmployeeExist) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        entityManager.close();
        scan.close();
    }
}
