package IntroductionToDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static IntroductionToDB.GetVillainsNames.getConnection;

public class PrintAllMinionNames {

    private static final String QUERY_GET_MINION_NAMES = "SELECT `name` FROM minions";

    public static void main(String[] args) throws SQLException {
        final Connection connection = getConnection();

        List<String> minionNames = new ArrayList<>();

        final PreparedStatement statement = connection.prepareStatement(QUERY_GET_MINION_NAMES);
        final ResultSet resultSet = statement.executeQuery();
        boolean hasRow = resultSet.next();

        while (hasRow) {
            minionNames.add(resultSet.getString("name"));

            hasRow = resultSet.next();
        }

        printMinions(minionNames);

        connection.close();
    }

    public static void printMinions(List<String> minions) {
        int firstToLastIndex = 0;
        int lastToFirstIndex = minions.size() - 1;

        for (int i = 0; i < minions.size(); i++) {
            if (i % 2 == 0) {
                System.out.println(minions.get(firstToLastIndex));

                firstToLastIndex++;
            } else {
                System.out.println(minions.get(lastToFirstIndex));

                lastToFirstIndex--;
            }
        }
    }
}