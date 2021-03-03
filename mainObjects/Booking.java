package UoKCovid19TestBookingSystem.mainObjects;

import UoKCovid19TestBookingSystem.helperModules.BookingStatus;
import UoKCovid19TestBookingSystem.helperModules.TimeSlot;

import java.time.LocalDateTime;
import java.util.*;

public class Booking {

    // Attributes

    private Assistant assistantID;
    private Room room;
    private String studentEmail;
    private TimeSlot timeSlot;
    private BookingStatus status;

    // Constructor

    public Booking(Assistant assistantID,
                   Room room,
                   String studentEmail,
                   TimeSlot timeSlot,
                   BookingStatus status){
        this.assistantID = assistantID;
        this.room = room;
        this.studentEmail = studentEmail;
        this.timeSlot = timeSlot;
        this.status = status;
    }

    // Methods


    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public Assistant getAssistantID() {
        return this.assistantID;
    }

    public TimeSlot getTimeSlot() {
        return this.timeSlot;
    }

    public Room getRoom() {
        return this.room;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    /**
     * @return booking attributes in the form | <assistantID> | <room> | <studentEmail> | <timeSlot.getStartTime()> -
     * <timeSlot.getEndTime()> | <status> |
     */
    public String toString() {
        return String.format("| %-12s | %-4s | %-15s | %s - %s | %-9s |", this.assistantID.getID(), this.room.getCode(), this.studentEmail,
                this.timeSlot.getStartTime(), this.timeSlot.getEndTime(), this.status);
    }
}
