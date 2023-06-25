package IntroductionToDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static IntroductionToDB.GetVillainsNames.getConnection;

public class ChangeTownNamesCasing {

    private static final String QUERY_GET_COUNTRY_TOWNS_UPPER_CASE = "SELECT UPPER(`name`) AS 'name' FROM towns WHERE `country` = ?";
    private static final String MESSAGE_AFFECTED_TOWNS_COUNT = "%d town names were affected.%n";
    private static final String MESSAGE_NO_AFFECTED_TOWNS = "No town names were affected.";

    public static void main(String[] args) throws SQLException {
        final Scanner scan = new Scanner(System.in);

        final String country = scan.nextLine();

        List<String> towns = new ArrayList<>();

        final Connection connection = getConnection();

        final PreparedStatement statement = connection.prepareStatement(QUERY_GET_COUNTRY_TOWNS_UPPER_CASE);
        statement.setString(1, country);

        final ResultSet result = statement.executeQuery();
        boolean hasRow = result.next();

        // Check if there are towns in the current country
        // If there are not, print message and close the program
        if (!hasRow) {
            System.out.println(MESSAGE_NO_AFFECTED_TOWNS);

            scan.close();
            connection.close();
            return;
        }

        while (hasRow) {
            towns.add(result.getString("name"));

            hasRow = result.next();
        }

        System.out.printf(MESSAGE_AFFECTED_TOWNS_COUNT, towns.size());
        printTowns(towns);

        scan.close();
        connection.close();
    }

    public static void printTowns(List<String> towns) {
        String result = String.join(", ", towns);
        System.out.print("[");
        System.out.print(result);
        System.out.print("]");
    }
}
