package persistance;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Testing the JSonWriter for grocery
// Taken from json serialization demo
public class JsonWriterGroceryTest extends JsonTest{
    @Test
    void testWriterInvalidFile() {
        try {
            ListOfGroceries log = new ListOfGroceries();
            JsonWriterGrocery writer = new JsonWriterGrocery("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyLOG() {
        try {
            ListOfGroceries log = new ListOfGroceries();
            JsonWriterGrocery writer = new JsonWriterGrocery("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(log);
            writer.close();

            JsonReaderGrocery reader = new JsonReaderGrocery("./data/testWriterEmptyWorkroom.json");
            log = reader.read();
            assertEquals(0, log.getSizeLoG());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralLOG() {
        try {
            ListOfGroceries log = new ListOfGroceries();
            log.addGrocery(new NonPerishable("tuna", new Money("1.99"), 1));
            log.addGrocery(new Perishable("eggs", new Money(599), 12, StoringMethod.fridge,
                    new Date(2022, 10, 25)));

            JsonWriterGrocery writer = new JsonWriterGrocery("./data/testWriterGeneralLOG.json");
            writer.open();
            writer.write(log);
            writer.close();

            JsonReaderGrocery reader = new JsonReaderGrocery("./data/testWriterGeneralLOG.json");
            log = reader.read();
            List<GroceryItem> groceries = log.getListOfGroceries();
            assertEquals(2, groceries.size());
            checkGroceryItem("tuna", 199, 1, (NonPerishable) groceries.get(0));
            checkGroceryItem("eggs", 599, 12, StoringMethod.fridge,
                    new Date(2022, 10, 25), (Perishable) groceries.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }




}
