package UoKCovid19TestBookingSystem.mainModules;

import java.time.Duration;
import java.util.*;

public class Booking {

    // Attributes

    private Date time;
    private Assistant assistantID;
    private Room room;
    private String studentEmail;
    private Duration duration;

    // Constructor

    public Booking(Date time,
                   Assistant assistantID,
                   Room room,
                   String studentEmail,
                   Duration duration){
        this.time = time;
        this.assistantID = assistantID;
        this.room = room;
        this.studentEmail = studentEmail;
        this.duration = duration;
    }

    // Methods
}
