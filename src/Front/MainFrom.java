package Front;

import BackEnd.AddAlbumService;
import BackEnd.Albums;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MainFrom extends JDialog{
    private JPanel mainPanel;
    private JPanel ParentPanel;
    private JButton AddButton;
    private JPanel addAlbumspanel;
    private JPanel viewAlbumsPanel;
    private JLabel albums;
    private JTextField artistField;
    private JLabel name_label;
    private JLabel artist_label;
    private JTextField nameField;
    private JTextField yearField;
    private JButton add;
    private JButton cancelButton;
    private JTable albumsTable;
    private JLabel album_label;

    public MainFrom() {
        AddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ParentPanel.removeAll();
                ParentPanel.add(addAlbumspanel);
                ParentPanel.repaint();
                ParentPanel.revalidate();
            }
        });
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    AddAlbumService service = new AddAlbumService(artistField, nameField, yearField);
                    service.addAlbum(this);
                    albumsView();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddAlbumService service = new AddAlbumService(artistField, nameField, yearField);
                service.cancel();
                albumsView();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainFrom");
        frame.setContentPane(new MainFrom().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void albumsView() {
        Albums albums1 = new Albums();
        JTable albums = null;
        int rowCount = 0;
        try {
            albums = albums1.getAlbums();
            rowCount = albums.getRowCount();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        albums.setBounds(50,25,700,rowCount * 17);
        albums.getTableHeader().setBounds(50,0,700,25);
        this.albumsTable.add(albums);
        albumsTable.add(albums.getTableHeader());
        ParentPanel.removeAll();
        ParentPanel.add(viewAlbumsPanel);
        ParentPanel.repaint();
        ParentPanel.revalidate();
    }
}
