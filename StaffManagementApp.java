import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StaffManagementApp extends JFrame {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/your_database_name";
    private static final String DB_USER = "your_username";
    private static final String DB_PASSWORD = "your_password";

    private JTextField idField, lastNameField, firstNameField, miField, addressField, cityField, stateField,
            telephoneField, emailField;
    private JButton viewButton, insertButton, updateButton;

    public StaffManagementApp() {
        setTitle("Staff Management");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(11, 2));

        JLabel idLabel = new JLabel("ID:");
        idField = new JTextField();
        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameField = new JTextField();
        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameField = new JTextField();
        JLabel miLabel = new JLabel("MI:");
        miField = new JTextField();
        JLabel addressLabel = new JLabel("Address:");
        addressField = new JTextField();
        JLabel cityLabel = new JLabel("City:");
        cityField = new JTextField();
        JLabel stateLabel = new JLabel("State:");
        stateField = new JTextField();
        JLabel telephoneLabel = new JLabel("Telephone:");
        telephoneField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();

        viewButton = new JButton("View");
        insertButton = new JButton("Insert");
        updateButton = new JButton("Update");

        add(idLabel);
        add(idField);
        add(lastNameLabel);
        add(lastNameField);
        add(firstNameLabel);
        add(firstNameField);
        add(miLabel);
        add(miField);
        add(addressLabel);
        add(addressField);
        add(cityLabel);
        add(cityField);
        add(stateLabel);
        add(stateField);
        add(telephoneLabel);
        add(telephoneField);
        add(emailLabel);
        add(emailField);
        add(viewButton);
        add(insertButton);
        add(updateButton);

        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewStaff();
            }
        });

        insertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                insertStaff();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateStaff();
            }
        });
    }

    private void viewStaff() {
        String id = idField.getText();
        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String sql = "SELECT * FROM Staff WHERE id=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                lastNameField.setText(rs.getString("lastName"));
                firstNameField.setText(rs.getString("firstName"));
                miField.setText(rs.getString("mi"));
                addressField.setText(rs.getString("address"));
                cityField.setText(rs.getString("city"));
                stateField.setText(rs.getString("state"));
                telephoneField.setText(rs.getString("telephone"));
                emailField.setText(rs.getString("email"));
            } else {
                JOptionPane.showMessageDialog(this, "Staff not found for ID: " + id, "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void insertStaff() {
        // Implement the code to insert a new staff record here
    }

    private void updateStaff() {
        // Implement the code to update an existing staff record here
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StaffManagementApp app = new StaffManagementApp();
            app.setVisible(true);
        });
    }
}