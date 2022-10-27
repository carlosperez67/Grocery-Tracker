package persistance;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Class testing the JSON reader. for budget
// Taken from JsonSerializationDemo
public class JsonReaderBudgetTest extends JsonTest{

    @Test
    void testReaderNonExistentFile() {
        JsonReaderBudget reader = new JsonReaderBudget("./data/noSuchFile.json");
        try {
            Budget budget = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    // Budget Cannot Possibly be empty
//    @Test
//    void testReaderEmptyBudget() {
//        JsonReaderBudget reader = new JsonReaderBudget("./data/testReaderEmptyBudget.json");
//        try {
//            Budget budget = reader.read();
//        } catch (IOException e) {
//            fail("Couldn't read from file");
//        }
//    }

    @Test
    void testReaderGeneralBudget() {
        JsonReaderBudget reader = new JsonReaderBudget("./data/testReaderGeneralBudget.json");
        try {
            Budget budget = reader.read();
            checkBudget(10000, 0, 10000, budget);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
