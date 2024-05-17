import java.io.*;
import java.net.*;

public class HelloUDPClient {
    public static void main(String[] args) {
        String hostname = "localhost"; // Server's hostname or IP address
        int port = 1234; // Port number

        try {
            DatagramSocket socket = new DatagramSocket();

            byte[] buffer = new byte[256];
            InetAddress address = InetAddress.getByName(hostname);

            DatagramPacket requestPacket = new DatagramPacket(buffer, buffer.length, address, port);
            socket.send(requestPacket);

            DatagramPacket responsePacket = new DatagramPacket(buffer, buffer.length);
            socket.receive(responsePacket);

            String received = new String(responsePacket.getData(), 0, responsePacket.getLength());
            System.out.println("Message from server: " + received);

            socket.close();
        } catch (IOException ex) {
            System.out.println("Client exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}