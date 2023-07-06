package Exercises;

import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class AddressesWithEmployeesCount {

    private static final String PRINT_INFORMATION = "%s, %s - %d employees%n";

    public static void main(String[] args) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni_db");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<Address> addresses = entityManager
                .createQuery("FROM Address ORDER BY employees.size DESC", Address.class)
                .setMaxResults(10)
                .getResultList();

        for (Address address : addresses) {
            System.out.printf(PRINT_INFORMATION, address.getText(), address.getTown().getName(), address.getEmployees().size());
        }

        entityManager.close();
    }
}
