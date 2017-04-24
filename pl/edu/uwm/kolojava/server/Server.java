package pl.edu.uwm.kolojava.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by kropiak on 2017-04-21.
 */
public class Server{

    ServerSocket servSocket;
    ArrayList<KlientManager> clientList;

    public static void main(String[] args){
        Server server = new Server();
        server.initializeServer();
        server.doService();
    }

    private void initializeServer(){
        //próba utworzenia nowego gniazda serwera nasłuchującego na porcie 7777
        try {
            this.servSocket = new ServerSocket(7777);
            System.out.println("Serwer nasłuchuje pod adresem: " + this.servSocket.getInetAddress()
            + ":" + this.servSocket.getLocalPort());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //inicjalizacja pustej listy menedżerów klientów
        this.clientList = new ArrayList<KlientManager>();
    }

    // "usługa" działająca w nieskończonej pętli nasłuchująca przychodzących połączeń od klientów
    private void doService(){
        while(true) {
            Socket socket = null;
            try {
                socket = this.servSocket.accept();
                // tworzymy nowego menedżera klienta z referencją do nowego gniazda
                KlientManager newClient = new KlientManager(socket);
                this.clientList.add(newClient);
                // Tworzymy nowy wątek dla menedżera klienta
                Thread t = new Thread(newClient);
                // uruchamiamy wątek
                t.start();
                System.out.println("Nowy klient połączył się z serwerem");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
