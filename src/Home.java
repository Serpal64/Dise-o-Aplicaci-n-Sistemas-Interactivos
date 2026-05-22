import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Home {
    public static void main(String[] args) throws Exception {

        JFrame jf = new JFrame("Home");
        BorderLayout bl = new BorderLayout(5, 5);

        // Panel principal de la vista
        JPanel main_panel = new JPanel(bl);
        main_panel.setBackground(Color.decode("#F3EDDF"));

        // El navbar de arriba
        JPanel header = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        header.setPreferredSize(new Dimension(390, 85));
        header.setBackground(Color.decode("#E73331")); 
        main_panel.add(header, BorderLayout.NORTH);

        // Iconos del navbar
        ImageIcon img = new ImageIcon(Home.class.getResource("images/menu.png"));
        AppButton menu = new AppButton(img, img.getIconWidth(), img.getIconHeight());

        header.add(menu);

        jf.add(main_panel);
        jf.setSize(390, 844);
        jf.setResizable(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);    
    }
}