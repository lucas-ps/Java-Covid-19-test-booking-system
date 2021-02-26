package UoKCovid19TestBookingSystem.mainModules;

import java.util.ArrayList;

import static UoKCovid19TestBookingSystem.helperModules.helperFunctions.*;

/*
 * Class for creation of dummy data
 */
public class UniversityResources {

    // Attributes

    public ArrayList<Room> rooms;
    public ArrayList<Assistant> assistants;

    // Constructor

    public UniversityResources() {

        this.assistants = new ArrayList<>();
        this.rooms = new ArrayList<>();
    }

    // Methods

    /**
     * Get/Set methods, these search through the arraylists to find the room/assistant object with the matching ID/code
     */

    public Assistant getAssistant(int ID) {
        for(Assistant assistant : assistants) {
            if(assistant.getID() == ID) {
                return assistant;
            }
        }
        return null;
    }

    public Room getRoom(String code) {
        for(Room room : rooms) {
            if(room.getCode() == code) {
                return room;
            }
        }
        return null;
    }

    /**
     * @return Formatted string with all bookable rooms and their details
     */
    public static String formattedBookableRooms() {
        return "";
    }

    /**
     * Removes a room from the rooms ArrayList
     */
    public void removeRoom() {
        print(UniversityResources.formattedBookableRooms());
        String roomCode = inputSTR("\nEnter the code of the room you'd like to remove");
        for (var i = 0; i < rooms.size(); i++) {
            if(rooms.get(i).getCode() == roomCode) {
                rooms.remove(rooms.get(i));
                print("Room "+ roomCode +" was successfully removed");
                // TODO: Remove all bookings in this room
            }
            else if (i == rooms.size()) {
                print("Room " + roomCode + " was not found on the system.");
            }
        }
    }

    /**
     * Adds newRoom to rooms ArrayList
     * @param newRoom room created by user
     */
    public void addRoom() {
        String code = inputSTR("Please enter the new room's code");
        int capacity = inputINT("Please enter the capacity of room " + code);
        Room newRoom = new Room(code, capacity);
        this.rooms.add(newRoom);
        print("Room " + code + "was successfully added");
    }

    /**
     * Methods below are used to populate the ArrayLists with hard-coded objects for testing
     */

    public void populateAssistants() {
        // TODO: Populate assistants ArrayList
    }

    public void populateRooms() {
        // TODO: Populate rooms arrayList
    }
}
