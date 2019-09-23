import java.util.Scanner;

/**
 * This class runs the application and handles the Product I/O
 *
 * @version 5.0
 */
public class Driver {

    private Scanner input = new Scanner(System.in);
    private Store store;

    public Driver() {
        store = new Store();
        runMenu();
    }

    public static void main(String[] args) {
        new Driver();
    }

    /**
     * mainMenu() - This method displays the menu for the application,
     * reads the menu option that the user entered and returns it.
     *
     * @return the users menu choice
     */
    private int mainMenu() {
        System.out.println("Shop Menu");
        System.out.println("---------");
        System.out.println("  1) Add a product");
        System.out.println("  2) List the products");
        System.out.println("  3) Update a product");
        System.out.println("  4) Delete a product");
        System.out.println("  --------------------");
        System.out.println("  5) List the current products");
        System.out.println("  6) Display average product unit cost");
        System.out.println("  7) Display cheapest product");
        System.out.println("  8) List products that are more expensive than a given price");
        System.out.println("  --------------------");
        System.out.println("  9)  Save products to products.xml");
        System.out.println("  10) Load products from products.xml");
        System.out.println("  --------------------");
        System.out.println("  0) Exit");
        return ScannerInput.readNextInt("==>>");
    }

    private void runMenu() {
        int option = mainMenu();
        while (option != 0) {

            switch (option) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    printProduct();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    deleteProduct();
                    break;
                case 5:
                    printCurrentProducts();
                    break;
                case 6:
                    printAverageProductPrice();
                    break;
                case 7:
                    printCheapestProduct();
                    break;
                case 8:
                    printProductsAboveAPrice();
                    break;
                case 9:
                    try{
                        store.save();
                    }
                    catch(Exception e){
                        System.err.println("Error writing to file: " + e);
                    }
                    break;
                case 10:
                    try{
                        store.load();
                    }
                    catch(Exception e){
                        System.err.println("Error reading from file: " + e);
                    }
                    break;
                default:
                    System.out.println("Invalid option entered: " + option);
                    break;
            }

            //pause the program so that the user can read what we just printed to the terminal window
            System.out.println("\nPress any key to continue...");
            input.nextLine();

            //display the main menu again
            option = mainMenu();
        }

        //the user chose option 0, so exit the program
        System.out.println("Exiting... bye");
        System.exit(0);
    }

    //gather the product data from the user and create a new product.
    private void addProduct() {
        System.out.print("Enter the Product Name:  ");
        String productName = input.nextLine();

        int productCode = ScannerInput.readNextInt("Enter the product code: ");
        double unitCost = ScannerInput.readNextDouble("Enter the Unit Cost:  ");

        System.out.print("Is this product in your current line (y/n): ");
        char currentProduct = input.next().charAt(0);
        boolean inCurrentProductLine = false;
        if ((currentProduct == 'y') || (currentProduct == 'Y'))
            inCurrentProductLine = true;

        store.add(new Product(productName, productCode, unitCost, inCurrentProductLine));
    }

    private void updateProduct(){
        //list the products
        System.out.println(store.listProducts());

        if (store.getProducts().size() > 0) {
            //only ask the user to choose a product if products exist
            int index = ScannerInput.readNextInt("Enter the index of the product to update ==> ");

            if ((index >= 0) && (index < store.getProducts().size())) {
                //if the index is valid, gather new details for each field from the user
                System.out.print("Enter the Product Name:  ");
                String productName = input.nextLine();

                int productCode = ScannerInput.readNextInt("Enter the product code: ");
                double unitCost = ScannerInput.readNextDouble("Enter the Unit Cost:  ");

                System.out.print("Is this product in your current line (y/n): ");
                char currentProduct = input.next().charAt(0);
                boolean inCurrentProductLine = false;
                if ((currentProduct == 'y') || (currentProduct == 'Y'))
                    inCurrentProductLine = true;

                //retrieve the selected product from the ArrayList and update the details
                Product product = store.getProducts().get(index);
                product.setProductCode(productCode);
                product.setProductName(productName);
                product.setUnitCost(unitCost);
                product.setInCurrentProductLine(inCurrentProductLine);
            }
            else {
                System.out.println("There are no products for this index number");
            }
        }
    }

    private void deleteProduct() {
        //list the products
        System.out.println(store.listProducts());

        if (store.getProducts().size() > 0) {
            //only ask the user to choose the product to delete if products exist
            //Ask the user to enter the index of the product they wish to delete
            int index = ScannerInput.readNextInt("Enter the index of the product to delete ==> ");

            if ((index >= 0) && (index < store.getProducts().size())) {
                //if the index is valid, delete the product at the given index
                store.getProducts().remove(index);
                System.out.println("Product deleted.");
            } else {
                System.out.println("There is no product for this index number");
            }
        }
    }

    //print the product (the toString method is automatically called).
    private void printProduct() {
        System.out.println("List of Products are:");
        System.out.println(store.listProducts());
    }

    private void printCurrentProducts() {
        System.out.println("List of CURRENT Products are:");
        System.out.println(store.listCurrentProducts());
    }

    private void printAverageProductPrice() {
        System.out.println("The average product price is: " + store.averageProductPrice());
    }

    private void printCheapestProduct() {
        System.out.println("The cheapest product is:  " + store.cheapestProduct());
    }

    private void printProductsAboveAPrice() {
        double price = ScannerInput.readNextDouble("View the product costing more than this price:  ");
        System.out.println(store.listProductsAboveAPrice(price));
    }

}