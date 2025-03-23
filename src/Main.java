import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private double balance;
    private int pin;
    private double limitedwithdrawl = 20000;
    private double amountWithdrawnToday = 0;
    private ArrayList<String> transacthistory;

    public Main(double initialBalance, int pin) {
        this.balance = initialBalance;
        this.pin = pin;
        this.transacthistory = new ArrayList<>();
    }

    public boolean validatePIN(int enteredPin) {
        return this.pin == enteredPin;
    }

    public void changePIN(int newPin) {
        this.pin = newPin;
        System.out.println("PIN changed successfully.");
    }

    public void checkBalance() {
        System.out.println("Your current balance is: ₹" + balance);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transacthistory.add("Deposited: ₹" + amount);
            System.out.println("₹" + amount + " has been deposited successfully.");
        } else {
            System.out.println("Invalid amount. Please enter a valid amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            if ((amountWithdrawnToday + amount) <= limitedwithdrawl) {
                balance -= amount;
                amountWithdrawnToday += amount;
                transacthistory.add("Withdrawn: ₹" + amount);
                System.out.println("Please collect your ₹" + amount);
            } else {
                System.out.println("Withdrawal limit has been exceeded. Limit: ₹" + limitedwithdrawl);
            }
        } else {
            System.out.println("Insufficient balance .");
        }
    }

    public void showTransactionHistory() {
        if (transacthistory.isEmpty()) {
            System.out.println("No transactions has been done.");
        } else {
            System.out.println("Transaction History:");
            for (String transaction : transacthistory) {
                System.out.println(transaction);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Main atm = new Main(10000, 1234); // Initial balance ₹10000 and PIN 1234

        System.out.print("Enter your PIN: ");
        int enteredPin = scanner.nextInt();

        if (atm.validatePIN(enteredPin)) {
            System.out.println("Access Granted. Welcome!");

            while (true) {
                System.out.println("\nATM Menu:");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit Money");
                System.out.println("3. Withdraw Money");
                System.out.println("4. Show Transaction History");
                System.out.println("5. Change PIN");
                System.out.println("6. Exit");
                System.out.print("Choose an option: ");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        atm.checkBalance();
                        break;
                    case 2:
                        System.out.print("Enter the amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        atm.deposit(depositAmount);
                        break;
                    case 3:
                        System.out.print("Enter the amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        atm.withdraw(withdrawAmount);
                        break;
                    case 4:
                        atm.showTransactionHistory();
                        break;
                    case 5:
                        System.out.print("Enter new PIN: ");
                        int newPin = scanner.nextInt();
                        atm.changePIN(newPin);
                        break;
                    case 6:
                        System.out.println("Thank you for using the ATM. Goodbye!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please choose a valid option.");
                }

                // Ask if the user wants to continue or exit
                System.out.print("\nDo you want to continue? (1(y)/2(n): ");
                scanner.nextLine(); // Clear buffer
                String continueChoice = scanner.nextLine().toLowerCase();

                if (!continueChoice.equals("yes")) {
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                }
            }
        } else {
            System.out.println("You have entered Invalid PIN. Exiting...");
        }
        scanner.close();
    }
}
