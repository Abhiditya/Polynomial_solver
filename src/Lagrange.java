import java.util.List;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class Lagrange {
    // Define a high precision MathContext for calculations
    private static final MathContext MC = new MathContext(100, RoundingMode.HALF_UP);

    public static BigDecimal lagrangeInterpolation(List<BigDecimal[]> points) {
        BigDecimal result = BigDecimal.ZERO;
        int n = points.size();

        for (int i = 0; i < n; i++) {
            BigDecimal term = points.get(i)[1];
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    // Calculate (0 - points[j][0])
                    BigDecimal numerator = BigDecimal.ZERO.subtract(points.get(j)[0]);
                    
                    // Calculate (points[i][0] - points[j][0])
                    BigDecimal denominator = points.get(i)[0].subtract(points.get(j)[0]);
                    
                    // Calculate the fraction and multiply with term
                    term = term.multiply(numerator.divide(denominator, MC));
                }
            }
            result = result.add(term);
        }
        return result;
    }
}
