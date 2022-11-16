package ui;

import model.Budget;
import model.ListOfGroceries;
import ui.buttons.Button;
import ui.buttons.LoadButton;
import ui.buttons.SaveButton;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// a GUI application for the grocery app
public class ButtonsSplash extends JFrame {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 500;

    private ListOfGroceries groceries;
    private Budget budget;
    private ArrayList<Button> buttons;
//    private JComboBox<String> printCombo;
//    private JDesktopPane desktop;
//    private JInternalFrame controlPanel;

    // taken from SimpleDrawingPLayer
    //Constructor
    public ButtonsSplash() {
        super("Grocery App");
        initializeFields();
        initializeGraphics();
    }

    // MODIFIES: this
    // EFFECTS:  creates new ListOfGroceries and sets Budget to null.
    private void initializeFields() {
        groceries = new ListOfGroceries();
        budget = null;
    }

    // taken from SimpleDrawingPLayer
    // MODIFIES: this
    // EFFECTS:  draws the JFrame window where this DrawingEditor will operate, and populates the tools to be used
    //           to manipulate this drawing
    private void initializeGraphics() {
        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        createTools();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS:  a helper method which declares and instantiates all tools
    private void createTools() {
        JPanel buttonZone = new JPanel();
        buttonZone.setLayout(new GridLayout(0,1));
        buttonZone.setSize(new Dimension(0, 0));
        add(buttonZone, BorderLayout.SOUTH);

        SaveButton saveButton = new SaveButton(this, buttonZone);
        LoadButton loadButton = new LoadButton(this, buttonZone);
    }




    //getter
    public ListOfGroceries getGroceries() {
        return groceries;
    }
    //getter
    public Budget getBudget() {
        return budget;
    }
}
