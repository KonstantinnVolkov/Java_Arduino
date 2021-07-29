package com.company;

import arduino.Arduino;
import java.util.Scanner;

public class arduinoControlConsole {

    public static void main(String[] args) throws InterruptedException{
        Arduino arduino = new Arduino("COM5",9600);
        Scanner scanner = new Scanner(System.in);

        boolean connected = arduino.openConnection();
        System.out.println("Соединение установлено: " + connected);
        Thread.sleep(2000);

        label_1:
        while (scanner.hasNext()) {

            String s = scanner.nextLine();

            switch (s) {
                case "on":
                    arduino.serialWrite('1');
                    break;
                case "off":
                    arduino.serialWrite('0');
                    break;
                case "exit":
                    arduino.serialWrite('0');
                    arduino.closeConnection();
                    break label_1;
                default:
                    System.out.println(s + " - не является командой");
                    break;
            }
        }

    }
}
