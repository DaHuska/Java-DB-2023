package Exercises;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeesFromDepartment {

    private static final String PRINT_FORMAT = "%s %s from %s - $%.2f%n";

    public static void main(String[] args) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni_db");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        final List<Employee> employees = entityManager
                .createQuery("FROM Employee WHERE department.name = :name ORDER BY salary, id", Employee.class)
                .setParameter("name", "Research and Development")
                .getResultList();

        employees
                .forEach(employee ->
                        System.out.printf(PRINT_FORMAT, employee.getFirstName(), employee.getLastName(), "Research and Development", employee.getSalary()));

        entityManager.close();
    }
}