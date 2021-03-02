package UoKCovid19TestBookingSystem.mainObjects;

import UoKCovid19TestBookingSystem.helperModules.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Assistant {

    // Attributes

    private int ID;
    private String name;
    private String email;
    private AssistantStatus status;
    private TimeSlot shift;

    // Constructor

    public Assistant(int ID,
                     String name,
                     String email,
                     AssistantStatus status,
                     TimeSlot shift){
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.status = status;
        this.shift = shift;
    }

    // Methods

    public int getID(){
        return this.ID;
    }

    public TimeSlot getShift() {
        return this.shift;
    }

    public AssistantStatus getStatus() {
        return this.status;
    }

    public void setStatus(AssistantStatus status) {
        this.status = status;
    }

    /**
     * @return  assistant attributes in the form | <ID> | <Name> | <Email> | <Status>
     *     | <shift.getStartTime()> - <shift.getEndTime()> |
     */
    public String toString(){
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String formattedDateTime = time.format(formatter);
        return String.format("| %-2s | %-17s | %-20s | %-6s | %s - %s |", this.ID, this.name, this.email, this.status,
                this.shift.getStartTime(), this.shift.getEndTime());
    }
}
