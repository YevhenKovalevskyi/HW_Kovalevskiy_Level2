package ru.gb.homeworks.hw03.task2;

import lombok.extern.slf4j.Slf4j;

import java.util.stream.IntStream;

/**
 * Main Class represents homework #3 #task2
 *
 * @author e.kovalevskiy
 * @version 1.0
 */
@Slf4j
public class Main {
    
    public static void main(String[] args) {
        log.info("Start program...");

        String[] names = {"Hilliard", "Meghan", "Hilliard", "Brander", "Francisco", "Renhold", "Brander"};
        String[] phones = {"4319519457", "3691277622", "3213550815", "5096734348", "2725112048", "3272337392", "4465876581"};
        
        Phonebook phonebook = new Phonebook();
    
        IntStream.range(0, names.length).forEach(i -> {
            phonebook.add(names[i], phones[i]);
        });
    
        System.out.println(phonebook.get("Meghan"));
        System.out.println(phonebook.get("Hilliard"));
        System.out.println(phonebook.get("Test"));
        
        log.info("End program.\n");
    }
}
