package com.company;

import arduino.Arduino;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class arduinoControlApp implements ActionListener{

    JFrame frame;
    JButton onButton, offButton;
    JLabel conditionLabel;
    Font myFont = new Font("System", Font.BOLD, 12);

    Arduino arduino = new Arduino("COM5", 9600);
    public boolean connection  = arduino.openConnection();;

    public arduinoControlApp() {

        frame = new JFrame("Control arduino");
        frame.setLayout(new FlowLayout());
        frame.setSize(220,90);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        onButton = new JButton("ВКЛ");
        offButton = new JButton("ВЫКЛ");

        onButton.addActionListener(this);
        offButton.addActionListener(this);

        frame.add(onButton);
        frame.add(offButton);

        if (connection) {
            conditionLabel = new JLabel("Connected");
            frame.add(conditionLabel);
        } else {
            conditionLabel = new JLabel("Disconnected");
            frame.add(conditionLabel);
        }

        frame.setVisible(true);
    }

    public static void main(String[] args) throws InterruptedException {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new arduinoControlApp();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ВКЛ")) {
            arduino.serialWrite('1');
        }
        if (e.getActionCommand().equals("ВЫКЛ")) {
            arduino.serialWrite('0');
        }
    }
}
