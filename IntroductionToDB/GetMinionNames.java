package IntroductionToDB;

import java.sql.*;
import java.util.Scanner;

import static IntroductionToDB.GetVillainsNames.getConnection;

public class GetMinionNames {

    private static final String QUERY_GET_VILLAIN_AND_MINION_NAMES = "SELECT \n" +
            "\tv.`name`,\n" +
            "m.`name`,\n" +
            "m.`age`\n" +
            "FROM villains AS v\n" +
            "JOIN minions_villains AS mv ON mv.`villain_id` = v.`id`\n" +
            "JOIN minions AS m ON m.`id` = mv.`minion_id`\n" +
            "WHERE v.`id` = ?";

    public static void main(String[] args) throws SQLException {
        final Connection connection = getConnection();

        int villainID = readVillainID();

        final PreparedStatement statement = connection.prepareStatement(QUERY_GET_VILLAIN_AND_MINION_NAMES);
        statement.setInt(1, villainID);

        final ResultSet result = statement.executeQuery();
        boolean hasRow = result.next();

        if (!hasRow) {
            System.out.printf("No villain with ID %d exists in the database", villainID);

            connection.close();

            return;
        }

        int minionCounter = 1;
        System.out.printf("Villain: %s%n", result.getString(1));
        while (hasRow) {
            System.out.printf("%d. %s %d%n", minionCounter, result.getString(2), result.getInt(3));

            minionCounter++;
            hasRow = result.next();
        }

        connection.close();
    }

    public static int readVillainID() {
        Scanner scan = new Scanner(System.in);

        return Integer.parseInt(scan.nextLine());
    }

}
