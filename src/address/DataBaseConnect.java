package address;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import address.data.AddressEntry;

/**
 * @author Arshdeep Padda
 *
 * DataBaseConnect facilitates connection to Oracle DB and provides
 * CRUD for AddressBookEntry table.
 */
public class DataBaseConnect
{
    /** Private connection instance */
    private Connection conn;

    /**
     * @throws ClassNotFoundException Thrown if DB Driver not present.
     */
    public DataBaseConnect () throws ClassNotFoundException
    {
        Class.forName("oracle.jdbc.OracleDriver");
    }

    /**
     * Establish connection to DB.
     *
     * @throws IOException If file not found or inaccessible.
     * @throws RuntimeException CredFileInvalidFormat if format invalid.
     * @throws RuntimeException Message: "CredFileInvalidFormat" if format invalid.
     *
     * @author Arshdeep Padda
     */
    public void connect () throws SQLException, IOException, RuntimeException
    {
        String uspwd = this.load_cred();
        this.conn = DriverManager.getConnection(
                "jdbc:oracle:thin:" +
                        uspwd +
                        "@adcsdb01.csueastbay.edu:1521/" +
                        "mcspdb.ad.csueastbay.edu"
        );
    }

    /**
     * Disconnect connection to DB.
     */
    public void disconnect () throws SQLException
    {
        this.conn.close();
    }

    /**
     * Load username and password credentials from file.
     *
     * Attempts to load "./db_cred.txt" from cwd.
     * Expected format of file: first line should be: [username]/[password]
     *
     * @return String representation of username / password read,
     * as [username]/[password].
     *
     * @throws IOException If file inaccessible.
     * @throws RuntimeException Message: "CredFileInvalidFormat" if format invalid.
     *
     * @author Arshdeep Padda
     */
    public String load_cred () throws IOException
    {
        File file = new File("./db_cred.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String cred = br.readLine();
        if (cred == null) throw new RuntimeException("CredFileInvalidFormat");
        else return cred;
    }

    /**
     * Read all entries in AddressBookEntry table.
     *
     * @return Array of AddressEntry.
     *
     * @throws SQLException SQL Exception
     *
     * @author Arshdeep Padda
     */
    public AddressEntry[] read () throws SQLException
    {
        Statement stmt = this.conn.createStatement();
        ResultSet rset = stmt.executeQuery("SELECT * FROM AddressBookEntry");

        ArrayList<AddressEntry> entries = new ArrayList<AddressEntry>();
        while (rset.next())
        {
            entries.add(new AddressEntry(
                    Long.parseLong(rset.getString("id")),
                    rset.getString("fname"),
                    rset.getString("lname"),
                    rset.getString("street"),
                    rset.getString("city"),
                    rset.getString("state"),
                    Integer.parseInt(rset.getString("zip")),
                    rset.getString("email"),
                    rset.getString("phone")
            ));
        }

        rset.close();
        stmt.close();
        return entries.toArray(new AddressEntry[0]);
    }

    /**
     * Read AddressEntry from DB using ID.
     *
     * @param id AE ID in table.
     * @return AddressEntry read, or null if not found.
     *
     * @throws SQLException SQL Exception.
     *
     * @author Arshdeep Padda
     */
    public AddressEntry read (long id) throws SQLException
    {
        String q = String.format("SELECT * FROM AddressBookEntry WHERE id=%d", id);
        Statement stmt = this.conn.createStatement();
        ResultSet rset = stmt.executeQuery(q);

        AddressEntry ae = null;
        if (rset.next())
        {
            ae = new AddressEntry(
                    rset.getString("fname"),
                    rset.getString("lname"),
                    rset.getString("street"),
                    rset.getString("city"),
                    rset.getString("state"),
                    Integer.parseInt(rset.getString("zip")),
                    rset.getString("email"),
                    rset.getString("phone")
            );
        }

        rset.close();
        stmt.close();
        return ae;
    }

    /**
     * Add AddressEntry to AddressBookEntry table.
     *
     * @param ae AE to add.
     * @return Integer representing number of rows affected.
     *
     * @throws SQLException SQL Exception
     *
     * @author Arshdeep Padda
     */
    public int create (AddressEntry ae) throws SQLException
    {
        long id = System.currentTimeMillis() / 1000;
        String q = String.format(
                "INSERT INTO AddressBookEntry " +
                        "(id, fname, lname, street, city, state, zip, email, phone) " +
                        "VALUES (%d, '%s', '%s', '%s', '%s', '%s', %d, '%s', '%s')",
            ae.getID(), ae.getFirstName(), ae.getLastName(),
            ae.getStreet(), ae.getCity(), ae.getState(), ae.getZip(),
            ae.getEmail(), ae.getPhone()
        );

        Statement stmt = this.conn.createStatement();
        int rows_affected = stmt.executeUpdate(q);
        stmt.close();
        return rows_affected;
    }

    /**
     * Delete AddressEntry in Database by ID.
     *
     * @param id ID of AE to delete. Type long.
     * @return Integer of how many rows were deleted.
     *
     * @throws SQLException SQL Exception
     *
     * @author Arshdeep Padda
     */
    public int delete (long id) throws SQLException
    {
        String q = String.format("DELETE FROM AddressBookEntry WHERE id=%d", id);
        Statement stmt = this.conn.createStatement();
        int rows_affected = stmt.executeUpdate(q);
        stmt.close();
        return rows_affected;
    }

    /**
     * Update AddressEntry in table identified by ID using new AE fields.
     *
     * All fields of $ae are used to update the row in the
     * db table identified by ID.
     *
     * @param ae AE to use to update table.
     * @param id ID of AE in table to target.
     * @return Integer representing number of rows affected.
     *
     * @throws SQLException SQL Exception
     *
     * @author Arshdeep Padda
     */
    public int update (AddressEntry ae, long id) throws SQLException
    {
        String q = String.format(
                "UPDATE AddressBookEntry " +
                        "SET fname='%s', lname='%s', " +
                        "street='%s', city='%s', state='%s', " +
                        "zip='%d', email='%s', phone='%s' " +
                        "WHERE id=%d",
                ae.getFirstName(), ae.getLastName(),
                ae.getStreet(), ae.getCity(), ae.getState(), ae.getZip(),
                ae.getEmail(), ae.getPhone(), id
        );

        Statement stmt = this.conn.createStatement();
        int rows_affected = stmt.executeUpdate(q);
        stmt.close();
        return rows_affected;
    }
}
