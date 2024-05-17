import java.io.*;
import java.net.*;

public class TCPServerFile {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5000); // Server listens on port 5000
            System.out.println("Server waiting for client connection...");

            Socket socket = serverSocket.accept(); // Wait for client connection
            System.out.println("Client connected.");

            // Open streams for communication
            DataInputStream in = new DataInputStream(socket.getInputStream());
            FileOutputStream fileOut = new FileOutputStream("received_file.txt");

            // Read file from client
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = in.read(buffer, 0, buffer.length)) != -1) {
                fileOut.write(buffer, 0, bytesRead);
            }

            System.out.println("File received.");

            // Close streams and socket
            fileOut.close();
            in.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}