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
import java.util.ArrayList;
import java.util.List;

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

    public AddressBookApplicationGUI() {
        displayButton.addActionListener(new ActionListener() {
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

        entryList.addListSelectionListener(new ListSelectionListener() {
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

        editOKBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String inputText;

                if (entryList.getSelectedIndex() != -1) {
                    AddressEntry entry = bookEntries.get(entryList.getSelectedIndex());

                    if (!firstNameField.getText().equals(entry.getFirstName())) {
                        entry.setFirstName(firstNameField.getText()); // Somewhat funky...
                    }

                    confirmChange.setVisible(false);
                    editOKBtn.setVisible(false);
                    cancelBtn.setVisible(false);
                }
            }
        });

        firstNameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

                confirmChange.setVisible(true);
                editOKBtn.setVisible(true);
                cancelBtn.setVisible(true);
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
