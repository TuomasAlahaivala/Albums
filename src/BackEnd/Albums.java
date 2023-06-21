package BackEnd;

import javax.swing.*;
import java.sql.*;

public class Albums {
    public JTable getAlbums() throws SQLException {

        int count = this.getAlbumsCount();

        ResultSet set = this.getAlbumsFromDB();
        String[] columnNames = {"Artist", "Name", "Year"};

        String[][] data = new String[count][3];

        int value = 0;
        while (set.next()) {
            var q = set.getRow();
            var w = set.getString("artist");
            data[value] = new String[]{set.getString("artist"), set.getString("name"), String.valueOf(set.getInt("year"))};
            value++;
        }

        return new JTable(data, columnNames);
    }

    private ResultSet getAlbumsFromDB() {
        ResultSet rs = null;
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/albums_db", "postgres", "admin");
            Statement stmt = connection.createStatement();
            String query = "SELECT * FROM album";

            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rs;
    }

    private int getAlbumsCount() throws SQLException {
        ResultSet rs = null;
        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/albums_db", "postgres", "admin");
            Statement stmt = connection.createStatement();
            String query = "SELECT COUNT(*) as count_record FROM album";

            rs = stmt.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        rs.next();
        return rs.getInt("count_record");
    }
}
