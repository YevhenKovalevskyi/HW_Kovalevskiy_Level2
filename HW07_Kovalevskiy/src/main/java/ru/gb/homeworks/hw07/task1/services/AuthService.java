package ru.gb.homeworks.hw07.task1.services;

import lombok.Value;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AuthService {

    private static final List<Entry> entries;
    
    static {
        entries = Arrays.asList(
                new Entry("Nick", "nick", "password1"),
                new Entry("Alex", "alex", "password2"),
                new Entry("Mike", "mike", "password3")
        );
    }
    
    public Optional<Entry> findUserByCredentials(String login, String password) {
        return entries.stream()
                .filter(entry -> login.equals(entry.getLogin()) && password.equals(entry.getPassword()))
                .findFirst();
    }
    
    @Value
    static class Entry {
    
        String name;
        String login;
        String password;
    }
}
