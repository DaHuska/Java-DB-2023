package Exercises;

import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EmployeesWithASalaryOver50000 {
    public static void main(String[] args) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni_db");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        final List<Employee> employees = entityManager.createQuery("FROM Employee", Employee.class).getResultList();
        List<Employee> employeesWithOver50000 = new ArrayList<>();

        for (Employee employee : employees) {
            final String firstName = employee.getFirstName();
            final BigDecimal salary = employee.getSalary();

            if (salary.doubleValue() > 50000) {
                employeesWithOver50000.add(employee);
                System.out.println(firstName);
            }
        }

        entityManager.close();
    }
}
