package address.data;

import java.sql.SQLException;
import java.util.*;
import java.io.*;

import address.DataBaseConnect;

/**
 * @author Student Name
 * @version 1.0
 * @since 1.2
 *
 * The purpose of this class is to represent a generic address book
 */
public class AddressBook {

    private DataBaseConnect db;

    /**
     * Initialize db connection.
     */
    public AddressBook ()
    {
        try {
            this.db = new DataBaseConnect();
            db.connect();
        }
        catch (ClassNotFoundException | IOException | SQLException e)
        { e.printStackTrace(); }
    }

    /**
     * Close connection to backend database.
     */
    public void Close ()
    {
        try {
            this.db.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * the data structures that will hold the data for the address book. Composed of a TreeMap
     * where the key is a String(the last name of the AddressEntry and the value is the a TreeSet
     * AddressEntry. This is because java does not contain a multiset in standard libraries.
     * Tree is used instead of hash because tree preserves the natural ordering of key which makes printing in
     * sorted order by last name(key) easy.
     */
    private final TreeMap<String, TreeSet<AddressEntry>> addressEntryList = new TreeMap<>();

    /** a method which prints out all fields in all entries of the address book
     *
     */
    public String list() {
        return this.toString();
    }

    /**
     * Remove AddressEntry object from AddressEntry store.
     * @param ae AddressEntry object to remove.
     * @author Arshdeep Padda
     */
    public void remove (AddressEntry ae)
    {
        TreeSet<AddressEntry> aes = this.addressEntryList.get(ae.getLastName());
        aes.remove(ae);
        if (aes.size() == 0)
            this.addressEntryList.remove(ae.getLastName());
        try {
            int r = db.delete(ae.getID());
            System.out.print("Deleting AE with ID=");
            System.out.println(ae.getID());
            System.out.print("Return code=");
            System.out.println(r);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /** a method which removes an address entry from the address book
     *
     * @param lastName is the last name(or some initial consecutive chars) of the person contained
     *                 in the AddressEntry to be removed
     *
     * First we get the prefixSet which is the set of all AddressEntry that have the first consecutive
     * of the lastName of AddressEntry match the lastName parameter passed. If the size of the set is 1 then
     * print out AddressEntry and prompt user if they wish to delete. If more than 1 element in set then print all
     * elements and ask user to select element based on index.
     */
    public void remove(String lastName) {
        //first obtain a set which contains all address entries in address book where
        //the first characters of their last name exactly match all of the chars in parameter lastname
        TreeSet<AddressEntry> s = this.getPrefixSet(lastName);
        Scanner keyboard = new Scanner(System.in);
        try {
            if (s.size() == 1) {
                System.out.println("The following entry was found in the address book.");
                System.out.printf("%-3s" + s.first() + "\n", " ");
                System.out.println("Hit 'y' to remove the entry or 'n' to return to main menu");
                if (keyboard.nextLine().compareTo("y") == 0)
                    addressEntryList.get(s.first().getLastName()).remove(s.first());
            } else if (s.size() > 1) {
                ArrayList<AddressEntry> list = new ArrayList<>();
                int i = 1;
                System.out.println("The following entries were found in the address book," +
                        "select number of entry you wish to remove:\n");
                for (AddressEntry entry : s) {
                    list.add(entry);
                    System.out.printf("%-3s" + entry + "\n\n", i + ":");
                    i++;
                }
                int removalIndex = keyboard.nextInt() - 1;
                keyboard.nextLine();
                if(removalIndex < list.size() && removalIndex >= 0)
                    System.out.println("Hit 'y' to remove the following entry or 'n' to return to main menu:\n");
                System.out.printf("%-3s" + list.get(removalIndex) + "\n\n", "  ");
                if (keyboard.nextLine().compareTo("y") == 0) {
                    TreeSet<AddressEntry> set = addressEntryList.get(list.get(removalIndex).getLastName());
                    set.remove(list.get(removalIndex));
                }
            } else
                System.out.println("No entries with last name " + lastName + " were found.");
        }
        catch(InputMismatchException e) {
            System.out.println("Error: You need to enter a valid integer. No action taken.");
        }
        catch(IndexOutOfBoundsException e) {
            System.out.println("Error: Invalid element selection. No action taken.");
        }
    }

    /** a method which adds an address entry to the address book
     *
     * @param entry is an instance of AddressEntry to add to the AddressBook
     *
     * If the key has never been seen before then a new TreeSet is created to contain the entry.
     * If the key has been seen before then entry is simply added to the correct set.
     */
    public void add(AddressEntry entry) {
        addressEntryList.computeIfAbsent(entry.getLastName(), k -> new TreeSet<>()).add(entry);
        try {
            this.db.create(entry);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void readFromFile (String fn) {}

    /**
     * Pull entries from backend db and add to internal store.
     */
    public void init_from_db ()
    {
        try {
            AddressEntry[] entries = db.read();
            for (AddressEntry entry : entries) this.add(entry);
        }
        catch (SQLException e)
        { e.printStackTrace(); }
    }

    /** a method which displays one or multiple address entries
     *
     * @param startOf_lastName is a string which contains either a full last name or the first consecutive chars
     * of a last name in an AddressEntry
     */
    public List<AddressEntry> find(String startOf_lastName) {
        SortedMap<String, TreeSet<AddressEntry>> tempMap;
        tempMap = addressEntryList.subMap(startOf_lastName, startOf_lastName + Character.MAX_VALUE);
        List<AddressEntry> tempSet = new ArrayList<AddressEntry>();

        if(tempMap.size() > 0) {
            int i = 1;
            //this line computes total number of Address entries in tempMap
            /* System.out.println("The following " + tempMap.values().stream().mapToInt(TreeSet::size).sum() +
                    " entries were found in the address book" +
                    " for a last name starting with " + "\"" + startOf_lastName + "\""); */
            for(Map.Entry<String, TreeSet<AddressEntry>> entry : tempMap.entrySet()) {
                for(AddressEntry item : entry.getValue()) {
                    //System.out.printf("%-3s" + item + "\n\n", i + ":");
                    tempSet.add(item);

                    i++;
                }
            }
        }
        /* else
             System.out.println("There were no entries were found in the address book" +
                    " for a last name starting with " + "\"" + startOf_lastName + "\""); */
        return tempSet;
    }

    /** a method that returns a set of address entries in which the first characters in the
     *  last name of each entry in the returned set are an exact match for the characters passed
     *  to the function
     *
     * @param startOf_lastName full last name or start of last name
     * @return A TreeSet which contains all of the AddressEntry instances whose lastName field
     * matches from the start every char provided in startOf_lastName.
     */
    private TreeSet<AddressEntry> getPrefixSet(String startOf_lastName) {
        SortedMap<String, TreeSet<AddressEntry>> tempMap;
        TreeSet<AddressEntry> tempSet = new TreeSet<>();
        tempMap = addressEntryList.subMap(startOf_lastName, startOf_lastName + Character.MAX_VALUE);

        for(Map.Entry<String, TreeSet<AddressEntry>> entry : tempMap.entrySet()) {
            tempSet.addAll(entry.getValue());
        }
        return tempSet;
    }

    /**
     * removes all AddressEntry from the AddressBook
     */
    public void clear() {
        addressEntryList.clear();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int i = 1;
        for(Map.Entry<String, TreeSet<AddressEntry>> entry : addressEntryList.entrySet()) {
            for(AddressEntry item : entry.getValue()) {
                if(item != null) {
                    result.append(String.format("%-3s" + item + "\n\n", i + ":"));
                    i++;
                }
            }
        }
        return result.toString();
    }
}
