import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PerFatTest {

    private PerFat patient;

    @BeforeEach
    public void setUp() {
        patient = new PerFat();

        patient.addProduct("Butter", 81);
    }

    @Test
    public void testAddProduct() {

        patient.addProduct("Milk", 3);

        assertEquals(3, patient.getProducts().get("Milk"));
    }

    @Test
    public void testRemoveProduct() {
        patient.removeProduct("Butter");


        assertNull(patient.getProducts().get("Butter"));
    }

    @Test
    public void testProductsInfo() {

        String expected = "Current list of products:\nButter: 81 grams \n";
        String actual = patient.productsInfo();


        assertEquals(expected, actual);
    }

    @Test
    public void testProductsConverterToFat() {

        patient.productsConverterToFat("C:\\Users\\julia\\Desktop\\do projektu\\products_list.json");


        assertEquals(65.6991, patient.getProductsWithFat().get("Butter"));

    }

    @Test
    public void testDoseOfMedicine() {
        int expected = 14;

        patient.productsConverterToFat("C:\\Users\\julia\\Desktop\\do projektu\\products_list.json");
        int actual = patient.doseOfMedicine();

        assertEquals(expected, actual);
    }

    @Test
    public void test2DoseOfMedicine() {
        int expected = 0;
        int actual = patient.doseOfMedicine();

        assertEquals(expected, actual);
    }

    @Test
    public void testProductsConverterToFatFileNotFoundException() {
        patient.productsConverterToFat("Mistake");

        assertNull(patient.getProductsWithFat().get("Butter"));
    }

}