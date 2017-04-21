package pl.edu.uwm.kolojava.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

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
                PrintWriter writer = new PrintWriter(socket.getOutputStream());
                writer.println("Witaj kliencie " + socket.getLocalAddress() + " !");
                writer.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
