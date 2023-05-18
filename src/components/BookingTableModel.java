package components;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.table.AbstractTableModel;
import utils.CustomDateChooser;

public class BookingTableModel extends AbstractTableModel{
    private final ArrayList<CustomerBookings> bookings;
    private CustomerBookings booking;
   
    public BookingTableModel(ArrayList<CustomerBookings> bookings){
        this.bookings = bookings;
    }
    
    @Override
    public int getRowCount() {
        return bookings.size();
    }

    @Override
    public int getColumnCount() {
    //Name, IC, Contact, Email, Room, Start, End, Checkout, Delete, View Receipt
        return 10;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }
    
    @Override
    //Class <?> User defined object
    public Class<?> getColumnClass(int columnIndex){
        switch (columnIndex) {
            case 0: return String.class;
            case 1: return String.class;
            case 2: return String.class;
            case 3: return String.class;
            case 4: return JComboBox.class;
            case 5: return CustomDateChooser.class;
            case 6: return CustomDateChooser.class;
            case 7: return Boolean.class;
            case 8: return JButton.class;
            case 9: return JButton.class;
                
        }
        return Object.class;
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0: return "Name";
            case 1: return "IC Number";
            case 2: return "Contact Number";
            case 3: return "Email";
            case 4: return "Room";
            case 5: return "Start Date";
            case 6: return "End Date";
            case 7: return "Checkout Status";
        }
        return null; //Last 2 columns are save and delete buttons
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //Get booking entry from array
        booking = bookings.get(rowIndex);
        switch (columnIndex){
            case 0: return booking.getName();
            case 1: return booking.getIC();
            case 2: return booking.getContact();
            case 3: return booking.getEmail();
            case 4: return booking.getRoomID();
            case 5: return booking.getStartDate();
            case 6: return booking.getEndDate();
            case 7: return booking.getCheckoutStatus();
        }
        return null;
    }
    
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        booking = bookings.get(rowIndex);
        switch (columnIndex){
            case 0: 
                booking.setName((String) value);
                break;
            case 1: 
                booking.setIC((String) value);
                break;
            case 2: 
                booking.setContact((String) value);
                break;
            case 3: 
                booking.setEmail((String) value);
                break;
            case 4:
                booking.setRoomID((String) value);
                break;
            case 5:
                booking.setStartDate((String) value);
                break;
            case 6:
                booking.setEndDate((String) value);
                break;   
            case 7:
                booking.setCheckoutStatus((boolean) value);
                break;
        }
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public void deleteRow(int rowIndex) {
        bookings.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void viewReceipt(int row) {
        booking = bookings.get(row);
        
        booking.showReceipt();
    }
    
}
