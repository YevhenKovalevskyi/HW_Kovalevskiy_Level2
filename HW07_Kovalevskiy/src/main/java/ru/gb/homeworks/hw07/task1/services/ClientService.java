package ru.gb.homeworks.hw07.task1.services;

import lombok.extern.slf4j.Slf4j;
import ru.gb.homeworks.hw07.task1.helpers.PrintAndLogHelper;
import ru.gb.homeworks.hw07.task1.server.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Optional;

@Slf4j
public class ClientService {
    
    private static final String AUTH = "-auth";
    private static final String EXIT = "-exit";
    
    Server server;
    Socket client;
    DataInputStream in;
    DataOutputStream out;
    String name;
    
    public ClientService(Server server, Socket client) {
        try {
            this.server = server;
            this.client = client;
            this.in = new DataInputStream(client.getInputStream());
            this.out = new DataOutputStream(client.getOutputStream());
    
            start();
        } catch (IOException e) {
            throw new RuntimeException("Something went wrong during client establishing...", e);
        }
    }
    
    public String getName() {
        return name;
    }
    
    private void start() {
        new Thread(() -> {
            doAuthentication();
            listenMessages();
            closeConnection();
        }).start();
    }
    
    private void doAuthentication() {
        try {
            while (true) {
                String streamData = in.readUTF();
                
                processAuthData(streamData);
            }
        } catch (IOException e) {
            PrintAndLogHelper.printAndLogError(
                    "ClientService crash on authentication!\n",
                    "ClientService crash on authentication! " + e.getMessage());
        }
    }
    
    private void processAuthData(String streamData) {
        if (streamData.startsWith(ClientService.AUTH)) {
            String[] credentials = streamData.split("\\s");
            
            if (credentials.length == 3) {
                Optional<AuthService.Entry> userData = server.getAuthService()
                        .findUserByCredentials(credentials[1], credentials[2]);
                
                if (userData.isPresent()) {
                    AuthService.Entry user = userData.get();
                    
                    if (server.isNotUserOccupied(user.getName())) {
                        name = user.getName();
                        
                        sendMessage("AUTH OK.");
                        sendMessage("Welcome.");
                        
                        server.broadcastMessage(String.format("User[%s] entered chat.", name));
                        server.subscribe(this);
                        return;
                    } else {
                        sendMessage("Current user is already logged in.");
                    }
                } else {
                    sendMessage("Invalid credentials!");
                }
            } else {
                sendMessage("Invalid auth params.");
            }
        } else {
            sendMessage("Invalid auth operation.");
        }
    }
    
    public void sendMessage(String outboundMessage) {
        try {
            out.writeUTF(outboundMessage);
        } catch (IOException e) {
            PrintAndLogHelper.printAndLogError(
                    "ClientService crash on send message!\n", "ClientService crash on send message! " + e.getMessage()
            );
        }
    }
    
    public void listenMessages() {
        try {
            while (true) {
                String inboundMessage = in.readUTF();
                
                if (inboundMessage.equals(ClientService.EXIT)) {
                    break;
                }
                
                server.broadcastMessage(inboundMessage);
            }
        } catch (IOException e) {
            PrintAndLogHelper.printAndLogError(
                    "ClientService crash on listen messages!\n",
                    "ClientService crash on listen messages! " + e.getMessage()
            );
        }
    }
    
    private void closeConnection() {
        server.unsubscribe(this);
        server.broadcastMessage(String.format("User[%s] is out.", name));
        
        try {
            in.close();
        } catch (IOException e) {
            PrintAndLogHelper.printAndLogError(
                    "ClientService crash on input stream!\n", "ClientService crash on input stream! " + e.getMessage()
            );
        }
    
        try {
            out.close();
        } catch (IOException e) {
            PrintAndLogHelper.printAndLogError(
                    "ClientService crash on output stream!\n", "ClientService crash on output stream! " + e.getMessage()
            );
        }
    
        try {
            client.close();
        } catch (IOException e) {
            PrintAndLogHelper.printAndLogError(
                    "ClientService crash on close client!\n", "ClientService crash on close client! " + e.getMessage()
            );
        }
    }
}
