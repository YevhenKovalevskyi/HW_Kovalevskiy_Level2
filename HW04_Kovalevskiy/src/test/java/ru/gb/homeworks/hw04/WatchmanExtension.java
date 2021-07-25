package ru.gb.homeworks.hw04;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.*;

import java.util.Optional;

@Slf4j
public class WatchmanExtension implements TestWatcher, AfterAllCallback, BeforeAllCallback, BeforeEachCallback {
    
    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        log.info("[ NOK ] Test Aborted!");
    
        if (cause != null) {
            log.info("Cause: {}", cause.getMessage());
        }
        
        log.info("");
    }
    
    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        log.info("[ NOK ] Test Disabled with reason: - {}", reason.orElse("No reason"));
        log.info("");
    }
    
    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        log.info("[ NOK ] Test Failed!");
    
        if (cause != null) {
            log.info("Cause: {}", cause.getMessage());
        }
        
        log.info("");
    }
    
    @Override
    public void testSuccessful(ExtensionContext context) {
        log.info("[ OK ] Test Succeeded!");
        log.info("");
    }
    
    @Override
    public void afterAll(ExtensionContext context) {
        log.info("All unit tests were finished!\n");
    }
    
    @Override
    public void beforeAll(ExtensionContext context) {
        log.info("All unit tests were started...");
        log.info("");
    }
    
    @Override
    public void beforeEach(ExtensionContext context) {
        log.info("[ {} ]", context.getRequiredTestMethod().getName());
    }
}
