package persistance;

import model.ListOfGroceries;
import org.json.JSONObject;

// Represents a writer that writes JSON representation of ListOfGroceries to file
// Taken with much inspiration from JsonSerializationDemo
public class JsonWriterGrocery extends JsonWriter {

    // EFFECTS: constructs writer to write to destination file
    public JsonWriterGrocery(String destination) {
        super(destination);
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of ListOfGroceries to file
    public void write(ListOfGroceries log) {
        JSONObject json = log.toJson();
        saveToFile(json.toString(TAB));
    }

}
