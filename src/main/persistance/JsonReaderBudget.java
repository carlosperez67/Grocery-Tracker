package persistance;

import model.*;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;

// Represents a reader that reads Budget from JSON data stored in file
// Taken with much inspiration from JsonSerializationDemo
public class JsonReaderBudget extends JsonReader {

    // EFFECTS: constructs reader to read from source file
    public JsonReaderBudget(String source) {
        super(source);
    }

    // EFFECTS: reads Budget from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Budget read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return addBudget(jsonObject);
    }

    // MODIFIES: Budget
    // EFFECTS: parses Budget from JSON object and loads budget
    private Budget addBudget(JSONObject jsonObject) {
        JSONObject json = jsonObject.getJSONObject("budget");
        int monthlyBudget = json.getInt("monthlyBudget");
        int amtSpent = json.getInt("amtSpent");
        int amtLeft = json.getInt("amtLeft");
        System.out.println("here");
        Money money = new Money(monthlyBudget);
        Budget budget = new Budget(money);
        budget.setAmtSpent(amtSpent);
        budget.setAmtLeft(amtLeft);
        return budget;
    }
    

}
