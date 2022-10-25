package persistance;

import model.Budget;
import model.ListOfGroceries;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Represents a writer that writes JSON representation of ListOfGroceries to file
// Taken with much inspiration from JsonSerializationDemo
public class JsonWriterBudget extends JsonWriter {

    // EFFECTS: constructs writer to write to destination file
    public JsonWriterBudget(String destination) {
        super(destination);
    }


    // MODIFIES: this
    // EFFECTS: writes JSON representation of Budget to file
    public void write(Budget b) {
        JSONObject json = b.toJson();
        saveToFile(json.toString(TAB));
    }



}
