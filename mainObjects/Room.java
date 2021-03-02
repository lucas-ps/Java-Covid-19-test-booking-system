package UoKCovid19TestBookingSystem.mainObjects;

import UoKCovid19TestBookingSystem.helperModules.*;

import java.time.*;
import java.time.format.*;
import java.util.*;


public class Room {
    // Attributes

    private String code;
    private int capacity;
    private RoomStatus status;

    // Constructor

    public Room(String code,
                int capacity,
                RoomStatus status){
        this.code = code;
        this.capacity = capacity;
        this.status = status;
        }

    // Methods

    /**
     * Get/Set methods
     */

    public String getCode() {
        return code;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public int getOccupancy(ArrayList Booking) {
        // TODO: Get occupancy from bookings ArrayList
        return 0;
    }


    /**
     * @return  room attributes in the form | <dd/mm/yyyy HH:MM> | <status> | <room_code> | occupancy: <occupancy> |
     */
    public String toString(int occupancy){
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = time.format(formatter);
        return String.format("| %-17s | %-9s | %-4s | %s/%-7s |",formattedDateTime, this.status ,this.code, occupancy, this.capacity);
    }
}
