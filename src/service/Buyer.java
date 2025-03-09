package service;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Buyer {
     int buyerId = 0;
    private String[] buyerName = new String[100];
    private String[] buyerEmail = new String[100];
    private String[] buyerPhno = new String[100];
    private String[] buyerPassword = new String[100];
    private int currentBuyerId = -1;
    private boolean login = false;
    private Scanner sc = new Scanner(System.in);
    private Equipments equipments;

    public Buyer() {}

    public Buyer(Equipments equipments) {
        this.equipments = equipments;
        addBuyer("Ramesh", "Ramesh@1234", "ramesh@example.com", "1234567890");
        addBuyer("Suresh", "Suresh@1234", "suresh@example.com", "9876543210");
        addBuyer("Mahesh", "Mahesh@1234", "mahesh@example.com", "4567891230");
    }

    public void login() {
        if (login) {
            System.out.println("You are already logged in.");
            return;
        }

        while (!login) {
            try {
                System.out.println("\n--- Login Dashboard ---");
                System.out.print("Enter Your ID: ");
                int currentId = sc.nextInt();
                sc.nextLine(); // Clear buffer
                if (currentId <= 0 || currentId > buyerId) {
                    System.out.println("Invalid ID. Please try again.");
                    continue;
                }

                System.out.print("Enter Your Password: ");
                String password = sc.nextLine();

                if (buyerPassword[currentId].equals(password)) {
                    login = true;
                    currentBuyerId = currentId;
                    System.out.println("Successfully logged in as " + buyerName[currentBuyerId]);
                } else {
                    System.out.println("Incorrect password. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.nextLine(); // Clear the buffer
            }
        }
    }

    public void logout() {
        if (!login) {
            System.out.println("You are not logged in.");
            return;
        }
        login = false;
        currentBuyerId = -1;
        System.out.println("Logged out successfully.");
    }

    public void register() {
        try {
            System.out.println("\n--- Register Dashboard ---");
            System.out.print("Enter your name: ");
            String name = sc.nextLine();
            System.out.print("Enter your email: ");
            String email = sc.nextLine();
            System.out.print("Enter your phone number: ");
            String phone = sc.nextLine();
            System.out.print("Enter a password: ");
            String password = sc.nextLine();

            addBuyer(name, password, email, phone);
            System.out.println("Registration successful! Your service.Buyer ID is: " + buyerId);
        } catch (Exception e) {
            System.out.println("An error occurred during registration. Please try again.");
        }
    }

    public void menu() {
        System.out.println("\nAre you a new buyer? (1 for Yes, 0 for No): ");
        String newBuyer = sc.nextLine();
        if (newBuyer.equals("1")) {
            register();
        } else {
            login();
        }

        boolean exit = false;
        while (!exit) {
            try {
                System.out.println("\n--- service.Buyer Menu ---");
                System.out.println("1. View All Products");
                System.out.println("2. View Available Products");
                System.out.println("3. View Sold Products");
                System.out.println("4. View My Products");
                System.out.println("5. Register");
                System.out.println("6. Login");
                System.out.println("7. Logout");
                System.out.println("8. Exit");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); 

                switch (choice) {
                    case 1 : viewProducts();
                    case 2 : viewAvailableProducts();
                    case 3 : viewSoldProducts();
                    case 4 : viewMyProducts();
                    case 5 : register();
                    case 6 : login();
                    case 7 : logout();
                    case 8 : exit = true;
                    default : System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.nextLine(); 
            }
        }
    }

    public void viewProducts() {
        if (!login) {
            System.out.println("Please log in to view products.");
            return;
        }
        equipments.viewProductsForBuyer();
    }

    public void viewSoldProducts() {
        if (!login) {
            System.out.println("Please log in to view sold products.");
            return;
        }
        equipments.viewSoldProductsForBuyer();
    }

    public void viewAvailableProducts() {
        if (!login) {
            System.out.println("Please log in to view available products.");
            return;
        }
        equipments.viewAvailableProductsForBuyer();
    }

    public void viewMyProducts() {
        if (!login) {
            System.out.println("Please log in to view your products.");
            return;
        }
        equipments.viewProductsByBuyerId(currentBuyerId);
    }

    public void addBuyer(String name, String password, String email, String phone) {
        buyerId++;
        buyerName[buyerId] = name;
        buyerEmail[buyerId] = email;
        buyerPhno[buyerId] = phone;
        buyerPassword[buyerId] = password;
    }

    public void viewBuyers() {
        if (buyerId == 0) {
            System.out.println("No buyers registered.");
            return;
        }

        System.out.println("service.Buyer ID\tName\t\tEmail\t\t\t\tPhone");
        for (int i = 1; i <= buyerId; i++) {
            System.out.println(i + "\t\t" + buyerName[i] + "\t" + buyerEmail[i] + "\t" + buyerPhno[i]);
        }
    }
}
