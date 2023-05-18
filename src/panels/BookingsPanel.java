package panels;

import components.BookingTable;
import components.CustomerBookings;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import static javax.swing.GroupLayout.DEFAULT_SIZE;
import static javax.swing.GroupLayout.PREFERRED_SIZE;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import managers.BookingsManager;
import managers.PanelManager;


public class BookingsPanel extends JPanel{
    
    private final BookingsManager bookingManager;
    private final JLabel title;
    private final JComboBox searchBox;
    private final JButton searchButton;
    private final JButton refreshButton;
    private final JButton saveButton;
    private final JButton cancelButton;
    private final JScrollPane tableScroll;
    private final JTextField searchField;
    private final BookingTable bookingTable;
    private final GroupLayout layout;
    
    private final String[] searchItems = {"Name", "IC", "Contact", "Email", "Room ID", "Start Date", "End Date"};
    
    public BookingsPanel() throws IOException{
        
        bookingManager = new BookingsManager();
        title = new JLabel("Bookings");
        searchBox = new JComboBox<>(searchItems);
        searchButton = new JButton ("Search");
        refreshButton = new JButton("Refresh");
        saveButton = new JButton("Save Changes");
        cancelButton = new JButton("Cancel");
        tableScroll = new JScrollPane();
        searchField = new JTextField(20);
        bookingTable = new BookingTable();
        
        title.setFont(new Font("STXihei", Font.BOLD, 25));
        title.setText("Bookings");

        searchButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String query = searchField.getText();
                int index = searchBox.getSelectedIndex();
                
                try {
                    bookingTable.loadModel(query, index);
                } catch (IOException ex) {
                    Logger.getLogger(BookingsPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        refreshButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    bookingTable.loadModel();
                } catch (IOException ex) {
                    Logger.getLogger(BookingsPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        saveButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    saveBookingsInfo(e);
                } catch (IOException ex) {
                    Logger.getLogger(BookingsPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        cancelButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
                int confirmation = JOptionPane.showConfirmDialog(bookingTable, "Are you sure you want to cancel changes?", "Cancel Changes", JOptionPane.YES_NO_OPTION);
                
                if (confirmation == 0) {
                    try {
                        bookingTable.loadModel();
                    } catch (IOException ex) {
                        Logger.getLogger(BookingsPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    PanelManager.previousPanel();
                }
                else{
                    return; //Closes the dialog without any changes
                }
                
            }
        });
        
        tableScroll.setViewportView(bookingTable); //Handles overflow issues

        bookingTable.loadModel();
        
        layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                    
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                        
                   .addGroup(layout.createSequentialGroup()
                        .addComponent(title)
                           .addGap(30, 30, 30)
                        .addComponent(searchField, PREFERRED_SIZE, PREFERRED_SIZE, PREFERRED_SIZE)
                           .addGap(5, 5, 5)
                        .addComponent(searchBox, 100, 100, 100)
                           .addGap(5, 5, 5)
                        .addComponent(searchButton, PREFERRED_SIZE, 154, PREFERRED_SIZE)  
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(refreshButton, PREFERRED_SIZE, 154, PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                        .addComponent(saveButton, PREFERRED_SIZE, 154, PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                        .addComponent(cancelButton, PREFERRED_SIZE, 154, PREFERRED_SIZE)
                           
                   )
                    .addComponent(tableScroll, PREFERRED_SIZE, 1204, PREFERRED_SIZE)
                )
                    
                .addContainerGap(44, Short.MAX_VALUE)
            )
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
                    
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                    
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                    .addGroup(layout.createParallelGroup(Alignment.CENTER)
                        .addComponent(searchField, 40, 40, 40)
                        .addComponent(searchBox, 40, 40, 40)
                        .addComponent(searchButton)
                    )
                    .addComponent(title, PREFERRED_SIZE, 53, PREFERRED_SIZE)
                    .addComponent(refreshButton)
                    .addComponent(saveButton)
                    .addComponent(cancelButton)
                )
                    
                .addGap(18, 18, 18)
                .addComponent(tableScroll, PREFERRED_SIZE, 551, PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE)
            )
        );
        
    }
    
    
    private void saveBookingsInfo(ActionEvent e) throws IOException{
        
        int rowCount = bookingTable.getRowCount();
        //Creating new Bookings Object to save modified data
        ArrayList<CustomerBookings> newBookingsList = new ArrayList<>(rowCount);

        for (int i = 0; i < rowCount; i++){
            //Parsing because getValueAt returns Object
            String name = (String) bookingTable.getValueAt(i, 0);
            String IC = (String) bookingTable.getValueAt(i, 1);
            String contactNumber = (String) bookingTable.getValueAt(i, 2);
            String email = (String) bookingTable.getValueAt(i, 3);
            String roomID = (String) bookingTable.getValueAt(i, 4);
            String startDate = (String) bookingTable.getValueAt(i, 5);
            String endDate = (String) bookingTable.getValueAt(i, 6);
            boolean checkoutStatus = (boolean) bookingTable.getValueAt(i, 7);
            CustomerBookings booking = new CustomerBookings(name, IC, contactNumber, email, startDate, endDate, roomID, checkoutStatus);
            
            newBookingsList.add(booking);
            
        }
        
        //Overwrite entire text file with new list
        bookingManager.overwriteBookingsList(newBookingsList);
        bookingManager.saveBookings();

    }
}
