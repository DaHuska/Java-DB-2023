package Exercises;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class IncreaseSalaries {

    private static final String PRINT_EMPLOYEE_INFO = "%s %s ($%.2f)%n";

    public static void main(String[] args) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni_db");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        List<Employee> employees = entityManager
                .createQuery("FROM Employee WHERE department.name IN ('Engineering', 'Tool Design', 'Marketing', 'Information Services')", Employee.class)
                .getResultList();

        for (Employee employee : employees) {
            updateSalary(employee);

            System.out.printf(PRINT_EMPLOYEE_INFO, employee.getFirstName(), employee.getLastName(), employee.getSalary());
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    private static void updateSalary(Employee employee) {
        final BigDecimal newSalary = employee.getSalary().add(employee.getSalary().multiply(BigDecimal.valueOf(0.12)));

        employee.setSalary(newSalary);
    }
}
