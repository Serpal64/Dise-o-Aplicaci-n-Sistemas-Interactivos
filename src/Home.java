import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Home {
    public static void main(String[] args) throws Exception {

        JFrame jf = new JFrame("Home");
        BorderLayout bl = new BorderLayout(5, 5);

        JPanel main_panel = new JPanel(bl);
        main_panel.setBackground(Color.decode("#F3EDDF"));

        JPanel header = new JPanel();
        header.setPreferredSize(new Dimension(390, 85));
        header.setBackground(Color.decode("#E73331")); 
        main_panel.add(header, BorderLayout.NORTH);

        jf.add(main_panel);
        jf.setSize(390, 844);
        jf.setResizable(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);    
    }
}