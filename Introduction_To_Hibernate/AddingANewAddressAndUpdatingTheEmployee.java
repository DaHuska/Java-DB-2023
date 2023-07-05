package Exercises;

import entities.Address;
import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;
import java.util.Set;

public class AddingANewAddressAndUpdatingTheEmployee {
    public static void main(String[] args) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni_db");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();

        final Scanner scan = new Scanner(System.in);

        final String lastNameInput = scan.nextLine();

        final Set<Employee> employees = Set.copyOf(entityManager
                .createQuery("FROM Employee WHERE lastName = :lastName", Employee.class)
                .setParameter("lastName", lastNameInput)
                .getResultList());

        final Town town = entityManager
                .createQuery("FROM Town WHERE id = 7", Town.class)
                .getSingleResult();

        Address address = new Address();
        address.setText("Vitoshka 15");
        address.setTown(town);

        entityManager.persist(address);

        for (Employee employee : employees) {
            employee.setAddress(address);
        }

        entityManager.flush();
        entityManager.getTransaction().commit();
        entityManager.close();
        scan.close();
    }
}