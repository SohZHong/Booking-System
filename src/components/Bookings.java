package components;

public class Bookings {
    private String name, ic, contactNumber, email, startDate, endDate, roomID;
    private boolean checkoutStatus;
    
    public Bookings( 
            String customerName,
            String ic,
            String contactNumber,
            String email,
            String startDate,
            String endDate,
            String roomID,
            boolean checkoutStatus
    ){
        this.name = customerName;
        this.ic = ic;
        this.contactNumber = contactNumber;
        this.email = email;
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomID = roomID;
        this.checkoutStatus = checkoutStatus;
    };
    
    public String getName(){
        return name;
    }
    
    public String getIC(){
        return ic;
    }
    
    public String getContact(){
        return contactNumber;
    }
        
    public String getEmail(){
        return email;
    }
    
    public String getStartDate(){
        return startDate;
    }
    
    public String getEndDate(){
        return endDate;
    }
    
    public String getRoomID(){
        return roomID;
    }
    
    public boolean getCheckoutStatus(){
        return checkoutStatus;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setIC(String ic){
        this.ic = ic;
    }
    
    public void setContact(String contactNumber){
        this.contactNumber = contactNumber;
    }
        
    public void setEmail(String email){
        this.email = email;
    }
    
    public void setStartDate(String startDate){
        this.startDate = startDate;
    }
    
    public void setEndDate(String endDate){
        this.endDate = endDate;
    }
    
    public void setRoomID(String roomID){
        this.roomID = roomID;
    }
    
    public void setCheckoutStatus(boolean status){
        this.checkoutStatus = status;
    }
}
