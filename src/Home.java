import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Home {
    public static void main(String[] args) throws Exception {

        JFrame jf = new JFrame("Home");
        BorderLayout bl = new BorderLayout(5, 5);

        // Panel principal de la vista
        JPanel main_panel = new JPanel(bl);
        main_panel.setBackground(Color.decode("#F3EDDF"));

        // El navbar de arriba
        JPanel header = new JPanel(new BorderLayout(5, 5));
        header.setPreferredSize(new Dimension(390, 85));
        header.setBackground(Color.decode("#E73331")); 

        // Para poner los botones que están a la izquierda y derecha
        JPanel header_left = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        header_left.setBackground(Color.decode("#E73331"));

        ImageIcon img_menu = new ImageIcon(Home.class.getResource("images/menu.png"));
        AppButton menu = new AppButton(img_menu, img_menu.getIconWidth(), img_menu.getIconHeight());
        ImageIcon img_search = new ImageIcon(Home.class.getResource("images/search_bar.png"));
        JLabel search = new JLabel(img_search);

        header_left.add(menu);
        header_left.add(search);


        JPanel header_right = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        header_right.setBackground(Color.decode("#E73331"));

        ImageIcon img_home = new ImageIcon(Home.class.getResource("images/home.png"));
        AppButton home = new AppButton(img_home, img_home.getIconWidth(), img_home.getIconHeight());
        ImageIcon img_tomato = new ImageIcon(Home.class.getResource("images/tomato.png"));
        AppButton tomato = new AppButton(img_tomato, img_tomato.getIconWidth(), img_tomato.getIconHeight());
        ImageIcon img_basket = new ImageIcon(Home.class.getResource("images/basket.png"));
        AppButton basket = new AppButton(img_basket, img_basket.getIconWidth(), img_basket.getIconHeight());

        header_right.add(home);
        header_right.add(tomato);
        header_right.add(basket);

        header.add(header_left, BorderLayout.WEST);
        header.add(header_right, BorderLayout.EAST);

        main_panel.add(header, BorderLayout.NORTH);

        jf.add(main_panel);
        jf.setSize(390, 844);
        jf.setResizable(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);    
    }
}