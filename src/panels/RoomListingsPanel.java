package panels;

import com.toedter.calendar.JDateChooser;
import components.Bookings;
import components.CustomerBookings;
import components.Room;
import components.RoomListingsItem;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import static javax.swing.GroupLayout.DEFAULT_SIZE;
import static javax.swing.GroupLayout.PREFERRED_SIZE;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import managers.BookingsManager;
import managers.PanelManager;
import static managers.PanelManager.DASHBOARD_PANEL;
import managers.RoomManager;
import utils.CustomDateChooser;
import utils.DateTime;

public class RoomListingsPanel extends JPanel{
    
    private final RoomManager roomManager;
    private final BookingsManager bookingsManager;
    private final JLabel title;
    private final JLabel wifiLabel;
    private final JLabel smokingLabel;
    private final JLabel startDateLabel;
    private final JLabel endDateLabel;
    private final JButton backButton;
    private final JTabbedPane floorMainPanel;
    private final JScrollPane floorOneScroll;
    private final JScrollPane floorTwoScroll;
    private final JPanel floorOnePanel;
    private final JPanel floorTwoPanel;
    private final JCheckBox wifiFilter;
    private final JCheckBox smokingFilter;
    private final CustomDateChooser startDateFilter;
    private final CustomDateChooser endDateFilter;
    
    private final GridLayout viewLayout;
    private final GroupLayout layout;
    
    public RoomListingsPanel() throws IOException, ParseException {
        
        roomManager = new RoomManager();
        bookingsManager = new BookingsManager();
        
        title = new JLabel("Room Listings");
        wifiLabel = new JLabel("Wifi");
        smokingLabel = new JLabel("Smoking");
        startDateLabel = new JLabel("Start Date");
        endDateLabel = new JLabel("End Date");
        backButton = new JButton("Return");
        
        //Back Button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PanelManager.showPanel(DASHBOARD_PANEL);
            }
        });
        
        floorMainPanel = new JTabbedPane();
        floorOneScroll = new JScrollPane();
        floorTwoScroll = new JScrollPane();
        floorOnePanel = new JPanel();
        floorTwoPanel = new JPanel();
        startDateFilter = new CustomDateChooser();
        endDateFilter = new CustomDateChooser();
        wifiFilter = new JCheckBox();
        smokingFilter = new JCheckBox();

        title.setFont(new Font("Sitka Text", Font.PLAIN, 30));

        viewLayout = new GridLayout(10, 0);
        floorOnePanel.setLayout(viewLayout);
        floorTwoPanel.setLayout(viewLayout);
        
        floorOneScroll.setViewportView(floorOnePanel);
        floorOneScroll.getVerticalScrollBar().setUnitIncrement(16); //Increase Scroll Speed, 16 is equivalent to Windows' speed
        floorMainPanel.addTab("Floor 1", floorOneScroll);

        floorTwoScroll.setViewportView(floorTwoPanel);
        floorTwoScroll.getVerticalScrollBar().setUnitIncrement(16);
        floorMainPanel.addTab("Floor 2", floorTwoScroll);

        //Adding Rooms to each floor panels
        roomManager.loadRoom();
        bookingsManager.loadBookings();

        startDateFilter.setDate(new Date());
        endDateFilter.setDate(new Date());
        //Disable selecting dates before today
        startDateFilter.setMinSelectableDate(new Date());
        startDateFilter.addPropertyChangeListener(new PropertyChangeListener() {
             @Override
             public void propertyChange(PropertyChangeEvent evt) {
                 
                if ("date".equals(evt.getPropertyName())) {
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(startDateFilter.getDate());
                    cal.add(Calendar.DAY_OF_MONTH, 1);

                    endDateFilter.setDate(cal.getTime());
                    endDateFilter.setMinSelectableDate(cal.getTime());
                }
            }
        });
        
        //ActionListeners to refresh the page when one filter is changed
        wifiFilter.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                generateListings();
            }  
        });
        
        smokingFilter.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                generateListings();
            }  
        });
        
        startDateFilter.addPropertyChangeListener(new PropertyChangeListener(){
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("date")) {
                    generateListings();
                }
            }
        });
        
        endDateFilter.addPropertyChangeListener(new PropertyChangeListener(){
            public void propertyChange(PropertyChangeEvent evt) {
                if (evt.getPropertyName().equals("date")) {
                    generateListings();
                }
            }
        });
        
        generateListings();


        layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(Alignment.CENTER, false)
                    .addComponent(floorMainPanel, PREFERRED_SIZE, 1204, PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(title)
//                        .addGap(677, 677, 677)
                        .addContainerGap(350, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup()
                            .addComponent(wifiFilter, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                            .addComponent(wifiLabel)
                        )
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup()
                            .addComponent(smokingFilter, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                            .addComponent(smokingLabel)
                        )
                        .addGap(40, 40, 40)
                            
                        .addGroup(layout.createParallelGroup()
                            .addComponent(startDateFilter, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                            .addComponent(startDateLabel)
                        )
                        .addGap(40, 40, 40)
                            
                        .addGroup(layout.createParallelGroup()
                            .addComponent(endDateFilter, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                            .addComponent(endDateLabel)
                        )
                        .addGap(40, 40, 40)
                            
                        .addComponent(backButton, PREFERRED_SIZE, 154, PREFERRED_SIZE)   

                    )
                )
                .addContainerGap(90, Short.MAX_VALUE)
            )
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                            .addComponent(title, PREFERRED_SIZE, 53, PREFERRED_SIZE)
                            .addComponent(backButton)))
                    .addGroup(Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(wifiFilter, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                .addComponent(wifiLabel)
                            )
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(smokingFilter, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                .addComponent(smokingLabel)
                            )
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(startDateFilter, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                .addComponent(startDateLabel)
                            )
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(endDateFilter, PREFERRED_SIZE, DEFAULT_SIZE, PREFERRED_SIZE)
                                .addComponent(endDateLabel)
                            )
                        )
                        .addGap(12, 12, 12)
                    )
                )
                .addGap(18, 18, 18)
                .addComponent(floorMainPanel, PREFERRED_SIZE, 551, PREFERRED_SIZE)
                .addContainerGap(116, Short.MAX_VALUE)
            )
        );
        
    }
    
    private void generateListings(){
        LocalDate startDate = DateTime.convertDate(startDateFilter.getDate());
        LocalDate endDate = DateTime.convertDate(endDateFilter.getDate());
        boolean isWifiSelected = wifiFilter.isSelected();
        boolean isSmokingSelected = smokingFilter.isSelected();
        //Comparing bookings with date for room availability
        ArrayList<Room> rooms = roomManager.getAllRooms();
        ArrayList<CustomerBookings> bookings = bookingsManager.getBookings();
        ArrayList<Integer> availableIDs = getAvailableRoomIDs(rooms, bookings, isWifiSelected, isSmokingSelected, startDate, endDate);
        generateRooms(floorOnePanel, availableIDs, 0, 10);
        generateRooms(floorTwoPanel, availableIDs, 10, 20);
    }
    
    private ArrayList<Integer> getAvailableRoomIDs(ArrayList<Room> rooms, ArrayList<CustomerBookings> bookings, boolean hasWifi, boolean hasSmoking, LocalDate startDate1, LocalDate endDate1){
        //ArrayList because size cannot be determined
        ArrayList<Integer> availableRoomIDs = new ArrayList<>();
        
    for (Room room : rooms){
        boolean isAvailable = true;
        
        for (Bookings booking : bookings){
            LocalDate startDate2 = DateTime.convertDate(booking.getStartDate());
            LocalDate endDate2 = DateTime.convertDate(booking.getEndDate());
            
            boolean overlap = (startDate1.compareTo(endDate2) <= 0 && endDate1.compareTo(startDate2) >= 0);
            
            if (
                    (booking.getRoomID().equals(room.getRoomID()) && overlap && !booking.getCheckoutStatus()) 
                    ||
                    (room.getWifi() != hasWifi || room.getSmoking() != hasSmoking)
                    
                ){
                isAvailable = false;
                break;
            }
        }
        
        if (isAvailable){
            availableRoomIDs.add(Integer.valueOf(room.getRoomID()));
        }
    }
        return availableRoomIDs;
    }

    private void generateRooms(JPanel parentComponent, ArrayList<Integer> availableIDs, int floorRoomStart, int floorRoomEnd){
        parentComponent.removeAll();
        
        for (int i = floorRoomStart; i <= floorRoomEnd; i++){
            if (availableIDs.contains(i)){
                try {
                    parentComponent.add(new RoomListingsItem(roomManager.getRoom(i)));
                } catch (ParseException ex) {
                    Logger.getLogger(RoomListingsPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else {
                continue;
            }
        }
        parentComponent.repaint();
        parentComponent.revalidate();
    }
}