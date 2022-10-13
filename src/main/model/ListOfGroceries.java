package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// a list of the groceries at home
public class ListOfGroceries {
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

    // Effects: returns the size of list
    public int getSizeLoG() {
        return listOfGroceries.size();
    }

    // Requires: cannot have more than one item with same label
    // Modifies: this
    // Effects: adds grocery to the home (can add same one multiple times)
    public void addGrocery(GroceryItem g) {
        listOfGroceries.add(g);
    }

    // Requires: specified item is already in the list
    // Modifies: this
    // Effects: removes certain grocery item from the list with the given label.
    //       - if there are two of the exact same label, it will
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
}
