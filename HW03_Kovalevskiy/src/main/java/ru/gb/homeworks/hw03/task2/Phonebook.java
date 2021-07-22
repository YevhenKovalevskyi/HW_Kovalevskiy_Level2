package ru.gb.homeworks.hw03.task2;

import lombok.Data;
import ru.gb.homeworks.hw03.task2.objects.Contact;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Phonebook {
    
    private List<Contact> contacts = new ArrayList<>();
    
    public void add(String name, String phone) {
        contacts.add(new Contact(name, phone));
    }
    
    public String get(String name) {
        List<Contact> contactsByName = contacts.stream()
                .filter(item -> name.equals(item.getName())).collect(Collectors.toList());
        
        return (contactsByName.size() > 0)
                ? Arrays.toString(contactsByName.stream().map(Contact::getPhone).toArray())
                : "No data";
    }
}
