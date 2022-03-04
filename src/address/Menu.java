package address;

import address.data.AddressEntry;

import java.util.*;

/**
 * @author Sterling Jeppson
 * @version 1.0
 * @since 1.2
 *
 * purpose: This class is used to provide prompt to the user to create enter in data to an
 * AddressEntry
 */
public class Menu {

    /** Prompts the user for a first name and returns the string which they enter
     *
     * @return a String which represents persons FirstName in an AddressEntry
     */
    public static String prompt_FirstName() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("First Name: ");
        return keyboard.nextLine();
    }

    /** Prompts the user for a last name and return the string which they enter
     *
     * @return a String which represents persons LastName in an AddressEntry
     */
    public static String prompt_LastName() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Last Name: ");
        return keyboard.nextLine();
    }

    /** Prompts the user for a street and returns the string which they enter
     *
     * @return a String which represents persons street in an AddressEntry
     */
    public static String prompt_Street() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Street: ");
        return keyboard.nextLine();
    }

    /** Prompts the user for a city and returns the string which they enter
     *
     * @return a String which represents persons city in an AddressEntry
     */
    public static String prompt_City() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("City: ");
        return keyboard.nextLine();
    }

    /** Prompts the user for a state and returns the string which they enter
     *
     * @return a String which represents persons state in an AddressEntry
     */
    public static String prompt_State() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("State: ");
        return keyboard.nextLine();
    }

    /** Prompts the user for a zip code. If the user does not enter an int then
     *  they are given an error message and re-prompted until an int is entered
     *
     * @return an int which represents persons zip in an AddressEntry
     */
    public static int prompt_Zip() {
        while(true) {
            try {
                Scanner Keyboard = new Scanner(System.in);
                System.out.println("Zip: ");
                return Keyboard.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /** Prompts the user for a phone number and returns the string which they enter
     *
     * @return a String which represents persons PhoneNumber in an AddressEntry
     */
    public static String prompt_Telephone() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Telephone: ");
        return keyboard.nextLine();
    }

    /** Prompts the user for an email address and returns the string which they enter
     *
     * @return a String which represents persons email in an AddressEntry
     */
    public static String prompt_Email() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Email: ");
        return keyboard.nextLine();
    }

    /** Instantiates and returns a new AddressEntry with values that are specified by
     * the user with calls to all of the prompt methods
     *
     * @return an AddressEntry which contains information specified by user
     */
    public static AddressEntry promptForNewAddressEntry() {

        return new AddressEntry(prompt_FirstName(), prompt_LastName(), prompt_Street(),
                                prompt_City(), prompt_State(), prompt_Zip(), prompt_Email(),
                                prompt_Telephone());
    }

    /**
     * Prints out a menu which prompts user for selection of operation to be performed on
     * AddressBook
     */
    public static void display_Menu() {
        System.out.print("\n\n*************************\n"+
                "Please enter your menu selection\n"+
                "a) Loading From File\n"+
                "b) Addition\n"+
                "c) Removal\n"+
                "d) Find\n"+
                "e) Listing\n"+
                "f) Quit\n"+
                "************************* \n\n");
    }

}
