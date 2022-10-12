package com.javatask;

import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) throws IOException {
        System.out.println("Waiting for client...");
        ServerSocket serverSocket = new ServerSocket(8080);
        Socket socket = serverSocket.accept();
        System.out.println("Client connected!");
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        String messageFromClient = "";
        String messageToClient = "";
        while(!messageFromClient.equals("STOP")){
            System.out.println("For exit enter \"STOP\"");
            messageFromClient = dataInputStream.readUTF();
            System.out.println("---------------------------------------");
            System.out.println("Client id: " + messageFromClient);
            System.out.println("Searching...");

            //TODO MESSAGEFROMCLIENT = ID

            messageToClient = "First name: Sebastian, Last name: Ryndak";
            System.out.println("Found it! Sending.");
            dataOutputStream.writeUTF(messageToClient);
            dataOutputStream.flush();
        }
    }
}
