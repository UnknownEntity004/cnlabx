import java.io.*;
import java.net.*;

public class UDPClientFile {
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost"); // Change to the server's IP address if
                                                                            // needed
            int serverPort = 5000;

            // File to be sent
            File file = new File("send_file.txt"); // Change to the file path as needed
            String fileName = file.getName();
            int fileSize = (int) file.length();

            // Send file name
            byte[] sendBuffer = fileName.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, serverPort);
            socket.send(sendPacket);

            // Send file size
            sendBuffer = String.valueOf(fileSize).getBytes();
            sendPacket = new DatagramPacket(sendBuffer, sendBuffer.length, serverAddress, serverPort);
            socket.send(sendPacket);

            // Wait for server's readiness
            byte[] receiveBuffer = new byte[4096];
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
            socket.receive(receivePacket);
            String ack = new String(receivePacket.getData(), 0, receivePacket.getLength());
            if (!"READY".equals(ack)) {
                System.out.println("Server not ready.");
                return;
            }

            // Send file data
            FileInputStream fileIn = new FileInputStream(file);
            int bytesRead;
            while ((bytesRead = fileIn.read(sendBuffer)) != -1) {
                sendPacket = new DatagramPacket(sendBuffer, bytesRead, serverAddress, serverPort);
                socket.send(sendPacket);
            }

            System.out.println("File " + fileName + " sent.");
            fileIn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }
}