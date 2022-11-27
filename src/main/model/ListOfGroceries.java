package model;

import eventlog.Event;
import eventlog.EventLog;
import org.json.JSONArray;
import org.json.JSONObject;
import persistance.Writable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// a list of the groceries at home
public class ListOfGroceries implements Writable {
    private List<GroceryItem> listOfGroceries;

    //Modifies: this
    // Effects: creates new empty list of groceries
    public ListOfGroceries() {
        listOfGroceries = new ArrayList<>();
    }

    //getter
    public List<GroceryItem> getListOfGroceries() {
        return listOfGroceries;
    }

    //Requires: list of groceries is not empty
    //
    // Modifies:
    // Effects: returns list<string> of the grocery labels
    public List<String> getListOfGroceryLabels() {
        List<String> loString = new ArrayList<>();
        for (GroceryItem g : listOfGroceries) {
            loString.add(g.getLabel());
        }
        EventLog.getInstance().logEvent(new Event("Displayed labels of all items."));
        return loString;
    }

    //Requires: list of groceries is not empty
    //          - ltn is StoringMethod (enum)
    // Modifies:
    // Effects: returns list of the grocery labels that are in a certain location
    public List<String> getListOfGroceryLabels(StoringMethod ltn) {
        List<String> loString = new ArrayList<>();
        for (GroceryItem g : listOfGroceries) {
            if (g.getStoringMethod().equals(ltn)) {
                loString.add(g.getLabel());
            }
        }
        EventLog.getInstance().logEvent(new Event("Displayed labels of items located in "
                + ltn.toString() + "."));
        return loString;
    }


    // Effects: returns the size of list
    public int getSizeLoG() {
        return listOfGroceries.size();
    }

    // Requires: cannot have more than one item with same label
    // Modifies: this
    // Effects: adds grocery to the grocery list
    public void addGrocery(GroceryItem g) {
        listOfGroceries.add(g);
        EventLog.getInstance().logEvent(new Event("Added " + g.getLabel()
                + " to " + g.getStoringMethod().toString() + "."));

    }

    // Requires: specified item is already in the list
    // Modifies: this
    // Effects: removes certain grocery item from the list with the given label.
    public void removeGrocery(String label) {
        for (GroceryItem g : listOfGroceries) {
            if (Objects.equals(label, g.getLabel())) {
                listOfGroceries.remove(g);
                break;
            }
        }
    }

    // Requires: specified item is already in the list
    // Modifies:
    // Effects: returns grocery item
    public GroceryItem findGrocery(String label) {
        for (GroceryItem g : listOfGroceries) {
            if (Objects.equals(label, g.getLabel())) {
                return g;
            }
        }
        return null;
    }


    //Taken from JsonSerializationDemo
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("groceries", groceriesToJson());
        return json;
    }

    // EFFECTS: returns groceries in this groceryList as a JSON array
    private JSONArray groceriesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (GroceryItem g : listOfGroceries) {
            jsonArray.put(g.toJson());
        }

        return jsonArray;
    }


}
