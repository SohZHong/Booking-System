package managers;


import components.CustomerBookings;
import java.io.IOException;
import java.util.ArrayList;

public class BookingsManager{
    
    private final FileManager fileManager = new FileManager("bookings");
    
    private ArrayList<CustomerBookings> bookings;
    private int lines;
    
    public BookingsManager(){
        bookings = new ArrayList<>();
    }
    
    //Outside of constructor to avoid conflict when overwriting content
    public void loadBookings() throws IOException{
        //Load Contents into array
        fileManager.readFile();
        
        lines = fileManager.contentLength();

        if (bookings == null){
            bookings = new ArrayList<>(lines); //Assigning capacity based on number of entries
        }
        else {
            bookings.clear(); //Delete any existing contents for memory optimization
        }
        
        for (int i = 0; i < lines; i++){
            String[] content = fileManager.readLine(i);
            bookings.add(
                new CustomerBookings(
                    content[0], content[1], content[2], content[3], content[5], content[6], content[4], Boolean.parseBoolean(content[7])
                )
            );
        }
    }
    
    public ArrayList<CustomerBookings> getBookings(){
      return bookings;
    }
    
    public void addBookings(CustomerBookings newCustomerBookings){
        bookings.add(newCustomerBookings);
    }
    
    public void saveBookings() throws IOException{
        //Clear contents of bookings file before writing
        fileManager.clearFile();
        
        for (int i = 0; i < bookings.size(); i++){
            
            CustomerBookings booking = bookings.get(i); //Getting bookings instances
            String[] data = new String[8];
            
            data[0] = booking.getName();
            data[1] = booking.getIC();
            data[2] = booking.getContact();
            data[3] = booking.getEmail();
            data[4] = booking.getRoomID();
            data[5] = booking.getStartDate();
            data[6] = booking.getEndDate();
            data[7] = booking.getCheckoutStatus() == true ? "true" : "false";
            fileManager.writeFile(data, true);
        }
    }
    
    public void overwriteBookingsList(ArrayList<CustomerBookings> newBookings) throws IOException{
        this.bookings = newBookings;
    }

}
