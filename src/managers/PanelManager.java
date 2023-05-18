package managers;

import components.BookingForm;
import panels.Dashboard;
import java.awt.CardLayout;
import java.io.IOException;
import java.text.ParseException;
import javax.swing.JPanel;
import panels.BookingsPanel;
import panels.LoginPanel;
import panels.RoomListingsPanel;

public class PanelManager {
    
    //Adding all panels
    private static JPanel mainPanel;
    private static LoginPanel loginPanel;
    private static CardLayout layout;
    private static Dashboard dashboardPanel;
    private static BookingsPanel bookingsPanel;
    private static RoomListingsPanel roomListingsPanel;
    private static BookingForm bookingForm;
    
    //Names for all panels
    public final static String LOGIN_PANEL = "Login Panel";
    public final static String DASHBOARD_PANEL = "Dashboard Panel";
    public final static String MANAGE_PANEL = "Bookings List";
    public final static String LISTINGS_PANEL = "Listings Panel";
    public final static String BOOKING_FORM = "Booking Form";
    
    private static String prevPanel, curPanel;
    
    public PanelManager(JPanel mainPanel) throws ParseException, InterruptedException, IOException{
        this.mainPanel = mainPanel;
        
        //get parent panel layout as card
        layout = (CardLayout) mainPanel.getLayout();
        
        //Initializing panels
        loginPanel = new LoginPanel();
        dashboardPanel = new Dashboard();
        roomListingsPanel = new RoomListingsPanel();
        bookingsPanel = new BookingsPanel();
        bookingForm = new BookingForm();

        //Adding them all to cardlayout
        mainPanel.add(loginPanel, LOGIN_PANEL);
        mainPanel.add(dashboardPanel, DASHBOARD_PANEL);
        mainPanel.add(bookingsPanel, MANAGE_PANEL);
        mainPanel.add(roomListingsPanel, LISTINGS_PANEL);
        mainPanel.add(bookingForm, BOOKING_FORM);
    }
    
    //Show panel accepted as argument through cardlayout
    public static void showPanel(String panelName){
        prevPanel = curPanel;
        curPanel = panelName;
        
        layout.show(mainPanel, panelName);
    }
    
    public static void previousPanel(){
        layout.show(mainPanel, prevPanel);
        
        curPanel = prevPanel;
    }
    
    public static BookingForm getBookingForm(){
        return bookingForm;
    }
}
