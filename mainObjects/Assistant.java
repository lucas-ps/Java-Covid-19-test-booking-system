package UoKCovid19TestBookingSystem.mainObjects;

public class Assistant {

    // Attributes

    private int ID;
    private String name;
    private String email;

    // Constructor

    public Assistant(int ID,
                     String name,
                     String email){
        this.ID = ID;
        this.name = name;
        this.email = email;
    }

    // Methods

    public int getID(){
        return this.ID;
    }
}
