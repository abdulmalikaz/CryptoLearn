This project is part of the Software Security Engineering course,
designed to implement a secure banking system with core security features.
The system demonstrates various security principles, including:

Authentication:
Users are authenticated through username-password login and an OTP (One-Time Password) mechanism. The default OTP used in this example is 444100.

Input Validation:
Ensures all user inputs (e.g., username, password, email) are sanitized to prevent SQL injection risks or other malicious inputs.

Data Encryption:
User passwords are securely encrypted using the DES algorithm to enhance data protection.
During the sign-up process:
The password provided by the user is encrypted using DES before it is saved in the database.

During login:
The password entered by the user is encrypted using DES and then compared to the encrypted password stored in the database.
This ensures that passwords are never stored or transmitted in plain text, providing an additional layer of security against potential breaches.

Access Control:
Implements account lockout mechanisms after three failed login attempts to prevent brute force attacks.
Language Used: Java

Installation Instructions
	1- Download the zip file and extract it.
	2- Create a new Java project and put the files in your preferred IDE (e.g., IntelliJ IDEA, Eclipse).
	3- run the main class.


Usage:

After running the main class, choose from the menu:

***********************************************
Menu options:                                 ||
1 - Sign Up                                   ||
2 - Login                                     ||
3 - Exit                                      ||
***********************************************

Sign Up: To create a new account, choose option 1.
The system will prompt you to enter the following details:

Username: Must be alphanumeric and between 5-15 characters.
Password: Must include at least 8 characters, one uppercase letter, one number, and one special character.
Address: User's city or residence.
Phone Number: Must follow the valid phone number format.
Email: Should be a valid email address without any SQL injection risks.
Job Title: Enter your occupation.

Example Workflow:

Enter username: mohammed
Enter password: Mohammed@101
Enter address: Riyadh
Enter phone number: 0000000000
Enter email: mohammed@gmail.com
Enter job: Software Engineer


Sign-up successful!


Login: To log in, choose option 2. The system will prompt you to enter the following:

Username: Enter your registered username.
Password: Enter your registered password.
The system validates your credentials. If valid, an OTP will be required. Enter the OTP to access your account.

Example Workflow:
Enter username: mohammed
Enter password: Mohammed@101
Enter the OTP sent to your mobile: 444100
Welcome, abdul1! Your balance is: $1000.0


Account Lockout:
If incorrect credentials are entered three times, the account will be locked:
You exceeded the maximum number of attempts. Your account is locked. Please contact customer service.


Account Menu: After successful login, the following menu will appear:

=== Menu ===
1. Check Balance
2. Deposit
3. Withdraw
4. Transfer
5. Pay Bill
6. View Account Information
7. Logout
Choose an option:


1-Check Balance:
Displays your current account balance.

2-Deposit:
Allows you to deposit money into your account.

3-Withdraw:
Enables you to withdraw funds from your account, subject to available balance.

4-Transfer:
Transfer money to another account. The system asks for:

Recipient Username
Amount to Transfer
Transfer Type (local/national/international)

Example Workflow:
Enter recipient username: abdul
Enter amount to transfer: $200
Enter transfer type (local/national/international): local
Enter the OTP sent to your mobile: 444100
Transfer successful. Amount transferred: $200.0
Transaction fee: $1.0
New balance: $799.0

5-Pay Bill:
Pay bills directly from your account.

6-View Account Information:
Displays a detailed account summary, including:

Personal Information: Address, phone number, email, and job title.
Recent Transactions: A list of completed transactions with type, amount, balance, and status.

Example Workflow:

=== Account Summary ===
Username: mohammed
Current Balance: $478.00

=== Profile Information ===
Address: Riyadh
Phone: 000000000
Email: mohammed@gmail.com
Job: Software Engineer

=== Recent Transactions ===
Date         Type          Amount  Balance  Status
2024-12-03   TRANSFER_SENT $200.0  $799.0   COMPLETED
2024-12-03   BILL_PAYMENT  $120.0  $679.0   COMPLETED
2024-12-03   TRANSFER_SENT $200.0  $478.0   COMPLETED


7-Logout:
Logs you out and redirects you to the main menu.

Contributors:
SALMAN ALMUTAIRI
ABDULRAHMAN ALZOMEA
ABDULLAH ALYOSEF
ABDULRAHMAN ALYOSEF
ABDULMALIK ALZEER