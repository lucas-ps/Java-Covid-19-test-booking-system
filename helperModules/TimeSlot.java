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
}
