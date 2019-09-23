import java.util.Scanner;

/**
 * This class runs the application and handles the Product I/O
 * @version 2.2
 */
public class Driver{

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
     * @return     the users menu choice
     */
    private int mainMenu(){
        System.out.println("Shop Menu");
        System.out.println("---------");
        System.out.println("  1) Add a product");
        System.out.println("  2) List the Products");
        System.out.println("  --------------------");
        System.out.println("  3) List the current products");
        System.out.println("  4) Display average product unit cost");
        System.out.println("  5) Display cheapest product");
        System.out.println("  6) List products that are more expensive than a given price");
        System.out.println("  --------------------");
        System.out.println("  0) Exit");
        System.out.print("==>> ");
        int option = input.nextInt();
        return option;
    }

    private void runMenu(){
        int option = mainMenu();
        while (option != 0){

            switch (option){
                case 1:    addProduct();
                    break;
                case 2:    printProduct();
                    break;
                case 3:    printCurrentProducts();
                    break;
                case 4:    printAverageProductPrice();
                    break;
                case 5:    printCheapestProduct();
                    break;
                case 6:    printProductsAboveAPrice();
                    break;
                default:   System.out.println("Invalid option entered: " + option);
                    break;
            }

            //pause the program so that the user can read what we just printed to the terminal window
            System.out.println("\nPress any key to continue...");
            input.nextLine();
            input.nextLine();  //this second read is required - bug in Scanner class; a String read is ignored straight after reading an int.

            //display the main menu again
            option = mainMenu();
        }

        //the user chose option 0, so exit the program
        System.out.println("Exiting... bye");
        System.exit(0);
    }

    //gather the product data from the user and create a new product.
    private void addProduct(){
        //dummy read of String to clear the buffer - bug in Scanner class.
        input.nextLine();
        System.out.print("Enter the Product Name:  ");
        String productName = input.nextLine();
        System.out.print("Enter the Product Code:  ");
        int productCode = input.nextInt();
        System.out.print("Enter the Unit Cost:  ");
        double unitCost = input.nextDouble();
        System.out.print("Is this product in your current line (y/n): ");
        char currentProduct = input.next().charAt(0);
        boolean inCurrentProductLine = false;
        if ((currentProduct == 'y') || (currentProduct == 'Y'))
            inCurrentProductLine = true;

        store.add(new Product(productName, productCode, unitCost, inCurrentProductLine));
    }


    //print the product (the toString method is automatically called).
    private void printProduct(){
        System.out.println("List of Products are:");
        System.out.println(store.listProducts());
    }

    private void printCurrentProducts(){
        System.out.println("List of CURRENT Products are:");
        System.out.println(store.listCurrentProducts());
    }

    private void printAverageProductPrice(){
        System.out.println("The average product price is: " + store.averageProductPrice());
    }

    private void printCheapestProduct(){
        System.out.println("The cheapest product is:  " + store.cheapestProduct());
    }

    private void printProductsAboveAPrice(){
        System.out.print("View the product costing more than this price:  ");
        double price = input.nextDouble();
        System.out.println(store.listProductsAboveAPrice(price));
    }

}