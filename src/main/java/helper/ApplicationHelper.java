package helper;

import config.AmadeusToken;
import helper.data_generators.TokenPreparation;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;

public class ApplicationHelper {
    public void initToken() {
        writeTokenToFile(new TokenPreparation().signIn());
    }

    public static String getCurrentToken() {
        AmadeusToken aToken = readTokenFromFile();
        synchronized (ApplicationHelper.class) {
            if (aToken == null || aToken.getExpiresIn() + aToken.getCreatedAt() - 10 <= Instant.now().getEpochSecond()) {
                AmadeusToken newToken = new TokenPreparation().signIn();
                return "Bearer " + newToken.getToken();
            }
        }
        return "Bearer " + aToken.getToken();
    }

    public static void writeTokenToFile(AmadeusToken token) {
        String fileName = "amadeusToken.txt";
        File csvOutputFile = new File(fileName);
        try {
            if (csvOutputFile.exists()) {
                csvOutputFile.delete();
            }
            csvOutputFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Error creating token file:" + e.getMessage());
        }
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            pw.println(token.getToken());
            pw.println(token.getExpiresIn());
            pw.println(token.getCreatedAt());
            pw.println(token.getRaw());
        } catch (IOException e) {
            throw new RuntimeException("Error writing to token file:" + e.getMessage());
        }
    }

    public static AmadeusToken readTokenFromFile() {
        String fileName = "amadeusToken.txt.txt";
        Path filePath = Path.of(fileName);
        try {
            if (Files.exists(filePath)) {
                String content = Files.readString(filePath);
                String separator = content.contains("\r\n") ? "\r\n" : "\n";
                String[] lines = Files.readString(filePath).split(separator);
                return new AmadeusToken(lines[0], Long.parseLong(lines[1]), Long.parseLong(lines[2]), lines[3]);
            }
        } catch (IOException e) {
            System.out.println("Error reading token file:" + e.getMessage());
        }
        return null;
    }
}
