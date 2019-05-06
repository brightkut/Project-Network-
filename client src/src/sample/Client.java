package sample;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
//นายดิศรณ์  ฐืติกรโกวิท 5810400990
public class Client extends Thread {

    DatagramSocket clientSocket;
    InetAddress ip;
    BufferedReader inFromUser;
    Controller controller;
    int cola = 0;
    int french = 0;
    int chicken = 0;
    int burger = 0;


    public Client(Controller controller) {
        try {
            this.controller = controller;
            clientSocket = new DatagramSocket();
            ip = InetAddress.getByName("localhost");
            inFromUser = new BufferedReader(new InputStreamReader(System.in));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getCola() {
        return cola;
    }

    public int getFrench() {
        return french;
    }

    public int getChicken() {
        return chicken;
    }

    public int getBurger() {
        return burger;
    }

    public void run() {
        try {
            main();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void main() throws Exception {
//        sent(inFromUser.readLine());
        while (true) {
//            System.out.println("3");
            byte[] data_received = new byte[1024];
            DatagramPacket rec_packet = new DatagramPacket(data_received, data_received.length);
            clientSocket.receive(rec_packet);

            String command = new String(rec_packet.getData()).trim();
            System.out.println("From Server:" + command);
            if (command.trim().equals("User login")) {
                controller.setTextArea();
            }
            if (command.trim().equals("m")) {
                controller.setA("Menu :\nCola   price   20\nFrench  fries   price   40\nChicken price   40\nBurger  price   60");
            }
            if (command.trim().equals("c")) {
                controller.setA("\nCola Order");
                cola+=1;
            }
            if (command.trim().equals("f")) {
                controller.setA("\nFrench fries Order");
                french+=1;
            }
            if (command.trim().equals("ch")) {
                controller.setA("\nChicken Order");
                chicken+=1;
            }
            if (command.trim().equals("b")) {
                controller.setA("\nBurger Order");
                burger+=1;
            }

        }
    }
    public void sent(String a)
    {
        byte[] data_send = new byte[1024];
        String sent = a.trim();
        data_send = sent.getBytes();
        DatagramPacket sen_packet = new DatagramPacket(data_send, data_send.length, ip, 51190);
        String send_to_server = new String(sen_packet.getData()).trim();
        System.out.println("Send to Server: "+ send_to_server);
//        System.out.println(ip);

        try {
            clientSocket.send(sen_packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

