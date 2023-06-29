package IntroductionToDB;

import java.sql.*;
import java.util.Properties;

public class GetVillainsNames {

    private static final String GET_VILLAINS_NAMES_AND_MINIONS_COUNT = "SELECT \n" +
            "\tv.`name`,\n" +
            "    COUNT(mv.`minion_id`) AS 'count'\n" +
            "FROM villains AS v\n" +
            "JOIN minions_villains AS mv ON mv.`villain_id` = v.`id`\n" +
            "GROUP BY v.`name`\n" +
            "HAVING `count` > 15\n" +
            "ORDER BY `count` DESC";

    public static void main(String[] args) throws SQLException {
        Connection connection = getConnection();

        PreparedStatement statement = connection.prepareStatement(GET_VILLAINS_NAMES_AND_MINIONS_COUNT);

        ResultSet result = statement.executeQuery();
        boolean hasRow = result.next();

        while (hasRow) {
            System.out.printf("%s %d%n", result.getString(1), result.getInt(2));

            hasRow = result.next();
        }

        connection.close();
    }
    public static Connection getConnection() throws SQLException {
        Properties props = new Properties();

        props.setProperty("user", "root");
        props.setProperty("password", "MNoole$n4");

        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/minions_db", props);
    }
}
