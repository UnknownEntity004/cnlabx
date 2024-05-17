import java.io.*;
import java.net.*;

public class HelloUDPServer {
    public static void main(String[] args) {
        int port = 1234; // Port number on which server will listen

        try {
            DatagramSocket socket = new DatagramSocket(port);
            System.out.println("Server is listening on port " + port);

            byte[] buffer = new byte[256];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                InetAddress address = packet.getAddress();
                int clientPort = packet.getPort();

                String message = "Hello";
                byte[] responseBuffer = message.getBytes();

                DatagramPacket responsePacket = new DatagramPacket(responseBuffer, responseBuffer.length, address, clientPort);
                socket.send(responsePacket);
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}