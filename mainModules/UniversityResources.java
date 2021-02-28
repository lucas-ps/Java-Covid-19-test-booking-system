package UoKCovid19TestBookingSystem.mainModules;

import UoKCovid19TestBookingSystem.helperModules.RoomStatus;

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

    public UniversityResources(ArrayList<Room> rooms, ArrayList<Assistant> assistants) {

        this.assistants = rooms;
        this.rooms = assistants;
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
    public void formattedBookableRooms() {
        print("");
        // TODO: Formatted available rooms
    }

    /**
     * Removes a room from the rooms ArrayList
     */
    public void removeRoom() {
        this.formattedBookableRooms();
        String roomCode = inputSTR("\nEnter the code of the room you'd like to remove");
        for (var i = 0; i < rooms.size(); i++) {
            if(rooms.get(i).getCode() == roomCode) {
                rooms.remove(rooms[i]);
                print("Room "+ roomCode +" was successfully removed");
                // TODO: Remove all bookings in this room
            }
        }
        print("Room " + roomCode + " was not found on the system.");
    }

    /**
     * Adds new room to rooms ArrayList, room created by user through inputs
     */
    public void addRoom() {
        String code = inputSTR("Please enter the new room's code");
        int capacity = inputINT("Please enter the capacity of room " + code);
        String status = "";
        while (!(status.equals("EMPTY"))||!(status.equals("AVAILABLE"))||!(status.equals("FULL")));
            print ("Please enter a valid room status (EMPTY/AVAILABLE/FULL");
            status = inputSTR("What is the status of room "+ code);
        RoomStatus roomStatus = RoomStatus.valueOf(status);
        // TODO: Not sure if that works, test it
        Room newRoom = new Room(code, capacity, roomStatus);
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
