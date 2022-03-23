package address;

import address.data.AddressBook;
import address.data.AddressEntry;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Bryce Schooling, Nguyen Nam Pham, Jason Lim, Arshdeep Padda
 * @version 1.0
 * @since 22 March 2022
 *
 * This GUI class is used to add data to and delete data from and query an AddressBook
 */
public class AddressBookApplicationGUI {

    /**
     * ab is an AddressBook in the AddressBookApplicationGUI
     */
    static AddressBook ab = new AddressBook();

    /**
     * addDialog is an AddAddressEntry object
     */
    static AddAddressEntry addDialog = new AddAddressEntry();

    /**
     * removeDialog is a RemoveDialog object
     */
    static RemoveDialog removeDialog = new RemoveDialog();

    /**
     * rootPanel is the root or main panel of this window
     */
    private JPanel rootPanel;

    /**
     * displayButton displays the list of AddressEntry objects
     */
    private JButton displayButton;

    /**
     * addButton brings up the AddAddressEntry dialog window
     */
    private JButton addButton;

    /**
     * removeButton brings up the RemoveDialog window
     */
    private JButton removeButton;

    /**
     * firstNameBox is a text field that display the first name
     */
    private JTextField firstNameBox;

    /**
     * lastNameBox is a text field that display the last name
     */
    private JTextField lastNameBox;

    /**
     * streetBox is a text field that display the street
     */
    private JTextField streetBox;

    /**
     * stateBox is a text field that display the state
     */
    private JTextField stateBox;

    /**
     * zipBox is a text field that display the Zip Code
     */
    private JTextField zipBox;

    /**
     * phoneBox is a text field that display the phone number
     */
    private JTextField phoneBox;

    /**
     * emailBox is a text field that display the email address
     */
    private JTextField emailBox;

    /**
     * cityBox is a text field that display the city
     */
    private JTextField cityBox;

    /**
     * entryList stores the AddressEntry objects as a JList
     */
    private JList entryList;

    /**
     * editOKBtn confirms the changes to the text fields in an AddressEntry
     */
    private JButton editOKBtn;

    /**
     * cancelBtn cancels the changes to an AddressEntry
     */
    private JButton cancelBtn;

    /**
     * confirmChange is a JLabel that is visible when a text field is focused
     */
    private JLabel confirmChange;

    /**
     * bookEntries is a list of AddressEntry objects
     */
    List<AddressEntry> bookEntries = new ArrayList<AddressEntry>();

    /**
     * listModel is the way to add elements before setting the listModel
     */
    DefaultListModel listModel = new DefaultListModel();

    /**
     * AddressBookApplicationGUI class handles GUI events
     */
    public AddressBookApplicationGUI() {
        confirmChange.setVisible(false);
        editOKBtn.setVisible(false);
        cancelBtn.setVisible(false);

        displayButton.addActionListener(new ActionListener() {
            /**
             * actionPerformed binds the method to the displayButton object
             * @param e is the ActionEvent object
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                //displayArea.setText(ab.list());
                listModel.clear();
                bookEntries = ab.find("");

                for (AddressEntry entry : bookEntries) {
                    listModel.addElement(entry.getLastName() + ", " + entry.getFirstName());
                }

                entryList.setModel(listModel);
            }
        });

        addButton.addActionListener(new ActionListener() {
            /**
             * actionPerformed binds the method to the addButton object
             * @param e is the ActionEvent object
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                addDialog.setTitle("Adding an AddressEntry");
                addDialog.setVisible(true);
            }
        });

        removeButton.addActionListener(new ActionListener() {
            /**
             * actionPerformed binds the method to the removeButton object
             * @param e is the ActionEvent object
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                removeDialog.setTitle("Removing an AddressEntry");
                removeDialog.setVisible(true);
            }
        });

        entryList.addListSelectionListener(new ListSelectionListener() {
            /**
             * valueChanged listens and gets the index to display
             * @param listSelectionEvent is the listSelectionEvent object
             */
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                if (!listSelectionEvent.getValueIsAdjusting()) {
                    if (entryList.getSelectedIndex() != -1) {
                        AddressEntry entry = bookEntries.get(entryList.getSelectedIndex());

                        firstNameBox.setText(entry.getFirstName());
                        lastNameBox.setText(entry.getLastName());
                        streetBox.setText(entry.getStreet());
                        cityBox.setText(entry.getCity());
                        stateBox.setText(entry.getState());
                        zipBox.setText(Integer.toString(entry.getZip()));
                        phoneBox.setText(entry.getPhone());
                        emailBox.setText(entry.getEmail());
                    }
                }
            }
        });

        editOKBtn.addActionListener(new ActionListener() {
            /**
             * actionPerformed binds the method to the editOKBtn object
             * @param actionEvent is an ActionEvent object
             */
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String inputText;

                if (entryList.getSelectedIndex() != -1) {
                    AddressEntry entry = bookEntries.get(entryList.getSelectedIndex());

                    if (!firstNameBox.getText().equals(entry.getFirstName())) {
                        entry.setFirstName(firstNameBox.getText()); // Somewhat funky...
                    }

                    if (!lastNameBox.getText().equals(entry.getLastName())) {
                        entry.setLastName(lastNameBox.getText());
                    }

                    if (!streetBox.getText().equals(entry.getStreet())) {
                        entry.setStreet(streetBox.getText()); 
                    }

                    if (!cityBox.getText().equals(entry.getCity())) {
                        entry.setCity(cityBox.getText());
                    }

                    if (!stateBox.getText().equals(entry.getState())) {
                        entry.setState(stateBox.getText());
                    }

                    if (Integer.parseInt(zipBox.getText()) != entry.getZip()) {
                        entry.setZip(Integer.parseInt(zipBox.getText())); // Somewhat funky...
                    }

                    if (!phoneBox.getText().equals(entry.getPhone())) {
                        entry.setPhone(phoneBox.getText()); // Somewhat funky...
                    }

                    if (!emailBox.getText().equals(entry.getEmail())) {
                        entry.setEmail(emailBox.getText()); // Somewhat funky...
                    }

                    confirmChange.setVisible(false);
                    editOKBtn.setVisible(false);
                    cancelBtn.setVisible(false);
                }
            }
        });

        firstNameBox.addFocusListener(new FocusAdapter() {
            /**
             * focusGained listens to when the firstNameBox is selected
             * @param e is a FocusEvent
             */
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

                confirmChange.setVisible(true);
                editOKBtn.setVisible(true);
                cancelBtn.setVisible(true);
            }
        });

        lastNameBox.addFocusListener(new FocusAdapter() {
            /**
             * focusGained listens to when the lastNameBox is selected
             * @param e is a FocusEvent
             */
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

                confirmChange.setVisible(true);
                editOKBtn.setVisible(true);
                cancelBtn.setVisible(true);
            }
        });

        streetBox.addFocusListener(new FocusAdapter() {
            /**
             * focusGained listens to when the streetBox is selected
             * @param e is a FocusEvent
             */
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

                confirmChange.setVisible(true);
                editOKBtn.setVisible(true);
                cancelBtn.setVisible(true);
            }
        });

        cityBox.addFocusListener(new FocusAdapter() {
            /**
             * focusGained listens to when the cityBox is selected
             * @param e is a FocusEvent
             */
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

                confirmChange.setVisible(true);
                editOKBtn.setVisible(true);
                cancelBtn.setVisible(true);
            }
        });

        stateBox.addFocusListener(new FocusAdapter() {
            /**
             * focusGained listens to when the stateBox is selected
             * @param e is a FocusEvent
             */
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

                confirmChange.setVisible(true);
                editOKBtn.setVisible(true);
                cancelBtn.setVisible(true);
            }
        });

        zipBox.addFocusListener(new FocusAdapter() {
            /**
             * focusGained listens to when the zipBox is selected
             * @param e is a FocusEvent
             */
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

                confirmChange.setVisible(true);
                editOKBtn.setVisible(true);
                cancelBtn.setVisible(true);
            }
        });

        phoneBox.addFocusListener(new FocusAdapter() {
            /**
             * focusGained listens to when the phoneBox is selected
             * @param e is a FocusEvent
             */
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

                confirmChange.setVisible(true);
                editOKBtn.setVisible(true);
                cancelBtn.setVisible(true);
            }
        });

        emailBox.addFocusListener(new FocusAdapter() {
            /**
             * focusGained listens to when the emailBox is selected
             * @param e is a FocusEvent
             */
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

                confirmChange.setVisible(true);
                editOKBtn.setVisible(true);
                cancelBtn.setVisible(true);
            }
        });

        cancelBtn.addActionListener(new ActionListener() {
            /**
             * actionPerformed binds the method to the cancelBtn object
             * @param actionEvent is an ActionEvent object
             */
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                confirmChange.setVisible(false);
                editOKBtn.setVisible(false);
                cancelBtn.setVisible(false);
            }
        });
    }

    /**
     * main runs the main method of AddressBookApplication
     * @param args is an array of String objects from the commandline
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame("AddressBookApplication");
        frame.setContentPane(new AddressBookApplicationGUI().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,500);
        frame.setVisible(true);

        ab.init_from_db();

        // close db connection on app exit
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent event) {
                ab.Close();
            }
        });
    }
}
