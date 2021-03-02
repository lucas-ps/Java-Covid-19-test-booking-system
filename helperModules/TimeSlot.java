package UoKCovid19TestBookingSystem.helperModules;

import java.time.*;

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

    public boolean isTimeInTimeSlot(LocalDateTime time) {
        if (time.isAfter(this.startTime) && time.isBefore(this.finishTime)) {
            return true;
        }
        return false;
    }

    public boolean isTimeAfterTimeSlot(LocalDateTime time) {
        if (time.isAfter(this.finishTime)) {
            return true;
        }
        return false;
    }
}