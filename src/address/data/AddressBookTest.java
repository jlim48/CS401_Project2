package address.data;

import address.data.AddressBook;
import address.data.AddressEntry;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * @author Student Name
 * @version 1.0
 * @since 1.2
 *
 * purpose: This class is used to test the AddressBook class
 */
public class AddressBookTest {

    /**
     * this will be the stream that System writes to during the test
     */
    private static final ByteArrayOutputStream testOutput = new ByteArrayOutputStream();

    /**
     * The AddressBook that the test class will use to test
     */
    private AddressBook ab;

    /**
     * An instance of AddressEntry to load into AddressBook
     */
    private final AddressEntry ae1 = new AddressEntry("John", "A", "Arroyo", "Dublin", "NY", 81777, "boring@gmail.com", "925-123-7924");

    /**
     * An instance of AddressEntry to load into AddressBook
     */
    private final AddressEntry ae2 = new AddressEntry("John", "Doe", "Arroyo", "Dublin", "NY", 81777, "boring@gmail.com", "925-123-7924");

    /**
     * An instance of AddressEntry to load into AddressBook
     */
    private final AddressEntry ae3 = new AddressEntry("John", "Dof", "Arroyo", "Dublin", "NY", 81777, "boring@gmail.com", "925-123-7924");

    /**
     * An instance of AddressEntry to load into AddressBook
     */
    private final AddressEntry ae4 = new AddressEntry("John", "A", "Arroyo", "Dublin", "NY", 81777, "boring@gmail.com", "925-123-7924");

    /**
     * A method which sets standard output to print to testOutput instead of the console
     * this is done once before any tests are run
     */
    @BeforeAll
    public static void setUp() {
        //We want to suppress the console output. We need to create a ByteArrayOutputStream so that
        //the console output can be placed somewhere. Then we tell System that we want the
        //standard output stream to a new PrintStream of our new ByteArrayOutputStream
        System.setOut(new PrintStream(testOutput));
    }

    /**
     * A method which creates a new AddressBook object and instantiates it with some
     * AddressEntry objects which are private fields. It does this before every test is run
     */
    @BeforeEach
    public void createAddressEntryObject() {
        this.ab = new AddressBook();
        ab.add(ae1);
        ab.add(ae2);
        ab.add(ae3);
        ab.add(ae4);
    }

    /**
     * clear the buffer of the testOutput after every test. testOutput is configured
     * to be the stream with System prints to instead of the console
     */
    @AfterEach
    public void cleanUp() {
        testOutput.reset();
        System.setIn(System.in);
    }

    /** A method which allows the test function to pass input to the functions simulated user input
     *  to the console
     *
     * @param data is a String which represents input the user would enter into the console
     */
    private void provideInput(String data) {
        ByteArrayInputStream testInput = new ByteArrayInputStream(data.getBytes());
        System.setIn(testInput);
    }

    /**
     * Test method for {@link AddressBook#list()}.
     */
    @Test
    void testList() {
        this.testToString();
    }

    /**
     * Test method for {@link AddressBook#remove(java.lang.String)}
     */
    @Test
    void testRemove() {
        //I am getting a strange error where the assertEquals will fail but then it claims that either the
        //contents are identical or they differ only in line separators. It has somethings to do with replacing
        //\n with \r\n in a seemingly random combination. This makes its way too difficult to test these functions
        //any more in this manner.
        String result1 = "The following entry was found in the address book.\r\n" +
                         "   " + ae1 + "\n" + "Hit 'y' to remove the entry or 'n' to return to main menu\r\n";
        String input1 = "y";
        provideInput(input1);
        ab.remove("A");
        assertEquals(result1, testOutput.toString());
    }

    /**
     * Test method for {@link AddressBook#add(AddressEntry)}
     */
    @Test
    void testAdd() {
        ab.clear();
        ab.add(ae1);
        ab.add(ae4);

        String expected = "1: John A\n   Arroyo\n   Dublin, NY 81777\n   boring@gmail.com\n   925-123-7924\n\n";
        assertEquals(expected, ab.toString());
    }

    /**
     * Test method for {@link AddressBook#readFromFile(java.lang.String)}
     */
    @Test
    void testReadFromFile() {
        ab.clear();
        ab.readFromFile("test.txt");
        String expected = "";
        expected += "1: John A\n   Arroyo\n   Dublin, NY 81777\n   boring@gmail.com\n   111-234-7924\n\n";
        expected += "2: John Doe\n   Arroyo\n   Dublin, NY 81777\n   boring@gmail.com\n   111-234-7924\n\n";
        expected += "3: John Dof\n   Arroyo\n   Dublin, NY 81777\n   boring@gmail.com\n   111-234-7924\n\n";
        assertEquals(expected, ab.toString());
    }

    /**
     * Test method for {@link AddressBook#find(java.lang.String)}
     */
    @Test
    void testFind() {
        String input1 = "";
        String input2 = "not here";
        String input3 = "A";
        String input4 = "D";

        ab.find(input1);
        String result1 = "The following 3 entries were found in the address book" +
                " for a last name starting with \"\"\r\n" + ab.toString();
        testOutput.reset();
        String result2 = "There were no entries were found in the address book" +
                " for a last name starting with " + "\"" + input2 + "\"\r\n";
        String result3 = "The following 1 entries were found in the address book" +
                " for a last name starting with " + "\"" + input3 + "\"\r\n" + "1: " + ae1 + "\n\n";
        String result4 = "The following 2 entries were found in the address book" +
                " for a last name starting with " + "\"" + input4 + "\"\r\n" + "1: " + ae2 + "\n\n"
                + "2: " + ae3 + "\n\n";

        ab.find(input1);
        assertEquals(result1, testOutput.toString());
        testOutput.reset();

        ab.find(input2);
        assertEquals(result2, testOutput.toString());
        testOutput.reset();

        ab.find(input3);
        assertEquals(result3, testOutput.toString());
        testOutput.reset();

        ab.find(input4);
        assertEquals(result4, testOutput.toString());
        testOutput.reset();
    }

    /**
     * Test method for {@link AddressBook#clear()}
     */
    @Test
    void testClear() {
        ab.clear();
        assertEquals("", ab.toString());
    }

    /**
     * Test method for {@link AddressBook#toString()}
     */
    @Test
    void testToString() {
        ab.clear();
        //We dont know if add works properly. This test is invalid and shows nothing!!!! For example imagine that add does
        //not work properly so it inputs garbage data. toString does work properly either. In fact it is so bad that it
        //takes garbage and outputs what we expect in an extreme twist of fate. Now we believe that toString works but we
        //have exercised circular reasoning. !!!!!!Garbage!!!!!!! How can I decouple the two. Only one independent variable.
        String expected = "";
        assertEquals(expected, ab.toString());

        ab.add(ae1);
        expected += "1: John A\n   Arroyo\n   Dublin, NY 81777\n   boring@gmail.com\n   925-123-7924\n\n";
        assertEquals(expected, ab.toString());

        expected += "2: John Doe\n   Arroyo\n   Dublin, NY 81777\n   boring@gmail.com\n   925-123-7924\n\n";
        ab.add(ae2);
        assertEquals(expected, ab.toString());

        ab.add(ae3);
        expected += "3: John Dof\n   Arroyo\n   Dublin, NY 81777\n   boring@gmail.com\n   925-123-7924\n\n";
        assertEquals(expected, ab.toString());
    }

}