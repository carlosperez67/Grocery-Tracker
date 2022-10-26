package persistance;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;

// Represents a reader that reads ListOfGroceries from JSON data stored in file
// Taken with much inspiration from JsonSerializationDemo
public class JsonReaderGrocery extends JsonReader {

    // EFFECTS: constructs reader to read from source file
    public JsonReaderGrocery(String source) {
        super(source);
    }

    // EFFECTS: reads ListOfGroceries from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ListOfGroceries read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseListOfGroceries(jsonObject);
    }

    // EFFECTS: parses ListOfGroceries from JSON object and returns it
    private ListOfGroceries parseListOfGroceries(JSONObject jsonObject) {
        ListOfGroceries log = new ListOfGroceries();
        addGroceries(log, jsonObject);
        return log;
    }


    // MODIFIES: ListOfGroceries
    // EFFECTS: parses groceryItems from JSON object and adds them to ListOfGroceries
    private void addGroceries(ListOfGroceries log, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("groceries");
        for (Object json : jsonArray) {
            JSONObject nextGrocery = (JSONObject) json;
            addGrocery(log, nextGrocery);
        }
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
