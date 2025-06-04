import java.util.List;
import java.util.ArrayList;

public class User {
    private String username;
    private String passwordHash;
    private double balance;
    private Profile profile;
    private List<Transaction> transactionHistory;

    public User(String username, String passwordHash) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.balance = 1000.0;
        this.transactionHistory = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        this.transactionHistory.add(transaction);
    }

    // Getters and setters
    public String getUsername() { return username; }
    public String getPasswordHash() { return passwordHash; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }
    public Profile getProfile() { return profile; }
    public void setProfile(Profile profile) { this.profile = profile; }
    public List<Transaction> getTransactionHistory() { return transactionHistory; }
}