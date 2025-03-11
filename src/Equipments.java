public class Equipments {
    int itemId = 0;
    String[] itemName = new String[100];
    String[] itemType = new String[100];
    String[] itemToken=new String[100];
    double[] itemPrice = new double[100];
    boolean[] isAvailable = new boolean[100];
    int[] issuedTo = new int[100];
    Buyer buyer=null;
    Token token=new Token();
    public void setBuyer(Buyer buyer) {
            this.buyer=buyer;
}
    public void viewProducts() {
        if (itemId == 0) {
            System.out.println("No items are entered");
        } else {
            System.out.println("Products:");
            System.out.println("Item ID\tItem Name\tItem Type\tItem Price\tIssued To\tIs Available\tToken");
            for (int i = 1; i <= itemId; i++) {
                System.out.println(
                    i + "\t" + itemName[i] + "\t" + itemType[i] + "\t" + itemPrice[i] + "\t\t" + issuedTo[i] + "\t\t" + isAvailable[i]+"\t\t"+itemToken[i]
                );
            }
        }
    }


    public void viewSoldProducts() {
        if (itemId == 0) {
            System.out.println("No items are entered");
        } else {
            System.out.println("Issued Products are:");
            System.out.println("Item ID\tItem Name\tItem Type\tItem Price\tIssued To\t\ttoken");
            for (int i = 1; i <= itemId; i++) {
                if (!isAvailable[i]) {
                    System.out.println(
                        i + "\t" + itemName[i] + "\t" + itemType[i] + "\t" + itemPrice[i] + "\t\t" + issuedTo[i]+"\t\t"+itemToken[i]
                    );
                }
            }
        }
    }

    public void viewAvailableProducts() {
        if (itemId == 0) {
            System.out.println("No items are entered");
        } else {
            System.out.println("Available Products are:");
            System.out.println("Item ID\tItem Name\tItem Type\tItem Price");
            for (int i = 1; i <= itemId; i++) {
                if (isAvailable[i]) {
                    System.out.println(
                        i + "\t" + itemName[i] + "\t" + itemType[i] + "\t" + itemPrice[i]
                    );
                }
            }
        }
    }

    public void addProduct(String itemName, String itemType, double itemPrice) {
        ++itemId;
        this.itemName[itemId] = itemName;
        this.itemType[itemId] = itemType;
        this.itemPrice[itemId] = itemPrice;
        this.isAvailable[itemId] = true;
        this.issuedTo[itemId] = 0;
        System.out.println("Item successfully added");
    }

    public void issueProduct(int itemId, int buyerId) {
        try {
            if (itemId <= 0 || itemId > this.itemId) {
                throw new IndexOutOfBoundsException("Item with provided ID does not exist.");
            }
            else if(buyerId<=0||buyerId>buyer.buyerId)
            {
                throw new IndexOutOfBoundsException("service.Buyer with provided ID does not exist.");
            }
            if (this.isAvailable[itemId]) {
                String iToken=token.generateToken(buyerId);
                this.itemToken[itemId]=iToken;
                this.isAvailable[itemId] = false;
                this.issuedTo[itemId] = buyerId;
                System.out.println(itemName[itemId] + " is successfully issued to buyer with id " + buyerId);
            } else {
                System.out.println("Item is not available");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    public void returnProduct(int itemId, String iToken) {
        try {
            if (itemId <= 0 || itemId > this.itemId) {
                throw new IndexOutOfBoundsException("Item with provided ID does not exist.");
            }
            // else if(buyerId<=0||buyerId>buyer.buyerId)
            // {
            //     throw new IndexOutOfBoundsException("service.Buyer with provided ID does not exist.");
            // }
            if (this.isAvailable[itemId]) {
                System.out.println("Please enter valid data. Item is already available.");
            } else {
                if (this.itemToken[itemId].equals(iToken)) {
                    this.isAvailable[itemId] = true;
                    this.issuedTo[itemId] = 0;
                    System.out.println("Item successfully returned");
                } else {
                    System.out.println("Invalid token");
                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }
    public void viewProductsForBuyer() {
        if (itemId == 0) {
            System.out.println("No items available");
        } else {
            System.out.println("Products are:");
            System.out.println("Item ID\tItem Name\tItem Type\tItem Price\tIs Available");
            for (int i = 1; i <= itemId; i++) {
                System.out.println(
                    i + "\t" + itemName[i] + "\t" + itemType[i] + "\t" + itemPrice[i] + "\t\t" + isAvailable[i]
                );
            }
        }
    }
    public void viewSoldProductsForBuyer() {
        if (itemId == 0) {
            System.out.println("No items are entered");
        } else {
            System.out.println("Issued Products are:");
            System.out.println("Item ID\tItem Name\tItem Type\tItem Price");
            for (int i = 1; i <= itemId; i++) {
                if (!isAvailable[i]) {
                    System.out.println(
                        i + "\t" + itemName[i] + "\t" + itemType[i] + "\t" + itemPrice[i]
                    );
                }
            }
        }
    }
    public void viewAvailableProductsForBuyer() {
        if (itemId == 0) {
            System.out.println("No items are entered");
        } else {
            System.out.println("Available Products are:");
            System.out.println("Item ID\tItem Name\tItem Type\tItem Price");
            for (int i = 1; i <= itemId; i++) {
                if (isAvailable[i]) {
                    System.out.println(
                        i + "\t" + itemName[i] + "\t" + itemType[i] + "\t" + itemPrice[i]
                    );
                }
            }
        }
    }


    public void viewProductsByBuyerId(int currentBuyerId) {
        System.out.println("Products Issued to you are:");
            System.out.println("Item ID\tItem Name\tItem Type\tItem Price");
            for (int i = 1; i <= itemId; i++) {
                if (issuedTo[i]==currentBuyerId) {
                    System.out.println(
                        i + "\t" + itemName[i] + "\t" + itemType[i] + "\t" + itemPrice[i]
                    );
                }
            }
        
    }


   


}
