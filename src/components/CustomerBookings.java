package components;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import managers.RoomManager;
import utils.DateTime;

public final class CustomerBookings extends Bookings{
    
    private RoomManager roomManager;
    private Room room;
    private double totalCharges, baseCharge;
    private long daysSpent;
    
    public CustomerBookings(
            String customerName,
            String ic,
            String contactNumber,
            String email,
            String startDate,
            String endDate,
            String roomID,
            boolean checkoutStatus
    ){
        super (customerName,ic, contactNumber,email,startDate, endDate, roomID, checkoutStatus);
        try {
            roomManager = new RoomManager();
            roomManager.loadRoom();
            room = roomManager.getRoom(Integer.parseInt(roomID));
            this.baseCharge = room.getCharges();
            this.daysSpent = 
                    DateTime.getDaysBetween(this.getStartDate(), this.getEndDate()) == 0 ? 1 
                    : DateTime.getDaysBetween(this.getStartDate(), this.getEndDate());
            this.totalCharges = calculateTotalCharges();
        } catch (IOException ex) {
            Logger.getLogger(CustomerBookings.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public double getTotalCharges(){
        return totalCharges;
    }
    
    public Room getRoom(){
        return room;
    }
    
    public void setRoom(String roomID){
        room = roomManager.getRoom(Integer.parseInt(roomID));
        this.totalCharges = calculateTotalCharges();
    }
    
    public double calculateTotalTax(double initialCharges, long daysOccupied){
        double serviceTax = initialCharges * 10 / 100; //10% of room charges
        long tourismTax = daysOccupied * 10;
        
        return (serviceTax + tourismTax);
    }
    
    public double calculateTotalCharges(){
        
        double initialCharges = (baseCharge * daysSpent);
        
        double totalTax = calculateTotalTax(initialCharges, daysSpent);
        
        double total = initialCharges + totalTax;
        
        return total;
    }
    
    public void showReceipt(){
        String lineSep = System.lineSeparator(); //new lines        
        StringBuilder receipt = new StringBuilder();
        receipt.append("Receipt").append(lineSep).append(lineSep);
        receipt.append("Name: ").append(this.getName()).append(lineSep);
        receipt.append("IC: ").append(this.getIC()).append(lineSep);
        receipt.append("Email: ").append(this.getEmail()).append(lineSep);
        receipt.append("Room: ").append(this.getRoomID()).append(lineSep);
        receipt.append("Days Reserved: ").append(daysSpent).append(lineSep);
        receipt.append("Total Tax: ").append(calculateTotalTax(totalCharges, daysSpent)).append(lineSep);
        receipt.append("Total Charges: ").append(getTotalCharges()).append(lineSep);
        
        JOptionPane.showMessageDialog(null, receipt.toString());
    }
}
