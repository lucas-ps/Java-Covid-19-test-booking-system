package UoKCovid19TestBookingSystem.mainModules;

import UoKCovid19TestBookingSystem.helperModules.*;
import UoKCovid19TestBookingSystem.mainObjects.*;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import static UoKCovid19TestBookingSystem.helperModules.helperFunctions.*;

/*
 * Class for creation of dummy data
 */
public class University {

    // Attributes

    public ArrayList<Room> rooms;
    public ArrayList<Assistant> assistants;
    public ArrayList<Booking> bookings;

    // Constructor

    public University(ArrayList<Room> rooms,
                      ArrayList<Assistant> assistants,
                      ArrayList<Booking> bookings) {
        this.rooms = rooms;
        this.assistants = assistants;
        this.bookings = bookings;
    }

    // Methods

    /**
     * @param ID The provided ID of the assistant that needs to be found
     * @return the Assistant object found (if it is found)
     */
    public Assistant getAssistant(int ID) {
        for(Assistant assistant : assistants) {
            if(assistant.getID() == ID) {
                return assistant;
            }
        }
        return null;
    }

    /**
     * @param code The provided code of the room that needs to be found
     * @return the Room object found
     */
    public Room getRoom(String code) {
        for(Room room : rooms) {
            if(room.getCode() == code) {
                return room;
            }
        }
        return null;
    }

    // To manage Bookable Rooms
    /**
     * @return Formatted string with all bookable rooms and their details
     */
    public void formattedBookableRooms() {
        String formattedBookableRooms = "";
        for (Room room : rooms) {
            formattedBookableRooms += room.toString(room.getOccupancy(bookings));
        }
        print(formattedBookableRooms);
    }



    /**
     * Removes a room from the rooms ArrayList
     */
    public void removeRoom() {
        this.formattedBookableRooms();
        String roomCode = inputSTR("\nEnter the code of the room you'd like to remove");
        for (var i = 0; i < rooms.size(); i++) {
            if(rooms.get(i).getCode() == roomCode) {
                rooms.remove(rooms.get(i));
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
        while (!(status == "EMPTY")|!(status == "AVAILABLE")|!(status == "FULL"));
            print ("Please enter a valid room status (EMPTY/AVAILABLE/FULL)");
            status = inputSTR("What is the status of room "+ code);
        RoomStatus roomStatus = RoomStatus.valueOf(status);
        // TODO: Not sure if that works, test it
        Room newRoom = new Room(code, capacity, roomStatus);
        this.rooms.add(newRoom);
        print("Room " + code + "was successfully added");
    }

    /**
     * Creates and adds room to ArrayList where parameters are already specified
     * @param code the new room's room code
     * @param capacity the new room's capacity
     * @param status the new rooms status (EMPTY/AVAILABLE/FULL)
     */
    public void addRoom(String code, int capacity, RoomStatus status) {
        Room newRoom = new Room(code, capacity, status);
        this.rooms.add(newRoom);
    }

    // To manage Assistants on Shift

    public void formattedAvailableAssistants(){
        // TODO: formattedAvailableAssistants()
    }

    public void addAssistant(){
        // TODO: addAssistant()
    }

    /**
     * Creates and adds new assistant to ArrayList where the parameters are already specified
     * @param ID
     * @param name
     * @param email
     */
    public void addAssistant(int ID, String name, String email, AssistantStatus status) {
        Assistant newAssistant = new Assistant(ID, name, email, status);
        this.assistants.add(newAssistant);
    }

    public void removeAssistant(){
        // TODO: removeAssistant()
    }

    // To manage Bookings

    public void formattedBookings(){
        // TODO: formattedBookings()
    }

    public void addBooking(){
        // TODO: addBooking()
    }
    public void addBooking(Assistant assistantID, Room room, String studentEmail, TimeSlot timeSlot,
                           BookingStatus status){
        Booking newBooking = new Booking(assistantID, room, studentEmail, timeSlot, status);
        this.bookings.add(newBooking);
    }

    public void removeBooking(){
        // TODO: removeBooking()
    }

    public void concludeBooking(){
        // TODO: concludeBooking()
    }

    /**
     * Populating ArrayLists with data
     */
    public void populateAssistants() {
        // TODO: Populate assistants ArrayList
    }
    public void populateRooms() {
        // TODO: Populate rooms arrayList
    }
    public void populateBookings() {
        // TODO: Populate bookings arraylist
    }
}
