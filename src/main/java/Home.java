import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Home {
    public static void main(String[] args) throws Exception {

        JFrame jf = new JFrame("Home");
        ChangeLanguage language_changer = ChangeLanguage.getInstance();

        BorderLayout bl = new BorderLayout(5, 5);

        // Panel principal de la vista
        JPanel main_panel = new JPanel(bl);
        main_panel.setBackground(Color.decode("#F3EDDF"));

        Header header = new Header();
        main_panel.add(header, BorderLayout.NORTH);

        main_panel.add(new Product("Producto1", new ImageIcon(Home.class.getResource("images/product/tomate_1.png")), "Descripcion1", (float)1.75), BorderLayout.CENTER);

        jf.add(main_panel);
        jf.setSize(390, 844);
        jf.setResizable(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);    

        // Para inicializar los textos 
        language_changer.actionPerformed(new ActionEvent(main_panel, 0, null));

    }
}