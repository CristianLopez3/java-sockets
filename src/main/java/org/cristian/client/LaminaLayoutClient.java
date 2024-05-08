package org.cristian.client;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

class LaminaLayoutClient extends JPanel {

    private JButton myButton;
    private JTextArea chatField;

    private JTextField ip;
    private JTextField nick;
    private JTextField message;

    public LaminaLayoutClient() {


        TitledBorder title = BorderFactory.createTitledBorder("- CHAT -");
        setBorder(title);

        nick = new JTextField(5);
        add(nick);

        ip = new JTextField(10);
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

    private class SendText implements ActionListener {

        private final String PORT = "192.168.20.25";

        @Override
        public void actionPerformed(ActionEvent e) {
            try {

                Socket mySocket = new Socket(PORT, 8080);
                SendPackage data = new SendPackage();
                data.setNick(nick.getText());
                data.setIp(ip.getText());
                data.setMessage(message.getText());

                ObjectOutputStream payload = new ObjectOutputStream(mySocket.getOutputStream());
                payload.writeObject(data);

                mySocket.close();

            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

    }

}
