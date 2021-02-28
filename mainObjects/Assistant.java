package UoKCovid19TestBookingSystem.mainObjects;

import UoKCovid19TestBookingSystem.helperModules.AssistantStatus;

public class Assistant {

    // Attributes

    private int ID;
    private String name;
    private String email;
    private AssistantStatus status;

    // Constructor

    public Assistant(int ID,
                     String name,
                     String email,
                     AssistantStatus status){
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.status = status;
    }

    // Methods

    public int getID(){
        return this.ID;
    }

    public void setStatus(AssistantStatus status) {
        this.status = status;
    }
}
