import java.util.Scanner;

public class AuthenticationController {
    private UserDatabase userDatabase;
    private InputValidator validator;
    private EncryptionManager encryptionManager;
    private AccessControlManager accessControlManager;
    private final int OTP = 444100; // Static OTP for demonstration

    public AuthenticationController() {
        this.userDatabase = new UserDatabase();
        this.validator = new InputValidator();
        this.encryptionManager = new EncryptionManager();
        this.accessControlManager = new AccessControlManager();
    }

    // Handle user registration
    public boolean handleRegistration(String username, String password) {
        if (!validator.isValidUsername(username)) {
            System.out.println("Invalid username. Must be alphanumeric and 5-15 characters.");
            return false;
        }
        if (!validator.isValidPassword(password)) {
            System.out.println("Invalid password. Must include uppercase, number, and special character.");
            return false;
        }

        String passwordHash = encryptionManager.encrypt(password);
        return userDatabase.addUser(username, passwordHash);
    }

    // Handle user login
    public boolean handleLogin(String username, String password) {
        Scanner read = new Scanner(System.in);
        User user = userDatabase.getUser(username);
        
        if (user == null) {
            System.out.println("User not found.");
            accessControlManager.logAccess(username, "Login Failed - User Not Found");
            return false;
        }

        String encryptedInputPassword = encryptionManager.encrypt(password);

        if (encryptedInputPassword.equals(user.getPasswordHash())) {
            accessControlManager.logAccess(username, "Login Successful - Awaiting OTP Verification");
            if (verifyOTP()) {
                accessControlManager.logAccess(username, "Login Successful - OTP Verified");
                return true;
            } else {
                accessControlManager.logAccess(username, "Login Failed - Incorrect OTP");
                return false;
            }
        } else {
            System.out.println("Incorrect password.");
            accessControlManager.logAccess(username, "Login Failed - Incorrect Password");
            return false;
        }
    }

    // Changed from void to boolean to fix the type mismatch
    public boolean verifyOTP() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the OTP sent to your mobile: ");
        try {
            int enteredOTP = scanner.nextInt();
            if (enteredOTP == OTP) {
                System.out.println("OTP verified successfully!");
                return true;
            } else {
                System.out.println("Incorrect OTP. Transaction failed.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Invalid OTP format. Transaction failed.");
            return false;
        }
    }

    // Getter for userDatabase
    public UserDatabase getUserDatabase() {
        return userDatabase;
    }
}