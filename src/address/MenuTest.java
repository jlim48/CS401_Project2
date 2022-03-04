package address;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterEach;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Student Name
 * @version 1.0
 * @since 1.2
 *
 * purpose: This class is used to test the Menu class
 */
public class MenuTest {
    private static final ByteArrayOutputStream testOutput = new ByteArrayOutputStream();

    /**
     * A method which sets standard output to print to testOutput instead of the console
     */
    @BeforeAll
    public static void setUp() {
        //We want to suppress the console output. We need to create a ByteArrayOutputStream so that
        //the console output can be placed somewhere. Then we tell System that we want the
        //standard output stream to a new PrintStream of our new ByteArrayOutputStream
        System.setOut(new PrintStream(testOutput));
    }

    /**
     * A method which clears testOutput after every test has completed
     */
    @AfterEach
    public void cleanUp() {
        testOutput.reset();
    }

    /** A method which provides bytes to go onto a ByteArrayInputStream which
     *  System will read from
     *
     * @param data is a String that represents the input that the user could enter
     */
    private void provideInput(String data) {
        ByteArrayInputStream testInput = new ByteArrayInputStream(data.getBytes());
        System.setIn(testInput);
    }

    /**
     * Test method for {@link Menu#prompt_FirstName()}.
     */
    @Test
    public void testPrompt_FirstName() {
        String firstName = "John";
        provideInput(firstName);
        assertEquals(firstName, Menu.prompt_FirstName());
    }

    /**
     * Test method for {@link Menu#prompt_LastName()}.
     */
    @Test
    public void testPrompt_LastName() {
        String lastName = "Doe";
        provideInput(lastName);
        assertEquals(lastName, Menu.prompt_LastName());
    }

    /**
     * Test method for {@link Menu#prompt_Street()}.
     */
    @Test
    public void testPrompt_Street() {
        String street = "street";
        provideInput(street);
        assertEquals(street, Menu.prompt_Street());
    }

    /**
     * Test method for {@link Menu#prompt_City()}.
     */
    @Test
    public void testPrompt_City() {
        String city = "city";
        provideInput(city);
        assertEquals(city, Menu.prompt_City());
    }

    /**
     * Test method for {@link Menu#prompt_State()}.
     */
    @Test
    public void testPrompt_State() {
        String state = "state";
        provideInput(state);
        assertEquals(state, Menu.prompt_State());
    }

    /**
     * Test method for {@link Menu#prompt_Zip()}.
     */
    @Test
    public void testPrompt_Zip() {
        String zip = "12345";
        provideInput(zip);
        assertEquals(Integer.parseInt(zip), Menu.prompt_Zip());
    }

    /**
     * Test method for {@link Menu#prompt_Telephone()}.
     */
    @Test
    public void testPrompt_Telephone() {
        String phone = "123-456-7891";
        provideInput(phone);
        assertEquals(phone, Menu.prompt_Telephone());
    }

    /**
     * Test method for {@link Menu#prompt_Email()}.
     */
    @Test
    public void testPrompt_Email() {
        String email = "bored@gmail.com";
        provideInput(email);
        assertEquals(email, Menu.prompt_Email());
    }

    /**
     * Test method for {@link Menu#prompt_All()}.
     */
    @Test
    public void testPrompt_All() {
        this.testPrompt_FirstName();
        this.testPrompt_LastName();
        this.testPrompt_Street();
        this.testPrompt_City();
        this.testPrompt_State();
        this.testPrompt_Zip();
        this.testPrompt_Telephone();
        this.testPrompt_Email();
    }

    /**
     * Test method for {@link Menu#display_Menu()}.
     */
    @Test
    public void testDisplay_Menu() {
        Menu.display_Menu();
        String expected = ("*************************\n"+
                "Please enter your menu selection\n"+
                "a) Loading From File\n"+
                "b) Addition\n"+
                "c) Removal\n"+
                "d) Find\n"+
                "e) Listing\n"+
                "f) Quit\n"+
                "************************* \n\n");
        assertEquals(expected, testOutput.toString());
    }
}
