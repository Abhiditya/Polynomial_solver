import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Main {
    public static void main(String[] args) {
        String currentDir = System.getProperty("user.dir");
        String filePath = currentDir + "/data/polynomial_input.json";
        
        // Read the new input format
        PolynomialInput input = Utils.readPolynomialInput(filePath);
        
        if (input == null) {
            System.err.println("Failed to read input file. Please check the file path and format.");
            return;
        }
        
        // Convert the input to points
        List<BigDecimal[]> points = Utils.convertPolynomialInputToPoints(input);
        
        // Calculate the constant term using Lagrange interpolation
        BigDecimal constant = Lagrange.lagrangeInterpolation(points);
        
        // Only print the constant term (secret C) as required by the assignment
        System.out.println(constant.setScale(2, RoundingMode.HALF_UP));
    }
}
