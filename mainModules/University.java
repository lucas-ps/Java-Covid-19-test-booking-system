package UoKCovid19TestBookingSystem.mainModules;

import UoKCovid19TestBookingSystem.helperModules.*;
import UoKCovid19TestBookingSystem.mainObjects.*;


import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static UoKCovid19TestBookingSystem.helperModules.helperFunctions.*;

/*
 * Class for creation of dummy data
 */
public class University {

    // Attributes

    public ArrayList<Room> rooms;
    public ArrayList<Assistant> assistants;
    public ArrayList<Booking> bookings;

    // Constructor

    public University(ArrayList<Room> rooms,
                      ArrayList<Assistant> assistants,
                      ArrayList<Booking> bookings) {
        this.rooms = rooms;
        this.assistants = assistants;
        this.bookings = bookings;
    }

    // Methods

    /**
     * @param ID The provided ID of the assistant that needs to be found
     * @return the Assistant object found (if it is found)
     */
    public Assistant getAssistant(int ID) {
        for (Assistant assistant : this.assistants) {
            if (assistant.getID() == ID) {
                return assistant;
            }
        }
        return null;
    }

    /**
     * @param code The provided code of the room that needs to be found
     * @return the Room object found
     */
    public Room getRoom(String code) {
        for (Room room : this.rooms) {
            if (room.getCode().equals(code)) {
                return room;
            }
        }
        return null;
    }

    public int getRoomOccupancy(Room room, LocalDateTime time) {
        int occupancy = 0;
        for (Booking booking : getBooking(room)) {
            if (booking.getTimeSlot().isTimeInTimeSlot(LocalDateTime.now())) {
                occupancy++;
            }
        }
        if (occupancy == room.getCapacity()){
            room.setStatus(RoomStatus.FULL);
        }
        else if (occupancy == 0){
            room.setStatus(RoomStatus.EMPTY);
        }
        else room.setStatus(RoomStatus.AVAILABLE);
        return occupancy;
    }

    /**
     * Search for bookings by room
     *
     * @param room the room being searched for
     * @return an arraylist of bookings in the room
     */
    public ArrayList<Booking> getBooking(Room room) {
        ArrayList<Booking> bookings = new ArrayList<>();
        for (Booking booking : this.bookings) {
            if (booking.getRoom().equals(room)) {
                bookings.add(booking);
            }
        }
        return bookings;
    }

    /**
     * Search for bookings by assistant
     *
     * @param assistant the assistant object that is being searched for
     * @return an ArrayList of bookings where the assistant is the one specified
     */
    public ArrayList<Booking> getBooking(Assistant assistant) {
        ArrayList<Booking> bookings = new ArrayList<>();
        for (Booking booking : this.bookings) {
            if (booking.getAssistantID().equals(assistant)) {
                bookings.add(booking);
            }
        }
        return bookings;
    }

    // To manage Bookable Rooms
    // TODO: Fix formatting of first 3 methods in line with specification

    /**
     * @return Formatted string with all bookable rooms and their details
     */
    public void formattedBookableRooms() {
        LocalDateTime now = LocalDateTime.now();
        String formattedBookableRooms = "| Current Date/Time | Status    | Code | Occupancy |\n";
        for (Room room : rooms) {
            if (!RoomStatus.FULL.equals(room.getStatus())) {
                formattedBookableRooms += room.toString(getRoomOccupancy(room, now)) + "\n";
            }
        }
        print(formattedBookableRooms);

        do {String input = inputSTR("Please, enter one of the following:\n" +
                "\n" +
                "0. Back to main menu.\n" +
                "-1. Quit application.\n" +
                "\n" +
                "Input:");
            if (input.equals("-1") || input.equals("0")) {
                BookingManager.refreshOrExit(input);
            } else print("Invalid input");
        } while (true);
    }

    public void formattedAllRooms() {
        LocalDateTime now = LocalDateTime.now();
        String formattedBookableRooms = "| Current Date/Time | Status    | Code | Occupancy |\n";
        for (Room room : rooms) {
            formattedBookableRooms += room.toString(getRoomOccupancy(room, now)) + "\n";
        }
        print(formattedBookableRooms);
    }

    public void formattedEmptyRooms() {
        LocalDateTime now = LocalDateTime.now();
        String formattedBookableRooms = "| Current Date/Time | Status    | Code | Occupancy |\n";
        for (Room room : rooms) {
            if (RoomStatus.EMPTY.equals(room.getStatus())) {
                formattedBookableRooms += room.toString(getRoomOccupancy(room, now)) + "\n";
            }
        }
        print(formattedBookableRooms);
    }


    /**
     * Removes specified empty room from the rooms ArrayList
     */
    public void removeRoom() {
        print("University of Knowledge - COVID test\n" +
                "\n" +
                "Removing bookable room\n" +
                "\n");
        formattedEmptyRooms();
        do {
            String input = inputSTR("Please, enter one of the following:\n" +
                    "\n" +
                    "The room code of the bookable room to be removed.\n" +
                    "0. Back to main menu.\n" +
                    "-1. Quit application.\n" +
                    "\n" +
                    "Input:");
            if (input.equals("-1") || input.equals("0")) {
                BookingManager.refreshOrExit(input);
            }
            try{
                Room roomToRemove = getRoom(input);
                String removedRoom = "| Current Date/Time | Status    | Code | Occupancy |\n" +
                        roomToRemove.toString(0);
                this.rooms.remove(roomToRemove);
                print("\nBookable Room removed successfully:\n" +
                        removedRoom + "\n");
            } catch (Exception e){
                print("Invalid input, input must be in the format 'RoomCode'");
            }
        } while (true);
    }

    /**
     * Make specified room available
     */
    public void addRoom() {
        print("University of Knowledge - COVID test\n" +
                "\n" +
                "Adding bookable room\n" +
                "\n");
        formattedAllRooms();
        do {String input = inputSTR("Please, enter one of the following:\n" +
                "\n" +
                "The room's code, a date (dd/mm/yyyy), and a time (HH:MM),\n" +
                "separated by a white space.\n" +
                "0. Back to main menu.\n" +
                "-1. Quit application.\n" +
                "\n" +
                "Input:");
        if (input.equals("-1") || input.equals("0")) {
            BookingManager.refreshOrExit(input);
        }
        try {
            String[] split = input.split(" ");
            String code = split[0];
            String startTime = split[1] + " " + split[2];
            Room room = getRoom(code);
            DateTimeFormatter strToDateTime = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime timeSlotStart = LocalDateTime.parse(startTime, strToDateTime);
            String endTime = split[1] + " " + "23:59";
            LocalDateTime timeSlotEnd = LocalDateTime.parse(endTime, strToDateTime);
            TimeSlot timeSlot = new TimeSlot(timeSlotStart, timeSlotEnd);
            print("\nBookable Room added successfully:\n" +
                    "\n" +
                    "| Current Date/Time | Status    | Code | Available from   |\n" +
                    room.toString() + " " + startTime + " |\n");
        } catch (Exception e) {
            print("Invalid input, input must be in the format 'code dd/mm/yyyy HH:MM'");
        }
        } while (true);
    }

    /**
     * Creates and adds room to ArrayList where parameters are already specified
     *
     * @param code     the new room's room code
     * @param capacity the new room's capacity
     * @param status   the new rooms status (EMPTY, AVAILABLE, FULL)
     */
    public void addRoom(String code, int capacity, RoomStatus status) {
        Room newRoom = new Room(code, capacity, status);
        this.rooms.add(newRoom);
    }

    // To manage Assistants on Shift

    /**
     * prints assistants on shift attributes in the form | <ID> | <Name> | <Email> | <Status>
     *     | <shift.getStartTime()> - <shift.getEndTime()> |
     */
    public void formattedAvailableAssistants() {
        print("University of Knowledge - COVID test\n" +
                "\n" +
                "List all assistants on shift");
        LocalDateTime now = LocalDateTime.now();
        String formattedAvailableAssistants = "| ID | Name                | Email                | Status " +
                "| Shift               |\n";
        for (Assistant assistant : assistants) {
            if (!AssistantStatus.NOT_ON_SHIFT.equals(assistant.getStatus())) {
                formattedAvailableAssistants += assistant.toString() + "\n";
            }
        }
        print(formattedAvailableAssistants);
        do {
            String input = inputSTR("Please, enter the following:\n" +
                    "\n" +
                    "0. Back to main menu.\n" +
                    "-1. Quit application.\n" +
                    "\n" +
                    "Input:");
            if (input.equals("-1") || input.equals("0")) {
                BookingManager.refreshOrExit(input);
            } else print(input + " is not a valid input.");
        } while (true);
    }

    /**
     * Prints assistants with the Assistant Status "FREE" in the form | <ID> | <Name> | <Email> | <Status>
     *    | <shift.getStartTime()> - <shift.getEndTime()> |
     */
    public void formattedFreeAssistants() {
        LocalDateTime now = LocalDateTime.now();
        String formattedFreeAssistants = "| ID | Name                | Email                | Status       " +
                "| Shift         |\n";
        for (Assistant assistant : assistants) {
            if (AssistantStatus.FREE.equals(assistant.getStatus())) {
                formattedFreeAssistants += assistant.toString() + "\n";
            }
        }
        print(formattedFreeAssistants);
    }

    /**
     * Prints all assistants, regardless of status in the form | <ID> | <Name> | <Email> | <Status>
     *      *     | <shift.getStartTime()> - <shift.getEndTime()> |
     */
    public void formattedAllAssistants() {
        String formattedAvailableAssistants = "| ID | Name                | Email                | Status       " +
                "| Shift         |\n";
        for (Assistant assistant : assistants) {
            formattedAvailableAssistants += (assistant.toString() + "\n");
        }
        print(formattedAvailableAssistants);
    }

    /**
     * Processes user inputs to add an assistant on shift on a specified date
     */
    public void addAssistant() {
        System.out.flush();
        print("University of Knowledge - COVID test\n" +
                "\n" +
                "Adding assistant on shift\n");
        formattedAllAssistants();
        String input = "";
        do {
            input = inputSTR("Please, enter the following:\n" +
                    "\n" +
                    "The ID of an assistant and date (dd/mm/yyyy), separated by a white space.\n" +
                    "0. Back to main menu.\n" +
                    "-1. Quit application.\n" +
                    "\n" +
                    "Input:");
            if (input.equals("-1") || input.equals("0")) {
                BookingManager.refreshOrExit(input);
            }
            String[] split = input.split(" ");
            try {
                int id = Integer.parseInt(split[0]);
                SimpleDateFormat dateParser = new SimpleDateFormat("dd/mm/yyyy");
                Date date = dateParser.parse(split[1]);
                Assistant assistant = getAssistant(id);
                assistant.addWorkingDay(date);
                print("Assistant on Shift added successfully:\n");
                print("| ID | Name                | Email                | Shift         | Date       |\n"
                + assistant.toString(date));
            } catch (Exception e){
                print("Invalid input, input must be in the format 'ID  dd/mm/yyyy'");
            }
        } while (true);
    }

    /**
     * Creates and adds new assistant to ArrayList where the parameters are already specified
     *
     * @param ID     the new assistant's ID
     * @param name   the new assistant's name
     * @param email  the new assistant's email address
     * @param status the new assistant's current status (FREE, BUSY)
     */
    public void addAssistant(int ID, String name, String email, AssistantStatus status, TimeSlot  shift) {
        Assistant newAssistant = new Assistant(ID, name, email, status, shift);
        this.assistants.add(newAssistant);
    }

    /**
     * Processes user input to remove an assistant from shift today
     */
    public void removeAssistant() {
        print("University of Knowledge - COVID test\n" +
                "\n" +
                "Removing assistant on shift\n");
        formattedFreeAssistants();
        String input = "";
        do {
            input = inputSTR("Please, enter the following:\n" +
                    "\n" +
                    "The sequential ID to select the assistant on shift to be removed.\n" +
                    "0. Back to main menu.\n" +
                    "-1. Quit application.\n" +
                    "\n" +
                    "Input:");
            if (input.equals("-1") || input.equals("0")) {
                BookingManager.refreshOrExit(input);
            }
            try {
                int ID = Integer.parseInt(input);
                Assistant assistant = getAssistant(ID);
                assistant.removeWorkingDay(Calendar.getInstance().getTime());
                assistant.setStatus(AssistantStatus.NOT_ON_SHIFT);
                print("Assistant on Shift removed successfully:\n");
                print("| ID | Name                | Email                | Status       | Previous shift|\n"
                        + assistant.toString());

            } catch (Exception e){
                print("Invalid input, ID was not found and no assistant has been removed from shift");
            }
        } while (true);
    }

    // To manage Bookings

    /**
     *
     */
    public void formattedBookings() {
        print("University of Knowledge - COVID test\n" +
                "\n" +
                "Listing scheduled bookings\n");
        String input = "";
        input = inputSTR("Select which bookings to list:\n" +
                "1. All\n" +
                "2. Only bookings status:SCHEDULED\n" +
                "3. Only bookings status:COMPLETED\n" +
                "0. Back to main menu.\n" +
                "-1. Quit application.\n" +
                "\n" +
                "Input:");
        if (input.equals("1")) {
            listAllBookings();
        } else if (input.equals("2")) {
            listScheduledBookings();
        } else if (input.equals("3")) {
            listCompletedBookings();
        }
        else if (input.equals("-1") || input.equals("0")) {
            BookingManager.refreshOrExit(input);
        }
        else {
            print ("\nInvalid option entered, showing all bookings\n");
            listAllBookings();
        }
        do {
            input = inputSTR("0. Back to main menu.\n" +
                    "-1. Quit application.\n" +
                    "\n" +
                    "Input:");
            if (input.equals("-1") || input.equals("0")) {
                BookingManager.refreshOrExit(input);
            }
            else print("Invalid input entered");
        } while (true);
    }

    public void listAllBookings() {
        String formattedBookings = "\n| Assistant ID | Room | Student Email   | Time Slot     | Status    |\n";
        for (Booking booking : this.bookings) {
            formattedBookings += booking.toString() + "\n";
        }
        print(formattedBookings);
    }

    public void listScheduledBookings() {
        String formattedBookings = "\n| Assistant ID | Room | Student Email   | Time Slot     | Status    |\n";
        for (Booking booking : this.bookings) {
            if (booking.getStatus().equals(BookingStatus.SCHEDULED)) {
                formattedBookings += booking.toString() + "\n";
            }
        }
        print(formattedBookings);
    }

    public void listCompletedBookings() {
        String formattedBookings = "\n| Assistant ID | Room | Student Email   | Time Slot     | Status    |\n";
        for (Booking booking : this.bookings) {
            if (booking.getStatus().equals(BookingStatus.COMPLETED)) {
                formattedBookings += booking.toString() + "\n";
            }
        }
        print(formattedBookings);
    }

    public void addBooking() {
        print("University of Knowledge - COVID test\n" +
                "\n" +
                "Adding booking (appointment for a COVID test) to the system\n");

        String input = "";
        do {
            ArrayList<AvailableAppointment> availableTimeSlots = AvailableTimeslots();
            print ("\n11. " + availableTimeSlots.get(0).getTimeSlot().getStartDateTime() + "\n" +
                    "12. " + availableTimeSlots.get(1).getTimeSlot().getStartDateTime() + "\n" +
                    "13. " + availableTimeSlots.get(2).getTimeSlot().getStartDateTime() + "\n" +
                    "14. " + availableTimeSlots.get(3).getTimeSlot().getStartDateTime() + "\n" +
                    "15. " + availableTimeSlots.get(4).getTimeSlot().getStartDateTime() + "\n" +
                    "...\n");
            input = inputSTR("Please, enter one of the following:\n" +
                    "\n" +
                    "The sequential ID of an available time-slot and the student email, separated by a white space.\n" +
                    "0. Back to main menu.\n" +
                    "-1. Quit application.\n" +
                    "\n" +
                    "Input:");
            if (input.equals("-1") || input.equals("0")) {
                BookingManager.refreshOrExit(input);
            }
            String[] split = input.split(" ");
            // TODO: Validate emails
            try {
                int index = Integer.parseInt(split[0]) - 11;
                AvailableAppointment chosenAppointment = availableTimeSlots.get(index);
                Booking newBooking = new Booking(chosenAppointment.getAssistant(), chosenAppointment.getRoom(), split[1],
                        chosenAppointment.getTimeSlot(), BookingStatus.SCHEDULED);
                this.bookings.add(newBooking);
                availableTimeSlots.remove(chosenAppointment);
                print("Booking added successfully:\n" +
                        "\n| Assistant ID | Room | Student Email   | Time Slot     | Status    |\n"
                        + newBooking.toString());
            } catch (Exception e) {
                print("Invalid input, input must be in the format 'ID student@UoK.ac.uk'");
                //e.printStackTrace();
            }
        } while (true);
    }

    public ArrayList<AvailableAppointment> AvailableTimeslots() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime roundedTime = roundedTime(now).plusMinutes(15);
        ArrayList<AvailableAppointment> availableTimeSlots = new ArrayList<>();

        // Iterate for 5 soonest available appointments
        for(int i = 0; i < 4; ++i) {
            for (Assistant assistant : this.assistants) {
                if (getAssistantStatus(roundedTime, assistant).equals(AssistantStatus.FREE)) {
                    for (Room room : this.rooms) {
                        if (room.getStatus().equals(RoomStatus.AVAILABLE) || room.getStatus().equals(RoomStatus.EMPTY)) {
                            TimeSlot timeSlot = new TimeSlot(roundedTime, roundedTime.plusMinutes(15));
                            availableTimeSlots.add(new AvailableAppointment(timeSlot, room, assistant));
                            roundedTime = roundedTime.plusMinutes(15);
                        }
                    }
                }
            }
        }
        return availableTimeSlots;
    }

    /**
     * Method to get an assistant's status at a specific time
     * @param assistant the assistant object being checked
     * @param time the time being checked
     * @return AssistantStatus of the assistant at that time
     */
    public AssistantStatus getAssistantStatus(LocalDateTime time, Assistant assistant) {
        for (Booking booking : getBooking(assistant)) {
            if (booking.getTimeSlot().isTimeInTimeSlot(time)) {
                return AssistantStatus.BUSY;
            }
        }
        if (assistant.getShift().isTimeInTimeSlot(time)) {
            return AssistantStatus.FREE;
        }
        return AssistantStatus.NOT_ON_SHIFT;
    }

    /**
     * Creates and adds new booking to ArrayList where parameters are already specified
     *
     * @param assistantID  which assistant will handle the booking
     * @param room         which room the booking will take place in
     * @param studentEmail the student's email
     * @param timeSlot     the booking's timeslot
     * @param status       the booking's status (SCHEDULED, COMPLETED)
     */
    private void addBooking(Assistant assistantID, Room room, String studentEmail, TimeSlot timeSlot,
                           BookingStatus status) {
        Booking newBooking = new Booking(assistantID, room, studentEmail, timeSlot, status);
        this.bookings.add(newBooking);
    }

    public void listScheduledBookings(Boolean withID) {
        String formattedBookings = "\n| Booking ID | Assistant ID | Room | Student Email   | Time Slot     | Status    |\n";
        int i = 1;
        for (Booking booking : this.bookings) {
            if (booking.getStatus().equals(BookingStatus.SCHEDULED)) {
                formattedBookings += "| " + i + "          " + booking.toString() + "\n";
                i++;
            }
        }
        print(formattedBookings);
    }


    public void removeBooking() {
         print("University of Knowledge - COVID test\n" +
                 "\n");
         listScheduledBookings(true);
         print("Removing booking from the system");
         String input = "";
         do {
             input = inputSTR("\n" +
                     "Please, enter one of the following:\n" +
                     "\n" +
                     "The sequential ID to select the booking to be removed from the listed bookings above.\n" +
                     "0. Back to main menu.\n" +
                     "-1. Quit application.\n" +
                     "\n" +
                     "Input:");
             if ("0".equals(input) || "-1".equals(input)) {BookingManager.refreshOrExit(input);}
             try {
                 int ID = Integer.parseInt(input);
                 Booking booking = this.bookings.get(ID);
                 String removedBooking = "\n| Booking ID | Assistant ID | Room | Student Email   | Time Slot     |" +
                         " Status    |\n";
                 removedBooking += "| " + ID + "          " + booking.toString();
                 this.bookings.remove(ID);
                 print("\nBooking removed successfully:");
                 print(removedBooking);
             } catch (Exception e) {
                 print("Invalid input entered, " + input +  " is not a valid ID from the list above");
             }
         } while (true);
    }

    public void concludeBooking() {
        print("University of Knowledge - COVID test\n");
        listScheduledBookings(true);
        print("Conclude booking");
        do {
            String input = inputSTR("\n" +
                    "Please, enter one of the following:\n" +
                    "\n" +
                    "The sequential ID to select the booking to be removed from the listed bookings above.\n" +
                    "0. Back to main menu.\n" +
                    "-1. Quit application.\n" +
                    "\n" +
                    "Input:");
            if ("0".equals(input) || "-1".equals(input)) {BookingManager.refreshOrExit(input);}
            try {
                int ID = Integer.parseInt(input);
                Booking booking = this.bookings.get(ID);
                booking.setStatus(BookingStatus.COMPLETED);
                print("\n| Booking ID | Assistant ID | Room | Student Email   | Time Slot     |" +
                        " Status    |\n" +
                        "| " + ID + "          " +  booking.toString());
            } catch (Exception e) {
                print("Invalid input entered, " + input +  " is not a valid ID from the list above");
            }
        } while (true);
    }

    /**
     * Update assistant statuses (FREE, BUSY) based on whether they are currently in an appointment
     */
    public void updateAssistantStatuses() {
        LocalDateTime now = LocalDateTime.now();
        for (Assistant assistant : this.assistants) {
             if (assistant.getShift().isTimeInTimeSlot(now)) {
                 assistant.setStatus(AssistantStatus.FREE);
                 if (getBooking(assistant).size() != 0) {
                     for (Booking booking : getBooking(assistant)) {
                         if (booking.getTimeSlot().isTimeInTimeSlot(now)) {
                             assistant.setStatus(AssistantStatus.BUSY);
                         }
                     }
                 }
             }
             else {
                 assistant.setStatus(AssistantStatus.NOT_ON_SHIFT);
             }
        }
    }

    /**
     * Update room statuses (EMPTY, AVAILABLE, FULL) based on their current occupancy and capacity
     */
    public void updateRoomStatuses() {
        LocalDateTime now = LocalDateTime.now();
        for (Room room : rooms) {
            int occupancy = getRoomOccupancy(room, now);
            if (occupancy == room.getCapacity()) {
                room.setStatus(RoomStatus.FULL);
            }
            if (occupancy == 0) {
                room.setStatus(RoomStatus.EMPTY);
            }
            else {
                room.setStatus(RoomStatus.AVAILABLE);
            }

        }
    }

    /**
     * Update booking statuses based on whether their TimeSlot is over yet
     */
    public void updateBookingStatuses() {
        LocalDateTime now = LocalDateTime.now();
        for (Booking booking : this.bookings) {
            if (booking.getTimeSlot().isTimeAfterTimeSlot(now)) {
                booking.setStatus(BookingStatus.COMPLETED);
            }
            else booking.setStatus(BookingStatus.SCHEDULED);
        }
    }

    /**
     * Populating ArrayLists with data, used excel spreadsheet to generate constructors
     */
    public void populateAssistants() {
        // TimeSlot objects for assistant shifts
        LocalDateTime datetime = LocalDateTime.now();
        LocalDate date = LocalDate.now();
        TimeSlot nineToFive = new TimeSlot(date.atTime(9, 0),date.atTime(17, 0));
        TimeSlot eightHourShift = new TimeSlot(roundedTime(datetime).minusHours(5),roundedTime(datetime).plusHours(3));

        // Creating assistants
        addAssistant(1,"Wanda Maximoff","W.Maximoff@UoK.ac.uk",AssistantStatus.BUSY,eightHourShift);
        addAssistant(2,"Agatha Harkness","A.Harkness@UoK.ac.uk",AssistantStatus.BUSY,eightHourShift);
        addAssistant(3,"Jimmy Woo","J.Woo@UoK.ac.uk",AssistantStatus.BUSY,eightHourShift);
        addAssistant(4,"Darcy Lewis","D.Lewis@UoK.ac.uk",AssistantStatus.BUSY,eightHourShift);
        addAssistant(5,"Monica Rambeau","M.Rambeau@UoK.ac.uk",AssistantStatus.BUSY,eightHourShift);
        addAssistant(6,"Vision Maximoff","V.Maximoff@UoK.ac.uk",AssistantStatus.BUSY,eightHourShift);
        addAssistant(7,"Javier Freeman","J.Freeman@UoK.ac.uk",AssistantStatus.BUSY,nineToFive);
        addAssistant(8,"Skyler Spears","S.Spears@UoK.ac.uk",AssistantStatus.BUSY,nineToFive);
        addAssistant(9,"Madalyn Levine","M.Levine@UoK.ac.uk",AssistantStatus.BUSY,nineToFive);
        addAssistant(10,"Christopher Bentley","C.Bentley@UoK.ac.uk",AssistantStatus.BUSY,nineToFive);
        addAssistant(11,"Oliver Kidd","O.Kidd@UoK.ac.uk",AssistantStatus.BUSY,nineToFive);
        addAssistant(12,"Marvin Rodgers","M.Rodgers@UoK.ac.uk",AssistantStatus.BUSY,nineToFive);

        // Checking and correcting assistant statuses
        updateAssistantStatuses();

    }

    public void populateRooms() {
        addRoom("A1",3,RoomStatus.FULL);
        addRoom("B2",5,RoomStatus.AVAILABLE);
        addRoom("C3",10,RoomStatus.EMPTY);
    }

    public void populateBookings() {
        // Creating TimeSlot objects for bookings
        LocalDateTime datetime = LocalDateTime.now();
        TimeSlot now;
        now = new TimeSlot(roundedTime(datetime),roundedTime(datetime).plusMinutes(15));
        TimeSlot thirtyMinsAgo;
        thirtyMinsAgo = new TimeSlot((roundedTime(datetime)).minusMinutes(60),(roundedTime(datetime)).minusMinutes(30));

        // Creating bookings
        addBooking(getAssistant(1),getRoom("A1"),"lp565@UoK.ac.uk",now,BookingStatus.SCHEDULED);
        addBooking(getAssistant(2),getRoom("A1"),"es623@UoK.ac.uk",now,BookingStatus.SCHEDULED);
        addBooking(getAssistant(3),getRoom("A1"),"hc389@UoK.ac.uk",now,BookingStatus.SCHEDULED);
        addBooking(getAssistant(4),getRoom("B2"),"fg710@UoK.ac.uk",now,BookingStatus.SCHEDULED);
        addBooking(getAssistant(5),getRoom("B2"),"nn777@UoK.ac.uk",now,BookingStatus.SCHEDULED);
        addBooking(getAssistant(6),getRoom("B2"),"gh123@UoK.ac.uk",thirtyMinsAgo,BookingStatus.COMPLETED);
    }

    /**
     * Rounding times to nearest quarter hour (assuming bookings take 15 mins, just to make bookings look tidier)
     *
     * @param time datetime entered (usually using LocalDateTime.now)
     * @return LocalDateTime rounded down to nearest 15 mins
     */
    public static LocalDateTime roundedTime(LocalDateTime time) {
        int minutes = time.getMinute();
        int remainder = minutes % 15;
        LocalDateTime roundedTime = time.minusMinutes(remainder);
        return roundedTime;
        }

}
