package UoKCovid19TestBookingSystem.mainModules;

import UoKCovid19TestBookingSystem.helperModules.*;
import UoKCovid19TestBookingSystem.mainObjects.*;


import java.time.*;
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
        for (Assistant assistant : this.assistants) {
            if (assistant.getID() == ID) {
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
        for (Room room : this.rooms) {
            if (room.getCode().equals(code)) {
                return room;
            }
        }
        return null;
    }

    public int getRoomOccupancy(Room room, LocalDateTime time) {
        int occupancy = 0;
        for (Booking booking : getBooking(room)) {
            if (booking.getTimeSlot().isTimeInTimeSlot(LocalDateTime.now())) {
                occupancy++;
            }
        }
        print(occupancy);
        return occupancy;
    }

    /**
     * Search for bookings by room
     *
     * @param room the room being searched for
     * @return an arraylist of bookings in the room
     */
    public ArrayList<Booking> getBooking(Room room) {
        ArrayList<Booking> bookings = new ArrayList<>();
        for (Booking booking : this.bookings) {
            if (booking.getRoom().equals(room)) {
                bookings.add(booking);
            }
        }
        return bookings;
    }

    /**
     * Search for bookings by assistant
     *
     * @param assistant the assistant object that is being searched for
     * @return an ArrayList of bookings where the assistant is the one specified
     */
    public ArrayList<Booking> getBooking(Assistant assistant) {
        ArrayList<Booking> bookings = new ArrayList<>();
        for (Booking booking : this.bookings) {
            if (booking.getAssistantID().equals(assistant)) {
                bookings.add(booking);
            }
        }
        return bookings;
    }

    // To manage Bookable Rooms

    /**
     * @return Formatted string with all bookable rooms and their details
     */
    public void formattedBookableRooms() {
        LocalDateTime now = LocalDateTime.now();
        String formattedBookableRooms = "| Current Date/Time | Status    | Code | Occupancy |\n";
        for (Room room : rooms) {
            formattedBookableRooms += room.toString(getRoomOccupancy(room, now)) + "\n";
        }
        print(formattedBookableRooms);
    }


    /**
     * Removes a room from the rooms ArrayList
     */
    public void removeRoom() {
        this.formattedBookableRooms();
        String roomCode = inputSTR("\nEnter the code of the room you'd like to remove");
        Room roomToRemove = getRoom(roomCode);
        if (roomToRemove != null) {// Removing all bookings in this room
            for (Booking booking : getBooking(roomToRemove)) {
                String email = ("Booking " + booking.getStudentEmail());
                bookings.remove(booking);
                print("Booking for " + email + " removed (booking was to take place in removed room)");
            }
            rooms.remove(roomToRemove);
            print("Room " + roomCode + " was successfully removed");
        }
        else print("Room " + roomCode + " was not found on the system.");
    }

    /**
     * Adds new room to rooms ArrayList, room created by user through inputs
     */
    public void addRoom() {
        String code = inputSTR("Please enter the new room's code");
        int capacity = inputINT("Please enter the capacity of room " + code);
        String status;
        print("Please enter a valid room status (EMPTY/AVAILABLE/FULL)");
        status = inputSTR("What is the status of room " + code);
        if (status.equals("EMPTY") || status.equals("AVAILABLE") || status.equals("FULL")){
            RoomStatus roomStatus = RoomStatus.valueOf(status);
            Room newRoom = new Room(code, capacity, roomStatus);
            this.rooms.add(newRoom);
            print("Room " + code + "was successfully added");
        }
        else print("Invalid RoomStatus entered, no new room has been added");
    }

    /**
     * Creates and adds room to ArrayList where parameters are already specified
     *
     * @param code     the new room's room code
     * @param capacity the new room's capacity
     * @param status   the new rooms status (EMPTY, AVAILABLE, FULL)
     */
    public void addRoom(String code, int capacity, RoomStatus status) {
        Room newRoom = new Room(code, capacity, status);
        this.rooms.add(newRoom);
    }

    // To manage Assistants on Shift

    public void formattedAvailableAssistants() {
        // TODO: formattedAvailableAssistants()
    }

    public void addAssistant() {
        // TODO: addAssistant()
    }

    /**
     * Creates and adds new assistant to ArrayList where the parameters are already specified
     *
     * @param ID     the new assistant's ID
     * @param name   the new assistant's name
     * @param email  the new assistant's email address
     * @param status the new assistant's current status (FREE, BUSY)
     */
    public void addAssistant(int ID, String name, String email, AssistantStatus status, TimeSlot  shift) {
        Assistant newAssistant = new Assistant(ID, name, email, status, shift);
        this.assistants.add(newAssistant);
    }

    public void removeAssistant() {
        // TODO: removeAssistant()
    }

    // To manage Bookings

    public void formattedBookings() {
        // TODO: formattedBookings()
    }

    public void addBooking() {
        // TODO: addBooking()
    }

    /**
     * Creates and adds new booking to ArrayList where parameters are already specified
     *
     * @param assistantID  which assistant will handle the booking
     * @param room         which room the booking will take place in
     * @param studentEmail the student's email
     * @param timeSlot     the booking's timeslot
     * @param status       the booking's status (SCHEDULED, COMPLETED)
     */
    private void addBooking(Assistant assistantID, Room room, String studentEmail, TimeSlot timeSlot,
                           BookingStatus status) {
        Booking newBooking = new Booking(assistantID, room, studentEmail, timeSlot, status);
        this.bookings.add(newBooking);
    }

    public void removeBooking() {
        // TODO: removeBooking()
    }

    public void concludeBooking() {
        // TODO: concludeBooking()
    }

    /**
     * Update assistant statuses (FREE, BUSY) based on whether they are currently in an appointment
     */
    public void updateAssistantStatuses() {
        LocalDateTime now = LocalDateTime.now();
        for (Assistant assistant : this.assistants) {
             if (assistant.getShift().isTimeInTimeSlot(now)) {
                 assistant.setStatus(AssistantStatus.FREE);
                 if (getBooking(assistant).size() != 0) {
                     for (Booking booking : getBooking(assistant)) {
                         if (booking.getTimeSlot().isTimeInTimeSlot(now)) {
                             assistant.setStatus(AssistantStatus.BUSY);
                         }
                     }
                 }
             }
             else {
                 assistant.setStatus(AssistantStatus.NOT_ON_SHIFT);
             }
        }
    }

    /**
     * Update room statuses (EMPTY, AVAILABLE, FULL) based on their current occupancy and capacity
     */
    public void updateRoomStatuses() {
        LocalDateTime now = LocalDateTime.now();
        for (Room room : rooms) {
            int occupancy = getRoomOccupancy(room, now);
            if (occupancy == room.getCapacity()) {
                room.setStatus(RoomStatus.FULL);
            }
            if (occupancy == 0) {
                room.setStatus(RoomStatus.EMPTY);
            }
            else {
                room.setStatus(RoomStatus.AVAILABLE);
            }

        }
    }

    /**
     * Update bookind statuses based on whether their TimeSlot is over yet
     */
    public void updateBookingStatuses() {
        LocalDateTime now = LocalDateTime.now();
        for (Booking booking : this.bookings) {
            if (booking.getTimeSlot().isTimeAfterTimeSlot(now)) {
                booking.setStatus(BookingStatus.COMPLETED);
            }
            else booking.setStatus(BookingStatus.SCHEDULED);
        }
    }

    /**
     * Populating ArrayLists with data, used excel spreadsheet to generate constructors
     */
    public void populateAssistants() {
        // TimeSlot objects for assistant shifts
        LocalDateTime datetime = LocalDateTime.now();
        LocalDate date = LocalDate.now();
        TimeSlot nineToFive = new TimeSlot(date.atTime(9, 0),date.atTime(17, 0));
        TimeSlot nowMinusEightHours = new TimeSlot(roundedTime(datetime),roundedTime(datetime).minusHours(8));

        // Creating assistants
        addAssistant(1,"Wanda Maximoff","W.Maximoff@UoK.ac.uk",AssistantStatus.BUSY,nowMinusEightHours);
        addAssistant(2,"Agatha Harkness","A.Harkness@UoK.ac.uk",AssistantStatus.BUSY,nowMinusEightHours);
        addAssistant(3,"Jimmy Woo","J.Woo@UoK.ac.uk",AssistantStatus.BUSY,nowMinusEightHours);
        addAssistant(4,"Darcy Lewis","D.Lewis@UoK.ac.uk",AssistantStatus.BUSY,nowMinusEightHours);
        addAssistant(5,"Monica Rambeau","M.Rambeau@UoK.ac.uk",AssistantStatus.BUSY,nowMinusEightHours);
        addAssistant(6,"Vision Maximoff","V.Maximoff@UoK.ac.uk",AssistantStatus.BUSY,nowMinusEightHours);
        addAssistant(7,"Javier Freeman","J.Freeman@UoK.ac.uk",AssistantStatus.BUSY,nineToFive);
        addAssistant(8,"Skyler Spears","S.Spears@UoK.ac.uk",AssistantStatus.BUSY,nineToFive);
        addAssistant(9,"Madalyn Levine","M.Levine@UoK.ac.uk",AssistantStatus.BUSY,nineToFive);
        addAssistant(10,"Christopher Bentley","C.Bentley@UoK.ac.uk",AssistantStatus.BUSY,nineToFive);
        addAssistant(11,"Oliver Kidd","O.Kidd@UoK.ac.uk",AssistantStatus.BUSY,nineToFive);
        addAssistant(12,"Marvin Rodgers","M.Rodgers@UoK.ac.uk",AssistantStatus.BUSY,nineToFive);

        // Checking and correcting assistant statuses
        updateAssistantStatuses();

    }

    public void populateRooms() {
        addRoom("A1",3,RoomStatus.FULL);
        addRoom("B2",5,RoomStatus.AVAILABLE);
        addRoom("C3",10,RoomStatus.EMPTY);
    }

    public void populateBookings() {
        // Creating TimeSlot objects for bookings
        LocalDateTime datetime = LocalDateTime.now();
        TimeSlot now;
        now = new TimeSlot(roundedTime(datetime),roundedTime(datetime).plusMinutes(15));
        TimeSlot thirtyMinsAgo;
        thirtyMinsAgo = new TimeSlot((roundedTime(datetime)).minusMinutes(60),(roundedTime(datetime)).minusMinutes(30));

        // Creating bookings
        addBooking(getAssistant(1),getRoom("A1"),"lp565@UoK.ac.uk",now,BookingStatus.SCHEDULED);
        addBooking(getAssistant(2),getRoom("A1"),"es623@UoK.ac.uk",now,BookingStatus.SCHEDULED);
        addBooking(getAssistant(3),getRoom("A1"),"hc389@UoK.ac.uk",now,BookingStatus.SCHEDULED);
        addBooking(getAssistant(4),getRoom("B2"),"fg710@UoK.ac.uk",now,BookingStatus.SCHEDULED);
        addBooking(getAssistant(5),getRoom("B2"),"nn777@UoK.ac.uk",now,BookingStatus.SCHEDULED);
        addBooking(getAssistant(6),getRoom("B2"),"gh123@UoK.ac.uk",thirtyMinsAgo,BookingStatus.COMPLETED);
    }

    /**
     * Rounding times to nearest quarter hour (assuming bookings take 15 mins, just to make bookings look tidier)
     *
     * @param time datetime entered (usually using LocalDateTime.now)
     * @return LocalDateTime rounded down to nearest 15 mins
     */
    public static LocalDateTime roundedTime(LocalDateTime time) {
        int minutes = time.getMinute();
        int remainder = minutes % 15;
        LocalDateTime roundedTime = time.minusMinutes(remainder);
        return roundedTime;
        }

}
