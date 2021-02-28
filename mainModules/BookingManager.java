package UoKCovid19TestBookingSystem.mainModules;

import UoKCovid19TestBookingSystem.mainObjects.Assistant;
import UoKCovid19TestBookingSystem.mainObjects.Booking;
import UoKCovid19TestBookingSystem.mainObjects.Room;

import java.util.*;

import static UoKCovid19TestBookingSystem.helperModules.helperFunctions.*;

public class BookingManager {

    // TODO: Validate inputs

    /**
     * The main function of the program, creates the UoK object and calls the mainMenu() method
     * @param args unused, would store arguments for main
     */
    public static void main(String[] args) {

        // Creating UoK object and populating it
        ArrayList<Room> rooms = new ArrayList<>();
        ArrayList<Assistant> assistants = new ArrayList<>();
        ArrayList<Booking> bookings = new ArrayList<>();

        University UoK = new University(rooms, assistants, bookings);
        UoK.populateAssistants();
        UoK.populateRooms();
        mainMenu(UoK);
    }


    /**
     * Outputs main menu of the program and allows user to choose what they want to do
     * @param university UniversityResources object which stores the uni's rooms and assistant ArrayLists
     */
    public static void mainMenu(University university){

        // TODO: Check which assistants/rooms are available, update their status

        System.out.flush();
        int option = inputINT("University of Knowledge - COVID test\n" +
                 "\n" +
                 "Manage Bookings\n" +
                 "Please, enter the corresponding number to select your option:\n" +
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
                "\n" +
                "Input: ");

        while (option > 10 | option < -1){
            option = inputINT(option + " is not a valid option. Please enter a valid option\n");
        }

        if (option == 0) mainMenu(university);
        else if (option == 1) university.formattedBookableRooms();
        else if (option == 2) university.addRoom();
        else if (option == 3) university.removeRoom();
        else if (option == 4) university.formattedAvailableAssistants();
        else if (option == 5) university.addAssistant();
        else if (option == 6) university.removeAssistant();
        else if (option == 7) university.formattedBookings();
        else if (option == 8) university.addBooking();
        else if (option == 9) university.removeBooking();
        else if (option == 10) university.concludeBooking();
        else if (option == -1) System.exit(0);
        // TODO: Stop program from ending after executing one of these

        int input = 2;
        while (input > 0 | input < -1) {
            input = inputINT("Press 0 to go back to main menu or -1 (or ctrl+c) to quit this application.\n" +
                    "Input: ");
            if (input == 0) {
                mainMenu(university);
            } else System.exit(0);
        }
        }
}
