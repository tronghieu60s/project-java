package ServerSocket;

import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main implements Runnable {

    String sentence_from_client;
    String sentence_to_client;
    ServerSocket welcomeSocket;

    public static void main(String[] args) throws IOException {
        Main main = new Main();
    }

    public Main() throws IOException {
        System.out.println("Server Already...");
        //Tạo socket server, chờ tại cổng '6543'
        this.welcomeSocket = new ServerSocket(Helpers.ConfigSocket.port);
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket connectionSocket = welcomeSocket.accept();
                if (connectionSocket != null) {
                    //Tạo input stream, nối tới Socket
                    BufferedReader inFromClient
                            = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                    
                    //Tạo outputStream, nối tới socket
                    DataOutputStream outToClient
                            = new DataOutputStream(connectionSocket.getOutputStream());
                    
                    //Đọc thông tin từ socket
                    sentence_from_client = inFromClient.readLine();
                    
                    sentence_to_client = sentence_from_client + " (Server accepted!)" + '\n';
                    //ghi dữ liệu ra socket
                    outToClient.writeBytes(sentence_to_client);
                }
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
