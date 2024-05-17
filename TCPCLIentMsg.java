import java.io.*;
import java.net.*;

public class TCPCLIentMsg {
    public static void main(String[] args) {
        String hostname = "localhost"; // Server's hostname or IP address
        int port = 1234; // Port number

        try (Socket socket = new Socket(hostname, port)) {

            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            String message = reader.readLine();
            System.out.println("Message from server: " + message);

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}