import java.io.*;
import java.net.*;

public class UDPServer {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(5000); // Server listens on port 5000
            byte[] receiveBuffer = new byte[4096];
            byte[] sendBuffer = new byte[4096];

            System.out.println("Server is waiting for file...");

            // Receive file name and size
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            socket.receive(receivePacket);
            String fileName = new String(receivePacket.getData(), 0, receivePacket.getLength());
            socket.receive(receivePacket);
            int fileSize = Integer.parseInt(new String(receivePacket.getData(), 0, receivePacket.getLength()));

            // Acknowledge receipt of file name and size
            InetAddress clientAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
            String ack = "READY";
            sendBuffer = ack.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, clientAddress, clientPort);
            socket.send(sendPacket);

            // Receive file data
            FileOutputStream fileOut = new FileOutputStream("received_" + fileName);
            int totalBytesReceived = 0;
            while (totalBytesReceived < fileSize) {
                socket.receive(receivePacket);
                int bytesRead = receivePacket.getLength();
                fileOut.write(receivePacket.getData(), 0, bytesRead);
                totalBytesReceived += bytesRead;
            }

            System.out.println("File " + fileName + " received.");
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}