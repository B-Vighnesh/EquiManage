
import java.util.InputMismatchException;
import java.util.Scanner;
//Main file 
public class EquipmentManagement {
    public static void main(String[] args) {
        Equipments equipments = new Equipments();
        Buyer buyer = new Buyer(equipments);
        equipments.setBuyer(buyer);
        Seller seller = new Seller(equipments, buyer);
        boolean exit = false;
        Scanner sc = new Scanner(System.in);

        while (!exit) {
            try {
                System.out.println("\n--- Equipments Dashboard ---");
                System.out.println("1. Seller Menu");
                System.out.println("2. Buyer Menu");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                
                int option = sc.nextInt();

                switch (option) {
                    case 1:
                        seller.menu();
                        break;
                    case 2:
                        buyer.menu();
                        break;
                    case 3:
                        System.out.println("Exiting the system. Goodbye!");
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid option. Please select a valid menu option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                sc.nextLine(); 
            }
        }
        
        sc.close(); 
    }
}
