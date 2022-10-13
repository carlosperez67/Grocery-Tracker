package ui;

import model.Budget;
import model.GroceryItem;
import model.NonPerishable;
import model.Perishable;
import model.StoringMethod;
import model.ListOfGroceries;
import model.Money;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

// Grocery Budget Tracker Application
public class GroceryApp {
    private Budget budget;
    private ListOfGroceries listOfGroceriesG;
    private Scanner input;
    private Date todaysDate;

    //Effects: runs the Grocery application
    public GroceryApp() {
        runGroceryApp();
    }

    //modifies: this
    // effects: processes user input and commits actions
    private void runGroceryApp() {
        boolean keepGoing = true;
        String command;
        initA();

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
    private void displayWelcome() {
        System.out.println("\nWelcome to your grocery tracker!");
        System.out.println("Press q at anytime to quit");
        System.out.println("\nWhat is your monthly groceries budget?");
        System.out.println("\tUse format dollars.cents");
        System.out.println("\tExample: 1.00");

    }

    // EFFECTS: displays menu of options to user and says today's date
    private void displayMenu() {
        System.out.println("\nToday is " + todaysDate);
        System.out.println("\nSelect from:");
        System.out.println("\tadd -> add grocery item");
        System.out.println("\tremove -> remove grocery item");
        System.out.println("\tgroceries -> get list of grocery items bought");
        System.out.println("\tuse -> use a serving of grocery item");
        System.out.println("\texpiry -> check if an item is expired");
        System.out.println("\tremaining -> get budget remaining");
        System.out.println("\tspent -> get amount spent");
        System.out.println("\tdate -> change current date");

        System.out.println("\tq -> quit");
    }


    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("add")) {
            doAdd();
        } else if (command.equals("remove")) {
            doRemove();
        } else if (command.equals("use")) {
            doUse();
        } else if (command.equals("expiry")) {
            doExpiry();
        } else if (command.equals("remaining")) {
            getRemaining();
        } else if (command.equals("date")) {
            setDate();
        } else if (command.equals("spent")) {
            getSpent();
        } else if (command.equals("groceries")) {
            getList();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    //Modifies: GroceryList, GroceryItem (Perishable or NonPerishable), Money, Budget
    // Effects: Makes a grocery item and adds it to the list with appropriate specifications
    private void doAdd() {
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
    private void addNonPerishable() {
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
            listOfGroceriesG.addGrocery(newNP);
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
    private void addPerishable() {
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
            listOfGroceriesG.addGrocery(newP);
            budget.spendBudget(moneyUsed);
            System.out.println("Bought and added " + newP.getLabel() + " to the " + storingMethod.name());
        }
    }

    //Requires: Label must exist already in the list
    //Modifies: GroceryList, GroceryItem (Perishable or NonPerishable)
    // Effects: Find a grocery item and removes it from the list
    private void doRemove() {
        String selection = "";
        while (selection.equals("")) {
            System.out.println("What is the label of the item you would like to remove?");
            selection = input.next();
            selection = selection.toLowerCase();
            listOfGroceriesG.removeGrocery(selection);
            System.out.println("Removed " + selection);
        }
    }


    //Effects: Waits for user command and returns the type of grocery item they chose
    private String selectType() {
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
    private void getList() {
        String selection = "";
        String filter = "";
        while (selection.equals("")) {
            System.out.println("Do you want to filter by storage location?");
            System.out.println("\t y or n");
            selection = input.next().toLowerCase();
            if (selection.equals("n")) {
                printLabelList(listOfGroceriesG.getListOfGroceryLabels());
            } else {
                while (filter.equals("")) {
                    System.out.println("Which location?");
                    System.out.println("\t fridge(fg) or freezer(fz) or pantry(p)");
                    filter = input.next().toLowerCase();
                    if (filter.equals("fg") || filter.equals("fridge")) {
                        printLabelList(listOfGroceriesG.getListOfGroceryLabels(StoringMethod.fridge));
                    } else if (filter.equals("fz") || filter.equals("freezer")) {
                        printLabelList(listOfGroceriesG.getListOfGroceryLabels(StoringMethod.freezer));
                    } else {
                        printLabelList(listOfGroceriesG.getListOfGroceryLabels(StoringMethod.pantry));
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
    private void setDate() {
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
        todaysDate = new Date(year, month, day);
    }

    //Requires:
    //Modifies:
    // Effects: prints remaining budget unspent
    private void getRemaining() {
        System.out.println(budget.getAmtLeft().getAmtDollars());
    }

    //Requires:
    //Modifies:
    // Effects: prints amount of budget spent
    private void getSpent() {
        System.out.println(budget.getAmtSpent().getAmtDollars());
    }

    //Requires: Label must exist already in the list
    //Modifies: GroceryItem (Perishable or NonPerishable)
    // Effects: Find a grocery item and uses a certain number of servings
    private void doExpiry() {
        String selection = "";
        while (selection.equals("")) {
            System.out.println("What is the label of the item you would like check?");
            selection = input.next();
            selection = selection.toLowerCase();
            GroceryItem g1 = listOfGroceriesG.findGrocery(selection);

            Money dummyMoney = new Money(1);
            NonPerishable dummyNP = new NonPerishable("dummy", dummyMoney, 12);

            if (g1.getClass() == dummyNP.getClass()) {
                System.out.println("This is non perishable!");
            } else {
                Perishable g2 = (Perishable) g1;
                System.out.println(g2.daysUntilExpired(todaysDate) + " days until expired.");
            }
        }
    }

    //Requires: Label must exist already in the list
    //Modifies: GroceryItem (Perishable or NonPerishable)
    // Effects: Find a grocery item and uses a certain number of servings
    private void doUse() {
        String servings = "";
        String selection = "";
        while (selection.equals("")) {
            System.out.println("What is the label of the item you would like to use?");
            selection = input.next();
            selection = selection.toLowerCase();
            GroceryItem g = listOfGroceriesG.findGrocery(selection);

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
    private void initA() {
        listOfGroceriesG = new ListOfGroceries();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        todaysDate = new Date(2022, Calendar.OCTOBER, 12);
    }

    //modifies: GroceryItem, Budget, Money
    // effects: initializes objects
    private void initB(String command) {
        Money initialBudgetM = new Money(command);
        budget = new Budget(initialBudgetM);
        System.out.println("Your monthly budget is set to be: $" + command);
    }

    private void printLabelList(List<String> los) {
        if (los.isEmpty()) {
            System.out.println("Nothing here :(");
        } else {
            for (String s : los) {
                System.out.println(s);
            }
        }
    }
}


