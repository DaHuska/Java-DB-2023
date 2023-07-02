package IntroductionToDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

import static IntroductionToDB.GetVillainsNames.getConnection;

public class IncreaseMinionsAge {

    private static final String QUERY_UPDATE_MINION_AGE_AND_NAME = "UPDATE minions SET `age` = `age` + 1, `name` = LOWER(`name`) WHERE `id` = ?";
    private static final String QUERY_GET_MINIONS_INFO = "SELECT name AS 'name', `age` FROM minions";
    static final String MINIONS_PRINT_FORMAT = "%s %d%n";

    public static void main(String[] args) throws SQLException {
        Scanner scan = new Scanner(System.in);

        int[] ids = Arrays.stream(scan.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();

        final Connection connection = getConnection();

        // Update minions age and name
        for (int i = 0; i < ids.length; i++) {
            updateMinionAge(connection, ids, i);
        }

        // Print minions info
        printMinionsInfo(connection);

        scan.close();
        connection.close();
    }

    public static void updateMinionAge(Connection connection, int[] ids, int index) throws SQLException {
        final PreparedStatement statement = connection.prepareStatement(QUERY_UPDATE_MINION_AGE_AND_NAME);
        statement.setInt(1, ids[index]);

        statement.executeUpdate();
    }

    public static void printMinionsInfo(Connection connection) throws SQLException {
        final PreparedStatement statement = connection.prepareStatement(QUERY_GET_MINIONS_INFO);

        final ResultSet result = statement.executeQuery();
        boolean hasRow = result.next();

        while (hasRow) {
            System.out.printf(MINIONS_PRINT_FORMAT, result.getString("name"), result.getInt("age"));

            hasRow = result.next();
        }
    }
}