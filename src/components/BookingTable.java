package components;

import java.awt.Component;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import managers.BookingsManager;
import utils.ContactInputVerifier;
import utils.EmailInputVerifier;
import utils.ICInputVerifier;
import utils.TextInputVerifier;

public class BookingTable extends JTable {
    private final BookingsManager bookingManager;
    private ArrayList<CustomerBookings> rowData;
    
    public BookingTable() throws IOException{
        
        bookingManager = new BookingsManager();
        setRowHeight(30);

    }
    
    public void loadModel() throws IOException{
        
        bookingManager.loadBookings();
        rowData = bookingManager.getBookings();
        
        this.setModel(new BookingTableModel(rowData));
    }
    
    public void loadModel(String search, int columnIndex) throws IOException{
        bookingManager.loadBookings();
        rowData = bookingManager.getBookings();
        String query;
        
        // Create an ArrayList to store the matched bookings
        ArrayList<CustomerBookings> matchedBookings = new ArrayList<>();
        
        //Perform search based on data and column
        for (CustomerBookings booking : rowData){
            
            switch (columnIndex){
                case 0:
                    query = booking.getName();
                    break;
                case 1:
                    query = booking.getIC();
                    break;
                case 2:
                    query = booking.getContact();
                    break;
                case 3:
                    query = booking.getEmail();
                    break;
                case 4:
                    query = String.valueOf(booking.getRoomID());
                    break;
                case 5:
                    query = booking.getStartDate();
                    break;
                case 6:
                    query = booking.getEndDate();
                    break;
                default:
                    query = null;
                    break;
            }
            //toLowerCase for case-insensitive
            if (query!= null && query.toLowerCase().contains(search.toLowerCase())){
                matchedBookings.add(booking);
            }
            
        }
        this.setModel(new BookingTableModel(matchedBookings));
    }
    
    @Override
    public TableCellEditor getCellEditor(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return new BookingTableCellEditor(new TextInputVerifier());
                
            case 1:
                // IC Number column
                return new BookingTableCellEditor(new ICInputVerifier());
                
            case 2:
                // Contact Number column
                return new BookingTableCellEditor(new ContactInputVerifier());
                
            case 3:
                // Email column
                return new BookingTableCellEditor(new EmailInputVerifier());
            
            case 4:
                // Room ID column
                return new BookingTableRoomCellEditor();
                
            case 5:
                //Start Date column
                return new BookingTableDateCellEditor();
                
            case 6:
                //End Date column
                return new BookingTableDateCellEditor();
                
            case 7:
                return new DefaultCellEditor(new JCheckBox());
            
            case 8:
                return new ReceiptButtonEditor();
            
            case 9:
                return new DeleteButtonEditor();
            default:
                // For other columns, return a default cell editor
                return super.getCellEditor(rowIndex, columnIndex);
        }
    }
    
    @Override
    public TableCellRenderer getCellRenderer(int rowIndex, int columnIndex){
        switch (columnIndex) {
            case 7:
                return new DefaultTableCellRenderer() {
                    private final JCheckBox checkBox = new JCheckBox();
                    @Override
                    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                        boolean selected = (Boolean) getValueAt(row, column);
                        checkBox.setSelected(selected);
                        return checkBox;
                    }
                };
            case 8:
                return new ReceiptButtonRenderer();
            case 9:
                return new DeleteButtonRenderer();
            default:
                // For other columns, return a default cell editor
                return super.getCellRenderer(rowIndex, columnIndex);
        }
    }
    
}
