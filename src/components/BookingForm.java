package components;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import static javax.swing.GroupLayout.DEFAULT_SIZE;
import static javax.swing.GroupLayout.PREFERRED_SIZE;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import managers.BookingsManager;
import managers.FileManager;
import managers.PanelManager;
import utils.DateTime;
import utils.InputField;
import utils.LoadImage;
import utils.CustomDateChooser;


public class BookingForm extends JPanel {

    private final BookingsManager bookingManager;
    private ArrayList<CustomerBookings> bookings;
    
    private GroupLayout layout;
    private JTextField nameField;
    private JTextField icField;
    private JTextField contactField;
    private JTextField emailField;
    private CustomDateChooser startDateChooser;
    private CustomDateChooser endDateChooser;

    private JLabel title;
    private JLabel nameLabel;
    private JLabel icLabel;
    private JLabel contactLabel;
    private JLabel emailLabel;
    private JLabel startDateLabel;
    private JLabel endDateLabel;
    private JButton submitButton;
    private JButton cancelButton;
    private JLabel roomImage;
    
    private String roomID;
    
    public BookingForm() throws ParseException{
        bookingManager = new BookingsManager();
        bookings = new ArrayList<>();
        layout = new GroupLayout(this);
        this.setLayout(layout);

    }

    public void setBookingRoomID(int roomID){
        this.roomID = Integer.toString(roomID);
    }
    
    public void loadForm() throws ParseException, IOException{
        
        this.removeAll(); //Remove previous loaded room
        bookingManager.loadBookings(); //Reload the bookings in case changes were made
        title = new JLabel("New Reservation");
        roomImage = new JLabel(new ImageIcon (LoadImage.getScaledImage("Room " + roomID + ".png", 590, 411)));
        nameLabel = new JLabel("Customer Name");
        icLabel = new JLabel("IC");
        contactLabel = new JLabel("Phone Number");
        emailLabel = new JLabel("Email");
        startDateLabel = new JLabel("Start Date");
        endDateLabel = new JLabel("End Date");
          
        nameField = InputField.createTextField(25);
        icField = InputField.createICField(25);
        contactField = InputField.createContactField(25);
        emailField = InputField.createEmailField(25);
        startDateChooser = new CustomDateChooser(); //Change Later
        endDateChooser = new CustomDateChooser(); //Change Later

        submitButton = new JButton("Complete Reservation");
        cancelButton = new JButton("Cancel");

        title.setFont(new Font("Sitka Text", Font.PLAIN, 30));
        
        bookings = bookingManager.getBookings();
        // Add date ranges to the date chooser
        for (int i = 0; i < bookings.size(); i++){
            Bookings booking = bookings.get(i);
            //Exclude those that have checked out or with non-matching ID
            if (booking.getRoomID().equals(roomID) && booking.getCheckoutStatus() == false){
                //Parse start and end dates to date
                Date startDate = DateTime.asDate(booking.getStartDate());
                Date endDate = DateTime.asDate(booking.getEndDate());
                
                startDateChooser.addDateRange(startDate, endDate);
                endDateChooser.addDateRange(startDate, endDate);
            }
        }
        
        startDateChooser.setMinSelectableDate(new Date());
        // If start date changes, update the end date to be a day later
        startDateChooser.addPropertyChangeListener(new PropertyChangeListener() {
             @Override
             public void propertyChange(PropertyChangeEvent evt) {
                 
                if ("date".equals(evt.getPropertyName())) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(startDateChooser.getDate());
                    cal.add(Calendar.DAY_OF_MONTH, 1);
                    endDateChooser.setMinSelectableDate(startDateChooser.getDate());
                    endDateChooser.setDate(cal.getTime());
                }
            }
        });
        
        
        
        submitButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String name = nameField.getText();
                    String ic = icField.getText();
                    String contact = contactField.getText();
                    String email = emailField.getText();
                    String startDate = ((JTextField) startDateChooser.getDateEditor().getUiComponent()).getText();
                    String endDate = ((JTextField) endDateChooser.getDateEditor().getUiComponent()).getText();

                    //Load booking
                    CustomerBookings booking = new CustomerBookings(name, ic, contact, email, startDate, endDate, roomID, false);
                    BookingsManager bookingManager = new BookingsManager();
                    bookingManager.loadBookings();
                    bookingManager.addBookings(booking);
                    bookingManager.saveBookings();
                    
                    JOptionPane.showMessageDialog(submitButton.getParent(), "Reservation Complete!");
                    PanelManager.previousPanel(); //Go back to room listings after finish booking
                    
                } catch (IOException ex) {
                    Logger.getLogger(BookingForm.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            
        });
        
        cancelButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {

                PanelManager.previousPanel();
            }
        });

        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(Alignment.LEADING, false)
                    .addComponent(endDateLabel)
                    .addComponent(startDateLabel)
                    .addComponent(emailField, DEFAULT_SIZE, 360, Short.MAX_VALUE)
                    .addComponent(emailLabel)
                    .addComponent(contactField, DEFAULT_SIZE, 360, Short.MAX_VALUE)
                    .addComponent(contactLabel)
                    .addComponent(icField, DEFAULT_SIZE, 360, Short.MAX_VALUE)
                    .addComponent(icLabel)
                    .addComponent(nameField)
                    .addComponent(nameLabel)
                    .addComponent(title)
                    .addComponent(startDateChooser, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(endDateChooser, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 254, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addComponent(roomImage, Alignment.TRAILING, PREFERRED_SIZE, 590, PREFERRED_SIZE)
                    .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(submitButton, PREFERRED_SIZE, 220, PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(cancelButton, PREFERRED_SIZE, 220, PREFERRED_SIZE)))
                .addGap(44, 44, 44))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(title, PREFERRED_SIZE, 53, PREFERRED_SIZE)
                .addGap(36, 36, 36)
                    
                .addGroup(layout.createParallelGroup(Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nameField, PREFERRED_SIZE, 39, PREFERRED_SIZE)
                        .addComponent(nameLabel)
                        .addGap(18, 18, 18)
                        .addComponent(icField, PREFERRED_SIZE, 39, PREFERRED_SIZE)
                        .addComponent(icLabel)
                        .addGap(18, 18, 18)
                        .addComponent(contactField, PREFERRED_SIZE, 39, PREFERRED_SIZE)
                        .addComponent(contactLabel)
                        .addGap(18, 18, 18)
                        .addComponent(emailField, PREFERRED_SIZE, 39, PREFERRED_SIZE)
                        .addComponent(emailLabel)
                        .addGap(18, 18, 18)
                        .addComponent(startDateChooser, PREFERRED_SIZE, 39, PREFERRED_SIZE)
                        .addComponent(startDateLabel)
                    )
                    .addComponent(roomImage, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                )
                .addGap(18, 18, 18)
                .addComponent(endDateChooser, DEFAULT_SIZE, 39, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                    
                .addGroup(layout.createParallelGroup(Alignment.LEADING, false)
                    .addComponent(endDateLabel)
                    .addComponent(submitButton, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cancelButton, DEFAULT_SIZE, DEFAULT_SIZE, Short.MAX_VALUE)
                )
                .addContainerGap(71, Short.MAX_VALUE)
            )
        );
    }
}
