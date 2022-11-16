package ui;


import model.Budget;
import model.ListOfGroceries;
import persistance.JsonReaderBudget;
import persistance.JsonReaderGrocery;
import persistance.JsonWriterBudget;
import persistance.JsonWriterGrocery;
import ui.buttons.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;

public class GuiSplashPage extends JFrame implements ActionListener {

    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;

    private static final String JSON_STORE_G = "./data/groceries.json";
    private static final String JSON_STORE_B = "./data/budget.json";

    private Budget budget;
    private ListOfGroceries groceries;
    private Scanner input;
    private Date todayDate;
    private JsonWriterGrocery jsonWriterGrocery;
    private JsonReaderGrocery jsonReaderGrocery;
    private JsonWriterBudget jsonWriterBudget;
    private JsonReaderBudget jsonReaderBudget;

    private JDesktopPane desktop;
    private JFrame controlPanel;
    private JPanel budgetPanel;
    private JFrame frame;

    private JTextField textField;

    public GuiSplashPage() {
        initializeFields();

        frame = new JFrame();
        frame.setSize(WIDTH / 2, HEIGHT / 2);

        budgetPanel = new JPanel();
        budgetPanel.setLayout(new GridLayout(1, 1));

        // addTextBox();
//        budgetPanel.setVisible(true);
//
//        JPanel textPanel = new JPanel();
//        textField = new JTextField(20);
//        textField.setToolTipText("Format in Dollars.Cents");
//        textPanel.add(textField);
//        budgetPanel.add(textField);

        frame.add(budgetPanel);

        controlPanel = new JFrame();
        controlPanel.setLayout(new BorderLayout());
        addButtonPanel();
        controlPanel.pack();
        controlPanel.setVisible(true);
        frame.add(budgetPanel);

        //frame.setVisible(true);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        centreOnScreen(frame);

        // ------------------------------------------------

//        JPanel panel = new JPanel(new GridLayout(1,0));
//        panel.add(textField);
//
//
//
//        JFrame frame = new JFrame();
//        frame.setContentPane(panel);
////        desktop.add(textField);
//        //frame.add(textField, BorderLayout.CENTER);
//        frame.setSize(200,200);
//        frame.setVisible(true);
//        frame.pack();
//
//
//        controlPanel.add(textField);
//
//        //desktop.add(textField);
//        setContentPane(desktop);
//        setTitle("Options");
//        setSize(WIDTH, HEIGHT);
//
//        addButtonPanel();
//
//        controlPanel.pack();
//        controlPanel.setVisible(true);
//        desktop.add(controlPanel);
//
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        centreOnScreen();
//        setVisible(true);
    }

    /**
     * Helper to add control buttons.
     */
    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(2, 1));
        buttonPanel.add(new JButton(new GuiSplashPage.LoadAction()));
        buttonPanel.add(new JButton(new GuiSplashPage.NewTrackerAction()));

        controlPanel.add(buttonPanel, BorderLayout.WEST);
    }

    /**
     * Helper to add text box
     */
    private void addTextBox() {
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(1, 1));
        textField = new JTextField(20);
        //textField.addActionListener();
        textField.setToolTipText("Please enter some text here");
        textPanel.add(textField);


        budgetPanel.add(textPanel);
    }


    // MODIFIES: this
    // EFFECTS:  creates new ListOfGroceries and sets Budget to null.
    private void initializeFields() {
        groceries = new ListOfGroceries();
        budget = null;

        input = new Scanner(System.in);
        input.useDelimiter("\n");
        todayDate = new Date();

        jsonWriterGrocery = new JsonWriterGrocery(JSON_STORE_G);
        jsonReaderGrocery = new JsonReaderGrocery(JSON_STORE_G);
        jsonWriterBudget = new JsonWriterBudget(JSON_STORE_B);
        jsonReaderBudget = new JsonReaderBudget(JSON_STORE_B);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

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
            // TODO (Create load button functionality)
            loadLOG();
            loadBudget();
            setVisible(false);
            new Gui();
        }
    }

    // Below ALL taken from AlarmSystem

    /**
     * Represents action to be taken when user wants to load from a saved grocery tracker
     */
    private class NewTrackerAction extends AbstractAction {

        NewTrackerAction() {
            super("Create a New Grocery Tracker");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            // TODO (Create new tracker button functionality)

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
    private void centreOnScreen(JFrame jframe) {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        jframe.setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    /**
     * Represents action to be taken when user clicks desktop
     * to switch focus. (Needed for key handling.)
     */
    private class DesktopFocusAction extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            GuiSplashPage.this.requestFocusInWindow();
        }
    }

    // starts the application
    public static void main(String[] args) {
        new GuiSplashPage();
    }
}

