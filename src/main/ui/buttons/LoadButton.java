package ui.buttons;

import ui.ButtonsSplash;
import ui.buttons.click.LoadButtonClickHandler;

import javax.swing.*;

public class LoadButton extends Button {

    public LoadButton(ButtonsSplash gui, JComponent parent) {
        super(gui, parent);
    }

    // MODIFIES: this
    // EFFECTS:  constructs a Load button which is then added to the JComponent (parent)
    //           which is passed in as a parameter
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Load");
        addToParent(parent);
    }

    // MODIFIES: this
    // EFFECTS:  constructs a new listener object which is added to the JButton
    @Override
    protected void addListener() {
        button.addActionListener(new LoadButtonClickHandler());
    }
}
