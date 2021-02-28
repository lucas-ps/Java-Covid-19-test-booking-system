package UoKCovid19TestBookingSystem.mainObjects;

import UoKCovid19TestBookingSystem.helperModules.*;

public class AssistantShift {

    // Attributes

    private Assistant assistantID;
    private TimeSlot shift;

    // Constructor

    public AssistantShift(Assistant assistantID,
                          TimeSlot shift){
        this.assistantID = assistantID;
        this.shift = shift;
    }

    // Methods

}
