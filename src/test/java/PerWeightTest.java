import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PerWeightTest {
    @Test
    public void perWeightTestOver4() {
        PerWeight patient = new PerWeight(ageOver4.YES, 48);
        int expected = 3;
        int actual = patient.doseOfMedicine();

        assertEquals(expected, actual);
    }

    @Test
    public void perWeightTestUnder4() {
        PerWeight patient = new PerWeight(ageOver4.NO, 12);
        int expected = 2;
        int actual = patient.doseOfMedicine();

        assertEquals(expected, actual);
    }




}
