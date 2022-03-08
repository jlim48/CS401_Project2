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

    public AddAddressEntry() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        this.setSize(500, 600);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
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

    private void onCancel() {
        // add your code here if necessary

        resetText();
        dispose();
    }
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

