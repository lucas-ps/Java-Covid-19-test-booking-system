package UoKCovid19TestBookingSystem;

import java.util.Date;
import java.time.*;

import static UoKCovid19TestBookingSystem.helperFunctions.*;

public class main {

    // TODO: Validate inputs

    public static void main(String[] args) {

    // Dummy data, remove
    Room[] rooms = new Room[5];
    Assistant[] assistants = new Assistant[5];
    Date time = 2021-02-25;

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
            print(Room.formattedBookableRooms(rooms));
        }
        else if (option == 2){
            String code = inputSTR("Please enter the new room's code");
            int capacity = inputINT("Please enter the capacity of room " + code);
            Room newRoom = new Room(code, capacity);
            addRoom(newRoom);
            print("Room " + code + "was successfully added");
        }
        else if (option == 3){
            Room[] roomArray = getBookableRooms(time, rooms);
            print(Room.formattedBookableRooms(rooms));
            int roomID = inputINT("\nChoose the corresponding ID of the room you'd like to remove");
            Room roomToRemove = roomArray[roomID];
            String roomName = roomToRemove.getCode();
            removeRoom(roomToRemove);
            print("Room "+ roomName +" was successfully removed");

            // TODO: Remove appointments in specified removed room

        }
    }

    public static void addRoom(Room newRoom) {
        int roomID = rooms.length;
        roomID++;
        // Create and add new room to rooms array
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

    public static Room[] getBookableRooms(Date time, Room[] rooms){
        for (Room room:rooms) {
            // TODO: CHeck all rooms to see if they fit the time slot
        }
    }

    public static Assistant[] getAvailableAssistants(Date time, Assistant[] assistants){
        for (Assistant assistant:assistants) {
            // TODO: Check all assistants to see if they fit the time slot
        }
    }

}
