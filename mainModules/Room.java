package UoKCovid19TestBookingSystem.mainModules;

import UoKCovid19TestBookingSystem.helperModules.*;

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
}
