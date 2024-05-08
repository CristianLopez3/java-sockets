package org.cristian.server;

import org.cristian.client.SendPackage;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

class LayoutServer extends JFrame implements Runnable {

    private JTextArea areaText;

    public LayoutServer() {

        setBounds(1100, 300, 280, 350);
        JPanel myLamina = new JPanel();

        myLamina.setLayout(new BorderLayout());
        areaText = new JTextArea();
        myLamina.add(areaText, BorderLayout.CENTER);
        add(myLamina);
        setVisible(true);

        Thread myThread = new Thread(this);
        myThread.start();

    }

    @Override
    public void run() {

        try {

            ServerSocket server = new ServerSocket(8080);
            String nick, ip, message;
            SendPackage receivedData;

            while (true) {
                Socket mySocket = server.accept();
                ObjectInputStream payload = new ObjectInputStream(mySocket.getInputStream());
                receivedData = (SendPackage) payload.readObject();

                nick = receivedData.getNick();
                ip = receivedData.getIp();
                message = receivedData.getMessage();

                areaText.append(String.format("%s: %s for %s\n", nick, message, ip));

                Socket sendToClient = new Socket(ip, 9090);
                ObjectOutputStream sendPayload = new ObjectOutputStream(sendToClient.getOutputStream());
                sendPayload.writeObject(receivedData);

                sendToClient.close();
                sendPayload.close();
                mySocket.close();

            }

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }


}