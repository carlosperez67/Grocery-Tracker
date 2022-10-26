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

    // MODIFIES: ListOfGroceries
    // EFFECTS: parses GroceryItem from JSON object and adds it to ListOfGrocery
    private void addGrocery(ListOfGroceries log, JSONObject jsonObject) {
        String label = jsonObject.getString("label");
        Money price = new Money(jsonObject.getInt("price"));
        StoringMethod storingMethod = StoringMethod.valueOf(jsonObject.getString("storingMethod"));
        int servingsLeft = jsonObject.getInt("servingsLeft");

        if (jsonObject.getInt("expiryDay") == -1) {
            NonPerishable nonPerishable = new NonPerishable(label, price, servingsLeft);
            log.addGrocery(nonPerishable);
        } else {
            Date d = new Date(jsonObject.getInt("expiryYear"),
                    jsonObject.getInt("expiryMonth"),
                    jsonObject.getInt("expiryDay"));
            Perishable perishable = new Perishable(label, price, servingsLeft, storingMethod, d);
            log.addGrocery(perishable);
        }
    }

}
