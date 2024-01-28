import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Class representing a PerFat object used for managing products and their fat content.
 */
public class PerFat implements UseType{

    protected int numberOfUnits; // Number of units for dose calculation
    protected int fatQuantity; // Fat quantity for dose calculation


    private HashMap<String, Integer> products; // Map containing products and their quantities
    private HashMap<String, Double> productsWithFat; // Map containing products and their fat values

    public HashMap<String, Integer> getProducts() {
        return products;
    }

    public HashMap<String, Double> getProductsWithFat() {
        return productsWithFat;
    }

    /**
     * Constructs a PerFat object with default values for number of units and fat quantity.
     */
    public PerFat() {
        this.numberOfUnits = 10000;
        this.fatQuantity = 5;
        this.products = new HashMap<>();
        this.productsWithFat = new HashMap<>();
    }

    /**
     * Constructs a PerFat object with specified values for number of units and fat quantity.
     * @param numberOfUnits The number of units for dosage calculation.
     * @param fatQuantity The fat quantity for dosage calculation.
     */
    public PerFat(int numberOfUnits, int fatQuantity){
        this.numberOfUnits = numberOfUnits;
        this.fatQuantity = fatQuantity;
        this.products = new HashMap<>();
        this.productsWithFat = new HashMap<>();
    }

    /**
     * Adds a product with specified quantity to the products map.
     * @param productName The name of the product.
     * @param quantity The quantity of the product.
     */
    public void addProduct(String productName, Integer quantity) {
        products.put(productName, quantity);
    }

    /**
     * Removes a product from the products map.
     * @param productName The name of the product to be removed.
     */
    public void removeProduct(String productName) {
        products.remove(productName);
    }

    /**
     * Generates a string containing information about the current list of products.
     * @return A string containing the names and quantities of products.
     */
    public String productsInfo() {
        StringBuilder info = new StringBuilder("Current list of products:\n");
        for (Map.Entry<String, Integer> entry : products.entrySet()) {
            info.append(entry.getKey()).append(": ").append(entry.getValue()).append(" grams \n");
        }
        return info.toString();
    }

    /**
     * Adds product information to a JSON file.
     * @param filePath The path to the JSON file.
     * @param productName The name of the product.
     * @param fatIn100gram The fat content of the product per 100 grams.
     */
    public void addProductToJSON(String filePath, String productName, double fatIn100gram) {
        File myFile = new File(filePath);
        String myData = productName + ";" + Double.toString(fatIn100gram);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(myFile, true))) {
            bw.write(myData);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("An error occurred while writing data to the file: " + e.getMessage());
            System.out.println("Please check if the file path is correct and if you have appropriate write permissions.");
        }
    }

    /**
     * Converts product information from a file to fat values and stores them in the productsWithFat map.
     * @param filePath The path to the file containing product information.
     */
    public void productsConverterToFat(String filePath) {
        File myFile = new File(filePath);
        try {
            Scanner sc = new Scanner(myFile);
            Map<String, String> productFatMap = new HashMap<>();

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] splitLine = line.split(";");
                if (splitLine.length == 2) {
                    productFatMap.put(splitLine[0], splitLine[1]);
                }
            }

            for (Map.Entry<String, Integer> entry : products.entrySet()) {
                String productName = entry.getKey();
                Integer productQuantity = entry.getValue();

                if (productFatMap.containsKey(productName)) {
                    double fat = productQuantity * Double.parseDouble(productFatMap.get(productName)) / 100;
                    productsWithFat.put(productName, fat);
                } else {
                    System.out.println("Product \"" + productName + "\" has not been found in file.");
                    System.out.println("Add using the \"addProductToJSON\" method.");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Wrong file path! It doesn't exist!");
        }
    }

    /**
     * Calculates the dose of medicine based on fat content and dosage criteria.
     * @return The calculated dose of medicine.
     */
    @Override
    public int doseOfMedicine() {
        if (productsWithFat.isEmpty()){
            System.out.println("Before obtaining dose of pills You need to run \"productsConverterToFat\" ! ");
            return 0;
        }else {
            double sum = productsWithFat.values().stream()
                    .mapToDouble(value -> value)
                    .sum();
            double result = Math.ceil(sum * numberOfUnits / (fatQuantity * 10000.0));

            return (int) result;
        }

    }
}
