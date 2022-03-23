package address;

import address.data.AddressEntry;

import javax.swing.*;
import java.awt.event.*;

/**
 * The AddAddressEntry class handles the AddAddressEntry window and adds an AddressEntry to AddressBook if confirmed
 */
public class AddAddressEntry extends JDialog {
    /**
     * The main contentPane of this dialog window
     */
    private JPanel contentPane;

    /**
     * buttonOk proceeds to add the AddressEntry to the AddressBook
     */
    private JButton buttonOK;

    /**
     * buttonCancel aborts the adding process
     */
    private JButton buttonCancel;

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
     * AddAddressEntry class adds an AddressEntry to the AddressBook
     */
    public AddAddressEntry() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        this.setSize(500, 600);

        buttonOK.addActionListener(new ActionListener() {
            /**
             * actionPerformed binds itself to buttonOK
             * @param e is an ActionEvent object
             */
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            /**
             * actionPerformed binds itself to buttonCancel
             * @param e is an ActionEvent object
             */
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        /*
         * addWindowListener adds a cast WindowAdapter to the AddAddressEntry window
         */
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            /**
             * actionPerformed binds itself to the contentPane object
             * @param e is an ActionEvent object
             */
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    /**
     * onOK adds the AddressEntry to the AddressBook
     */
    private void onOK() {

        /* If any fields are blank, alert user for correction */
        if (
                firstNameBox.getText().isEmpty() ||
                lastNameBox.getText().isEmpty() ||
                streetBox.getText().isEmpty() ||
                cityBox.getText().isEmpty() ||
                stateBox.getText().isEmpty() ||
                phoneBox.getText().isEmpty() ||
                emailBox.getText().isEmpty()
        )
        {
            JOptionPane.showMessageDialog(this, "Fields cannot be blank.",
                    "Warning", JOptionPane.PLAIN_MESSAGE
            );
            return;
        }

        AddressEntry ae = new AddressEntry(
                System.currentTimeMillis() / 1000,
                firstNameBox.getText(),
                lastNameBox.getText(),
                streetBox.getText(),
                cityBox.getText(),
                stateBox.getText(),
                Integer.parseInt(zipBox.getText()),
                phoneBox.getText(),
                emailBox.getText()
        );

        AddressBookApplicationGUI.ab.add(ae);

        resetText();
        dispose();
    }

    /**
     * onCancel aborts the adding process and return to the main window
     */
    private void onCancel() {
        // add your code here if necessary

        resetText();
        dispose();
    }

    /**
     * resetText clears the text fields in the window
     */
    private void resetText(){
        firstNameBox.setText("");
        lastNameBox.setText("");
        streetBox.setText("");
        cityBox.setText("");
        stateBox.setText("");
        zipBox.setText("");
        phoneBox.setText("");
        emailBox.setText("");
    }

}

