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

    static AddressBook ab = new AddressBook();
    static AddAddressEntry addDialog = new AddAddressEntry();
    static RemoveDialog removeDialog = new RemoveDialog();
;
    private JPanel rootPanel;
    private JButton displayButton;
    private JTextArea displayArea;
    private JButton addButton;
    private JButton removeButton;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField streetField;
    private JTextField cityField;
    private JTextField stateField;
    private JTextField zipField;
    private JTextField phoneField;
    private JTextField emailField;
    private JList entryList;
    private JButton editOKBtn;
    private JButton cancelBtn;
    private JLabel confirmChange;

    List<AddressEntry> bookEntries = new ArrayList<AddressEntry>();
    DefaultListModel listModel = new DefaultListModel();

    /**
     * AddressBookApplicationGUI class handling GUI events
     */
    public AddressBookApplicationGUI() {
        confirmChange.setVisible(false);
        editOKBtn.setVisible(false);
        cancelBtn.setVisible(false);

        /**
         * addActionListener adds an ActionListener to the displayButton
         */
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

        /**
         * addActionListener adds an ActionListener to the displayButton
         */
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

        /**
         * addActionListener adds an ActionListener to the removeButton
         */
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

        /**
         * addListSelectionListener adds a ListSelectionListener to the entryList
         */
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

                        firstNameField.setText(entry.getFirstName());
                        lastNameField.setText(entry.getLastName());
                        streetField.setText(entry.getStreet());
                        cityField.setText(entry.getCity());
                        stateField.setText(entry.getState());
                        zipField.setText(Integer.toString(entry.getZip()));
                        phoneField.setText(entry.getPhone());
                        emailField.setText(entry.getEmail());
                    }
                }
            }
        });

        /**
         * addActionListen adds an ActionListener to the editOKBtn
         */
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

                    if (!firstNameField.getText().equals(entry.getFirstName())) {
                        entry.setFirstName(firstNameField.getText()); // Somewhat funky...
                    }

                    if (!lastNameField.getText().equals(entry.getLastName())) {
                        entry.setLastName(lastNameField.getText());
                    }

                    if (!streetField.getText().equals(entry.getStreet())) {
                        entry.setStreet(streetField.getText()); 
                    }

                    if (!cityField.getText().equals(entry.getCity())) {
                        entry.setCity(cityField.getText());
                    }

                    if (!stateField.getText().equals(entry.getState())) {
                        entry.setState(stateField.getText());
                    }

                    if (Integer.parseInt(zipField.getText()) != entry.getZip()) {
                        entry.setZip(Integer.parseInt(zipField.getText())); // Somewhat funky...
                    }

                    if (!phoneField.getText().equals(entry.getPhone())) {
                        entry.setPhone(phoneField.getText()); // Somewhat funky...
                    }

                    if (!emailField.getText().equals(entry.getEmail())) {
                        entry.setEmail(emailField.getText()); // Somewhat funky...
                    }

                    confirmChange.setVisible(false);
                    editOKBtn.setVisible(false);
                    cancelBtn.setVisible(false);
                }
            }
        });

        /**
         * addFocusListener adds a FocusAdapter to the firstNameField
         */
        firstNameField.addFocusListener(new FocusAdapter() {
            /**
             * focusGained listens to when the firstNameField is selected
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

        /**
         * addFocusListener adds a FocusAdapter to the lastNameField
         */
        lastNameField.addFocusListener(new FocusAdapter() {
            /**
             * focusGained listens to when the lastNameField is selected
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

        /**
         * addFocusListener adds a FocusAdapter to the streetField
         */
        streetField.addFocusListener(new FocusAdapter() {
            /**
             * focusGained listens to when the streetField is selected
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

        /**
         * addFocusListener adds a FocusAdapter to the cityField
         */
        cityField.addFocusListener(new FocusAdapter() {
            /**
             * focusGained listens to when the cityField is selected
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

        /**
         * addFocusListener adds a FocusAdapter to the stateField
         */
        stateField.addFocusListener(new FocusAdapter() {
            /**
             * focusGained listens to when the stateField is selected
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

        /**
         * addFocusListener adds a FocusAdapter to the zipField
         */
        zipField.addFocusListener(new FocusAdapter() {
            /**
             * focusGained listens to when the zipField is selected
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

        /**
         * addFocusListener adds a FocusAdapter to the phoneField
         */
        phoneField.addFocusListener(new FocusAdapter() {
            /**
             * focusGained listens to when the phoneField is selected
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

        /**
         * addFocusListener adds a FocusAdapter to the emailField
         */
        emailField.addFocusListener(new FocusAdapter() {
            /**
             * focusGained listens to when the emailField is selected
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

        /**
         * addActionListen adds an ActionListener to the cancelBtn
         */
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
