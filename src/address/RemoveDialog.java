package address;

import address.data.AddressEntry;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class RemoveDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField removeLastName;
    private JButton searchLastNameButton;
    private JScrollPane foundListPane;
    private JList entriesFoundBox;

    List<AddressEntry> entriesFound = new ArrayList<AddressEntry>();
    DefaultListModel listModel = new DefaultListModel();

    public RemoveDialog() {

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        this.setSize(800, 400);
        buttonOK.setEnabled(false);

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
        searchLastNameButton.addActionListener(new ActionListener() {
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
        entriesFoundBox.addListSelectionListener(new ListSelectionListener() {
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

    private void onOK() {
        // add your code here
        if (entriesFoundBox.getSelectedIndex() != -1)
            AddressBookApplicationGUI.ab.remove(entriesFound.get(entriesFoundBox.getSelectedIndex()));
        listModel.clear();
        removeLastName.setText("");
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        listModel.clear();
        removeLastName.setText("");
        dispose();
    }
}
