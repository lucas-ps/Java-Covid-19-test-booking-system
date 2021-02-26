package UoKCovid19TestBookingSystem.mainModules;

public class Room {
    // Attributes

    private String code;
    private int capacity;

    // Constructor

    public Room(String code,
                int capacity){
        this.code = code;
        this.capacity = capacity;
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
}
