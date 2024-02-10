// Importing necessary Java libraries
import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

public class Theatre {  // A class to represent a theatre
    static int[][] seats = {{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // An array to represent the seats in the row 1
                           {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},  // An array to represent the seats in the row 2
                           {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}}; // An array to represent the seats in the row 3

    static ArrayList<Ticket> tickets = new ArrayList<>(); // An array list to store tickets


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);  // Creating a scanner object to read user input
        System.out.println("Welcome to the New Theatre!");
        boolean quit = false;
        while (!quit) {
            System.out.println("--------------------------------------------------");
            System.out.println("\nPlease select an option:");
            System.out.println("1) Buy a ticket");             // Displaying the menu options
            System.out.println("2) Print seating area");
            System.out.println("3) Cancel ticket");
            System.out.println("4) List available seats");
            System.out.println("5) Save to file");
            System.out.println("6) Load from file");
            System.out.println("7) Print ticket information and total price");
            System.out.println("8) Sort tickets by price");
            System.out.println("0) Quit");
            System.out.println("--------------------------------------------------");
            System.out.print("\nEnter option: ");
            try {
                int option = scanner.nextInt();
                switch (option) {    // Handling different menu options
                    case 0 -> quit = true; // Quit the program
                    case 1 -> buy_ticket(scanner); // Buy a ticket
                    case 2 -> print_seating_area();  // Print the seating area
                    case 3 -> cancel_ticket(scanner); // Cancel a ticket
                    case 4 -> show_available(); // Show available seats
                    case 5 -> save(); // Save the tickets to a file
                    case 6 -> load(); // Load the tickets from a file
                    case 7 -> show_tickets_info(tickets); // Show ticket information and total price
                    case 8 -> {
                    ArrayList < Ticket > sortedTickets = sort_tickets(tickets);
                    show_tickets_info(sortedTickets);
                    } // Sort the tickets by price

                    default -> System.out.println("Invalid option. Please try again.");// print this error if user entered integer not in range of (0-8)
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");  // print this error if the user enters a non-integer input
                scanner.next();
            }
        }
        scanner.close();  // Closing the scanner object
    }

    public static void buy_ticket(Scanner scanner) {//method to buy ticket
        while (true) {
            try {
                System.out.print("Enter row number (1-3)  : ");//get input for row number
                int row = scanner.nextInt();
                if (row > 3 || row < 1) {   // Check if row number is valid
                    System.out.println("Row number out of range.Please select(1-3)");//if not print this error
                    continue;
                }

                int seat;
                while (true) {   // Loop until valid seat number is entered
                    try {
                        System.out.print("Enter seat number (1-" + seats[row - 1].length + "): ");
                        seat = scanner.nextInt();

                        if (seat < 1 || seat > seats[row - 1].length) {  // Check if seat number in the valid range
                            System.out.println("Seat number out of range.Try again");// if not print this error
                            continue;
                        }
                        break;  // Break out of the loop if valid seat number is entered
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid number.");// print this error if the user enters a non-integer input
                        scanner.next();
                    }
                }
                if (seats[row - 1][seat - 1] == 1) {  // Check if seat is already occupied
                    System.out.println("Seat is already occupied.");// print this message if the seat is already occupied
                    continue;
                }
                // Reserve seat and collect customer information
                seats[row - 1][seat - 1] = 1;
                System.out.println(" ");
                System.out.println("Enter your name  : ");
                String name = scanner.next();

                System.out.println("Enter your surname: ");
                String surname = scanner.next();


                String email = "";  // Initialize email variable to an empty string
                boolean isValidEmail = false;  // Initialize isValidEmail variable to false
                while (!isValidEmail) {     // Start a while loop that will continue until isValidEmail becomes true
                    System.out.println("Enter your email : ");
                    email = scanner.next();

                    if (email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {   // Use regular expression to check if email is valid
                        isValidEmail = true;   // Set isValidEmail to true if email is valid
                    } else {
                        System.out.println("Invalid.Please enter valid email.");// print this error if the email is not valid
                    }
                }
                // Calculate ticket price and create ticket object
                Person person = new Person(name, surname, email);
                double ticket_price = calculate_price(row);
                Ticket ticket = new Ticket(row, seat, ticket_price, person);
                tickets.add(ticket);
                System.out.println(" ");
                System.out.println("Ticket purchased successfully.");


                while (true) {
                    System.out.println("Do you want to buy another ticket? yes/no");// Ask if the user wants to buy another ticket
                    String choice = scanner.next();
                    if (choice.equalsIgnoreCase("yes")) {  // If the user wants to buy another ticket, break out of the loop and continue with the ticket buying process
                        break;
                    } else if (choice.equalsIgnoreCase("no")) {  // If user doesn't want to buy another ticket return to  the menu
                        return;
                    } else {
                        System.out.println("Invalid input. Please enter 'yes' or 'no'.");//print this error if user enter anything other than yes or no
                    }
                }
            } catch (InputMismatchException e) {   // Catch invalid input error
                System.out.println("Invalid input. Please enter a valid number.");//print this error if the user enters a non-integer input
                scanner.next();
            }
        }
    }

    public static double calculate_price(int row) {   // This method calculates the price of a seat based on its row number.
        if (row == 1) {
            return 10; // If the row number is 1, return a price of 10.
        } else if (row == 2) {
            return 20;  // If the row number is 2, return a price of 20.
        } else {
            return 30; // For any other row number, return a price of 30.
        }
    }

    public static void print_seating_area() {//this method prints the seating area
        // Print the stage header
        System.out.println(" \t\t*************");
        System.out.println(" \t\t*   STAGE   *");
        System.out.println(" \t\t*************");
        // Create StringBuilder objects to store each row of the seating area
        StringBuilder row01 = new StringBuilder("        "); // Create a StringBuilder with 8 spaces for row 1
        StringBuilder row02 = new StringBuilder("      ");// Create a StringBuilder with 6 spaces for row 2
        StringBuilder row03 = new StringBuilder("    ");// Create a StringBuilder with 4 spaces for row 3
        // Get the arrays of seat availability from the "seats" variable
        int[] row1 = seats[0];     // Get the array of seats for row 1
        int[] row2 = seats[1];
        int[] row3 = seats[2];

        // Loop through the seats in the first row and add an "X" if the seat is taken or a "0" if it's available
        StringBuilder formattedRow01 = formatPrintArea(row1, row01); // Format row 1 and store it in a StringBuilder
        StringBuilder formattedRow02 = formatPrintArea(row2, row02);
        StringBuilder formattedRow03 = formatPrintArea(row3, row03);

        System.out.println( formattedRow01 + "\n" + formattedRow02 + "\n" + formattedRow03 + "\n");  // Print out the formatted seating area
    }

    public static StringBuilder formatPrintArea(int[] row, StringBuilder rowNumber){
        int spaceIndex = row.length/2 -1;// Calculate the index where a space should be added for formatting purposes
        for (int i = 0; i < row.length; i++) {
            // Loop through the seats in the current row and add an "X" if the seat is taken or a "0" if it's available
            if (row[i] == 1) {
                rowNumber.append("X");
            } else {
                rowNumber.append("0");
            }
            // Add a space after the 3rd seat for formatting purposes
            if (i == spaceIndex) {
                rowNumber.append(" ");
            }
        }
        return rowNumber;//Return the formatted row as a StringBuilder object
    }

    public static void cancel_ticket(Scanner scanner) {   // Method to cancel a ticket.
        while (true) {   // Loop until a ticket is cancelled or there are no tickets left to cancel.
            if (tickets.isEmpty()) {   // If there are no tickets, inform the user and return.
                System.out.println("No tickets to cancel!");
                return;
            }

            try {
                System.out.print("Enter row number (1-3): ");   // Prompt user to enter row number.
                int row = scanner.nextInt();

                if (row < 1 || row > 3) {   // Check if row number is valid.
                    System.out.println("Row number out of range.Try again");//if not print this error
                    continue;  // continue with the loop
                }

                int seat;
                while (true) {
                    try {
                        System.out.print("Enter seat number (1-" + seats[row - 1].length + "): ");  // Prompt user to enter seat number.
                        seat = scanner.nextInt();

                        if (seat < 1 || seat > seats[row - 1].length) {  // Check if seat number in the valid range.
                            System.out.println("Seat number out of range.Try Again");//if not print this error
                            continue;  // continue with the loop
                        }
                        break;  // Break out of the loop if a valid seat number is entered.
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a valid number.");//print this error if the user enters a non-integer input
                        scanner.next();  // Clear the scanner buffer.
                    }
                }

                if (seats[row - 1][seat - 1] == 0) {   // Check if seat is not occupied.
                    System.out.println(" ");
                    System.out.println("Seat is not occupied.");// print this message if the seat is not occupied.
                    continue;   // continue with the loop
                }

                seats[row - 1][seat - 1] = 0;   // Mark seat as unoccupied.

                for (int i = 0; i < tickets.size(); i++) {   // Loop through tickets to find the ticket to cancel.
                    Ticket ticket = tickets.get(i);
                    if (ticket.getRow() == row && ticket.getSeat() == seat) {   // If the ticket matches the selected row and seat, remove the ticket
                        tickets.remove(i);
                        System.out.println(" ");
                        System.out.println("Ticket cancelled successfully.");//inform the user that the ticket has been cancelled.
                        break;   // Break out of the loop if the ticket is cancelled.
                    }
                }

                while (true) {
                    System.out.println("Do you want to cancel another ticket? yes/no");// Ask if the user wants to cancel another ticket
                    String choice = scanner.next();
                    if (choice.equalsIgnoreCase("yes")) {  // If user wants to cancel another ticket, break out of loop and continue with ticket cancelling process
                        break;
                    } else if (choice.equalsIgnoreCase("no")) {  // If user doesn't want to cancel another ticket,return to the menu
                        return;
                    } else {
                        System.out.println("Invalid input. Please enter 'yes' or 'no'.");//print this error if user enter anything other than yes or no
                    }
                }
            } catch (InputMismatchException e) {   // If the user enters a non-integer input, inform the user and prompt for input again.
                System.out.println("Invalid input. Please enter Clothing or Electronics.");
                scanner.next();
            }
        }
    }
    public static void show_available() { // method to show available seats
        for (int i = 0; i < seats.length; i++) {     // iterate over each row of seats
            int[] available_seats = new int[seats[i].length]; // create an array to store available seats in this row
            int counter = 0;  // initialize a counter to keep track of the number of available seats found
            for (int j = 0; j < seats[i].length; j++) {   // iterate over each seat in this row
                if (seats[i][j] == 0) {
                    available_seats[counter] = j + 1;   // if the seat is available ,add it to the array of available seats
                    counter++;
                }
            }
            System.out.println("Seats available in row " + (i + 1) + ": " + Arrays.toString(Arrays.copyOf(available_seats, counter)));   // print out the row number and the array of available seats in this row
        }
    }

    public static void save() {  //method to save data in to file
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("seats.txt"));  // create a new BufferedWriter object to write to the file "seats.txt"
            for (int[] seat : seats) {  // iterate over each row of seats
                for (int j = 0; j < seat.length; j++) {  // iterate over each seat in the row
                    writer.write(Integer.toString(seat[j]));  // write the seat number to the file, followed by a comma (unless it's the last seat in the row)
                    if (j != seat.length - 1) {
                        writer.write(",");
                    }
                }
                writer.write("\n");
            }
            writer.close();  // close the writer when done
            System.out.println("Data saved to file.");  // print a message indicating that the data was successfully saved
        } catch (IOException e) {
            System.out.println("An error occurred while saving the data.");   // if there was an error, print an error message and print the stack trace for the exception
            e.printStackTrace();
        }
    }
    public static void load() { //method to load file
        File file = new File("seats.txt");   // create a new File object representing "seats.txt" file
        try {
            Scanner scanner = new Scanner(file);  // create a new Scanner object that reads from the "seats.txt" file
            String[] array1 = scanner.nextLine().split(",");// read the first lines of the file and split it into an array of seat strings
            String[] array2 = scanner.nextLine().split(",");
            String[] array3 = scanner.nextLine().split(",");

            seats = new int[][]{convertNumArray(array1), convertNumArray(array2), convertNumArray(array3)};   // Convert the string arrays into integer arrays using the convertNumArray method

            System.out.println("Row 1: " + Arrays.toString(array1));
            System.out.println("Row 2: " + Arrays.toString(array2));     // Print the contents of each row of the "seats" array
            System.out.println("Row 3: " + Arrays.toString(array3));
            scanner.close();  // close the scanner object
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");  // if the file isn't found, print an error message to the console
        }
    }
    public static int[] convertNumArray(String[] array){
        int[] numbersArray = new int[array.length];  // Create an integer array of the same length as the string array
        for(int i = 0;i < array.length;i++)// Loop through each element of the string array and convert it to an integer
        {
            numbersArray[i] = Integer.parseInt(array[i]);  // parse each string in the array as an integer and store it in the corresponding position in the new array
        }
        return numbersArray;// Return the integer array
    }
    public static void show_tickets_info(ArrayList<Ticket> ticketList) {//method to show ticket information
        if (ticketList.isEmpty()) {   // Check if the tickets list is empty
            System.out.println("No tickets purchased yet!");
            return;
        }
        System.out.println("Ticket Information:");  // If there are tickets, print ticket information for each ticket
        for (int i = 0; i < ticketList.size(); i++) {
            Ticket ticket = ticketList.get(i);
            System.out.println("Ticket " + (i + 1) + ":");
            System.out.println("\tName     : " + ticket.getPerson().getName());
            System.out.println("\tSurname  : " + ticket.getPerson().getSurname());
            System.out.println("\tEmail    : " + ticket.getPerson().getEmail());
            System.out.println("\tRow No   : " + ticket.getRow());
            System.out.println("\tSeat No  : " + ticket.getSeat());
            System.out.println("\tPrice    : $" + ticket.getPrice());
        }
        System.out.println("------------------------------------------------");
        System.out.println("Total number of tickets sold: " + ticketList.size());  // Print the total number of tickets sold
        double totalRevenue = 0;
        for (Ticket ticket : ticketList) {
            totalRevenue += ticket.getPrice();
        }
        System.out.println("Total ticket price          : $" + totalRevenue);   // Print the total ticket price
    }
    public static ArrayList<Ticket> sort_tickets(ArrayList<Ticket> ticketList) { // method to sort tickets
        ArrayList<Ticket> SortedTicketList = new ArrayList<>(ticketList);// Create a new ArrayList to store the sorted tickets, and copy the contents of the original list
        int len = SortedTicketList.size(); // Get the length of the tickets list
        Ticket sort;// Create a temporary Ticket object to use for swapping tickets
        for (int i = 0; i < len - 1; i++) {  // Loop through the tickets list using a bubble sort algorithm to sort the tickets by price
            for (int j = 0; j < len - 1; j++) {
                // Compare the prices of tickets, and swap them if they are not in the correct order
                if (SortedTicketList.get(j).getPrice() > SortedTicketList.get(j + 1).getPrice()) {
                    sort = SortedTicketList.get(j);
                    SortedTicketList.set(j, SortedTicketList.get(j + 1));
                    SortedTicketList.set(j + 1, sort);
                }
            }
        }
        return SortedTicketList;// return the sorted tickets list
    }
}
