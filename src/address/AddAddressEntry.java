package address;

import address.data.AddressEntry;

import javax.swing.*;
import java.awt.event.*;

public class AddAddressEntry extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField firstNameBox;
    private JTextField lastNameBox;
    private JTextField streetBox;
    private JTextField stateBox;
    private JTextField zipBox;
    private JTextField phoneBox;
    private JTextField emailBox;
    private JTextField cityBox;

    /**
     * AddAddressEntry class adds an AddressEntry to the AddressBook
     */
    public AddAddressEntry() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        this.setSize(500, 600);

        /**
         * addActionListener adds an ActionListener to the buttonOK object
         */
        buttonOK.addActionListener(new ActionListener() {
            /**
             * actionPerformed binds itself to buttonOK
             * @param e is an ActionEvent object
             */
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        /**
         * addActionListener adds an ActionListener to the buttonCancel object
         */
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

        /**
         * addWindowListener adds a casted WindowAdapter to the AddAddressEntry window
         */
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        /**
         * registerKeyboardAction calls onCancel() when ESCAPE is pressed
         */
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

