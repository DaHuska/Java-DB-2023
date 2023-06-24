package IntroductionToDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public enum Utils {
    ;
    public static Connection getConnection() throws SQLException {
        Properties props = new Properties();

        props.setProperty("user", "root");
        props.setProperty("password", "...");

        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/minions_db", props);
    }
}
