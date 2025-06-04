public class TransactionController {
    private AuthenticationController authController;
    private AccessControlManager accessControlManager;

    public TransactionController(AuthenticationController authController) {
        this.authController = authController;
        this.accessControlManager = new AccessControlManager();
    }

    public void deposit(User user, double amount) {
        if (authController.verifyOTP()) {
            if (amount > 0) {
                user.setBalance(user.getBalance() + amount);
                Transaction transaction = new Transaction(
                    "DEPOSIT",
                    amount,
                    user.getUsername(),
                    user.getUsername(),
                    user.getBalance()
                );
                user.addTransaction(transaction);
                System.out.println("Deposit successful. New balance: $" + user.getBalance());
                accessControlManager.logAccess(user.getUsername(), "Deposit - Amount: $" + amount);
            } else {
                System.out.println("Deposit amount must be greater than zero.");
            }
        }
    }

    public void withdraw(User user, double amount) {
        if (authController.verifyOTP()) {
            if (amount > 0 && user.getBalance() >= amount) {
                user.setBalance(user.getBalance() - amount);
                Transaction transaction = new Transaction(
                    "WITHDRAW",
                    amount,
                    user.getUsername(),
                    user.getUsername(),
                    user.getBalance()
                );
                user.addTransaction(transaction);
                System.out.println("Withdrawal successful. New balance: $" + user.getBalance());
                accessControlManager.logAccess(user.getUsername(), "Withdrawal - Amount: $" + amount);
            } else {
                System.out.println("Insufficient balance or invalid amount.");
            }
        }
    }

    public void transfer(User sender, String recipientUsername, double amount, String transferType) {
        if (authController.verifyOTP()) {
            User recipient = authController.getUserDatabase().getUser(recipientUsername);
            if (recipient == null) {
                System.out.println("Recipient not found.");
                return;
            }

            double fee = getTransactionFee(transferType);
            double totalAmount = amount + fee;

            if (amount > 0 && sender.getBalance() >= totalAmount) {
                sender.setBalance(sender.getBalance() - totalAmount);
                recipient.setBalance(recipient.getBalance() + amount);

                Transaction senderTransaction = new Transaction(
                    "TRANSFER_SENT",
                    amount,
                    sender.getUsername(),
                    recipient.getUsername(),
                    sender.getBalance()
                );
                Transaction recipientTransaction = new Transaction(
                    "TRANSFER_RECEIVED",
                    amount,
                    sender.getUsername(),
                    recipient.getUsername(),
                    recipient.getBalance()
                );

                sender.addTransaction(senderTransaction);
                recipient.addTransaction(recipientTransaction);

                System.out.println("Transfer successful. Amount transferred: $" + amount);
                System.out.println("Transaction fee: $" + fee);
                System.out.println("New balance: $" + sender.getBalance());
                
                accessControlManager.logAccess(sender.getUsername(), 
                    "Transfer - Amount: $" + amount + " to " + recipientUsername + " Type: " + transferType);
            } else {
                System.out.println("Insufficient balance or invalid amount.");
            }
        }
    }

    public void payBill(User user, double amount, String billType) {
        if (authController.verifyOTP()) {
            if (amount > 0 && user.getBalance() >= amount) {
                user.setBalance(user.getBalance() - amount);
                Transaction transaction = new Transaction(
                    "BILL_PAYMENT",
                    amount,
                    user.getUsername(),
                    "BILL_" + billType.toUpperCase(),
                    user.getBalance()
                );
                user.addTransaction(transaction);
                System.out.println("Bill payment successful. Paid $" + amount + " for " + billType);
                System.out.println("New balance: $" + user.getBalance());
                accessControlManager.logAccess(user.getUsername(), 
                    "Bill Payment - Amount: $" + amount + " Type: " + billType);
            } else {
                System.out.println("Insufficient balance or invalid amount.");
            }
        }
    }

    
    private double getTransactionFee(String transferType) {
        switch (transferType.toLowerCase()) {
            case "local":
                return 1.0; // Low fee for local transfers
            case "national":
                return 5.0; // Higher fee for national transfers
            case "international":
                return 15.0; // High fee for international transfers
            default:
                return 0.0; // No fee for undefined transfer type
        }
    }
}
    
