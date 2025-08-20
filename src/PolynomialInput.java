import java.util.HashMap;
import java.util.Map;

public class PolynomialInput {
    public Keys keys;
    public Map<String, YValue> points;
    
    // This is needed for Gson deserialization
    public PolynomialInput() {
        points = new HashMap<>();
    }
    
    public static class Keys {
        public int n;
        public int k;
    }
}