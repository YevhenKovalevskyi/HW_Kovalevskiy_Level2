package ru.gb.homeworks.hw06.task1.client;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import ru.gb.homeworks.hw06.task1.helpers.PrintAndLogHelper;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class Client {
    
    private static final String SIGN = "[client]: ";
    private static final String EXIT = "exit";
    
    static final String serverHost = "127.0.0.1";
    static final int serverPort = 8888;
    
    private static AtomicBoolean isAlive = new AtomicBoolean(true);
    private Socket client;
    
    public Client() {
        start();
    }
    
    private void start() {
        try {
            Thread.sleep(3000);
            client = new Socket(Client.serverHost, Client.serverPort);
    
            PrintAndLogHelper.printAndLog("Write a message\n", "Write a message");
            
            communicate();

        } catch (IOException | InterruptedException e) {
            PrintAndLogHelper.printAndLogError("Client crash on start!\n", "Client crash on start! " + e.getMessage());
        }
    }
    
    private void communicate() {
        new Thread(this::readFromServer).start();
        new Thread(this::writeToServer).start();
    }
    
    private void readFromServer() {
        try {
            String serverMessage;
        
            @Cleanup DataInputStream in = new DataInputStream(client.getInputStream());
    
            while (true) {
                serverMessage = in.readUTF();
    
                if (serverMessage.equals(Client.EXIT)) {
                    serverMessage = "... server came out";
                    PrintAndLogHelper.printAndLog(serverMessage, serverMessage);
                    break;
                }
    
                PrintAndLogHelper.printAndLog(serverMessage, serverMessage);
                System.out.print("-> ");
            }
    
            client.close();
            
            PrintAndLogHelper.printAndLog("\nCommunication finished\n", "Communication finished");
        } catch (IOException e) {
            PrintAndLogHelper.printAndLogError(
                    String.format("[client] Communication [READ] failed! %s\n", e.getMessage()),
                    String.format("[client] Communication [READ] failed! %s", e.getMessage())
            );
        }
    }
    
    private void writeToServer() {
        try {
            String clientMessage;
        
            @Cleanup DataOutputStream out = new DataOutputStream(client.getOutputStream());
            @Cleanup Scanner console = new Scanner(System.in);
    
            while (true) {
                System.out.print("-> ");
                clientMessage = console.nextLine();
    
                if (clientMessage.equals(Client.EXIT)) {
                    out.writeUTF(clientMessage);
                    break;
                }
                
                clientMessage = String.format("%s %s", Client.SIGN, clientMessage);
                out.writeUTF(clientMessage);
            }
        
            client.close();
            
            PrintAndLogHelper.printAndLog("\nCommunication finished\n", "Communication finished");
        } catch (IOException e) {
            PrintAndLogHelper.printAndLogError(
                    String.format("[client] Communication [WRITE] failed! %s\n", e.getMessage()),
                    String.format("[client] Communication [WRITE] failed! %s", e.getMessage())
            );
        }
    }
}