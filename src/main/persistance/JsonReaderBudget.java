package persistance;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.stream.Stream;

// Represents a reader that reads ListOfGroceries from JSON data stored in file
// Taken with much inspiration from JsonSerializationDemo
public class JsonReaderBudget extends JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReaderBudget(String source) {
        super(source);
    }

    // EFFECTS: reads Budget from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Budget read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseBudget(jsonObject);
    }

    // EFFECTS: parses ListOfGroceries from JSON object and returns it
    private Budget parseBudget(JSONObject jsonObject) {
        return addBudget(jsonObject);
    }


    // MODIFIES: Budget
    // EFFECTS: parses Budget from JSON object and loads budget
    private Budget addBudget(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("groceries");
        int monthlyBudget = jsonObject.getInt("monthlyBudget");
        int amtSpent = jsonObject.getInt("amtSpent");
        int amtLeft = jsonObject.getInt("amtLeft");
        Money money = new Money(monthlyBudget);
        Budget budget = new Budget(money);
        budget.setAmtSpent(amtSpent);
        budget.setAmtLeft(amtLeft);
        return budget;
    }


}
