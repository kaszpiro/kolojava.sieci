package pl.edu.uwm.kolojava.klient;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by kropiak on 2017-04-21.
 */
public class Klient {

    private static PrintWriter writer;
    private static BufferedReader reader;
    private static String server;
    private static Socket socket;
    private static int port = 7777;

    public static void main(String[] args){
        Klient klient = new Klient();
        server = args[0];
        //inicjalizujemy połaczenie poprzez gniazdo
        connect();

        while(true){
            System.out.println("Wpisz treść wiadomości");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String message;
            try {
                while ((message = br.readLine()) != null) {
                    sendMessage(message);
                    System.out.println(reader.readLine());
                    if(message.equals("quit")){
                        socket.close();
                        System.exit(0);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void connect(){
        try {
            socket = new Socket(server, port);
            InputStreamReader inStream = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(inStream);
            System.out.println(reader.readLine());
            writer = new PrintWriter(socket.getOutputStream());
            //buffReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendMessage(String message){
        writer.println(message);
        writer.flush();
        //writer.close();
    }
}