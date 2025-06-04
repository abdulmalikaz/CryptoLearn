public class Transaction {
    private String transactionId;
    private String type;        // DEPOSIT, WITHDRAW, TRANSFER, BILL_PAYMENT
    private double amount;
    private String date;
    private String senderAccountId;
    private String receiverAccountId;
    private String status;
    private double balanceAfterTransaction;
    public Transaction(String type, double amount, String senderAccountId, 
                      String receiverAccountId, double balanceAfterTransaction) {
        this.transactionId = generateTransactionId();
        this.type = type;
        this.amount = amount;
        this.date = java.time.LocalDateTime.now().toString();
        this.senderAccountId = senderAccountId;
        this.receiverAccountId = receiverAccountId;
        this.status = "COMPLETED";
        this.balanceAfterTransaction = balanceAfterTransaction;
    }
    private String generateTransactionId() {
        return "TXN" + System.currentTimeMillis();
    }
    // Getters
    public String getTransactionId() { return transactionId; }
    public String getType() { return type; }
    public double getAmount() { return amount; }
    public String getDate() { return date; }
    public String getSenderAccountId() { return senderAccountId; }
    public String getReceiverAccountId() { return receiverAccountId; }
    public String getStatus() { return status; }
    public double getBalanceAfterTransaction() { return balanceAfterTransaction; }
}
