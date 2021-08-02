package ru.gb.homeworks.hw06.task1.server;

import lombok.Cleanup;
import lombok.extern.slf4j.Slf4j;
import ru.gb.homeworks.hw06.task1.helpers.PrintAndLogHelper;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

@Slf4j
public class Server {
    
    private static final String SIGN = "[server]: ";
    private static final String EXIT = "exit";
    
    static final String serverHost = "127.0.0.1";
    static final int serverPort = 8888;
    
    private ServerSocket server;
    private Socket client;
    
    public Server() {
        start();
    }
    
    private void start() {
        try {
            server = new ServerSocket(Server.serverPort);
            
            PrintAndLogHelper.printAndLog("Socket created", "Socket created on " + Server.serverPort);
            PrintAndLogHelper.printAndLog("Waiting for connections...\n", "Waiting for connections...");
            
            client = server.accept();
            
            PrintAndLogHelper.printAndLog("Client connected: " + client, "Client connected: " + client);
            PrintAndLogHelper.printAndLog("Write a message\n", "Write a message");
    
            communicate();
            
        } catch (IOException e) {
            PrintAndLogHelper.printAndLogError("Server crash on start!\n", "Server crash on start! " + e.getMessage());
        }
    }
    
    private void communicate() {
        new Thread(this::readFromClient).start();
        new Thread(this::writeToClient).start();
    }
    
    private void readFromClient() {
        try {
            String clientMessage;
        
            @Cleanup DataInputStream in = new DataInputStream(client.getInputStream());
    
            while (true) {
                clientMessage = in.readUTF();

                if (clientMessage.equals(Server.EXIT)) {
                    clientMessage = "... client came out";
                    PrintAndLogHelper.printAndLog(clientMessage, clientMessage);
                    break;
                }
            
                PrintAndLogHelper.printAndLog(clientMessage, clientMessage);
                System.out.print("-> ");
            }
        
            server.close();
            
            PrintAndLogHelper.printAndLog("\nCommunication finished\n", "Communication finished");
        } catch (IOException e) {
            PrintAndLogHelper.printAndLogError(
                    String.format("[server] Communication [READ] failed! %s\n", e.getMessage()),
                    String.format("[server] Communication [READ] failed! %s", e.getMessage())
            );
        }
    }
    
    private void writeToClient() {
        try {
            String serverMessage;
        
            @Cleanup DataOutputStream out = new DataOutputStream(client.getOutputStream());
            @Cleanup Scanner console = new Scanner(System.in);
    
            while (true) {
                System.out.print("-> ");
                serverMessage = console.nextLine();
                
                if (serverMessage.equals(Server.EXIT)) {
                    out.writeUTF(serverMessage);
                    break;
                }
    
                serverMessage = String.format("%s %s", Server.SIGN, serverMessage);
                out.writeUTF(serverMessage);
            }
    
            server.close();
    
            PrintAndLogHelper.printAndLog("\nCommunication finished\n", "Communication finished");
        } catch (IOException e) {
            PrintAndLogHelper.printAndLogError(
                    String.format("[server] Communication [WRITE] failed! %s\n", e.getMessage()),
                    String.format("[server] Communication [WRITE] failed! %s", e.getMessage())
            );
        }
    }
}
