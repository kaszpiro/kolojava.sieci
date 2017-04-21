package pl.edu.uwm.kolojava.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * Created by kropiak on 2017-04-21.
 */
public class Server {

    public static void main(String[] args){
        Server server = new Server();
        server.initializeServer();
    }

    private void initializeServer(){
        try {
            ServerSocket servSocket = new ServerSocket(7777);
            System.out.println("Serwer nasłuchuje pod adresem: " + servSocket.getInetAddress()
            + ":" + servSocket.getLocalPort());

            while(true) {
                Socket socket = servSocket.accept();
                InputStreamReader in = new InputStreamReader(socket.getInputStream());
                BufferedReader reader = new BufferedReader(in);
                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                writer.println("Witaj kliencie " + socket.getLocalAddress() + " !");
                writer.flush();
                String message;
                while((message = reader.readLine()) != null){
                    System.out.println("Odczytano: " + message);
                    writer.println("Echo " + new Date().toString() + ": " + message);
                    writer.flush();
                }
                writer.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
