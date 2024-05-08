package org.cristian.client;


import javax.swing.*;

class LayoutClient extends JFrame {

    public LayoutClient() {

        setBounds(600, 300, 280, 350);
        LaminaLayoutClient myLamina = new LaminaLayoutClient();
        add(myLamina);
        setVisible(true);
    }

}