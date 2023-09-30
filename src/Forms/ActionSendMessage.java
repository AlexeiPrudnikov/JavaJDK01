package Forms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ActionSendMessage implements ActionListener {
    private MainForm mainForm;

    public ActionSendMessage(MainForm mainForm) {
        this.mainForm = mainForm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!mainForm.isServStatus()) {
            JOptionPane.showMessageDialog(null, "Service not started", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            String message = mainForm.getMessageText().getText();
            String log = mainForm.getLogText().getText();
            Calendar now = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            log += df.format(now.getTime()) + ", " + mainForm.getUserText().getText() + ": " + message + "\n";
            JTextArea logText = mainForm.getLogText();
            logText.setText(log);
            mainForm.getMessageText().setText("");
        }

    }
}
