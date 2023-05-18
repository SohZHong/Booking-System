package components;

import java.awt.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import managers.BookingsManager;
import utils.DateTime;


public class BookingTableRoomCellEditor extends AbstractCellEditor implements TableCellEditor{
    private final BookingsManager bookingManager;
    private ArrayList<CustomerBookings> bookings;
    private JComboBox<String> availableRooms;
    
    public BookingTableRoomCellEditor(){
        this.availableRooms = new JComboBox<>();
        
        bookingManager = new BookingsManager();
        try {
            bookingManager.loadBookings();
            bookings = bookingManager.getBookings();
        } catch (IOException ex) {
            Logger.getLogger(BookingTableDateCellEditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Object getCellEditorValue() {
        return availableRooms.getSelectedItem();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        String roomID = (String) value;
        ArrayList<String> availableIDs = new ArrayList<>();
        
        //Default to all rooms available
        for (int i = 1; i <= 20; i++) {
            availableIDs.add(String.valueOf(i));
        }
        
        Date startDate = DateTime.asDate((String)table.getModel().getValueAt(row, 5));
        Date endDate = DateTime.asDate((String)table.getModel().getValueAt(row, 6));
        // Compare all start and end dates with the same room ID
        for (int i = 0; i < bookings.size(); i++){
            if (i == row) {
                continue; // Skip the row that provided the data
            }
            Bookings booking = bookings.get(i);
            
            //Only compare those with same Room ID and not checked out
            if (booking.getCheckoutStatus() == false){
                //Get the start and end date of each booking
                Date newStartDate = DateTime.asDate(booking.getStartDate());
                Date newEndDate = DateTime.asDate(booking.getEndDate());
                
                if (!(newEndDate.before(startDate)) || newStartDate.after(endDate)){
                    availableIDs.remove(booking.getRoomID());
                }
            }
        }
        //Convert array list to array and add into combo box
        availableRooms.setModel(new DefaultComboBoxModel<>(availableIDs.toArray(String[]::new)));
        availableRooms.setSelectedItem(roomID);
        
        return availableRooms;
    }
}
