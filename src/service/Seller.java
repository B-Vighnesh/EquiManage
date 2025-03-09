package service;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Seller {
    private String userName = "Anshul";
    private int sellerId=1234;
    private String mobileNo = "1234567891";
    private String emailId = "Anshul@gmail.com";
    private String password = "Anshul@1234";
    private Equipments equipments = null;
    private boolean login = false;
    private Scanner sc = new Scanner(System.in);
    Buyer buyer=null;
    public Seller(Equipments equipments,Buyer buyer)
    {
        this.equipments=equipments;
        this.buyer=buyer;
    }
    public void login() {
        if (login) {
            System.out.println("You are already logged in.");
            return;
        }
        
        while (!login) {
            System.out.println("\n--- Login Dashboard ---");
            System.out.println("Enter your username or type 'exit' to quit: ");
            String name = sc.nextLine();
            if ("exit".equalsIgnoreCase(name)) {
                System.out.println("Exiting login.");
                return;
            }
    
            System.out.println("Enter your password: ");
            String password = sc.nextLine();
    
            if (this.userName.equals(name) && this.password.equals(password)) {
                login = true;
                System.out.println("Login Successful!");
            } else {
                System.out.println("Invalid credentials. Please try again.");
            }
        }
    }
    
public void logout(){
    login = false;
    System.out.println("Logged out successfully.");

}
    public void menu() {
        // if (!login) {
        //     System.out.println("Please log in first.");
        //     return;
        // }
        boolean exit=false;
        System.out.println("Please log in first.");
        login();
        int choice;
        do {
            System.out.println("\n--- service.Seller Menu ---");
            System.out.println("1. View All Products");
            System.out.println("2. View Available Products");
            System.out.println("3. View Issued Products");
            System.out.println("4. Add Products");
            System.out.println("5. Issue Products");
            System.out.println("6. Return Products");
            System.out.println("7. View Buyers");
            System.out.println("8. Log In");
            System.out.println("9. Log Out");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");

            try {
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        viewProducts();
                        break;
                    case 2:
                        viewAvailableProducts();
                        break;
                    case 3:
                        viewSoldProducts();
                        break;
                    case 4:
                        addProducts();
                        break;
                    case 5:
                        issueProducts();
                        break;
                    case 6:
                        returnProduct();
                        break;
                    case 7:viewBuyers();
                    break;
                    case 8:
                            login();
                            break;
                    case 9:
                       logout();
                        break;
                    case 10:exit=true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.nextLine();
                choice = 0; 
            }
        } while (!exit);
    }
    public void viewBuyers()
    {
        if(!login)
        {
            System.out.println("Please log in to view sold products.");
            return;
        }

        buyer.viewBuyers();
    }
    public void viewProducts() {
        if (login) {
            equipments.viewProducts();
        } else {
            System.out.println("Please log in to view products.");
        }
    }

    public void viewSoldProducts() {
        if (login) {
            equipments.viewSoldProducts();
        } else {
            System.out.println("Please log in to view sold products.");
        }
    }

    public void viewAvailableProducts() {
        if (login) {
            equipments.viewAvailableProducts();
        } else {
            System.out.println("Please log in to view available products.");
        }
    }

    public void addProducts() {
        if (!login) {
            System.out.println("Please log in to add products.");
            return;
        }

        try {
            System.out.print("Enter the item name: ");
            String itemName = sc.nextLine();

            System.out.print("Enter the item type: ");
            String itemType = sc.nextLine();

            System.out.print("Enter the price of the item: ");
            double itemPrice = sc.nextDouble();
            sc.nextLine();

            equipments.addProduct(itemName, itemType, itemPrice);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid data.");
            sc.nextLine();
            System.out.println("Re-enter the product data.");
            addProducts();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public void issueProducts() {
        if (!login) {
            System.out.println("Please log in to issue products.");
            return;
        }

        try {
            System.out.print("Enter the ID of the item: ");
            int itemId = sc.nextInt();

            System.out.print("Enter the ID of the buyer: ");
            int buyerId = sc.nextInt();
            sc.nextLine();

            equipments.issueProduct(itemId, buyerId);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            sc.nextLine();
            System.out.println("Re-enter the data to issue product.");
            issueProducts();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Item ID does not exist. Please try again.");
            sc.nextLine();
            System.out.println("Re-enter the data to issue product.");
            issueProducts();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public void returnProduct() {
        if (!login) {
            System.out.println("Please log in to return products.");
            return;
        }

        try {
            System.out.print("Enter the ID of the item to return: ");
            int itemId = sc.nextInt();

            sc.nextLine();
            System.out.print("Enter the token: ");
            String iToken = sc.nextLine();

            equipments.returnProduct(itemId, iToken);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            sc.nextLine();
            System.out.println("Re-enter the data to return product.");
            returnProduct();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Item ID does not exist. Please try again.");
            sc.nextLine();
            System.out.println("Re-enter the data to return product.");
            returnProduct();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
