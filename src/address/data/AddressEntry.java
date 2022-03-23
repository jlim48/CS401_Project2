package address.data;

/**
 * @author Student Name
 * @version 1.0
 * @since 1.2
 *
 * purpose: This class is used to contain and provide data necessary to represent an
 * AddressEntry
 */
public class AddressEntry implements Comparable<AddressEntry>{

    /**
     * first name
     */
    private String firstName;
    /**
     * last name
     */
    private String lastName;
    /**
     * street
     */
    private String street;
    /**
     * city
     */
    private String city;
    /**
     * state
     */
    private String state;
    /**
     * zip code
     */
    private Integer zip;
    /**
     * phone number
     */
    private String phone;
    /**
     * email
     */
    private String email;
    /**
     * ID (reflective of db)
     */
    private long id = 0;

    /**returns an AddressEntry where all fields are initialized to default values
     *
     */
    public AddressEntry() {
        firstName = "";
        lastName = "";
        street = "";
        city = "";
        state = "";
        zip = 0;
        phone = "";
        email = "";
    }

    /**returns an address entry initialized to the data in parameters provided
     *
     * @param firstName is a firstname
     * @param lastName is a listname
     * @param street is a street
     * @param city is a city
     * @param state is a state
     * @param zip is a zip code
     * @param phone is a phone number
     * @param email is an email
     */
    public AddressEntry(String firstName, String lastName, String street,
                        String city, String state, int zip, String email, String phone)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
        this.email = email;
    }

    /**returns an address entry initialized to the data in parameters provided
     *
     * @param id ID
     * @param firstName is a firstname
     * @param lastName is a listname
     * @param street is a street
     * @param city is a city
     * @param state is a state
     * @param zip is a zip code
     * @param phone is a phone number
     * @param email is an email
     */
    public AddressEntry(long id, String firstName, String lastName, String street,
                        String city, String state, int zip, String email, String phone)
    {
        this(firstName, lastName, street, city, state, zip, email, phone);
        this.setID(id);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + "\n   " +
                street + "\n   " + city + ", " + state + " " + zip +
                "\n   " + email + "\n   " + phone;
    }

    /**
     * compareTo - Compare two AddressEntry objects.
     *
     * Ordering: By way of the compareTo of the following
     * fields in the specified order below:
     *      Last name, First name, Street, City, State,
     *      ZIP, Email, Phone.
     *
     * @param other Other AddressEntry object.
     * @return Integer, NEG if this object precedes $other. ZERO if equal,
     * and POS if this object succeeds $other.
     */
    @Override
    public int compareTo(AddressEntry other)
    {
        if( this.lastName.compareTo(other.lastName) != 0 )
            return this.lastName.compareTo(other.lastName);
        if( this.firstName.compareTo(other.firstName) != 0 )
            return this.firstName.compareTo(other.firstName);
        if( this.street.compareTo(other.street) != 0 )
            return this.street.compareTo(other.street);
        if( this.city.compareTo(other.city) != 0 )
            return this.city.compareTo(other.city);
        if( this.state.compareTo(other.state) != 0 )
            return this.state.compareTo(other.state);
        if( this.zip.compareTo(other.zip) != 0 )
            return this.zip.compareTo(other.zip);
        if( this.email.compareTo(other.email) != 0 )
            return this.email.compareTo(other.email);
        if( this.phone.compareTo(other.phone) != 0 )
            return this.phone.compareTo(other.phone);
        return 0;
    }

    /**
     * Get ID.
     */
    public long getID ()
    {
        return this.id;
    }

    /**
     * SET ID.
     *
     * @param id ID
     */
    public void setID (long id)
    {
        this.id = id;
    }

    /** method to set the first name of the address entry
     *
     * @param firstName is a firstname
     */
    public void setFirstName(String firstName) { this.firstName = firstName; }

    /** method to set the last name of the address entry
     *
     * @param lastName is a lastName
     */
    public void setLastName(String lastName) { this.lastName = lastName; }

    /** method to set the street name of an address entry
     *
     * @param street is a street
     */
    public void setStreet(String street) { this.street = street; }

    /** method to set the city name of the address entry
     *
     * @param city is a city
     */
    public void setCity(String city) { this.city = city; }

    /** method to set the state name of the address entry
     *
     * @param state is a state
     */
    public void setState(String state) { this.state = state; }

    /** method to set the zip code of the address entry
     *
     * @param zip is a zip code
     */
    public void setZip(int zip) { this.zip = zip; }

    /** method to set the phone of the address entry
     *
     * @param phone is a phone number
     */
    public void setPhone(String phone) { this.phone = phone; }

    /** method to set the email address of the address entry
     *
     * @param email is an email address
     */
    public void setEmail(String email) { this.email = email; }

    /** method to return the first name of the address entry
     *
     * @return a String which represents first name
     */
    public String getFirstName() { return firstName; }

    /** method to return the last name of the address entry
     *
     * @return a String which represents last name
     */
    public String getLastName() { return lastName; }

    /** method to return the street name of the address entry
     *
     * @return a String which represents street
     */
    public String getStreet() { return street; }

    /** method to return the city name of the address entry
     *
     * @return a String which represents city
     */
    public String getCity() { return city; }

    /** method to return the state name of the address entry
     *
     * @return a String which represents state
     */
    public String getState() { return state; }

    /** method to return the zip code of the address entry
     *
     * @return an int which represents zip code
     */
    public int getZip() { return zip; }

    /** method to return the phone number of the address entry
     *
     * @return a String which represents phone number
     */
    public String getPhone() { return phone; }

    /** method to return the email address of the address entry
     *
     * @return a String which represents email
     */
    public String getEmail() { return email; }
}
