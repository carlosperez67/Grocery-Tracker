package ui;

import model.Budget;
import model.GroceryItem;
import model.NonPerishable;
import model.Perishable;
import model.StoringMethod;
import model.ListOfGroceries;
import model.Money;
import persistance.JsonReaderGrocery;
import persistance.JsonWriterGrocery;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

// Grocery Budget Tracker Application
public class GroceryApp {
    // fields taken with inspiration from JsonSerializationDemo
    private static final String JSON_STORE = "./data/groceries.json";
    private Budget budget;
    private ListOfGroceries loG;
    private Scanner input;
    private Date todayDate;
    private JsonWriterGrocery jsonWriterGrocery;
    private JsonReaderGrocery jsonReaderGrocery;

    //Effects: runs the Grocery application
    public GroceryApp() {
        runGroceryApp();
    }

    //modifies: this
    // effects: processes user input and commits actions
    public void runGroceryApp() {
        boolean keepGoing = true;
        String command;
        initA();
    // from TellerApp
        while (keepGoing) {
            displayWelcome();
            command = input.next().toLowerCase();
            if (command.equals("q")) {
                keepGoing = false;
            } else {
                initB(command);
                while (keepGoing) {
                    displayMenu();
                    command = input.next().toLowerCase();
                    if (command.equals("q")) {
                        keepGoing = false;
                    } else {
                        processCommand(command);
                    }
                }
            }
        }

        System.out.println("\nGoodbye!");
    }

    //effects: displays screen asking for monthly budget
    public void displayWelcome() {
        System.out.println("\nWelcome to your grocery tracker!");
        System.out.println("Press q at anytime to quit");
        System.out.println("\nWhat is your monthly groceries budget?");
        System.out.println("\tUse format dollars.cents");
        System.out.println("\tExample: 1.00");

    }

    // EFFECTS: displays menu of options to user and says today's date
    public void displayMenu() {
        System.out.println("\nToday is " + todayDate);
        System.out.println("\nSelect from:");
        System.out.println("\tadd -> add grocery item");
        System.out.println("\tremove -> remove grocery item");
        System.out.println("\tgroceries -> get list of grocery items bought");
        System.out.println("\tuse -> use a serving of grocery item");
        System.out.println("\texpiry -> check if an item is expired");
        System.out.println("\tremaining -> get budget remaining");
        System.out.println("\tspent -> get amount spent");
        System.out.println("\tdate -> change current date");
        System.out.println("\tsave -> save current groceries and budget");
        System.out.println("\tload -> load past groceries and budget");

        System.out.println("\tq -> quit");
    }


    // MODIFIES: this
    // EFFECTS: processes user command
    @SuppressWarnings("checkstyle:MethodLength")
    public void processCommand(String command) {
        switch (command) {
            case "add":
                doAdd();
                break;
            case "remove":
                doRemove();
                break;
            case "use":
                doUse();
                break;
            case "expiry":
                doExpiry();
                break;
            case "remaining":
                getRemaining();
                break;
            case "date":
                setDate();
                break;
            case "spent":
                getSpent();
                break;
            case "groceries":
                getList();
                break;
            case "save":
                saveLOG();
                saveBudget();
                break;
            case "load":
                loadLOG();
                break;
            default:
                System.out.println("Selection not valid...");
                break;
        }
    }

    // EFFECTS: saves the list of groceries to file
    private void saveLOG() {
        try {
            jsonWriterGrocery.open();
            jsonWriterGrocery.write(loG);
            jsonWriterGrocery.close();
            System.out.println("Saved groceries to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // EFFECTS: saves the budget to file
    private void saveBudget() {
        try {
            jsonWriterGrocery.open();
            jsonWriterGrocery.write(budget);
            jsonWriterGrocery.close();
            System.out.println("Saved budget to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadLOG() {
        try {
            loG = jsonReaderGrocery.read();
            System.out.println("Loaded groceries from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }







    //Modifies: GroceryList, GroceryItem (Perishable or NonPerishable), Money, Budget
    // Effects: Makes a grocery item and adds it to the list with appropriate specifications
    public void doAdd() {
        if (selectType().equals("np")) {
            addNonPerishable();
        } else {
            addPerishable();
        }
    }

    // Requires: the input is formatted exactly as asked
    //            - Comma separated, no space
    //            - PriceInDollars must be in format Dollars.xx where "
    //                    + "is the amount of cents. For 0 cents put 00.
    //            - PriceInDollars represents a value >0
    //Modifies: GroceryList, GroceryItem (NonPerishable), Money, Budget
    // Effects: Makes a non-perishable grocery item and adds it to the list with appropriate specifications
    //           -spends the money from the budget
    public void addNonPerishable() {
        String selection = "";
        // similar code to TellerApp. attributed to TellerApp
        while (selection.equals("")) {
            System.out.println("Adding a Non Perishable food item.");
            System.out.println("Please give a Label,PriceInDollars,NumberOfServings");
            System.out.println("\t Note: Comma separated, no space");
            System.out.println("PriceInDollars must be in format Dollars.xx where "
                    + "is the amount of cents. For 0 cents put 00.");
            selection = input.next().toLowerCase();

            String[] splitValue = selection.split(",");
            String label = splitValue[0];
            int servings = Integer.parseInt(splitValue[2]);

            Money moneyUsed = new Money(splitValue[1]);

            NonPerishable newNP = new NonPerishable(label, moneyUsed, servings);
            loG.addGrocery(newNP);
            budget.spendBudget(moneyUsed);
            System.out.println("Bought and added " + label + " to the pantry!");
        }
    }

    // Requires: the input is formatted exactly as asked
    //            - Comma separated, no space
    //            - PriceInDollars must be in format Dollars.xx where "
    //                    + "is the amount of cents. For 0 cents put 00.
    //            - PriceInDollars represents a value >0
    //Modifies: GroceryList, GroceryItem (Perishable), Money, Budget
    // Effects: Makes a perishable grocery item and adds it to the list with appropriate specifications
    //           -spends the money from the budget
    public void addPerishable() {
        String selection = "";
        // similar code to TellerApp. attributed to TellerApp
        while (selection.equals("")) {
            System.out.println("Adding a Perishable food item.");
            System.out.println("Please give a Label,PriceInDollars,NumberOfServings,StoringMethod,Year,Month,Day");
            System.out.println("\t Note: Comma separated, no space");
            System.out.println("PriceInDollars must be in format Dollars.xx where "
                    + "is the amount of cents. For 0 cents put 00.");
            System.out.println("\t StoringMethod is one of fridge,freezer,pantry");
            System.out.println("\t Year,Month,Day are all integer values that represent the expiry date");
            selection = input.next();
            selection = selection.toLowerCase();

            String[] splitValue = selection.split(",");
            String label = splitValue[0];
            int servings = Integer.parseInt(splitValue[2]);
            StoringMethod storingMethod = StoringMethod.valueOf(splitValue[3]);

            Money moneyUsed = new Money(splitValue[1]);

            Perishable newP = new Perishable(label, moneyUsed, servings, storingMethod,
                    new Date(Integer.parseInt(splitValue[4]), Integer.parseInt(splitValue[5]) - 1,
                            Integer.parseInt(splitValue[6])));
            loG.addGrocery(newP);
            budget.spendBudget(moneyUsed);
            System.out.println("Bought and added " + newP.getLabel() + " to the " + storingMethod.name());
        }
    }

    //Requires: Label must exist already in the list
    //Modifies: GroceryList, GroceryItem (Perishable or NonPerishable)
    // Effects: Find a grocery item and removes it from the list
    public void doRemove() {
        String selection = "";
        while (selection.equals("")) {
            System.out.println("What is the label of the item you would like to remove?");
            selection = input.next();
            selection = selection.toLowerCase();
            loG.removeGrocery(selection);
            System.out.println("Removed " + selection);
        }
    }


    //Effects: Waits for user command and returns the type of grocery item they chose
    public String selectType() {
        String selection = "";
        while (!(selection.equals("np") || selection.equals("p"))) {
            System.out.println("np for non perishable");
            System.out.println("p for perishable");
            selection = input.next();
            selection = selection.toLowerCase();
        }
        return selection;
    }


    // Requires:
    // Modifies:
    // Effects: prints out the list of labels in the list of grocery items with option of putting in filters based on
    //          location of items
    public void getList() {
        String selection = "";
        String filter = "";
        while (selection.equals("")) {
            System.out.println("Do you want to filter by storage location?");
            System.out.println("\t y or n");
            selection = input.next().toLowerCase();
            if (selection.equals("n")) {
                printLabelList(loG.getListOfGroceryLabels());
            } else {
                while (filter.equals("")) {
                    System.out.println("Which location?");
                    System.out.println("\t fridge(fg) or freezer(fz) or pantry(p)");
                    filter = input.next().toLowerCase();
                    if (filter.equals("fg") || filter.equals("fridge")) {
                        printLabelList(loG.getListOfGroceryLabels(StoringMethod.fridge));
                    } else if (filter.equals("fz") || filter.equals("freezer")) {
                        printLabelList(loG.getListOfGroceryLabels(StoringMethod.freezer));
                    } else {
                        printLabelList(loG.getListOfGroceryLabels(StoringMethod.pantry));
                    }
                }

            }
        }
    }


    //Requires: day is [1,31]
    //          month is [1,12]
    //          year is of length 4
    //Modifies: Date
    //Effects: refreshes "today's" date
    public void setDate() {
        String selection = "";
        int year = 0;
        int month = 0;
        int day = 0;
        while (selection.equals("")) {
            System.out.println("What is the date?");
            System.out.println("\t Format in YEAR/MM/DD");
            selection = input.next();
            selection = selection.toLowerCase();

            String[] splitValue = selection.split("/");
            year = Integer.parseInt(splitValue[0]);
            month = Integer.parseInt(splitValue[1]);
            day = Integer.parseInt(splitValue[2]);
        }
        // Java's setDate function to an existing date is very finicky, so I chose to create a new date object
        todayDate = new Date(year, month - 1, day);
    }

    //Requires:
    //Modifies:
    // Effects: prints remaining budget unspent
    public void getRemaining() {
        System.out.println(budget.getAmtLeft().getAmtDollars());
    }

    //Requires:
    //Modifies:
    // Effects: prints amount of budget spent
    public void getSpent() {
        System.out.println(budget.getAmtSpent().getAmtDollars());
    }

    //Requires: Label must exist already in the list
    //Modifies: GroceryItem (Perishable or NonPerishable)
    // Effects: Find a grocery item and uses a certain number of servings
    public void doExpiry() {
        String selection = "";
        while (selection.equals("")) {
            System.out.println("What is the label of the item you would like check?");
            selection = input.next();
            selection = selection.toLowerCase();
            GroceryItem g1 = loG.findGrocery(selection);

            Money dummyMoney = new Money(1);
            NonPerishable dummyNP = new NonPerishable("dummy", dummyMoney, 12);

            if (g1.getClass() == dummyNP.getClass()) {
                System.out.println("This is non perishable!");
            } else {
                Perishable g2 = (Perishable) g1;
                System.out.println(g2.daysUntilExpired(todayDate) + " days until expired.");
            }
        }
    }

    //Requires: Label must exist already in the list
    //Modifies: GroceryItem (Perishable or NonPerishable)
    // Effects: Find a grocery item and uses a certain number of servings
    public void doUse() {
        String servings = "";
        String selection = "";
        while (selection.equals("")) {
            System.out.println("What is the label of the item you would like to use?");
            selection = input.next();
            selection = selection.toLowerCase();
            GroceryItem g = loG.findGrocery(selection);

            while (servings.equals("")) {
                System.out.println("How many servings are you using? This item has "
                        + g.getServingsLeft() + " servings left");
                servings = input.next();
                servings = servings.toLowerCase();
                g.useMultipleServing(Integer.parseInt(servings));
                System.out.println("This item has " + g.getServingsLeft() + " servings left");
            }
        }
    }


    //modifies:
    // effects: initializes listOfGroceries
    // modified from TellerApp
    public void initA() {
        loG = new ListOfGroceries();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        todayDate = new Date(2022, 9, 25);

        jsonWriterGrocery = new JsonWriterGrocery(JSON_STORE);
        jsonReaderGrocery = new JsonReaderGrocery(JSON_STORE);
    }

    //modifies: GroceryItem, Budget, Money
    // effects: initializes objects
    public void initB(String command) {
        Money initialBudgetM = new Money(command);
        budget = new Budget(initialBudgetM);
        System.out.println("Your monthly budget is set to be: $" + command);
    }

    public void printLabelList(List<String> los) {
        if (los.isEmpty()) {
            System.out.println("Nothing here :(");
        } else {
            for (String s : los) {
                System.out.println(s);
            }
        }
    }
}


