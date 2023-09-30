package Forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;


public class MainForm extends JFrame {
    private int POSITION_X = 300;
    private int POSITION_Y = 300;
    private int SIZE_HEIGHT = 500;
    private int SIZE_WIDTH = 300;
    private JPanel panel;
    private JPanel buttonsPanel;
    private JButton startButton = new JButton("Start");
    private JButton sendButton = new JButton("Send Message");

    public JButton getStartButton() {
        return startButton;
    }

    public JButton getStopButton() {
        return stopButton;
    }

    private JButton stopButton = new JButton("Stop");
    private JLabel servLabel = new JLabel("Server");
    private JTextField servText = new JTextField(19);
    private JLabel portLabel = new JLabel("Port");
    private JTextField portText = new JTextField(20);
    private JLabel userLabel = new JLabel("User");

    private JTextField userText = new JTextField(20);

    public JTextField getUserText() {
        return userText;
    }

    private JLabel passLabel = new JLabel("Password");
    private JPasswordField passText = new JPasswordField(17);

    private JTextArea logText = new JTextArea(5, 30);

    public void setLogText(JTextArea logText) {
        this.logText = logText;
    }

    public JTextArea getLogText() {
        return logText;
    }

    private JTextField messageText = new JTextField();

    public JTextField getMessageText() {
        return messageText;
    }

    public void setMessageText(JTextField messageText) {
        this.messageText = messageText;
    }

    public boolean isServStatus() {
        return servStatus;
    }

    public void setServStatus(boolean servStatus) {
        this.servStatus = servStatus;
    }

    private boolean servStatus = false;

    private String readLog() throws IOException {
        File log = new File("system.log");
        try (FileReader fr = new FileReader(log)) {
            String line;
            String result = "";
            BufferedReader bufferedReader = new BufferedReader(fr);
            while ((line = bufferedReader.readLine()) != null) {
                result += line + "\n";
            }
            return result;
        }
    }

    private void writeLog() throws IOException {
        try (FileWriter fileWriter = new FileWriter("system.log", false)) {
            fileWriter.write(logText.getText());
        }
    }

    public MainForm() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(SIZE_WIDTH, SIZE_HEIGHT);
        setLocation(POSITION_X, POSITION_Y);
        ActionService action = new ActionService(this);
        ActionSendMessage actionSendMessage = new ActionSendMessage(this);
        stopButton.addActionListener(action);
        stopButton.setEnabled(false);
        startButton.addActionListener(action);
        sendButton.addActionListener(actionSendMessage);
        setResizable(false);
        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    writeLog();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        panel = new JPanel(new GridLayout(7, 1));
        JPanel subPanel = new JPanel();
        subPanel.add(servLabel);
        subPanel.add(servText);
        panel.add(subPanel);
        subPanel = new JPanel();
        subPanel.add(portLabel);
        subPanel.add(portText);
        panel.add(subPanel);
        subPanel = new JPanel();
        subPanel.add(userLabel);
        subPanel.add(userText);
        panel.add(subPanel);
        subPanel = new JPanel();
        subPanel.add(passLabel);
        subPanel.add(passText);
        panel.add(subPanel);
        panel.add(messageText);
        panel.add(sendButton);
        logText.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(logText);
        add(scrollPane);
        add(panel, BorderLayout.NORTH);
        buttonsPanel = new JPanel(new GridLayout(1, 2));
        buttonsPanel.add(startButton);
        buttonsPanel.add(stopButton);
        if (servStatus) {
            setTitle("Service start");
        } else {
            setTitle("Service stop");
        }
        this.add(buttonsPanel, BorderLayout.SOUTH);
        try {
            logText.setText(readLog());
        } catch (IOException exception) {

        }
        setVisible(true);
    }
}

