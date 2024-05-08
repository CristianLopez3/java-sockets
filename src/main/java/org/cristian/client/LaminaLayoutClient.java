package org.cristian.client;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

class LaminaLayoutClient extends JPanel implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(LaminaLayoutClient.class.getName());

    private JButton myButton;
    private JTextArea chatField;
    private JComboBox<String> ip;
    private JLabel nick;
    private JTextField message;
    private ExecutorService executorService;

    public LaminaLayoutClient() {
        createUIComponents();
        executorService = Executors.newSingleThreadExecutor();
        executorService.submit(this);
    }

    private void createUIComponents() {
        String userNick = JOptionPane.showInputDialog("Enter your nick: ");

        TitledBorder title = BorderFactory.createTitledBorder(" Online:");
        setBorder(title);

        JLabel nameNIck = new JLabel("Nick: ");
        add(nameNIck);

        nick = new JLabel();
        nick.setText(userNick);
        add(nick);

        ip = new JComboBox<>();
        ip.addItem("User 1");
        ip.addItem("User 2");
        ip.addItem("User 3");
        add(ip);

        chatField = new JTextArea(12, 20);
        add(chatField);

        message = new JTextField(20);
        add(message);

        myButton = new JButton("Send");
        SendText sendText = new SendText();
        myButton.addActionListener(sendText);
        myButton.setBackground(Color.BLACK);
        myButton.setForeground(Color.WHITE);

        add(myButton);
    }

    @Override
    public void run() {
        try (ServerSocket server = new ServerSocket(9090)) {
            while (true) {
                try (Socket client = server.accept();
                     ObjectInputStream payload = new ObjectInputStream(client.getInputStream())) {

                    SendPackage receivedData = (SendPackage) payload.readObject();
                    chatField.append(String.format("%s: %s\n", receivedData.getNick(), receivedData.getMessage()));
                }
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error in server socket", ex);
        }
    }

    private class SendText implements ActionListener {

        private final String IP = "192.168.20.25";

        @Override
        public void actionPerformed(ActionEvent e) {
            chatField.append(String.format("%s: %s\n", nick.getText(), message.getText()));

            try (Socket mySocket = new Socket(IP, 8080);
                 ObjectOutputStream payload = new ObjectOutputStream(mySocket.getOutputStream())) {

                SendPackage data = new SendPackage();
                data.setNick(nick.getText());
                data.setIp(ip.getSelectedItem().toString());
                data.setMessage(message.getText());

                payload.writeObject(data);

            } catch (IOException ex) {
                LOGGER.log(Level.SEVERE, "Error in client socket", ex);
            }
        }
    }
}