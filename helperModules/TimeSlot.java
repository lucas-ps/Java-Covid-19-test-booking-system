package UoKCovid19TestBookingSystem.helperModules;

import java.time.*;
import java.time.format.*;
import java.util.Timer;

public class TimeSlot {

    // Attributes

    private LocalDateTime startTime;
    private LocalDateTime finishTime;

    // Constructor

    public TimeSlot(LocalDateTime startTime, LocalDateTime finishTime){
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    // Methods

    /**
     * @return A string version of the compiled start time and finish time
     */
    public String toString() {
        return String.format("| %s | %s |", this.startTime, this.finishTime);
    }

    /**
     * Checks if time provided is within the TimeSlot object
     * @param time the time that needs to be checked
     * @return boolean true/false, true if time is within TimeSlot
     */
    public boolean isTimeInTimeSlot(LocalDateTime time) {
        if (time.isAfter(this.startTime) && time.isBefore(this.finishTime)) {
            return true;
        }
        return false;
    }

    /**
     * Checks if time provided comes after the end time of the TimeSlot object
     * @param time the time that needs to be checked
     * @return boolean true/false, true if time is after TimeSlot
     */
    public boolean isTimeAfterTimeSlot(LocalDateTime time) {
        if (time.isAfter(this.finishTime)) {
            return true;
        }
        return false;
    }

    /**
     * @return a string version of the object's startTime in the form "HH:mm"
     */
    public String getStartTime() {
        DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm");
        String formattedStartTime = this.startTime.format(time);
        return formattedStartTime;
    }

    /**
     * @return a string version of the object's start time and date in the form "dd/MM/yyyy HH:mm"
     */
    public String getStartDateTime() {
        DateTimeFormatter time = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String formattedStartTime = this.startTime.format(time);
        return formattedStartTime;
    }

    /**
     * @return a string version of the object's endTime in the form "HH:mm"
     */
    public String getEndTime() {
        DateTimeFormatter time = DateTimeFormatter.ofPattern("HH:mm");
        String formattedEndTime = this.finishTime.format(time);
        return formattedEndTime;
    }
}
