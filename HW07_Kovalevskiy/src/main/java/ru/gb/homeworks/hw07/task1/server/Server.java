package ru.gb.homeworks.hw07.task1.server;

import lombok.extern.slf4j.Slf4j;
import ru.gb.homeworks.hw07.task1.helpers.PrintAndLogHelper;
import ru.gb.homeworks.hw07.task1.services.AuthService;
import ru.gb.homeworks.hw07.task1.services.ClientService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Server {
    
    private static final int PORT = 8888;
    private static final String PRIVATE = "/w";
    
    private ServerSocket server;
    private List<ClientService> loggedUsers;
    private AuthService authService;
    
    public Server() {
        try {
            this.authService = new AuthService();
            this.loggedUsers = new ArrayList<>();
            this.server = new ServerSocket(Server.PORT);
            
            while (true) {
                Socket client = server.accept();
                new ClientService(this, client);
            }
        } catch (IOException e) {
            PrintAndLogHelper.printAndLogError("Server crash on create!\n", "Server crash on create! " + e.getMessage());
        }
    }
    
    public AuthService getAuthService() {
        return authService;
    }
    
    public void subscribe(ClientService user) {
        loggedUsers.add(user);
    }
    
    public void unsubscribe(ClientService user) {
        loggedUsers.remove(user);
    }
    
    public boolean isNotUserOccupied(String name) {
        return !isUserOccupied(name);
    }
    
    public boolean isUserOccupied(String name) {
        return loggedUsers.stream()
                .anyMatch(user -> name.equals(user.getName()));
    }
    
    public void broadcastMessage(String outboundMessage) {
        if (outboundMessage.startsWith(Server.PRIVATE)) {
            privateMessage(outboundMessage);
        } else {
            publicMessage(outboundMessage);
        }
    }
    
    private void privateMessage(String outboundMessage) {
        if (loggedUsers.size() > 1) {
            String[] messageParts = outboundMessage.split("\\s", 3);
            
            if (messageParts.length == 3) {
                loggedUsers.stream()
                        .filter(clientService -> messageParts[1].equals(clientService.getName()))
                        .findFirst()
                        .ifPresent(clientService -> clientService.sendMessage(messageParts[2]));
            }
        }
    }
    
    private void publicMessage(String outboundMessage) {
        if (loggedUsers.size() > 1) {
            loggedUsers.forEach(clientService -> clientService.sendMessage(outboundMessage));
        }
    }
}
