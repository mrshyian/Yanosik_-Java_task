package com.javatask;

import java.io.*;
import java.net.Socket;

import static com.javatask.utils.ConsoleColors.*;
import static com.javatask.utils.ConsoleColors.ANSI_RESET;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8080);
        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String messageFromServer = "";
        String messageToClient = "";
        while (true) {
            System.out.println(ANSI_RED + "For exit enter " + ANSI_BLACK + ANSI_RED_BACKGROUND + "\"STOP\"" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "Enter ID of client:" + ANSI_RESET);
            messageFromServer = bufferedReader.readLine();
            dataOutputStream.writeUTF(messageFromServer);
            dataOutputStream.flush();
            messageToClient = dataInputStream.readUTF();
            if (messageFromServer.equals("STOP")){
                break;
            }
            System.out.println(ANSI_GREEN + "Information about this client: " + ANSI_RESET);
            System.out.println(messageToClient);
        }
    }
}
