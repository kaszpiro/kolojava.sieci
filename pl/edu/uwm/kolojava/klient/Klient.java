package pl.edu.uwm.kolojava.klient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by kropiak on 2017-04-21.
 */
public class Klient {

    public static void main(String[] args){
        Klient klient = new Klient();
        klient.connectToServer(args[0]);
    }

    private void connectToServer(String host){
        int port = 7777;

        try{
            Socket socket = new Socket(host, port);
            InputStreamReader inStream = new InputStreamReader(socket.getInputStream());
            BufferedReader buffReader = new BufferedReader(inStream);
            System.out.println(buffReader.readLine());
            buffReader.close();

        } catch (UnknownHostException e) {
            System.out.println("Nie udało się odnaleźć hosta " + host);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage(String recipient){

    }

    private void sendEcho(String message){

    }
}