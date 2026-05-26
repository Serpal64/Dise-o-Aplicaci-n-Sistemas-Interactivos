import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Home {
    public static void main(String[] args) throws Exception {

        JFrame jf = new JFrame("Home");

        BorderLayout bl = new BorderLayout(5, 5);

        // Panel principal de la vista
        JPanel main_panel = new JPanel(bl);
        main_panel.setBackground(Color.decode("#F3EDDF"));

        Header header = new Header();
        main_panel.add(header, BorderLayout.NORTH);

        jf.add(main_panel);
        jf.setSize(390, 844);
        jf.setResizable(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);    

    }
}