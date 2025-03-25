package main;

import dao.BuyerDAO;
import dao.EquipmentDAO;
import dao.SellerDAO;
import model.Buyer;
import model.Equipments;
import model.Seller;
import util.Token;

import java.sql.SQLOutput;
import java.util.Scanner;

public class EquipmentManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BuyerDAO buyerDAO = new BuyerDAO();
        SellerDAO sellerDAO = new SellerDAO();
        while (true) {
            System.out.println("Welcome to Equipment Management System!");
            System.out.println("1. Register as Buyer");
            System.out.println("2. Login as Buyer");
            System.out.println("3. Login as Seller");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter Name:");
                    String buyerName = scanner.nextLine();
                    System.out.println("Enter Email:");
                    String buyerEmail = scanner.nextLine();
                    System.out.println("Enter Password:");
                    String buyerPassword = scanner.nextLine();
//                    System.out.println("Enter role:");
//                    String role= scanner.nextLine();
                    buyerDAO.registerBuyer(new Buyer( buyerName, buyerEmail, buyerPassword));
                    break;
                case 2:
                    System.out.println("Enter Email or ID:");
                    String loginBuyerEmailORId = scanner.nextLine();
                    System.out.println("Enter Password:");
                    String loginBuyerPassword = scanner.nextLine();
                    if (buyerDAO.loginBuyer(loginBuyerEmailORId, loginBuyerPassword)!=null) {
                        buyerMenu(scanner, buyerDAO,buyerDAO.loginBuyer(loginBuyerEmailORId, loginBuyerPassword));
                    } else {
                        System.out.println("Invalid credentials.");
                    }
                    break;
                case 3:

                    System.out.println("Enter Email or ID:");
                    String loginSellerEmailORId = scanner.nextLine();
                    System.out.println("Enter Password:");
                    String loginSellerPassword = scanner.nextLine();
                    if (sellerDAO.loginSeller(loginSellerEmailORId, loginSellerPassword)!=null) {
                        sellerMenu(scanner, sellerDAO,sellerDAO.loginSeller(loginSellerEmailORId, loginSellerPassword));
                    } else {
                        System.out.println("Invalid credentials.");
                    }
                    break;
                case 4:
                    System.out.println("Exiting system. Goodbye!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void sellerMenu(Scanner scanner, SellerDAO sellerDAO, Seller seller) {
        Equipments equipments = new Equipments();
        EquipmentDAO equipmentDAO = new EquipmentDAO();
        BuyerDAO buyerDAO = new BuyerDAO();
        Token token=new Token();

        while (true) {
            System.out.println("\nSeller Menu");
            System.out.println("1. Add Equipment");
            System.out.println("2. View All Equipments");
            System.out.println("3. View Available Equipments");
            System.out.println("4. View Issued Equipments");
            System.out.println("5. View Equipment Issued to a Specific Buyer");
            System.out.println("6. View Report");
            System.out.println("7. Issue Equipment to Buyer");
            System.out.println("8. Accept Returned Equipment");
            System.out.println("9. Remove Equipment");
            System.out.println("10. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Enter Equipment Name:");
                    String equipmentName = scanner.nextLine();
                    System.out.println("Enter Type:");
                    String type = scanner.nextLine();
                    equipments.setName(equipmentName);
                    equipments.setType(type);
                    equipmentDAO.addEquipment(equipments);
                    break;
                case 2:
                    System.out.println("All Equipments:");
                    equipmentDAO.viewEquipments();
                    break;
                case 3:
                    System.out.println("Available Equipments:");
                    equipmentDAO.viewAvailableEquipments();
                    break;
                case 4:
                    System.out.println("Issued Equipments:");
                    equipmentDAO.viewIssuedEquipments();
                    break;
                case 5:
                    System.out.println("Enter Buyer ID:");
                    int buyerId = scanner.nextInt();
                    equipmentDAO.viewEquipmentIssuedToBuyer(buyerId);
                    break;
                case 6:
                    System.out.println("Report:");
                    equipmentDAO.getReport();
                    break;
                case 7:
                    System.out.println("Enter Buyer ID:");
                    int issueBuyerId = scanner.nextInt();
                    System.out.println("Enter Equipment ID to Issue:");
                    int issueEquipmentId = scanner.nextInt();
//                    System.out.println("Enter rental duration (in days):");
//                    int rentalDays = scanner.nextInt();
                    String token1=token.generateToken(issueBuyerId);
                    equipmentDAO.rentEquipment(issueBuyerId, issueEquipmentId,token1);
                    System.out.println("Token is "+token1);
                    break;
                case 8:
                    System.out.println("Enter Buyer ID:");
                    int returnBuyerId = scanner.nextInt();
                    System.out.println("Enter Equipment ID to Accept Return:");
                    int returnEquipmentId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Enter the Token");
                    String token2 =scanner.nextLine();
                    equipmentDAO.returnEquipment(returnBuyerId, returnEquipmentId,token2);
                    break;
                case 9:
                    System.out.println("Enter the Equipment ID to remove");
                    int deleteEquipmentId= scanner.nextInt();
                    equipmentDAO.removeEquipment(deleteEquipmentId);
                    break;


                case 10:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void buyerMenu(Scanner scanner, BuyerDAO buyerDAO, Buyer buyer) {
        EquipmentDAO equipmentDAO = new EquipmentDAO();
        while (true) {
            System.out.println("\nBuyer Menu");
            System.out.println("1. View All Equipments");
            System.out.println("2. View Available Equipment");
            System.out.println("3. View Equipment Issued to Me");
            System.out.println("4. Get My Report");
            System.out.println("5. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("All Equipments:");
                    equipmentDAO.viewEquipments();
                    break;
                case 2:
                    System.out.println("Available Equipments:");
                    equipmentDAO.viewAvailableEquipments();
                    break;
                case 3:
                    System.out.println("Equipment Issued to You:");
                    equipmentDAO.viewEquipmentIssuedToBuyer(buyer.getId());
                    break;
                case 4:
                    System.out.println("Your Report:");
                    equipmentDAO.getMyReport(buyer.getId());
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

}
