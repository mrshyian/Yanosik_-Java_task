package com.javatask;

import com.javatask.model.UserModel;
import com.javatask.model.VehicleModel;

import java.io.*;
import java.net.*;

import static com.javatask.utils.ConsoleColors.*;

public class Server {

    public static void main(String[] args) throws IOException {
        System.out.println(ANSI_PURPLE + "Waiting for client..." + ANSI_RESET);

        ServerSocket serverSocket = new ServerSocket(8080);
        Socket socket = serverSocket.accept();
        System.out.println(ANSI_GREEN + "Client connected!" + ANSI_RESET);

        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        OutputStream outputStream = socket.getOutputStream();
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        String messageFromClient = "";

        while(true){
            System.out.println("=======================================");
            System.out.println(ANSI_RED + "For exit enter " + ANSI_BLACK + ANSI_RED_BACKGROUND + "\"STOP\"" + ANSI_RESET);

            messageFromClient = dataInputStream.readUTF();
            if (messageFromClient.equals("STOP")){
                dataOutputStream.writeUTF("STOP");
                dataOutputStream.flush();
                break;
            }

            System.out.println("=======================================");
            System.out.println(ANSI_GREEN + "Client id: " + ANSI_YELLOW_BACKGROUND + ANSI_BLACK + messageFromClient + ANSI_RESET);
            System.out.println(ANSI_PURPLE + "Searching..." + ANSI_RESET);

            GetDataFromDB getDataFromDB = new GetDataFromDB();
            UserModel userModel = getDataFromDB.connectToDB(messageFromClient);

            StringBuilder messageToClient = new StringBuilder(ANSI_BLUE + "Nickname" + ANSI_RESET + "           ->      "+ ANSI_PURPLE + userModel.getNick() + ANSI_RESET);
            messageToClient.append("\n");
            messageToClient.append("---------------------------------------");
            for (VehicleModel car : userModel.getCars()){
                addInfoAboutCar(messageToClient, car);
            }

            System.out.println(ANSI_GREEN + "Found it! Sending." + ANSI_RESET);
            dataOutputStream.writeUTF(messageToClient.toString());
            dataOutputStream.flush();
        }
    }

    public static StringBuilder addInfoAboutCar(StringBuilder messageToClient, VehicleModel car){
        messageToClient.append("\n");
        messageToClient.append(ANSI_BLUE + "Car brand" + ANSI_RESET + "          ->      ").append(ANSI_PURPLE).append(car.getCarBrand()).append(ANSI_RESET);
        messageToClient.append("\n");
        messageToClient.append(ANSI_BLUE + "Car model" + ANSI_RESET + "          ->      ").append(ANSI_PURPLE).append(car.getCarModel()).append(ANSI_RESET);
        messageToClient.append("\n");
        messageToClient.append(ANSI_BLUE + "Car bought date" + ANSI_RESET + "    ->      ").append(ANSI_PURPLE).append(car.getCarBoughtDate(), 0, car.getCarBoughtDate().length()-7).append(ANSI_RESET);
        messageToClient.append("\n");
        messageToClient.append(ANSI_BLUE + "Insurer name" + ANSI_RESET + "       ->      ").append(ANSI_PURPLE).append(car.getInsurerName()).append(ANSI_RESET);
        messageToClient.append("\n");
        messageToClient.append(ANSI_BLUE + "Insurance price" + ANSI_RESET + "    ->      ").append(ANSI_PURPLE).append(car.getInsurancePrice()).append(ANSI_RESET);
        messageToClient.append("\n");
        messageToClient.append(ANSI_BLUE + "Insurance date" + ANSI_RESET + "     ->      ").append(ANSI_PURPLE).append(car.getInsuranceDate(), 0, car.getInsuranceDate().length()-7).append(ANSI_RESET);
        messageToClient.append("\n");
        messageToClient.append("---------------------------------------");

        return messageToClient;
    }
}
