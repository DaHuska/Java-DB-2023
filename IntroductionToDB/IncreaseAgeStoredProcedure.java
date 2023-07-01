package IntroductionToDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static IntroductionToDB.GetVillainsNames.getConnection;
import static IntroductionToDB.IncreaseMinionsAge.MINIONS_PRINT_FORMAT;

public class IncreaseAgeStoredProcedure {

    private static final String QUERY_CALL_PROCEDURE_INCREASE_MINION_AGE = "CALL usp_get_older(?)";
    private static final String QUERY_GET_MINION_AGE_AND_NAME = "SELECT `name`, `age` FROM minions WHERE `id` = ?";

    public static void main(String[] args) throws SQLException {
        final Connection connection = getConnection();

        final Scanner scan = new Scanner(System.in);

        int minionID = Integer.parseInt(scan.nextLine());

        // Update minion's age by ID
        updateMinionAge(connection, minionID);

        // Print minion's name and age
        printMinionInfo(connection, minionID);

    }

    private static void printMinionInfo(Connection connection, int minionID) throws SQLException {
        final PreparedStatement statement = connection.prepareStatement(QUERY_GET_MINION_AGE_AND_NAME);
        statement.setInt(1, minionID);

        final ResultSet result = statement.executeQuery();
        result.next();

        System.out.printf(MINIONS_PRINT_FORMAT, result.getString("name"), result.getInt("age"));
    }

    public static void updateMinionAge(Connection connection, int minionID) throws SQLException {
        final PreparedStatement statement = connection.prepareStatement(QUERY_CALL_PROCEDURE_INCREASE_MINION_AGE);
        statement.setInt(1, minionID);

        statement.executeUpdate();
    }
}
