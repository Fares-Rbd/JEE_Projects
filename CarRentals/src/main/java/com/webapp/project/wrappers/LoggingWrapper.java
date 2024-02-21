package com.webapp.project.wrappers;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggingWrapper {

    private static final String LOG_FILE = "log.txt"; //l'emplacement doit etre changé selon le cwd

    public static void logRequest(String requestMethod, String requestURI) {
        String logMessage = String.format("[%s] Request: %s %s\n", getCurrentTimestamp(), requestMethod, requestURI);
        writeLog(logMessage);
        System.out.println("REQUEST LOGGED");
    }

    public static void logResponse(int responseStatus) {
        String logMessage = String.format("[%s] Response: Status %d\n", getCurrentTimestamp(), responseStatus);
        writeLog(logMessage);
        System.out.println("REQUEST LOGGED");

    }

    private static void writeLog(String message) {
        try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
            writer.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getCurrentTimestamp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }
}

