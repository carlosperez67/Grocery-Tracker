package persistance;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Class testing the JSON reader. for groceries
// Taken from JsonSerializationDemo
public class JsonReaderGroceryTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReaderGrocery reader = new JsonReaderGrocery("./data/noSuchFile.json");
        try {
            ListOfGroceries listOfGroceries = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyLOG() {
        JsonReaderGrocery reader = new JsonReaderGrocery("./data/testReaderEmptyLOG.json");
        try {
            ListOfGroceries listOfGroceries = reader.read();
            assertEquals(0, listOfGroceries.getSizeLoG());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralLOG() {
        JsonReaderGrocery reader = new JsonReaderGrocery("./data/testReaderGeneralLOG.json");
        try {
            ListOfGroceries listOfGroceries = reader.read();
            List<GroceryItem> log = listOfGroceries.getListOfGroceries();
            assertEquals(2, log.size());
            checkGroceryItem("beans", 100, 2, (NonPerishable) log.get(0)); // for nonperishable
            checkGroceryItem("milk", 399, 12, StoringMethod.fridge, new Date(2022, 10, 2), (Perishable) log.get(1)); // for perishable
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }


}
