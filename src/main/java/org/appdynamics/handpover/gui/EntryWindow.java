package org.appdynamics.handpover.gui;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import org.appdynamics.handpover.export.Excel;
import org.appdynamics.handpover.export.Zip;
import org.appdynamics.handpover.rest.*;
import org.appdynamics.handpover.config.Globals;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.*;

public class EntryWindow extends JDialog {
    private JPanel EntryWindow;
    private JButton buttonOK;
    private JButton buttonCancel;
    @SuppressWarnings("Since15")
    private JComboBox PROTOCOL;
    private JTextField HOSTNAME;
    private JTextField PORT;
    private JTextField ACCOUNTNAME;
    private JTextField USER;
    private JPasswordField PASSWORD;
    private JTextField PREVIEW;
    private JLabel label_protocol;
    private JLabel label_hostname;
    private JLabel label_port;
    private JLabel label_accountname;
    private JLabel label_user;
    private JLabel label_password;
    private JProgressBar progressBar;

    private EntryWindow() {
        setLocationRelativeTo(null);
        setContentPane(EntryWindow);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

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

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        EntryWindow.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        PROTOCOL.addItem(Globals.CONTROLLER_HTTP);
        //PROTOCOL.addItem(Globals.CONTROLLER_HTTPS);

        HOSTNAME.getDocument().addDocumentListener(textListener());
        PORT.getDocument().addDocumentListener(textListener());

        updatePreview();
    }

    private DocumentListener textListener() {

        return new DocumentListener() {
            public void insertUpdate(DocumentEvent documentEvent) {
                updatePreview();
            }

            public void removeUpdate(DocumentEvent documentEvent) {
                updatePreview();
            }

            public void changedUpdate(DocumentEvent documentEvent) {
                updatePreview();
            }
        };

    }

    private void onOK() {

        class Worker extends SwingWorker<Void, Void> {
            protected Void doInBackground() {
                Auth auth = new Auth();
                String password = new String(PASSWORD.getPassword());
                progressBar.setValue(10);
                try {
                    auth.doAuth(USER.getText(), password, ACCOUNTNAME.getText());
                    Excel excel = new Excel();
                    progressBar.setValue(30);
                    excel.createInitial();
                    progressBar.setValue(50);
                    //GetApps.doGetApps();
                    //progressBar.setValue(40);
                    GetSettings.doGetControllerSettings();
                    //progressBar.setValue(50);
                    GetAudit.doGetAudit();
                    progressBar.setValue(80);
                    GetLogs.doGetControllerLogs();
                    progressBar.setValue(90);
                    Zip.zipFiles();
                    progressBar.setValue(100);
                    JOptionPane.showMessageDialog(null, Globals.DONE_MESSAGE + System.getProperty("user.home") + Globals.ROOT + Globals.OUTPUT_FILE, Globals.DONE, JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), Globals.ERROR,
                            JOptionPane.ERROR_MESSAGE);
                    progressBar.setValue(0);
                }
                return null;
            }

            protected void done() {
                progressBar.setIndeterminate(false);
            }
        }

        new Worker().execute();
    }

    private void onCancel() {
        dispose();
    }

    private void updatePreview() {

        if (PORT.getText().matches(Globals.EMPTY + Globals.PIPE + Globals.CONTROLLER_HTTP_PORT + Globals.PIPE + Globals.CONTROLLER_HTTPS_PORT)) {
            Globals.URL = PROTOCOL.getSelectedItem().toString() + HOSTNAME.getText();
            PREVIEW.setText(Globals.URL);
        } else {
            Globals.URL = PROTOCOL.getSelectedItem().toString() + HOSTNAME.getText() + Globals.COLON + PORT.getText();
            PREVIEW.setText(Globals.URL);
        }
    }

    public static void main(String[] args) {
        EntryWindow dialog = new EntryWindow();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        EntryWindow = new JPanel();
        EntryWindow.setLayout(new GridLayoutManager(8, 2, new Insets(10, 10, 10, 10), -1, -1));
        EntryWindow.setAlignmentX(1.0f);
        EntryWindow.setAlignmentY(1.0f);
        EntryWindow.setMinimumSize(new Dimension(500, 300));
        EntryWindow.setName("Handover Tool");
        EntryWindow.setPreferredSize(new Dimension(500, 300));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        EntryWindow.add(panel1, new GridConstraints(7, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1, true, false));
        panel1.add(panel2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonOK = new JButton();
        buttonOK.setText("OK");
        buttonOK.setMnemonic('O');
        buttonOK.setDisplayedMnemonicIndex(0);
        panel2.add(buttonOK, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonCancel = new JButton();
        buttonCancel.setText("Cancel");
        buttonCancel.setMnemonic('C');
        buttonCancel.setDisplayedMnemonicIndex(0);
        panel2.add(buttonCancel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        progressBar = new JProgressBar();
        progressBar.setEnabled(true);
        progressBar.setIndeterminate(false);
        progressBar.setStringPainted(true);
        progressBar.setVisible(true);
        panel1.add(progressBar, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label_protocol = new JLabel();
        label_protocol.setText("Protocol");
        EntryWindow.add(label_protocol, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label_hostname = new JLabel();
        label_hostname.setText("Hostname");
        EntryWindow.add(label_hostname, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label_port = new JLabel();
        label_port.setText("Port");
        EntryWindow.add(label_port, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label_accountname = new JLabel();
        label_accountname.setText("Account Name");
        EntryWindow.add(label_accountname, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label_user = new JLabel();
        label_user.setText("User");
        EntryWindow.add(label_user, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label_password = new JLabel();
        label_password.setText("Password");
        EntryWindow.add(label_password, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        PROTOCOL = new JComboBox();
        EntryWindow.add(PROTOCOL, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        HOSTNAME = new JTextField();
        HOSTNAME.setText("");
        EntryWindow.add(HOSTNAME, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        PORT = new JTextField();
        PORT.setText("");
        EntryWindow.add(PORT, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        ACCOUNTNAME = new JTextField();
        ACCOUNTNAME.setText("customer1");
        EntryWindow.add(ACCOUNTNAME, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        USER = new JTextField();
        USER.setText("");
        EntryWindow.add(USER, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        PASSWORD = new JPasswordField();
        PASSWORD.setText("");
        EntryWindow.add(PASSWORD, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        PREVIEW = new JTextField();
        PREVIEW.setEditable(false);
        EntryWindow.add(PREVIEW, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Preview");
        EntryWindow.add(label1, new GridConstraints(6, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        label_protocol.setLabelFor(PROTOCOL);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return EntryWindow;
    }
}
