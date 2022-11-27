package persistance;

import eventlog.Event;
import eventlog.EventLog;
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
        EventLog.getInstance().logEvent(new Event("Saved grocery list data to JSON."));
        JSONObject json = log.toJson();
        saveToFile(json.toString(TAB));
    }

}
