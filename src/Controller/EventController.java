package Controller;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFrame;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EventController {
    public static boolean areFieldsFilled(String... fields) {
        for (String field : fields) {
            if (field == null || field.trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        try {
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static boolean isNumeric(String str) {
        if (str == null || str.trim().isEmpty()) return false;
        try {
            Integer.parseInt(str.trim());
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void handleCreate(JFrame parent, JTextField... fields) {
        if (!areFieldsFilled(
                fields[0].getText(),
                fields[1].getText(),
                fields[2].getText(),
                fields[3].getText(),
                fields[4].getText())) {
            JOptionPane.showMessageDialog(parent, "Please insert the value");
        } else if (!isValidDate(fields[2].getText())) {
            JOptionPane.showMessageDialog(parent, "Please enter a valid date (YYYY-MM-DD)");
        } else if (!isNumeric(fields[4].getText())) {
            JOptionPane.showMessageDialog(parent, "Please enter a valid floor number");
        } else {
            JOptionPane.showMessageDialog(parent, "Reservation is Created succefully");
        }
    }

    public static void handleUpdate(JFrame parent, JTextField... fields) {
        if (!areFieldsFilled(
                fields[0].getText(),
                fields[1].getText(),
                fields[2].getText(),
                fields[3].getText(),
                fields[4].getText())) {
            JOptionPane.showMessageDialog(parent, "Please insert the value");
        } else if (!isValidDate(fields[2].getText())) {
            JOptionPane.showMessageDialog(parent, "Please enter a valid date (YYYY-MM-DD)");
        } else if (!isNumeric(fields[4].getText())) {
            JOptionPane.showMessageDialog(parent, "Please enter a valid floor number");
        } else {
            JOptionPane.showMessageDialog(parent, "Reservation is Update successfully");
        }
    }

    public static void handleCancel(JTextField... fields) {
        for (JTextField field : fields) {
            field.setText("");
        }
    }
} 