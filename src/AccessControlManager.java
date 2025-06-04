import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class AccessControlManager {

    private static final String LOG_FILE = "src/login_attempts.log"; // File to store login attempts

    public void logAccess(String username, String action) {
        try (FileWriter fileWriter = new FileWriter(LOG_FILE, true); // Open in append mode
             PrintWriter printWriter = new PrintWriter(fileWriter)) {

            // Get current timestamp
            LocalDateTime timestamp = LocalDateTime.now();

            // Write log entry to the file
            printWriter.printf("Timestamp: %s, User: %s, Action: %s%n", timestamp, username, action);

        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
}
