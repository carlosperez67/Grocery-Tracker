package ui.buttons;

import ui.ButtonsSplash;
import ui.buttons.click.SaveButtonClickHandler;

import javax.swing.*;

public class SaveButton extends Button {


    public SaveButton(ButtonsSplash gui, JComponent parent) {
        super(gui, parent);
    }

    // MODIFIES: this
    // EFFECTS:  constructs a Save button which is then added to the JComponent (parent)
    //           which is passed in as a parameter
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Save");
        addToParent(parent);
    }

    // MODIFIES: this
    // EFFECTS:  constructs a new listener object which is added to the JButton
    @Override
    protected void addListener() {
        button.addActionListener(new SaveButtonClickHandler());
    }

}
