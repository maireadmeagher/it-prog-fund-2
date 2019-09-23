import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Store {

    private ArrayList<Product> products;

    public Store(){
        products = new ArrayList<Product>();
    }

    public void add (Product product){
        products.add (product);
    }

    public ArrayList<Product> getProducts()
    {
        return products;
    }

    public String listProducts(){
        if (products.size() == 0){
            return "No products";
        }
        else{
            String listOfProducts = "";
            for (int i = 0; i < products.size(); i++){
                listOfProducts = listOfProducts + i + ": " + products.get(i) + "\n";
            }
            return listOfProducts;
        }
    }

    public String cheapestProduct()
    {
        if (!products.isEmpty()){
            Product cheapestProduct = products.get(0);
            for (Product product : products){
                if (product.getUnitCost() < cheapestProduct.getUnitCost() )
                    cheapestProduct = product;
            }
            return cheapestProduct.getProductName();
        }
        else
            return "No Products are in the ArrayList";
    }

    public String listCurrentProducts(){
        if (products.size() == 0){
            return "No products";
        }
        else{
            String listOfProducts = "";
            for (int i = 0; i < products.size(); i++){
                if (products.get(i).isInCurrentProductLine()) {
                    listOfProducts = listOfProducts + i + ": " + products.get(i) + "\n";
                }
            }
            return listOfProducts;
        }
    }

    public double averageProductPrice()
    {
        if (products.size() != 0){
            double totalPrice = 0;
            for (int i = 0; i < products.size(); i++){
                totalPrice = totalPrice + products.get(i).getUnitCost();
            }
            return toTwoDecimalPlaces(totalPrice / products.size());
        }
        else{
            return 0.0;
        }
    }

    public String listProductsAboveAPrice(double price)
    {
        if (!products.isEmpty()){
            String str = "";
            for (int i = 0; i < products.size(); i++){
                if (products.get(i).getUnitCost() > price)
                    str = str + i + ": " + products.get(i) + "\n";
            }
            return str;
        }
        else {
            return "No Products are more expensive than: " + price;
        }
    }

    @SuppressWarnings("unchecked")
    public void load() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("products.xml"));
        products = (ArrayList<Product>) is.readObject();
        is.close();
    }

    public void save() throws Exception
    {
        XStream xstream = new XStream(new DomDriver());
        ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("products.xml"));
        out.writeObject(products);
        out.close();
    }

    private double toTwoDecimalPlaces(double num){
        return (int) (num *100 ) /100.0;
    }
}
