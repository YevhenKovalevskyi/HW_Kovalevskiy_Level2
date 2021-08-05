package ru.gb.homeworks.hw07.task1.helpers;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PrintAndLogHelper {
    
    public static void printAndLog(String printMessage, String logMessage) {
        System.out.println(printMessage);
        log.info(logMessage);
    }
    
    public static void printAndLogError(String printMessage, String logMessage) {
        System.out.println(printMessage);
        log.error(logMessage);
    }
    
    public static void printOnly(String printMessage) {
        System.out.println(printMessage);
    }
    
    public static void logOnly(String logMessage) {
        log.info(logMessage);
    }
    
    public static void logErrorOnly(String logMessage) {
        log.error(logMessage);
    }
}
