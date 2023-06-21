package BackEnd;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddAlbumService {
    private JTextField artistField;
    private JTextField nameField;
    private JTextField yearField;
    public AddAlbumService(
            JTextField artistField,
            JTextField nameField,
            JTextField yearField
    ) {
        this.artistField = artistField;
        this.nameField = nameField;
        this.yearField = yearField;
    }

    public void addAlbum(
            ActionListener parent
    ) throws SQLException {
        String artist = artistField.getText();
        String name  = nameField.getText();

        if (yearField.getText().isEmpty()) {
            JOptionPane.showMessageDialog((Component) parent, "Year is Empty", "Try Again", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Integer year = Integer.parseInt(yearField.getText());

        if (artist.isEmpty()) {
            JOptionPane.showMessageDialog((Component) parent, "Artist is Empty", "Try Again", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog((Component) parent, "Album name is Empty", "Try Again", JOptionPane.ERROR_MESSAGE);
            return;
        }

        album = addAlbumToDataBase(artist, name, year);
        if (album != null) {
            cancel();
        }
    }

    public Album album;
    private Album addAlbumToDataBase(String artist, String name, Integer year) throws SQLException {
        Album album = null;

        try {
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/albums_db", "postgres", "admin");
            Statement stmt = connection.createStatement();
            String query = "INSERT INTO album (artist, name, year) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, artist);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, year);

            int addRows = preparedStatement.executeUpdate();
            if (addRows > 0) {
                album = new Album(artist, name, year);
            }
            stmt.close();
            connection.close();

            if (album != null) {
                System.out.println("Album added: " + album.getAllData());
            } else {
                System.out.println("Fail!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return album;
    }

    public void cancel() {
        artistField.setText(null);
        nameField.setText(null);
        yearField.setText(null);
    }
}
