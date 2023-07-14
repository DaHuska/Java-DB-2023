package Exercises;

import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class FindTheLatest10Projects {

    private static final String PRINT_PROJECT_INFORMATION = "Project name: %s%n"
            + "Project Description: %s%n"
            + "Project Start Date: %tY-%<tm-%<td %<tH:%<tM:%<tS:%<tL%n"
            + "Project End Date: %tY-%<tm-%<td %<tH:%<tM:%<tS:%<tL%n";

    private static final String PRINT_PROJECT_INFORMATION_WHEN_END_DATE_NULL = "Project name: %s%n"
            + "Project Description: %s%n"
            + "Project Start Date: %tY-%<tm-%<td %<tH:%<tM:%<tS:%<tL%n"
            + "Project End Date: null%n";

    public static void main(String[] args) {
        final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni_db");
        final EntityManager entityManager = entityManagerFactory.createEntityManager();

        List<Project> projects = entityManager
                .createQuery("FROM Project ORDER BY start_date DESC", Project.class)
                .setMaxResults(10)
                .getResultList();

        projects
                .stream()
                .sorted((a, b) -> a.getName().compareTo(b.getName()))
                .forEach(p -> {
                    if (p.getEndDate() == null) {
                        System.out.printf(PRINT_PROJECT_INFORMATION_WHEN_END_DATE_NULL, p.getName(), p.getDescription(), p.getStartDate());
                    } else {
                        System.out.printf(PRINT_PROJECT_INFORMATION, p.getName(), p.getDescription(), p.getStartDate(), p.getEndDate());
                    }
                });

        entityManager.close();
    }
}
