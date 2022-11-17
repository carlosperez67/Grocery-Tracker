package ui;


import model.Budget;
import model.ListOfGroceries;
import model.Money;
import persistance.JsonReaderBudget;
import persistance.JsonReaderGrocery;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

// class that creates a gui splash page before the main application
public class GuiSplashPage extends JFrame {

    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;

    private static final String JSON_STORE_G = "./data/groceries.json";
    private static final String JSON_STORE_B = "./data/budget.json";

    // Grocery App related objects
    private Budget budget;
    private ListOfGroceries groceries;
    private JsonReaderGrocery jsonReaderGrocery;
    private JsonReaderBudget jsonReaderBudget;
    private JDialog dialog;

    // New Things I NEED
    private JPanel controlPanel;
    private JTextField textField;

// Constructor:
    public GuiSplashPage() {
        super("Gui Splash Page");
        initializeFields();
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        centreOnScreen();
        createControlPanel();
        //createBudgetPanel();
        setVisible(true);

    }

    // Effects: Creates control panel
    public void createControlPanel() {
        controlPanel = new JPanel();
        addButtonPanel();
        try {
            createImage();
        } catch (IOException e) {
            System.out.println("can't create picture");
        }

        setContentPane(controlPanel);
    }

    // Modifies: this
    // Effects: reads image from file path and adds it to the control panel
    public void createImage() throws IOException {
        BufferedImage groceryPicture = ImageIO.read(
                new File("data/images/groceries.jpg"));

        JLabel picLabel = new JLabel(new ImageIcon(groceryPicture));
        controlPanel.add(picLabel);
    }

    // Modifies: this
    // Effects: creates the gui that allows user to create a new budget
    private void addBudgetDialog() {
        dialog = new JDialog(this, true);

        // create panel at the bottom
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Enter Monthly Budget");
        textField = new JTextField(20);
        textField.setToolTipText("Format: 'dollars.cents' where dollars and cents are integers");
        JButton done = new JButton(new DoneAction());
        panel.add(label); // Components Added using Flow Layout
        panel.add(textField);
        panel.add(done);

        // Text Area at the Center
        JTextArea textArea = new JTextArea("Welcome to your new Grocery Tracker! Please input your grocery budget"
                + " for each month so I can help you manage your grocery spending.");
        textArea.setEditable(false);

        dialog.add(BorderLayout.SOUTH, panel);
        dialog.add(BorderLayout.CENTER, textArea);
        dialog.pack();

        centreOnScreen(dialog);
        dialog.setVisible(true);
    }


    // Modifies: this
    // Effects: Creates a button panel gui
    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1));
        buttonPanel.add(new JButton(new GuiSplashPage.LoadAction()));
        buttonPanel.add(new JButton(new GuiSplashPage.NewTrackerAction()));

        controlPanel.add(buttonPanel, BorderLayout.WEST);
    }


    // MODIFIES: this
    // EFFECTS:  creates new ListOfGroceries and sets Budget to null.
    private void initializeFields() {
        groceries = new ListOfGroceries();
        budget = null;
        Date todayDate = new Date();

        jsonReaderGrocery = new JsonReaderGrocery(JSON_STORE_G);
        jsonReaderBudget = new JsonReaderBudget(JSON_STORE_B);
    }


    /* Below ALL taken from AlarmSystem */

    /**
     * Represents action to be taken when user wants to load from a saved grocery tracker
     */
    private class LoadAction extends AbstractAction {

        LoadAction() {
            super("Load Existing Grocery Tracker");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            loadLOG();
            loadBudget();
            setVisible(false);
            new GuiAlpha(groceries, budget);
        }
    }




    /**
     * Represents action to be taken when user is done adding a monthly budget
     */
    private class DoneAction extends AbstractAction {

        DoneAction() {
            super("Done");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            String textFieldValue = textField.getText();
            Money initialBudgetMoney = new Money(textFieldValue);
            budget = new Budget(initialBudgetMoney);
            dialog.setVisible(false);
            new GuiAlpha(groceries, budget);
        }
    }

    /**
     * Represents action to be taken when user wants to create a new grocery tracker
     */
    private class NewTrackerAction extends AbstractAction {

        NewTrackerAction() {
            super("Create a New Grocery Tracker");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            addBudgetDialog();
            setVisible(false);

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


    // Modifies: this
    // Effects: Centres this on screen
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    // Modifies: this
    // Effects: Centres given dialog on screen
    private void centreOnScreen(JDialog dialog) {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        dialog.setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    //getter
    public Budget getBudget() {
        return budget;
    }

    //getter
    public ListOfGroceries getGroceries() {
        return groceries;
    }

}

