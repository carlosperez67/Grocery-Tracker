package persistance;

import model.Budget;
import model.ListOfGroceries;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Represents a writer that writes JSON representation of ListOfGroceries to file
// Taken with much inspiration from JsonSerializationDemo
public class JsonWriterGrocery extends JsonWriter {

    // EFFECTS: constructs writer to write to destination file
    public JsonWriterGrocery(String destination) {
        super(destination);
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of ListOfGroceries to file
    public void write(ListOfGroceries log) {
        JSONObject json = log.toJson();
        saveToFile(json.toString(TAB));
    }

}
