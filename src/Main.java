import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        LoginPage loginPage = new LoginPage();
        InputValidator validator = new InputValidator();
        Scanner scanner = new Scanner(System.in);
        TransactionController transController = null;
        AccountViewController accountViewController = null;
        User currentUser = null;

        final int MAX_TRIES = 3;
        HashMap<String, Integer> loginAttempts = new HashMap<>();

        while (true) {
            try {
                if (currentUser == null) {
                    System.out.println("\nChoose an option: 1) Sign Up  2) Login  3) Exit");
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (choice) {
                        case 1:
                            // Sign Up
                            System.out.print("Enter username (alphanumeric and 5-15 characters): ");
                            String signUpUsername = scanner.nextLine();
                            if (validator.hasSqlInjectionRisk(signUpUsername)) {
                                System.out.println("Invalid username: Contains SQL injection risk. Please try again.");
                                break;
                            }
                            if (!validator.isValidUsername(signUpUsername)) {
                                System.out.println("Invalid username. Must be alphanumeric and 5-15 characters.");
                                break;
                            }

                            System.out.print("Enter password at least 8 characters (Must include uppercase, number, and special character): ");
                            String signUpPassword = scanner.nextLine();
                            if (validator.hasSqlInjectionRisk(signUpPassword)) {
                                System.out.println("Invalid password: Contains SQL injection risk. Please try again.");
                                break;
                            }
                            if (!validator.isValidPassword(signUpPassword)) {
                                System.out.println("Invalid password. Must include uppercase, number, and special character.");
                                break;
                            }

                            // Collect profile information
                            System.out.print("Enter address: ");
                            String address = scanner.nextLine();
                            if (validator.hasSqlInjectionRisk(address)) {
                                System.out.println("Invalid: Contains SQL injection risk. Please try again.");
                                break;
                            }
                            System.out.print("Enter phone number: ");
                            String phone = scanner.nextLine();
                            if (validator.hasSqlInjectionRisk(phone)) {
                                System.out.println("Invalid: Contains SQL injection risk. Please try again.");
                                break;
                            }
                            System.out.print("Enter email: ");
                            String email = scanner.nextLine();
                            if (validator.hasSqlInjectionRisk(email)) {
                                System.out.println("Invalid: Contains SQL injection risk. Please try again.");
                                break;
                            }
                            System.out.print("Enter job: ");
                            String occupation = scanner.nextLine();
                            if (validator.hasSqlInjectionRisk(occupation)) {
                                System.out.println("Invalid: Contains SQL injection risk. Please try again.");
                                break;
                            }


                            if (loginPage.displaySignUp(signUpUsername, signUpPassword)) {
                                User newUser = loginPage.getAuthController().getUserDatabase().getUser(signUpUsername);
                                Profile profile = new Profile(signUpUsername, address, phone, email, occupation);
                                newUser.setProfile(profile);
                                System.out.println("Registration successful! Please login.");
                            }
                            break;

                        case 2:
                            // Login
                            System.out.print("Enter username: ");
                            String loginUsername = scanner.nextLine();
                            if (validator.hasSqlInjectionRisk(loginUsername)) {
                                System.out.println("Invalid username: Contains SQL injection risk. Please try again.");
                                break;
                            }

                            loginAttempts.putIfAbsent(loginUsername, 0);

                            if (loginAttempts.get(loginUsername) >= MAX_TRIES) {
                                System.out.println("Your account is locked due to too many failed login attempts. Please contact customer service.");
                                break;
                            }

                            System.out.print("Enter password: ");
                            String loginPassword = scanner.nextLine();
                            if (validator.hasSqlInjectionRisk(loginPassword)) {
                                System.out.println("Invalid password: Contains SQL injection risk. Please try again.");
                                break;
                            }

                            if (loginPage.displayLogin(loginUsername, loginPassword)) {
                                loginAttempts.put(loginUsername, 0);
                                currentUser = loginPage.getAuthController().getUserDatabase().getUser(loginUsername);
                                transController = new TransactionController(loginPage.getAuthController());
                                accountViewController = new AccountViewController(currentUser);
                                System.out.println("Welcome, " + loginUsername + "! Your balance is: $" + currentUser.getBalance());
                            } else {
                                loginAttempts.put(loginUsername, loginAttempts.get(loginUsername) + 1);
                                if (loginAttempts.get(loginUsername) >= MAX_TRIES) {
                                    System.out.println("You exceeded the maximum number of attempts. Your account is locked. Please contact customer service.");
                                }
                            }
                            break;

                        case 3:
                            System.out.println("Exiting...");
                            scanner.close();
                            return;

                        default:
                            System.out.println("Invalid choice. Please select 1, 2, or 3.");
                    }
                } else {
                    // Transaction menu for logged-in users
                    System.out.println("\n=== Menu ===");
                    System.out.println("1. Check Balance");
                    System.out.println("2. Deposit");
                    System.out.println("3. Withdraw");
                    System.out.println("4. Transfer");
                    System.out.println("5. Pay Bill");
                    System.out.println("6. View Account Information");
                    System.out.println("7. Logout");
                    System.out.print("Choose an option: ");

                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    switch (choice) {
                        case 1:
                            System.out.println("Current balance: $" + currentUser.getBalance());
                            break;

                        case 2:
                            System.out.print("Enter amount to deposit: $");
                            double depositAmount = scanner.nextDouble();
                            transController.deposit(currentUser, depositAmount);
                            break;

                        case 3:
                            System.out.print("Enter amount to withdraw: $");
                            double withdrawAmount = scanner.nextDouble();
                            transController.withdraw(currentUser, withdrawAmount);
                            break;

                        case 4:
                            System.out.print("Enter recipient username: ");
                            String recipientUsername = scanner.nextLine();
                            System.out.print("Enter amount to transfer: $");
                            double transferAmount = scanner.nextDouble();
                            scanner.nextLine();
                            System.out.print("Enter transfer type (local/national/international): ");
                            String transferType = scanner.nextLine();
                            transController.transfer(currentUser, recipientUsername, transferAmount, transferType);
                            break;

                        case 5:
                            System.out.print("Enter bill type (electricity/water/gas/internet): ");
                            String billType = scanner.nextLine();
                            System.out.print("Enter amount to pay: $");
                            double billAmount = scanner.nextDouble();
                            transController.payBill(currentUser, billAmount, billType);
                            break;

                        case 6:
                            System.out.println("\n=== Account View Options ===");
                            System.out.println("1. View Account Summary");
                            System.out.println("2. View Transaction History");
                            System.out.println("3. Search Transactions by Date");
                            System.out.println("4. Back to Main Menu");
                            System.out.print("Choose an option: ");

                            int viewChoice = scanner.nextInt();
                            scanner.nextLine(); // Consume newline

                            switch (viewChoice) {
                                case 1:
                                    accountViewController.displayAccountSummary();
                                    break;
                                case 2:
                                    System.out.print("Enter page number: ");
                                    int page = scanner.nextInt();
                                    accountViewController.displayRecentTransactions(page);
                                    break;
                                case 3:
                                    System.out.print("Enter start date (YYYY-MM-DD): ");
                                    String startDate = scanner.nextLine();
                                    System.out.print("Enter end date (YYYY-MM-DD): ");
                                    String endDate = scanner.nextLine();
                                    accountViewController.searchTransactions(startDate, endDate);
                                    break;
                                case 4:
                                    break;
                                default:
                                    System.out.println("Invalid option.");
                            }
                            break;

                        case 7:
                            System.out.println("Logging out. Goodbye, " + currentUser.getUsername() + "!");
                            currentUser = null;
                            transController = null;
                            accountViewController = null;
                            break;

                        default:
                            System.out.println("Invalid option. Please try again.");
                    }
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine(); // Clear invalid input
            }
        }
    }
}
