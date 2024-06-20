package com.inventory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.logging.LogFile;
import org.springframework.boot.logging.LoggingInitializationContext;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.logging.Logger;

@SpringBootApplication
public class InventoryMain {
    private static final Logger logger = Logger.getLogger(InventoryMain.class.getName());

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(InventoryMain.class);
        application.addListeners(new LoggingSystemInitializer());
        application.run(args);
    }

    private static class LoggingSystemInitializer implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
        @Override
        public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
            logger.info("Prepare the log file");
            ConfigurableEnvironment environment = event.getEnvironment();

            // Fetch log file configuration using LogFile utility
            LogFile logFile = LogFile.get(environment);

            if (logFile != null) {
                Path logFilePath = Paths.get(logFile.toString());
                try {
                    /* Open the file in write mode and truncate it */
                    FileChannel.open(logFilePath, StandardOpenOption.WRITE).truncate(0).close();
                    System.out.println("Cleared existing log file: " + logFilePath.toAbsolutePath());
                } catch (IOException e) {
                    System.err.println("Failed to clear existing log file: " + logFilePath.toAbsolutePath());
                }
            }

            // Initialize logging system with custom log file configuration
            LoggingSystem loggingSystem = LoggingSystem.get(getClass().getClassLoader());
            loggingSystem.initialize(new LoggingInitializationContext(environment),
                    "classpath:logging.properties",
                    logFile);
        }
    }
}