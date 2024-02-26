package com.webapp.project.wrappers;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoggingWrapper {

    private static final String LOG_FILE = "log.txt"; //l'emplacement doit etre changé selon le cwd

    public static void logRequest(String requestMethod, String requestURI) {
        String logMessage = String.format("[%s] Request: %s %s\n", getCurrentTimestamp(), requestMethod, requestURI);
        writeLog(logMessage);
        System.out.println("The request has been logged in the following log file :\n"+Paths.get("").toAbsolutePath().toString() + "\\" + LOG_FILE);
    }

    public static void logResponse(int responseStatus) {
        String logMessage = String.format("[%s] Response: Status %d\n", getCurrentTimestamp(), responseStatus);
        writeLog(logMessage);
        System.out.println("The response has been logged in the following log file :\n"+Paths.get("").toAbsolutePath().toString() + "\\" + LOG_FILE);

    }

    private static void writeLog(String message) {
        String logFilePath = Paths.get("").toAbsolutePath().toString() + "\\" + LOG_FILE;
        try (FileWriter writer = new FileWriter(logFilePath, true)) {
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
