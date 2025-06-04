    import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class AccountViewController {
    private User user;
    private static final int TRANSACTIONS_PER_PAGE = 10;

    public AccountViewController(User user) {
        this.user = user;
    }

    public void displayAccountSummary() {
        System.out.println("\n=== Account Summary ===");
        System.out.println("Username: " + user.getUsername());
        System.out.println("Current Balance: $" + String.format("%.2f", user.getBalance()));
        System.out.println("\n=== Profile Information ===");
        displayProfileInformation();
        System.out.println("\n=== Recent Transactions ===");
        displayRecentTransactions(1); // Show first page of transactions
    }

    public void displayProfileInformation() {
        Profile profile = user.getProfile();
        if (profile != null) {
            System.out.println("Address: " + profile.getAddress());
            System.out.println("Phone: " + profile.getPhoneNumber());
            System.out.println("Email: " + profile.getEmail());
            System.out.println("Occupation: " + profile.getOccupation());
        } else {
            System.out.println("Profile information not available.");
        }
    }

    public void displayRecentTransactions(int page) {
        List<Transaction> transactions = user.getTransactionHistory();
        int totalPages = (int) Math.ceil(transactions.size() / (double) TRANSACTIONS_PER_PAGE);
        
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        int startIdx = (page - 1) * TRANSACTIONS_PER_PAGE;
        int endIdx = Math.min(startIdx + TRANSACTIONS_PER_PAGE, transactions.size());

        System.out.println("\nDate\t\t\tType\t\tAmount\t\tBalance\t\tStatus");
        System.out.println("----------------------------------------------------------------");

        for (int i = startIdx; i < endIdx; i++) {
            Transaction txn = transactions.get(i);
            System.out.printf("%s\t%s\t$%.2f\t\t$%.2f\t\t%s\n",
                txn.getDate().substring(0, 10),
                txn.getType(),
                txn.getAmount(),
                txn.getBalanceAfterTransaction(),
                txn.getStatus()
            );
        }

        System.out.println("\nPage " + page + " of " + totalPages);
    }

    public void searchTransactions(String startDate, String endDate) {
        System.out.println("\n=== Transaction Search Results ===");
        System.out.println("Transactions between " + startDate + " and " + endDate);
        
        List<Transaction> filteredTransactions = user.getTransactionHistory().stream()
            .filter(txn -> {
                String txnDate = txn.getDate().substring(0, 10);
                return txnDate.compareTo(startDate) >= 0 && txnDate.compareTo(endDate) <= 0;
            })
            .collect(Collectors.toList());

        if (filteredTransactions.isEmpty()) {
            System.out.println("No transactions found in the specified date range.");
            return;
        }

        System.out.println("\nDate\t\t\tType\t\tAmount\t\tBalance\t\tStatus");
        System.out.println("----------------------------------------------------------------");
        
        for (Transaction txn : filteredTransactions) {
            System.out.printf("%s\t%s\t$%.2f\t\t$%.2f\t\t%s\n",
            		txn.getDate().substring(0, 10),
                    txn.getType(),
                    txn.getAmount(),
                    txn.getBalanceAfterTransaction(),
                    txn.getStatus()
                );
            }
        }
    }


            		