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
public class JsonReaderBudget{
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReaderBudget(String source) {
        this.source = source;
    }

    // EFFECTS: reads ListOfGroceries from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ListOfGroceries read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseListOfGroceries(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses ListOfGroceries from JSON object and returns it
    private ListOfGroceries parseListOfGroceries(JSONObject jsonObject) {
        ListOfGroceries log = new ListOfGroceries();
        addGroceries(log, jsonObject);
        addBudget(jsonObject);
        return log;
    }

    // MODIFIES: Budget
    // EFFECTS: parses Budget from JSON object and loads budget
    private void addBudget(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("groceries");
        int monthlyBudget = jsonObject.getInt("monthlyBudget");
        int amtSpent = jsonObject.getInt("amtSpent");
        int amtLeft = jsonObject.getInt("amtLeft");
        Money money = new Money(monthlyBudget);
        Budget budget = new Budget(money);
        budget.setAmtSpent(amtSpent);
        budget.setAmtLeft(amtLeft);
    }



}
