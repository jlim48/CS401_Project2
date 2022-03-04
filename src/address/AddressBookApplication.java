package address;
import address.data.AddressBook;
import address.data.AddressEntry;

import java.io.*;
import java.util.*;

/**
 * @author Student Name
 * @version 1.0
 * @since 1.2
 *
 * purpose: This class is used to add data to and delete data from and query an address book
 */
public class AddressBookApplication {


    /**
     * creates an AddressBook initializes the AddressBook with some AddressEntry's and
     * then prompts the user to add, delete, list, and search for entries.
     * @param args command line arguments passed to main
     */
    public static void main(String[] args) {

        //create instance of AddressBook for application
        AddressBook ab = new AddressBook();
        initAddressBook(ab);

        //Code to Loop until user chooses to Quit
        //Display Menu of options, based on user's choice
        //invoke apprpriate code.
        Scanner keyboard = new Scanner(System.in);
        String answer;

        //Loop
        boolean continueLoop = true;
        while(continueLoop) {
            //display menu
            Menu.display_Menu();
            //grab users choice and based on this invoke code
            answer = keyboard.nextLine();
            //print a few line returns before processing
            System.out.println("\n\n");
            switch (answer) {
                case "a" -> { //case of read from file
                    System.out.println("Enter in FileName:");
                    ab.readFromFile(keyboard.nextLine());
                }
                case "b" -> ab.add(Menu.promptForNewAddressEntry());  //case add new address object using Menu class prompts to User
                case "c" -> { //remove an address entry
                    System.out.println("Enter in Last Name of contact to remove:");
                    ab.remove(keyboard.nextLine());
                }
                case "d" -> { //find AddressEntry based on last name or start of it
                    System.out.println("Enter in all or beginning of last name you wish to find:");
                    ab.find(keyboard.nextLine());
                }
                case "e" -> ab.list();  //list alphanumerically based on last name all the users
                case "f" -> {
                    System.out.println("Quitting.");
                    continueLoop = false;
                }
                default -> System.out.println("Error: " + answer + " is not a valid selection.");
            }
        }
    }

    /**
     * initializes 2 AddressEntry instances with hard-coded data. Then adds entries to AddressBook class passed to function.
     * @param ab is an instance of AddressBook class
     */
    public static void initAddressBook(AddressBook ab) {
        AddressEntry entry1 = new AddressEntry("Sterling", "Jeppson",
                                                "2759 Vine Dr.","Livermore",
                                                "CA", 94550, "sterlingijeppson@gmail.com", "925-289-6963");
        AddressEntry entry2 = new AddressEntry("D.S", "Malik",
                "2759 Vine Dr.","Livermore",
                "CA", 94550, "sterlingijeppson@gmail.com","925-289-6963");
        ab.add(entry1);
        ab.add(entry2);
    }




}
