package managers;

import components.Room;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class RoomManager {
  
    private HashMap<Integer, Room> rooms;
    private int lines;
    private final FileManager fileManager = new FileManager("rooms");    
    
    public RoomManager() {
        rooms = new HashMap<>();
    }
    
    private void addRoom(String roomID, String roomType, String bedType, String wifi, String smoking){
        Room room = new Room(roomID, roomType, bedType, wifi, smoking);
        //Put into array with room ID as key
        rooms.put(Integer.valueOf(roomID), room);
    }
    
    public void loadRoom() throws IOException{
        //Load Contents into arrayit has
        fileManager.readFile();
        lines = fileManager.contentLength();
        
        for (int i = 0; i < lines; i++){
            String[] content = fileManager.readLine(i);
            addRoom(content[0], content[1], content[2], content[3], content[4]);
        }
    }
    
    public Room getRoom(int roomID){
        return rooms.get(roomID);
    }
    
    public ArrayList<Room> getAllRooms(){
        ArrayList<Room> roomList = new ArrayList<Room>(rooms.size());
        for (int i = 1; i <= rooms.size(); i++){
            roomList.add(rooms.get(i));
        }
        return roomList;
    }
}