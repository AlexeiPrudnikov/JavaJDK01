package Forms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ActionService implements ActionListener {
    private MainForm parent;

    public ActionService(MainForm parent) {
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Calendar now = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String message  = df.format(now.getTime()) + ": ";
        if (e.getActionCommand().equals("Start")) {
            if (parent.isServStatus()) {
                JOptionPane.showMessageDialog(null, "Service not started", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                parent.setTitle("Service started");
                parent.setServStatus(true);
                parent.getStartButton().setEnabled(false);
                parent.getStopButton().setEnabled(true);
                message += "service started";
                parent.getLogText().setText(parent.getLogText().getText() + message + "\n");

            }
        } else {
            if (e.getActionCommand().equals("Stop")) {
                if (parent.isServStatus()) {
                    parent.setTitle("Service stop");
                    parent.setServStatus(false);
                    parent.getStartButton().setEnabled(true);
                    parent.getStopButton().setEnabled(false);
                    message += "service stoped";
                    parent.getLogText().setText(parent.getLogText().getText() + message + "\n");
                } else {
                    JOptionPane.showMessageDialog(null, "Service not started", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
