package com.javatask;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8080);
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String messageFromServer = "";
        String messageToClient = "";
        while (!messageFromServer.equals("STOP")) {
            System.out.println("For exit enter \"STOP\"");
            System.out.println("Enter ID of client:");
            messageFromServer = bufferedReader.readLine();
            dataOutputStream.writeUTF(messageFromServer);
            dataOutputStream.flush();
            messageToClient = dataInputStream.readUTF();
            System.out.println("Information about this client: " + messageToClient);
        }
    }
}
