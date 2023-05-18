package components;
import javax.swing.ImageIcon;
import utils.LoadImage;

public class Room {
    private String roomID, roomType, bedType, wifi, smoking;
    
    public Room(String roomID, String roomType, String bedType, String wifi, String smoking) {
        this.roomID = roomID;
        this.roomType = roomType;
        this.bedType = bedType;
        this.wifi = wifi;
        this.smoking = smoking;
    }

    public String getRoomID() {
        return roomID;
    }

    public ImageIcon getRoomImage() {
        String imgFile = "Room" + roomID;
        
        ImageIcon img = new ImageIcon(LoadImage.getScaledImage(imgFile, 300, 300));
        
        return img;
    }
   
    public String getRoomType() {
        return roomType;
    }

    public String getBedType() {
        return bedType;
    }
    
    public boolean getWifi() {
        return Boolean.parseBoolean(wifi);
    }
    
    public boolean getSmoking() {
        return Boolean.parseBoolean(smoking);
    }
    
    public double getCharges() {
        return calculateRoomCharges(this);
    }
    
    public static double calculateRoomCharges(Room room) {
        double basePrice = 350.0;
        double roomCharge = basePrice;

        if (room.getRoomType().equals("Double") || room.getBedType().equals("Queen")) {
            // 2 person room or queen bed
            roomCharge += 0;
        }
        if (room.getRoomType().equals("Triple") || room.getBedType().equals("King")){
            // 3 person room or king bed
            roomCharge += 50;
        }
        if (room.getRoomType().equals("Quad")) {
            // Quad room
            roomCharge += 100;
        }
        if (room.getRoomType().equals("Executive Suite")) {
            // Executive suite
            roomCharge += 450;
        };
        if (room.getBedType().equals("Full XL") || room.getBedType().equals("Twin XL")) {
            // full XL or twin XL bed
            roomCharge -= 30;
        } 
        if (room.getRoomType().equals("Single") || room.getBedType().equals("Full")) {
            // Single room or full size bed
            roomCharge -= 50;
        } 
        if (room.getBedType().equals("Twin")) {
            // twin bed
            roomCharge -= 80;
        } 

        if (!room.getWifi()) {
            roomCharge -= 20;
        }

        if (!room.getSmoking()) {
            roomCharge -= 50;
        }

        return roomCharge;
    }

}