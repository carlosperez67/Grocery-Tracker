package ui;

import model.*;
import persistance.JsonReaderBudget;
import persistance.JsonReaderGrocery;
import persistance.JsonWriterBudget;
import persistance.JsonWriterGrocery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class GuiAlpha extends JFrame {

    //Constants
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;
    private static final String JSON_STORE_G = "./data/groceries.json";
    private static final String JSON_STORE_B = "./data/budget.json";

    // For Persistence
    private JsonWriterGrocery jsonWriterGrocery;
    private JsonReaderGrocery jsonReaderGrocery;
    private JsonWriterBudget jsonWriterBudget;
    private JsonReaderBudget jsonReaderBudget;

    // Grocery App Objects
    private ListOfGroceries groceries;
    private Budget budget;

    // Java Swing Objects
    private JDialog dialog;
    private JTextArea textArea;

    // Text Fields
    private JTextField textFieldA;
    private JTextField textFieldB;
    private JTextField textFieldC;
    private JTextField textFieldD;
    private JTextField textFieldE;
    private JTextField textFieldF;
    private JTextField textFieldG;


    // Constructor
    public GuiAlpha(ListOfGroceries groceries, Budget budget) {
        super("Grocery App");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initializeFields(groceries, budget);

        addButtonPanel();
        centreOnScreen();
        setVisible(true);
    }


    // Modifies: this
    // Effects: Initializes fields
    public void initializeFields(ListOfGroceries groceries, Budget budget) {

        if (groceries == null) {
            this.groceries = new ListOfGroceries();
        } else {
            this.groceries = groceries;
        }

        this.budget = budget;
        Date date = new Date();

        jsonWriterGrocery = new JsonWriterGrocery(JSON_STORE_G);
        jsonReaderGrocery = new JsonReaderGrocery(JSON_STORE_G);
        jsonWriterBudget = new JsonWriterBudget(JSON_STORE_B);
        jsonReaderBudget = new JsonReaderBudget(JSON_STORE_B);

        init();
    }

    // Effects initializing text fields
    public void init() {
        textFieldA = new JTextField(20);
        textFieldB = new JTextField(20);
        textFieldC = new JTextField(20);
        textFieldD = new JTextField(20);
        textFieldE = new JTextField(20);
        textFieldF = new JTextField(20);
        textFieldG = new JTextField(20);
    }


    // Modifies: This
    // Effects: Adds button panel
    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1));
        buttonPanel.add(new JButton(new GuiAlpha.SaveAction()));
        buttonPanel.add(new JButton(new GuiAlpha.LoadAction()));
        buttonPanel.add(new JButton(new GuiAlpha.AddAction()));
        buttonPanel.add(new JButton(new GuiAlpha.ShowAction()));

        this.add(buttonPanel, BorderLayout.WEST);
    }

    // Class to Remove something from the grocery list
    private class ShowAction extends AbstractAction {

        ShowAction() {
            super("Display Grocery Items");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            chooseDisplay();
        }
    }

    private void chooseDisplay() {
        dialog = new JDialog(this, true);

        JPanel panel = new JPanel();


        JButton showAll = new JButton(new GuiAlpha.ShowAllAction());
        JButton showPantry = new JButton(new GuiAlpha.ShowPantryAction());
        JButton showFridge = new JButton(new GuiAlpha.ShowFridgeAction());
        JButton showFreezer = new JButton(new GuiAlpha.ShowFreezerAction());


        panel.add(showAll);
        panel.add(showPantry);
        panel.add(showFridge);
        panel.add(showFreezer);

        dialog.add(panel);
        dialog.pack();
        centreOnScreen(dialog);
        dialog.setVisible(true);


    }

    /**
     * Represents action to be taken when user wants to add a grocery item to the grocery tracker
     */
    private class ShowAllAction extends AbstractAction {

        ShowAllAction() {
            super("Display all Grocery Items");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            displayGroceries(groceries.getListOfGroceryLabels());
        }
    }

    public void displayGroceries(List<String> groceryLabelList) {
        dialog = new JDialog(this, true);

        if (groceryLabelList.isEmpty()) {
            textArea = new JTextArea("No groceries");
            textArea.setEditable(false);

        } else {
            //List<String> groceryLabelList = groceries.getListOfGroceryLabels();
            StringBuilder list = new StringBuilder();
            for (String label : groceryLabelList) {
                list.append(", ").append(label);
            }
            textArea = new JTextArea(list.toString());
            textArea.setEditable(false);
        }
        dialog.add(textArea);
        dialog.pack();
        centreOnScreen(dialog);
        dialog.setVisible(true);

    }


    /**
     * Represents action to be taken when user wants to add a grocery item to the grocery tracker
     */
    private class ShowPantryAction extends AbstractAction {

        ShowPantryAction() {
            super("Display all groceries in pantry");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            displayGroceries(groceries.getListOfGroceryLabels(StoringMethod.pantry));
        }
    }

    /**
     * Represents action to be taken when user wants to add a grocery item to the grocery tracker
     */
    private class ShowFridgeAction extends AbstractAction {

        ShowFridgeAction() {
            super("Display all groceries in fridge");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            displayGroceries(groceries.getListOfGroceryLabels(StoringMethod.fridge));
        }
    }

    /**
     * Represents action to be taken when user wants to add a grocery item to the grocery tracker
     */
    private class ShowFreezerAction extends AbstractAction {

        ShowFreezerAction() {
            super("Display all groceries in freezer");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            displayGroceries(groceries.getListOfGroceryLabels(StoringMethod.freezer));
        }
    }

    /**
     * Represents action to be taken when user wants to add a grocery item to the grocery tracker
     */
    private class AddAction extends AbstractAction {

        AddAction() {
            super("Add new grocery item");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            doAdd();
        }
    }

    // Requires: The user input to be in correct format
    // Modifies: this
    // Effects: adds given user grocery item to groceries
    private void doAdd() {
        chooseP();
    }

    //Modifies:
    //Effects: Creates Gui so user can choose between perishable and non-perishable
    private void chooseP() {
        dialog = new JDialog(this, true);
        JPanel panel = new JPanel();


        panel.add(new JButton(new PerishableAction()));
        panel.add(new JButton(new NonPerishableAction()));

        JTextArea label = new JTextArea("Are you adding a Perishable or Non-Perishable grocery item?");

        label.setEditable(false);

        dialog.add(BorderLayout.SOUTH, panel);
        dialog.add(BorderLayout.CENTER, label);
        dialog.pack();

        centreOnScreen(dialog);
        dialog.setVisible(true);
    }


    // modifies: this
    // Effects: Create gui to create new perishable item
    public void perishableGui() {
        dialog = new JDialog(this, true);
        JPanel panel = new JPanel(new GridLayout(8, 2));

        panel = createBiggerPanelHelper(panel);

        JButton doneP = new JButton(new GuiAlpha.DoneActionP());
        panel.add(doneP);
        dialog.add(panel);
        dialog.pack();
        dialog.setVisible(true);
        centreOnScreen(dialog);

    }

    // Effects: helper to create gui
    public JPanel createBiggerPanelHelper(JPanel pl) {
        JPanel panelLabel = new JPanel();
        JPanel panelPrice = new JPanel();
        JPanel panelServings = new JPanel();
        JPanel panelStoringMethod = new JPanel();
        JPanel panelYear = new JPanel();
        JPanel panelMonth = new JPanel();
        JPanel panelDay = new JPanel();

        pl.add(createLittlePanelHelper(textFieldA, panelLabel, "What is the label of this perishable item?",
                "Alphanumeric characters only"));
        pl.add(createLittlePanelHelper(textFieldB, panelPrice, "What is the price of this perishable item?",
                "Dollar.Cent format. (integers only)"));
        pl.add(createLittlePanelHelper(textFieldC, panelServings, "How many servings in this perishable item?",
                "integer greater than 0"));
        pl.add(createLittlePanelHelper(textFieldD, panelStoringMethod, "Where are you storing this perishable item?",
                "One of: fridge, freezer, pantry"));
        pl.add(createLittlePanelHelper(textFieldE, panelYear, "What is the expiry year of this perishable item?",
                "Dollar.Cent format. (integers only)"));
        pl.add(createLittlePanelHelper(textFieldF, panelMonth, "What is the expiry month of this perishable item?",
                "Dollar.Cent format. (integers only)"));
        pl.add(createLittlePanelHelper(textFieldG, panelDay, "What is the expiry day of this perishable item?",
                "Dollar.Cent format. (integers only)"));
        return pl;
    }

    // Effects: helper to create gui
    public JPanel createLittlePanelHelper(JTextField textFieldHelper, JPanel panel, String button, String toolTip) {
        panel.add(new Button(button));
        textFieldHelper.setToolTipText(toolTip);
        panel.add(textFieldHelper);

        return panel;
    }

    /**
     * Represents action to be taken when user wants to add a perishable grocery item to the grocery tracker
     */
    private class PerishableAction extends AbstractAction {

        PerishableAction() {
            super("Perishable");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            perishableGui();
        }
    }

    /**
     * Represents action to be taken when user is done inputting values for perishable item
     */
    private class DoneActionP extends AbstractAction {

        DoneActionP() {
            super("Done");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String textFieldValueA = textFieldA.getText();
            String textFieldValueB = textFieldB.getText();
            String textFieldValueC = textFieldC.getText();
            String textFieldValueD = textFieldD.getText();
            String textFieldValueE = textFieldE.getText();
            String textFieldValueF = textFieldF.getText();
            String textFieldValueG = textFieldG.getText();

            int servings = Integer.parseInt(textFieldValueC);
            StoringMethod storingMethod = StoringMethod.valueOf(textFieldValueD);
            Money moneyUsed = new Money(textFieldValueB);

            Perishable newP = new Perishable(textFieldValueA, moneyUsed, servings, storingMethod,
                    new Date(Integer.parseInt(textFieldValueE), Integer.parseInt(textFieldValueF) - 1,
                            Integer.parseInt(textFieldValueG)));

            groceries.addGrocery(newP);
            dialog.setVisible(false);
            textArea = new JTextArea("Added " + textFieldValueA + " to the list of groceries!");
            add(textArea);
            textArea.setEditable(false);
        }
    }

    /**
     * Represents action to be taken when user is done inputting values for perishable item
     */
    private class DoneActionNp extends AbstractAction {

        DoneActionNp() {
            super("Done");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String textFieldValueA = textFieldA.getText();
            String textFieldValueB = textFieldB.getText();
            String textFieldValueC = textFieldC.getText();

            int servings = Integer.parseInt(textFieldValueC);
            Money moneyUsed = new Money(textFieldValueB);

            NonPerishable newNp = new NonPerishable(textFieldValueA, moneyUsed, servings);

            groceries.addGrocery(newNp);
            dialog.setVisible(false);
            textArea = new JTextArea("Added " + textFieldValueA + " to the list of groceries!");
            add(textArea);
            textArea.setEditable(false);

            resetTextFields();
        }
    }

    public void resetTextFields() {
        textFieldA.setText("");
        textFieldB.setText("");
        textFieldC.setText("");
        textFieldD.setText("");
        textFieldE.setText("");
        textFieldF.setText("");
        textFieldG.setText("");
    }

    /**
     * Represents action to be taken when user wants to add a non-perishable grocery item to the grocery tracker
     */
    private class NonPerishableAction extends AbstractAction {

        NonPerishableAction() {
            super("Non Perishable");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            nonPerishableGui();
        }
    }

    // Create gui to create non perishable
    private void nonPerishableGui() {
        dialog = new JDialog(this, true);
        JPanel panel = new JPanel(new GridLayout(4, 2));

        JPanel panelLabel = new JPanel();
        JPanel panelPrice = new JPanel();
        JPanel panelServings = new JPanel();

        panel.add(createLittlePanelHelper(textFieldA, panelLabel, "What is the label of this perishable item?",
                "Alphanumeric characters only"));
        panel.add(createLittlePanelHelper(textFieldB, panelPrice, "What is the price of this perishable item?",
                "Dollar.Cent format. (integers only)"));
        panel.add(createLittlePanelHelper(textFieldC, panelServings, "How many servings in this perishable item?",
                "integer greater than 0"));

        JButton doneNp = new JButton(new DoneActionNp());
        panel.add(doneNp);

        dialog.add(panel);
        dialog.pack();
        dialog.setVisible(true);
        centreOnScreen(dialog);
    }

    /**
     * Represents action to be taken when user wants to save the grocery tracker
     */
    private class SaveAction extends AbstractAction {

        SaveAction() {
            super("Save Grocery Tracker");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            textArea = new JTextArea("Saved Grocery Tracker to memory!");
            add(textArea);
            textArea.setEditable(false);
            saveBudget();
            saveLOG();
        }
    }

    /**
     * Represents action to be taken when user wants to load from a saved grocery tracker
     */
    private class LoadAction extends AbstractAction {

        LoadAction() {
            super("Load Existing Grocery Tracker");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            textArea = new JTextArea("Loaded Saved Grocery Tracker from memory!");
            add(textArea);
            textArea.setEditable(false);

            loadLOG();
            loadBudget();

            add(textArea);
        }
    }


    // HELPER FUNCTIONS -------------------------------------------------------------------------

    // EFFECTS: saves the list of groceries to file
    private void saveLOG() {
        try {
            jsonWriterGrocery.open();
            jsonWriterGrocery.write(groceries);
            jsonWriterGrocery.close();
            System.out.println("Saved groceries to " + JSON_STORE_G);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_G);
        }
    }

    // EFFECTS: saves the budget to file
    private void saveBudget() {
        try {
            jsonWriterBudget.open();
            jsonWriterBudget.write(budget);
            jsonWriterBudget.close();
            System.out.println("Saved budget to " + JSON_STORE_B);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_B);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads ListOfGroceries from file
    private void loadLOG() {
        try {
            groceries = jsonReaderGrocery.read();
            System.out.println("Loaded groceries from " + JSON_STORE_G);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_G);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads Budget from file
    private void loadBudget() {
        try {
            this.budget = jsonReaderBudget.read();
            System.out.println("Loaded budget from " + JSON_STORE_B);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_B);
        }
    }

    /**
     * Helper to centre main application window on desktop
     */
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    /**
     * Helper to centre dialog frame
     */
    private void centreOnScreen(JDialog dialog) {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        dialog.setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

//    // starts the application
//    public static void main(String[] args) {
//        new GuiAlpha(null, new Budget(new Money("100.00")));
//    }
}
