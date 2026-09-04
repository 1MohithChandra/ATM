
import java.util.ArrayList;
import java.util.Scanner;


// ================= USER CLASS =================

class User {

    Scanner sc;

    private int accNo;
    private int pin;

    boolean verified = false;

    int index;


    User(Scanner sc) {
        this.sc = sc;
    }


    boolean validate(int accNo) {

        this.accNo = accNo;

        int[] accNums = {
            10001, 10002, 10003, 10004, 10005
        };

        String[] userNames = {
            "Mohith",
            "Chandan",
            "Amit",
            "Arun",
            "Ramesh"
        };

        int[] pins = {
            1001, 1002, 1003, 1004, 1005
        };


        // Creating ArrayLists

        ArrayList<Integer> accNos = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Integer> pinList = new ArrayList<>();


        // Adding array values to ArrayLists

        for (int i = 0; i < accNums.length; i++) {

            accNos.add(accNums[i]);
            names.add(userNames[i]);
            pinList.add(pins[i]);
        }


        // Checking account number

        if (accNos.contains(accNo)) {

            index = accNos.indexOf(accNo);

            System.out.println();
            System.out.println("Welcome " + names.get(index));

            System.out.print("Enter the PIN: ");

            pin = sc.nextInt();


            // Checking PIN

            if (pinList.get(index) == pin) {

                System.out.println("PIN verified successfully");

                verified = true;

            } else {

                System.out.println("Invalid PIN");
            }

        } else {

            System.out.println("Invalid account number");
        }


        return verified;
    }
}



// ================= USER INTERFACE CLASS =================

class UserInterface {

    private int accNo;
    private int index;

    Scanner sc;


    // Temporary balance data
    // Later you can replace this with MongoDB

    double[] balances = {
        10010.0,
        109090.0,
        1212121.0,
        3232345.0,
        54576.0
    };


    // Constructor

    UserInterface(int accNo, int index, Scanner sc) {

        this.accNo = accNo;
        this.index = index;
        this.sc = sc;

        menu();
    }


    // ================= ATM MENU =================

    void menu() {

        boolean running = true;


        while (running) {

            System.out.println();
            System.out.println("========== ATM MENU ==========");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Balance Enquiry");
            System.out.println("4. Exit");
            System.out.println("==============================");

            System.out.print("Enter your choice: ");

            int userChoice = sc.nextInt();


            switch (userChoice) {

                case 1:

                    withdraw();
                    break;


                case 2:

                    deposit();
                    break;


                case 3:

                    balance();
                    break;


                case 4:

                    exit();
                    running = false;
                    break;


                default:

                    System.out.println("Invalid input");
            }
        }
    }


    // ================= WITHDRAW =================

    void withdraw() {

        System.out.print("Enter the amount: ");

        double withdrawAmount = sc.nextDouble();


        if (withdrawAmount <= 0) {

            System.out.println("Invalid amount");

        } else if (withdrawAmount > balances[index]) {

            System.out.println("Insufficient balance");

        } else {

            balances[index] -= withdrawAmount;

            System.out.println(
                "Amount of " +
                withdrawAmount +
                " has been withdrawn."
            );

            System.out.println(
                "Remaining balance: " +
                balances[index]
            );
        }
    }


    // ================= DEPOSIT =================

    void deposit() {

        System.out.print("Enter the amount: ");

        double depositAmount = sc.nextDouble();


        if (depositAmount <= 0) {

            System.out.println("Invalid amount");

        } else {

            balances[index] += depositAmount;

            System.out.println(
                "Amount of " +
                depositAmount +
                " has been deposited."
            );

            System.out.println(
                "Current balance: " +
                balances[index]
            );
        }
    }


    // ================= BALANCE =================

    void balance() {

        System.out.println();

        System.out.println(
            "Your balance is: " +
            balances[index]
        );
    }


    // ================= EXIT =================

    void exit() {

        System.out.println();
        System.out.println("Thank you for using the ATM.");
    }
}



// ================= MAIN CLASS =================

public class atm {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


        // Create User object

        User u = new User(sc);


        // Get account number

        System.out.print("Enter your account number: ");

        int accNo = sc.nextInt();


        // Validate account and PIN

        boolean verified = u.validate(accNo);


        // Open ATM menu only if verified

        if (verified) {

            UserInterface ui =
                new UserInterface(
                    accNo,
                    u.index,
                    sc
                );
        }


        sc.close();
    }
}