package view;

import com.sun.tools.jconsole.JConsoleContext;
import interface_adapter.logged_in.LoggedInState;
import interface_adapter.menu.MenuController;
import interface_adapter.menu.MenuState;
import interface_adapter.menu.MenuViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class MenuView extends JPanel implements ActionListener, PropertyChangeListener{
    public final String viewName = "menu";
    private final MenuViewModel menuViewModel;
    private final MenuController menuController;
    JLabel username;
    final JButton betPrediction;
    final JButton logOut;
    public MenuView(MenuViewModel menuViewModel, MenuController menuController) {
        this.menuViewModel = menuViewModel;
        this.menuController = menuController;
        this.menuViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(menuViewModel.TITLE_LABEL);

        username = new JLabel("USERNAME");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();

        betPrediction = new JButton(menuViewModel.BET_PREDICTION_BUTTON);
        buttons.add(betPrediction);

        logOut = new JButton(menuViewModel.LOG_OUT_BUTTON);
        buttons.add(logOut);

        betPrediction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuController.openBetPredictor();
            }
        });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(username);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        MenuState state = (MenuState) evt.getNewValue();
        username.setText(state.getUsername());
    }

}
