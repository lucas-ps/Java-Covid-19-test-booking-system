package UoKCovid19TestBookingSystem.mainObjects;

import UoKCovid19TestBookingSystem.helperModules.*;

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

    public void setStatus(AssistantStatus status) {
        this.status = status;
    }
}
