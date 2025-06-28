//package Controller;
//
//import javax.swing.JOptionPane;
//import javax.swing.JTextField;
//import javax.swing.JFrame;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//
//public class EventController {
//    public static boolean areFieldsFilled(String... fields) {
//        for (String field : fields) {
//            if (field == null || field.trim().isEmpty()) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public static boolean isValidDate(String date) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        sdf.setLenient(false);
//        try {
//            sdf.parse(date);
//            return true;
//        } catch (ParseException e) {
//            return false;
//        }
//    }
//
//    public static boolean isNumeric(String str) {
//        if (str == null || str.trim().isEmpty()) return false;
//        try {
//            Integer.parseInt(str.trim());
//            return true;
//        } catch (NumberFormatException e) {
//            return false;
//        }
//    }
//
//    public static void handleCreate(JFrame parent, JTextField... fields) {
//        if (!areFieldsFilled(
//                fields[0].getText(),
//                fields[1].getText(),
//                fields[2].getText(),
//                fields[3].getText(),
//                fields[4].getText())) {
//            JOptionPane.showMessageDialog(parent, "Please insert the value");
//        } else if (!isValidDate(fields[2].getText())) {
//            JOptionPane.showMessageDialog(parent, "Please enter a valid date (YYYY-MM-DD)");
//        } else if (!isNumeric(fields[4].getText())) {
//            JOptionPane.showMessageDialog(parent, "Please enter a valid floor number");
//        } else {
//            JOptionPane.showMessageDialog(parent, "Reservation is Created succefully");
//        }
//    }
//
//    public static void handleUpdate(JFrame parent, JTextField... fields) {
//        if (!areFieldsFilled(
//                fields[0].getText(),
//                fields[1].getText(),
//                fields[2].getText(),
//                fields[3].getText(),
//                fields[4].getText())) {
//            JOptionPane.showMessageDialog(parent, "Please insert the value");
//        } else if (!isValidDate(fields[2].getText())) {
//            JOptionPane.showMessageDialog(parent, "Please enter a valid date (YYYY-MM-DD)");
//        } else if (!isNumeric(fields[4].getText())) {
//            JOptionPane.showMessageDialog(parent, "Please enter a valid floor number");
//        } else {
//            JOptionPane.showMessageDialog(parent, "Reservation is Update successfully");
//        }
//    }
//
//    public static void handleCancel(JTextField... fields) {
//        for (JTextField field : fields) {
//            field.setText("");
//        }
//    }
//} 
//
//package Controller;
//
//import Dao.EventDao;
//import Model.EventModel;
//import View.EventManagement;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class EventController {
//
//    public static void handleCreate(EventManagement aThis, JTextField Customer_Name, JTextField Event, JTextField Date, JTextField AssignedStaff, JTextField Floor) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    public static void handleUpdate(EventManagement aThis, JTextField Customer_Name, JTextField Event, JTextField Date, JTextField AssignedStaff, JTextField Floor) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    public static void handleCancel(JTextField Customer_Name, JTextField Event, JTextField Date, JTextField AssignedStaff, JTextField Floor) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//
//    public static void handleCreate(EventManagement aThis, JTextField Customer_Name, JTextField Event, JTextField Date, JTextField AssignedStaff, JTextField Floor) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }
//    private final EventDao eventDao;
//    private final EventManagement eventView;
//
//    public EventController(EventManagement eventView) {
//        this.eventView = eventView;
//        this.eventDao = new EventDao();
//
//        this.eventView.addCreateEventListener(new CreateEventListener());
//        this.eventView.addUpdateEventListener(new UpdateEventListener());
//        this.eventView.addCancelEventListener(new CancelEventListener());
//    }
//
//    class CreateEventListener implements ActionListener {
//        public void actionPerformed(ActionEvent e) {
//            try {
//                String customerName = eventView.getCustomerNameField().getText();
//                String event = eventView.getEventField().getText();
//                int date = Integer.parseInt(eventView.getDateField().getText());
//                String staff = eventView.getStaffAssignedField().getText();
//                int floor = Integer.parseInt(eventView.getFloorField().getText());
//
//                EventModel model = new EventModel(customerName, event, date, staff, floor);
//                eventDao.createEvent(model);
//                JOptionPane.showMessageDialog(eventView, "Event created successfully.");
//            } catch (Exception ex) {
//                JOptionPane.showMessageDialog(eventView, "Error: " + ex.getMessage());
//            }
//        }
//    }
//
//    class UpdateEventListener implements ActionListener {
//        public void actionPerformed(ActionEvent e) {
//            try {
//                int eventId = Integer.parseInt(JOptionPane.showInputDialog(eventView, "Enter Event ID to update:"));
//
//                String customerName = eventView.getCustomerNameField().getText();
//                String event = eventView.getEventField().getText();
//                int date = Integer.parseInt(eventView.getDateField().getText());
//                String staff = eventView.getStaffAssignedField().getText();
//                int floor = Integer.parseInt(eventView.getFloorField().getText());
//
//                EventModel model = new EventModel(customerName, event, date, staff, floor);
//                boolean updated = eventDao.updateEvent(model, eventId);
//
//                if (updated) {
//                    JOptionPane.showMessageDialog(eventView, "Event updated.");
//                } else {
//                    JOptionPane.showMessageDialog(eventView, "Update failed. ID not found.");
//                }
//            } catch (Exception ex) {
//                JOptionPane.showMessageDialog(eventView, "Error: " + ex.getMessage());
//            }
//        }
//    }
//
//    class CancelEventListener implements ActionListener {
//        public void actionPerformed(ActionEvent e) {
//            try {
//                int eventId = Integer.parseInt(JOptionPane.showInputDialog(eventView, "Enter Event ID to cancel:"));
//                boolean deleted = eventDao.cancelEvent(eventId);
//
//                if (deleted) {
//                    JOptionPane.showMessageDialog(eventView, "Event cancelled.");
//                } else {
//                    JOptionPane.showMessageDialog(eventView, "Cancel failed. ID not found.");
//                }
//            } catch (Exception ex) {
//                JOptionPane.showMessageDialog(eventView, "Error: " + ex.getMessage());
//            }
//        }
//    }
//}

package Controller;

import Dao.EventDao;
import Model.EventModel;
import View.EventManagement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventController {
    private final EventDao eventDao;
    private final EventManagement eventView;

    public EventController(EventManagement eventView) {
        this.eventView = eventView;
        this.eventDao = new EventDao();

        this.eventView.addCreateEventListener(new CreateEventListener());
        this.eventView.addUpdateEventListener(new UpdateEventListener());
        this.eventView.addCancelEventListener(new CancelEventListener());
    }

    class CreateEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String customerName = eventView.getCustomerNameField().getText();
                String event = eventView.getEventField().getText();
                int date = Integer.parseInt(eventView.getDateField().getText());
                String staff = eventView.getStaffAssignedField().getText();
                int floor = Integer.parseInt(eventView.getFloorField().getText());

                EventModel model = new EventModel(customerName, event, date, staff, floor);
                eventDao.createEvent(model);
                JOptionPane.showMessageDialog(eventView, "Event created successfully.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(eventView, "Error: " + ex.getMessage());
            }
        }
    }

    class UpdateEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int eventId = Integer.parseInt(JOptionPane.showInputDialog(eventView, "Enter Event ID to update:"));

                String customerName = eventView.getCustomerNameField().getText();
                String event = eventView.getEventField().getText();
                int date = Integer.parseInt(eventView.getDateField().getText());
                String staff = eventView.getStaffAssignedField().getText();
                int floor = Integer.parseInt(eventView.getFloorField().getText());

                EventModel model = new EventModel(customerName, event, date, staff, floor);
                boolean updated = eventDao.updateEvent(model, eventId);

                if (updated) {
                    JOptionPane.showMessageDialog(eventView, "Event updated.");
                } else {
                    JOptionPane.showMessageDialog(eventView, "Update failed. ID not found.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(eventView, "Error: " + ex.getMessage());
            }
        }
    }

    class CancelEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                int eventId = Integer.parseInt(JOptionPane.showInputDialog(eventView, "Enter Event ID to cancel:"));
                boolean deleted = eventDao.cancelEvent(eventId);

                if (deleted) {
                    JOptionPane.showMessageDialog(eventView, "Event cancelled.");
                } else {
                    JOptionPane.showMessageDialog(eventView, "Cancel failed. ID not found.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(eventView, "Error: " + ex.getMessage());
            }
        }
    }
}
