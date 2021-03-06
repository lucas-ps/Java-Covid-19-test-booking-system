package UoKCovid19TestBookingSystem.mainObjects;

import UoKCovid19TestBookingSystem.helperModules.*;

import java.text.*;
import java.time.format.*;
import java.util.*;

public class Assistant {

    // Attributes

    private int ID;
    private String name;
    private String email;
    private AssistantStatus status;
    private TimeSlot shift;
    private ArrayList<Date> DaysWorking;

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
        this.DaysWorking = new ArrayList<Date>();
        addWorkingDay(Calendar.getInstance().getTime());
    }

    // Methods

    /**
     * Get/Set methods
     */
    public int getID(){return this.ID;}
    public TimeSlot getShift() {return this.shift;}
    public AssistantStatus getStatus() {return this.status;}
    public void setStatus(AssistantStatus status) {this.status = status;}
    public void addWorkingDay(Date date) {this.DaysWorking.add(date);}

    /**
     * Removes a working day from the DaysWorking ArrayList
     * @param date
     */
    public void removeWorkingDay(Date date) {
        this.DaysWorking.remove(date);
    }

    /**
     * @return  assistant attributes in the form | <ID> | <Name> | <Email> | <Status>
     *     | <shift.getStartTime()> - <shift.getEndTime()> |
     */
    public String toString(){
        String formatted = String.format("| %-2s | %-19s | %-20s | %-12s | %s - %s |", this.ID, this.name, this.email,
                this.status,this.shift.getStartTime(), this.shift.getEndTime());
        return formatted;
    }

    /**
     * @return  assistant attributes in the form | <ID> | <Name> | <Email> | <shift.getStartTime()> -
     * <shift.getEndTime()> | <date> |
     */
    public String toString(Date date){
        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattedDate = dateFormat.format(date);
        return String.format("| %-2s | %-19s | %-20s | %s - %s | %s |", this.ID, this.name, this.email,
                this.shift.getStartTime(), this.shift.getEndTime(), formattedDate);
    }
}
