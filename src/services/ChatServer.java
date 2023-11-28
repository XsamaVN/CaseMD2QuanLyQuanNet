package services;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServer {
    public static void sever() {
        int portNumber = 3333;
        boolean check = true;
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.println("Chat với khách hàng");
            System.out.println("-------------------");

            while (check) {
                try (Socket clientSocket = serverSocket.accept();
                     InputStream input = clientSocket.getInputStream();
                     OutputStream output = clientSocket.getOutputStream()) {
                    System.out.println("Khách đã kết nối!!! ");

                    new Thread(() -> {
                        Scanner scanner = new Scanner(input);
                        while (scanner.hasNextLine()) {
                            String clientMessage = scanner.nextLine();
                            System.out.println("Khách: " + clientMessage);
                        }
                    }).start();

                    Scanner consoleScanner = new Scanner(System.in);
                    while (check) {
                        String serverMessage = consoleScanner.nextLine();
                        if (serverMessage.equals("quit")) {
                            check = false;

                        }
                        output.write((serverMessage + "\n").getBytes());
                    }

                } catch (IOException e) {
                    System.out.println("Chưa có khách kết nối");
                }
            }
        } catch (IOException e) {
            System.out.println("Chưa có khách kết nối");
        }
    }


    public static void client() {
        String serverAddress = "localhost";
        int serverPort = 3333;

        try (Socket socket = new Socket(serverAddress, serverPort);
             InputStream input = socket.getInputStream();
             OutputStream output = socket.getOutputStream()) {
            new Thread(() -> {
                Scanner scanner = new Scanner(input);
                while (scanner.hasNextLine()) {
                    String serverMessage = scanner.nextLine();
                    if (!serverMessage.equals("quit")) {
                        System.out.println("Anh Long: " + serverMessage);
                    }
                }
            }).start();

            Scanner consoleScanner = new Scanner(System.in);
            System.out.println("Chat với Admin\n" +
                    "(Nhập 'bye' để thoát.)");
            while (true) {
                String clientMessage = consoleScanner.nextLine();
                if (clientMessage.equals("bye")) {
                    break;
                }
                output.write((clientMessage + "\n").getBytes());
            }

        } catch (IOException e) {
            System.out.println("Sever đang không có người trực. Mời ra quầy!!!");
        }
    }

}
