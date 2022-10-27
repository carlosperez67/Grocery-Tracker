package persistance;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import static org.junit.jupiter.api.Assertions.fail;

// testing json budget writer
// taken from json serialization demo
public class JsonWriterBudgetTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            Budget b = new Budget(new Money(10000));
            b.setAmtLeft(10000);
            b.setAmtSpent(0);
            JsonWriterBudget writer = new JsonWriterBudget("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterGeneralLOG() {
        try {
            Budget b = new Budget(new Money(10000));

            JsonWriterBudget writer = new JsonWriterBudget("./data/testWriterGeneralBudget.json");
            writer.open();
            writer.write(b);
            writer.close();

            JsonReaderBudget reader = new JsonReaderBudget("./data/testWriterGeneralBudget.json");
            b = reader.read();
            checkBudget(10000, 0, 10000, b);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
