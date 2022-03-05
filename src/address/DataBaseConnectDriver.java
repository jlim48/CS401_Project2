package address;

import address.DataBaseConnect;
import address.data.AddressEntry;

import java.io.IOException;
import java.sql.SQLException;

public class DataBaseConnectDriver
{
    public static void main (String[] args) throws SQLException, ClassNotFoundException, IOException, RuntimeException
    {
        // Empty handle on db. No connection made yet.
        DataBaseConnect db = new DataBaseConnect();
        // Attempt to establish connection. May throw SQLException,
        // IOException if credential file inaccessible, or runtime exception
        // if cred file formatted incorrectly.
        db.connect();
        // Now ready to query and perform CRUD.

        db.read(); // => AddressEntry[]
        db.read(123); // => AddressEntry

        db.create(new AddressEntry()); // => 1 if added successfully, 0 if not,
        // and something else if the moon is made of blue cheese.

        db.update(new AddressEntry(), 123); // => rows affected

        db.delete(123); // => rows affected

        db.disconnect(); // may throw SQLException
    }
}
