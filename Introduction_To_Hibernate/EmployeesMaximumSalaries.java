package Exercises;

import entities.Department;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeesMaximumSalaries {
    public static void main(String[] args) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni_db");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager
                .createQuery("SELECT department.name, MAX(salary) " +
                        "FROM Employee " +
                        "GROUP BY department.name " +
                        "HAVING MAX(salary) NOT BETWEEN 30000 AND 70000", Object[].class)
                .getResultList()
                .forEach(object -> System.out.printf("%s %s%n", object[0], object[1]));
    }
}