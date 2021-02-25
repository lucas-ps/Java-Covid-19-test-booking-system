package UoKCovid19TestBookingSystem.mainModules;

import UoKCovid19TestBookingSystem.mainModules.Shift;

public class Assistant {

    // Attributes

    private int ID;
    private String name;
    private String email;
    private Shift[] shifts;

    // Constructor

    public Assistant(int ID,
                     String name,
                     String email){
        this.ID = ID;
        this.name = name;
        this.email = email;
    }

    // Methods
}
