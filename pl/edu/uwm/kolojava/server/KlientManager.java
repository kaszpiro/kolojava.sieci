package pl.edu.uwm.kolojava.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * Created by kropiak on 2017-04-21.
 */
public class KlientManager implements Runnable{
    Socket socket;
    BufferedReader reader;
    PrintWriter writer;

    public KlientManager(Socket pSocket){
        this.socket = pSocket;
        try {
            InputStreamReader inReader = new InputStreamReader(socket.getInputStream());
            this.reader = new BufferedReader(inReader);
            this.writer = new PrintWriter(socket.getOutputStream());
            writer.println("Witaj kliencie " + socket.getLocalAddress() + " !");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    // metoda run implementowana z interfejsu Runnable, uruchamiana po wywołaniu funkcji start() dla nowego wątku
    // w nieskończonej pętli menadżer próbuje odczytać linię z bufora wejściowego i jeżeli coś się pojawi na wejściu
    // to odsyła komunikat echo z aktualną datą i treścią oryginalnej wiadomości
    public void run() {
        while(true){
            String message = null;
            try {
                message = this.reader.readLine();
                System.out.println("Odczytano: " + message);
                writer.println("ServerEcho " + new Date().toString() + ": " + message);
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
