package components;

import java.awt.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;
import managers.BookingsManager;
import utils.CustomDateChooser;
import utils.DateTime;

public class BookingTableDateCellEditor extends AbstractCellEditor implements TableCellEditor{
    private final BookingsManager bookingManager;
    private ArrayList<CustomerBookings> bookings;
    private CustomDateChooser dateChooser;

    public BookingTableDateCellEditor() {
        this.dateChooser = new CustomDateChooser();
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
        return ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        Date date = DateTime.asDate((String) value);
        
        //End Date chooser's value cannot go below start date
        if (column == 6){
            Date startDate = DateTime.asDate((String)table.getModel().getValueAt(row, 5));
            
            dateChooser.setMinSelectableDate(startDate);
        }
        
        //Value of room ID at column 4
        String roomID = (String) table.getModel().getValueAt(row, 4);

        //Default value
        dateChooser.setDate(date);
        
        // Add date ranges to the date chooser
        for (int i = 0; i < bookings.size(); i++){
            if (i == row) {
                continue; // Skip the row that provided the data
            }
            CustomerBookings booking = bookings.get(i);
            //Exclude those that have checked out or with non-matching ID
            if (booking.getRoomID().equals(roomID) && booking.getCheckoutStatus() == false){
                //Parse start and end dates to date
                Date startDate = DateTime.asDate(booking.getStartDate());
                Date endDate = DateTime.asDate(booking.getEndDate());
                
                dateChooser.addDateRange(startDate, endDate);
            }
        }
        
        return dateChooser;
    }
}


