package ClientSocket;

import Helpers.ConfigSocket;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

public class Socket {
    public static void main(String[] args) throws Exception{
        String sentence_to_server;
        String sentence_from_server;

        //Tạo Inputstream(từ bàn phím)
        System.out.print("Input from client: ");
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        //Lấy chuỗi ký tự nhập từ bàn phím
        sentence_to_server = inFromUser.readLine();

        //Tạo socket cho client kết nối đến server qua ID address và port number
        java.net.Socket clientSocket = new java.net.Socket("127.0.0.1", ConfigSocket.port);

        //Tạo OutputStream nối với Socket
        DataOutputStream outToServer
                = new DataOutputStream(clientSocket.getOutputStream());

        //Tạo inputStream nối với Socket
        BufferedReader inFromServer
                = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        //Gửi chuỗi ký tự tới Server thông qua outputStream đã nối với Socket (ở trên)
        outToServer.writeBytes(sentence_to_server + '\n');

        //Đọc tin từ Server thông qua InputSteam đã nối với socket
        sentence_from_server = inFromServer.readLine();

        //print kết qua ra màn hình
        System.out.println("FROM SERVER: " + sentence_from_server);

        //Đóng liên kết socket
        clientSocket.close();
    }
}
