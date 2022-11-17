package ui;

import model.Budget;
import model.ListOfGroceries;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GuiBeta extends JFrame {

    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;

    private ListOfGroceries groceries;
    private Budget budget;
    private JDesktopPane desktop;
    private JInternalFrame controlPanel;

    public GuiBeta() {
        initializeFields();

        desktop = new JDesktopPane();
        desktop.addMouseListener(new DesktopFocusAction());

        controlPanel = new JInternalFrame("Grocery App", false, false, false, false);
        controlPanel.setLayout(new BorderLayout());

        setContentPane(desktop);
        setTitle("Options");
        setSize(WIDTH, HEIGHT);

        addButtonPanel();

        controlPanel.pack();
        controlPanel.setVisible(true);
        desktop.add(controlPanel);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        centreOnScreen();
        setVisible(true);
    }

    /**
     * Helper to add control buttons.
     */
    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 1));
        buttonPanel.add(new JButton(new SaveAction()));
        buttonPanel.add(new JButton(new LoadAction()));
        buttonPanel.add(new JButton(new AddAction()));
        buttonPanel.add(new JButton(new RemoveAction()));

        controlPanel.add(buttonPanel, BorderLayout.WEST);
    }


    // MODIFIES: this
    // EFFECTS:  creates new ListOfGroceries and sets Budget to null.
    private void initializeFields() {
        groceries = new ListOfGroceries();
        budget = null;
    }

    /**
     * Represents action to be taken when user wants to remove a grocery item from the grocery tracker
     */
    private class RemoveAction extends AbstractAction {

        RemoveAction() {
            super("Remove existing grocery item");
        }

        @Override
        public void actionPerformed(ActionEvent evt) {
            // TODO (Create remvoe button functionality)
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
            // TODO (Create add button functionality)
        }
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
            // TODO (Create save button functionality)
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
            // TODO (Create load button functionality)
        }
    }

    // Below ALL taken from AlarmSystem

    /**
     * Helper to centre main application window on desktop
     */
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    /**
     * Represents action to be taken when user clicks desktop
     * to switch focus. (Needed for key handling.)
     */
    private class DesktopFocusAction extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            GuiBeta.this.requestFocusInWindow();
        }
    }

    // starts the application
    public static void main(String[] args) {
        new GuiBeta();
    }
}
