package IntroductionToDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static IntroductionToDB.GetVillainsNames.getConnection;

public class AddMinion {

    private static final String QUERY_GET_TOWN_ID = "SELECT `id` FROM towns WHERE `name` = ?";
    private static final String QUERY_GET_VILLAIN_NAME = "SELECT `name` FROM villains WHERE name = ?";
    private static final String QUERY_GET_VILLAIN_ID = "SELECT `id` FROM villains WHERE name = ?";
    private static final String QUERY_GET_MINION_ID = "SELECT `id` FROM minions ORDER BY `id` DESC LIMIT 1";

    private static final String QUERY_ADD_TOWN_TO_DATABASE = "INSERT INTO towns(`name`) VALUES (?)";
    private static final String QUERY_ADD_MINION_TO_DATABASE = "INSERT INTO minions(`name`, `age`) VALUES (?, ?)";
    private static final String QUERY_ADD_VILLAIN_TO_DATABASE = "INSERT INTO villains(`name`, `evilness_factor`) VALUES (?, 'evil')";
    private static final String QUERY_ADD_VILLAIN_AND_MINION_ID_TO_MAPPING_TABLE = "INSERT INTO minions_villains(`minion_id`, `villain_id`) VALUES(?, ?)";

    private static final String MESSAGE_TOWN_ADDED_TO_DATABASE = "Town %s was added to the database.%n";
    private static final String MESSAGE_VILLAIN_ADDED_TO_DATABASE = "Villain %s was added to the database.%n";
    private static final String MESSAGE_CONNECT_MINION_AND_VILLAIN = "Successfully added %s to be minion of %s.%n";

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {

        System.out.print("Minion: ");
        String[] input = scan.nextLine().split("\\s+");
        final String minionName = input[0];
        final int minionAge = Integer.parseInt(input[1]);
        final String townName = input[2];

        System.out.print("Villain: ");
        final String villainName = scan.nextLine();

        final Connection connection = getConnection();

        // Execute get_town query
        final PreparedStatement statementGetTownID = connection.prepareStatement(QUERY_GET_TOWN_ID);
        statementGetTownID.setString(1, townName);

        // If town exists don't do anything otherwise add it to the DB
        ResultSet result = statementGetTownID.executeQuery();
        boolean hasRow = result.next();

        if (!hasRow) {
            addTownToDB(connection, townName);

            System.out.printf(MESSAGE_TOWN_ADDED_TO_DATABASE, townName);
        }

        // Add minion to database
        addMinionToDB(connection, minionName, minionAge);

        // If villain doesn't exist add it to the database otherwise don't do anything
        final PreparedStatement statementGetVillain = connection.prepareStatement(QUERY_GET_VILLAIN_NAME);
        statementGetVillain.setString(1, villainName);

        result = statementGetVillain.executeQuery();
        hasRow = result.next();

        if (!hasRow) {
            // Add villain
            addVillainToDB(connection, villainName);

            System.out.printf(MESSAGE_VILLAIN_ADDED_TO_DATABASE, villainName);
        }

        // Get villain and minion id
        int villainID = getVillainID(connection, villainName);
        int minionID = getMinionID(connection);

        // Add minion and villain to mapping table
        addVillainAndMinionToMapTable(connection, minionID, villainID);
        System.out.printf(MESSAGE_CONNECT_MINION_AND_VILLAIN, minionName, villainName);

        scan.close();
        connection.close();
    }

    public static void addTownToDB(Connection connection, String name) throws SQLException {
        final PreparedStatement statementInsertTown = connection.prepareStatement(QUERY_ADD_TOWN_TO_DATABASE);
        statementInsertTown.setString(1, name);

        statementInsertTown.executeUpdate();
    }

    public static void addMinionToDB(Connection connection, String name, int age) throws SQLException {
        final PreparedStatement statementInsertMinion = connection.prepareStatement(QUERY_ADD_MINION_TO_DATABASE);
        statementInsertMinion.setString(1, name);
        statementInsertMinion.setInt(2, age);

        statementInsertMinion.executeUpdate();
    }

    public static void addVillainToDB(Connection connection, String name) throws SQLException {
        final PreparedStatement statementAddVillain = connection.prepareStatement(QUERY_ADD_VILLAIN_TO_DATABASE);
        statementAddVillain.setString(1, name);

        statementAddVillain.executeUpdate();
    }

    public static int getVillainID(Connection connection, String name) throws SQLException {
        final PreparedStatement statementGetID = connection.prepareStatement(QUERY_GET_VILLAIN_ID);
        statementGetID.setString(1, name);

        final ResultSet result = statementGetID.executeQuery();
        result.next();

        return result.getInt("id");
    }

    public static int getMinionID(Connection connection) throws SQLException {
        final PreparedStatement statement = connection.prepareStatement(QUERY_GET_MINION_ID);

        final ResultSet result = statement.executeQuery();
        result.next();

        return result.getInt("id");
    }

    public static void addVillainAndMinionToMapTable(Connection connection, int minionID, int villainID) throws SQLException {
        final PreparedStatement statementAddIDs = connection.prepareStatement(QUERY_ADD_VILLAIN_AND_MINION_ID_TO_MAPPING_TABLE);
        statementAddIDs.setInt(1, minionID);
        statementAddIDs.setInt(2, villainID);

        statementAddIDs.executeUpdate();
    }
}
