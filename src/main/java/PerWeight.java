
/**
 * Enumeration representing age categories indicating whether the age is over 4 or not.
 */
enum ageOver4 {
    YES, // Indicates that the age is over 4 years old
    NO   // Indicates that the age is not over 4 years old
}
/**
 * Represents a PerWeight object used for calculating the dose of medicine based on age and weight.
 */
public class PerWeight implements UseType {

    protected ageOver4 age; // Indicates whether the age is over 4 (YES) or not (NO)
    protected double weight; // Weight of the individual

    /**
     * Constructs a PerWeight object with the specified age and weight.
     * @param age The age category (YES if over 4 years old, NO otherwise).
     * @param weight The weight of the individual.
     */
    public PerWeight(ageOver4 age, double weight) {
        this.age = age;
        this.weight = weight;
    }

    /**
     * Calculates the dose of medicine based on the age and weight of the individual.
     * @return The calculated dose of medicine in capsules.
     * @throws IllegalArgumentException if the age category is not supported (use YES or NO).
     */
    @Override
    public int doseOfMedicine() {
        switch (age) {
            case YES:
                double units = 500 * weight;
                int capsules = (int) Math.ceil(units / 10000);
                return capsules;
            case NO:
                double units1 = 1000 * weight;
                int capsules1 = (int) Math.ceil(units1 / 10000);
                return capsules1;
            default:
                throw new IllegalArgumentException("Unsupported value of enum ageOver4: " + age + "! Use YES or NO");
        }
    }
}