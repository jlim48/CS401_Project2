package address;

import address.data.AddressEntry;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * RemoveDialog class handles the RemoveDialog window events and removes the AddressEntry if confirmed
 */
public class RemoveDialog extends JDialog {
    /**
     * contentPane is the main content pane of this dialog window
     */
    private JPanel contentPane;

    /**
     * buttonOK adds the AddressEntry to the AddressBook
     */
    private JButton buttonOK;

    /**
     * buttonCancel aborts the adding process and returns to the main window
     */
    private JButton buttonCancel;

    /**
     * removeLastName is a text field that the user inputs to find last names that match
     */
    private JTextField removeLastName;

    /**
     * searchLastNameButton searches the last names that match the removeLastName field
     */
    private JButton searchLastNameButton;

    /**
     * foundListPane is the pane that houses the entriesFoundBox
     */
    private JScrollPane foundListPane;

    /**
     * entriesFoundBox list the entries that match with the removeLastName query
     */
    private JList entriesFoundBox;

    /**
     * entriesFound is a list of AddressEntry objects that match with the removeLastName query
     */
    List<AddressEntry> entriesFound = new ArrayList<AddressEntry>();

    /**
     * listModel adds entries before being set
     */
    DefaultListModel listModel = new DefaultListModel();


    /**
     * RemoveDialog class handles the RemoveDialog window
     */
    public RemoveDialog() {

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        this.setSize(800, 400);
        buttonOK.setEnabled(false);

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
         * addWindowListener adds a WindowAdapter to the class
         */
        addWindowListener(new WindowAdapter() {
            /**
             * windowClosing binds the method to close the window
             * @param e is a WindowEvent
             */
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        /**
         * registerkeyboardAction calls onCancel() on pressing ESCAPE
         */
        contentPane.registerKeyboardAction(new ActionListener() {
            /**
             * actionPerformed binds the method to the contentPane
             * @param e is an ActionEvent
             */
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        /**
         * addActionLister adds an ActionListener to the searchLastNameButton
         */
        searchLastNameButton.addActionListener(new ActionListener() {
            /**
             * actionPerformed binds itself to searchLastNameButton
             * @param e is an ActionEvent
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                listModel.clear();
                entriesFound = AddressBookApplicationGUI.ab.find(removeLastName.getText());
                for (AddressEntry i: entriesFound){
                    listModel.addElement(i);
                }
                entriesFoundBox.setModel(listModel);
            }
        });

        /**
         * addListSelectionListener adds an ListSelectionListener to the entriesFoundBox object
         */
        entriesFoundBox.addListSelectionListener(new ListSelectionListener() {
            /**
             * valueChanged listens and get the index to remove
             * @param e is a ListSelectionEvent
             */
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == false) {

                    if (entriesFoundBox.getSelectedIndex() == -1) {
                        buttonOK.setEnabled(false);

                    } else {
                        buttonOK.setEnabled(true);
                    }
                }
            }
        });
    }

    /**
     * onOK removes the selected entry from the AddressBook
     */
    private void onOK() {
        // add your code here
        if (entriesFoundBox.getSelectedIndex() != -1)
            AddressBookApplicationGUI.ab.remove(entriesFound.get(entriesFoundBox.getSelectedIndex()));

        listModel.clear();
        removeLastName.setText("");
        dispose();
    }

    /**
     * onCancel aborts the removal process and returns to the main window
     */
    private void onCancel() {
        // add your code here if necessary
        listModel.clear();
        removeLastName.setText("");
        dispose();
    }
}
