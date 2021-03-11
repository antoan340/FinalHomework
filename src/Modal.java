

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Modal extends JDialog {
    /**
     * @param "Клас с който се създава изкачащ прозорец като се инициализира"
     * @author Antoan
     */
    public Modal( JFrame parent, String title, String message ) {
        super(parent, title, true);
        JPanel panel = new JPanel();
        JLabel label = new JLabel(message);
        panel.add(Button);
        panel.add(label);
        getContentPane().add(panel);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }
    public static JButton Button = new JButton("Restart");
    /**
     * @param "Визоализиране на JPanel"
     * @author Antoan
     */
    public static void render(JFrame parent, String title, String message) {
        new Modal(parent, title, message);
    }
    /**
     * @param "Рестартиране на игралното поле"
     * @author Antoan
     */
    public static void Restart(GameBoard gameBoard) {
        Button.addActionListener(new ButtonClick());
    }
    public static class ButtonClick implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            GameBoard gameBoard = new GameBoard();
        }
    }

}