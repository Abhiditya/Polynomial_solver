import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Utils {
    public static RootList readRoots(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            Gson gson = new Gson();
            return gson.fromJson(reader, RootList.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static PolynomialInput readPolynomialInput(String filePath) {
        try (FileReader reader = new FileReader(filePath)) {
            JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
            
            PolynomialInput input = new PolynomialInput();
            
            // Parse the keys object
            JsonObject keysObject = jsonObject.getAsJsonObject("keys");
            input.keys = new PolynomialInput.Keys();
            input.keys.n = keysObject.get("n").getAsInt();
            input.keys.k = keysObject.get("k").getAsInt();
            
            // Parse the points
            for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
                String key = entry.getKey();
                
                // Skip the "keys" entry
                if (key.equals("keys")) continue;
                
                JsonObject valueObject = entry.getValue().getAsJsonObject();
                YValue yValue = new YValue();
                yValue.base = Integer.parseInt(valueObject.get("base").getAsString());
                yValue.value = valueObject.get("value").getAsString();
                
                input.points.put(key, yValue);
            }
            
            return input;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static BigDecimal convertToDecimal(YValue y) {
        // Convert the string value in the specified base to a BigInteger
        BigInteger bigIntValue = new BigInteger(y.value, y.base);
        // Convert BigInteger to BigDecimal
        return new BigDecimal(bigIntValue);
    }
    
    public static List<BigDecimal[]> convertPolynomialInputToPoints(PolynomialInput input) {
        List<BigDecimal[]> points = new ArrayList<>();
        
        for (Map.Entry<String, YValue> entry : input.points.entrySet()) {
            BigDecimal x = new BigDecimal(entry.getKey());
            BigDecimal y = convertToDecimal(entry.getValue());
            points.add(new BigDecimal[]{x, y});
        }
        
        return points;
    }
}
