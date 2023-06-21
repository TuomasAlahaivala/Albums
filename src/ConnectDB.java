import com.sun.security.jgss.GSSUtil;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {
    public static void main(String[] args) {

        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres","admin");

            if (connection != null) {
                System.out.println("Connection is OK");
            } else {
                System.out.println("Connection not OK");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
