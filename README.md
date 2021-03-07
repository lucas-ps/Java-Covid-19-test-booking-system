# Java CA2 Covid-19 Test Booking System 
Java Continuous Assessment 2 University of Exeter - University of Knowledge Covid-19 Test Booking System

My second Java Continuous assessment, a basic text based object-oriented student Covid-19 test booking system for a
theoretical university called the University of Knowledge. The assessment brief described a booking system which manages
assistants, rooms and bookings.

## Usage

To compile files, open the main directory in CMD or terminal then run: 
`javac @sources.txt`

If this doesnt work, try re-maaking the sources file by doing:

#### Linux / MacOS
`find -name "*.java" > sources.txt`
`javac @sources.txt`
#### Windows
`dir /s /B *.java > sources.txt`
`javac @sources.txt`

To run system, run the java file called BookingManager in the mainModules, you can do this by opening the main 
directory in CMD/terminal then running

`java mainModules/BookingManager`

Data has been hard-coded in for testing purposes using the populateBookings(), populateRooms() and populateAssistants() 
methods in the University.java file.

## Main functions

#### Managing rooms
- university.formattedBookableRooms() - Lists all bookable rooms nd their attributes
- university.addRoom() - Makes a room available for the current day
- university.removeRoom() - Lists unused rooms then allows the user to make one unavailable for the current day
#### Managing assistants
- university.formattedAvailableAssistants() - Lists all available assistants and their attributes
- university.addAssistant() - Processes user inputs to add an assistant on shift on a specified date
- university.removeAssistant() - Lists assistants currently on shift and allows user to remove one
#### Managing bookings
- university.formattedBookings() - Sends user to a menu which allows them to list bookings and their attributes based on
  the room's current status (ALL / SCHEDULED / COMPLETED)
- university.addBooking() - Outputs available timeslots and adds a booking in based on user input
- university.removeBooking()
- university.concludeBooking()
#### Managing flow of program
- mainMenu() - Outputs main menu of the program and allows user to choose what they want to do
- refreshOrExit() - Allows the user to go back to the main menu or end the program

## License
[MIT](https://choosealicense.com/licenses/mit/)
