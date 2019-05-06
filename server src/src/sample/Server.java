package sample;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;
//นายดิศรณ์  ฐืติกรโกวิท 5810400990
public class Server {
    DatagramSocket serverSocket = null;
    ArrayList<Integer> port_array;
    ArrayList<InetAddress> inetAddressArrayList;
    int c = 0;
    boolean ready = false;


    public Server() {
        try {
            serverSocket = new DatagramSocket(51190);
            process();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void sendTo(InetAddress ip, int port, String msg) throws IOException {
        InetSocketAddress address = new InetSocketAddress(ip, port);
        byte[] buffer = msg.getBytes();


        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        packet.setSocketAddress(address);
        serverSocket.send(packet);
    }

    public void broadcast(String msg) throws IOException {
        sendTo(inetAddressArrayList.get(0), port_array.get(0), msg);
        sendTo(inetAddressArrayList.get(1), port_array.get(1), msg);
    }

    public void process() throws Exception {
        boolean running = true;


        byte[] data_send = new byte[1024];


        String player1 = "";
        String player2 = "";

        System.out.println("Server Open");
        port_array = new ArrayList<>();
        inetAddressArrayList = new ArrayList<>();
        while (running) {
            byte[] data_received = new byte[1024];


            DatagramPacket rec_packet = new DatagramPacket(data_received, data_received.length);
            serverSocket.receive(rec_packet);
            String rec = new String(rec_packet.getData());
            System.out.println("From User: " + rec.trim());

            InetAddress ip = rec_packet.getAddress();
            int port = rec_packet.getPort();
            System.out.println("open");

            if (port_array.size() != 2 && rec.trim().equals("connect")) {
                port_array.add(port);
                inetAddressArrayList.add(ip);
                if (player1.equals("")) {
                    player1 += "User1";
                    System.out.println("User1 login ");
                    String msg = "User login";
                    sendTo(ip,port,msg);
//                    System.out.println(ip);
//                    System.out.println(port);
                    System.out.println("Send to User: " + msg);


                } else if (player1.equals("player1") && player2.equals("")) {
                    System.out.println("player2 login ");
                    player2 += "player2";
                    String msg = "all login";
                    broadcast(msg);
                    System.out.println("Send to User: " + msg );


                }
            }
            if(rec.trim().equals("Menu")){
                String msg = "m";
                sendTo(ip,port,msg);
                System.out.println(port);
                System.out.println(ip);

            }
            if(rec.trim().equals("Cola")){
                String msg = "c";
                sendTo(ip,port,msg);

            }
            if(rec.trim().equals("French fries")){
                String msg = "f";
                sendTo(ip,port,msg);

            }
            if(rec.trim().equals("Chicken")){
                String msg = "ch";
                sendTo(ip,port,msg);

            }
            if(rec.trim().equals("Burger")){
                String msg = "b";
                sendTo(ip,port,msg);

            }




        }
    }
}
