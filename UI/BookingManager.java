package UoKCovid19TestBookingSystem.UI;

import UoKCovid19TestBookingSystem.mainModules.Assistant;
import UoKCovid19TestBookingSystem.mainModules.Room;
import UoKCovid19TestBookingSystem.mainModules.UniversityResources;

import java.util.*;
import java.time.*;

import static UoKCovid19TestBookingSystem.helperModules.helperFunctions.*;

public class BookingManager {

    // TODO: Validate inputs

    public static void main(String[] args) {

    // Dummy data, remove
    ArrayList<Room> rooms = new ArrayList<>();
    ArrayList<Assistant> assistants = new ArrayList<>();
    LocalDateTime time = LocalDateTime.now();

        print("University of Knowledge - COVID test\n" +
                "\n" +
                "Manage Bookings\n");

        int option = 11;
        while (option > 10 | option < -1){
            option = mainMenu();
            if (option > 10 | option < -1){
                print(option + " is not a valid option.\n");
            }
        }

        if (option == 1){
            print(UniversityResources.formattedBookableRooms());
        }
        else if (option == 2){
            UniversityResources.addRoom();
        }
        else if (option == 3){
            UniversityResources.removeRoom();
        }
    }

    public static int mainMenu(){
        System.out.flush();
        String mainMenu = "Please, enter the corresponding number to select your option:\n" +
                "\n" +
                "To manage Bookable Rooms:\n" +
                "\t1. List\n" +
                "\t2. Add\n" +
                "\t3. Remove\n" +
                "To manage Assistants on Shift:\n" +
                "\t4. List\n" +
                "\t5. Add\n" +
                "\t6. Remove\n" +
                "To manage Bookings:\n" +
                "\t7. List\n" +
                "\t8. Add\n" +
                "\t9. Remove\n" +
                "\t10. Conclude\n" +
                "After selecting one the options above, you will be presented other screens.\n" +
                "If you press 0, you will be able to return to this main menu.\n" +
                "Press -1 (or ctrl+c) to quit this application.\n" +
                "\n";
        int option = inputINT(mainMenu);
        return option;
    }
}
