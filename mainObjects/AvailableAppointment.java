package UoKCovid19TestBookingSystem.mainObjects;

import UoKCovid19TestBookingSystem.helperModules.TimeSlot;

public class AvailableAppointment {

    // Attributes

    private TimeSlot timeSlot;
    private Room room;
    private Assistant assistant;

    // Constructor

    public AvailableAppointment(TimeSlot timeSlot, Room room, Assistant assistant) {
        this.timeSlot = timeSlot;
        this.room = room;
        this.assistant = assistant;
    }

    // Methods

    /**
     * Get/Set methods
     */
    public TimeSlot getTimeSlot() {return timeSlot;}
    public Room getRoom() {return room;}
    public Assistant getAssistant() {return assistant;}
}
