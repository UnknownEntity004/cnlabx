import java.io.*;
import java.net.*;

public class TCPClientFile {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 5000); // Connect to server on localhost and port 5000
            System.out.println("Connected to server.");

            // Open streams for communication
            FileInputStream fileIn = new FileInputStream("send_file.txt");
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            // Send file to server
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = fileIn.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

            System.out.println("File sent.");

            // Close streams and socket
            fileIn.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}