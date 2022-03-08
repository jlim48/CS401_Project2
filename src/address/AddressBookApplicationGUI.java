package address;

import address.data.AddressBook;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public AddressBookApplicationGUI() {
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayArea.setText(ab.list());
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDialog.setTitle("Adding an AddressEntry");
                addDialog.setVisible(true);
            }
        });
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeDialog.setTitle("Removing an AddressEntry");
                removeDialog.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("AddressBookApplication");
        frame.setContentPane(new AddressBookApplicationGUI().rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,500);
        frame.setVisible(true);


        ab.readFromFile("entries.txt");

    }
}
