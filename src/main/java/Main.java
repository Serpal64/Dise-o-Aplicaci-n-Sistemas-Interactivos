import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;


public class Main {
    public static void main(String[] args) throws Exception {

        FlatLightLaf.setup();
        UIManager.put("Button.arc", 20);
        UIManager.put("MenuItem.background",        Color.decode("#E73331"));
        UIManager.put("MenuItem.foreground",        Color.WHITE);
        UIManager.put("MenuItem.selectionBackground", Color.decode("#C42020")); // hover
        UIManager.put("MenuItem.selectionForeground", Color.WHITE);
        UIManager.put("MenuItem.font",              new Font("Fraunces", Font.PLAIN, 16));
        UIManager.put("PopupMenu.background",       Color.decode("#E73331"));

        JFrame jf = new JFrame("Home");
        ChangeLanguage language_changer = ChangeLanguage.getInstance();

        BorderLayout bl = new BorderLayout(5, 5);

        // Panel principal de la vista
        JPanel main_panel = new JPanel(bl);
        main_panel.setBackground(Color.decode("#F3EDDF"));

        Header header = new Header();
        main_panel.add(header, BorderLayout.NORTH);

        // Panel donde se irán cambiando el contenido porque el header se queda igual
        CardLayout cardLayout = new CardLayout();
        JPanel content = new JPanel(cardLayout);

        content.add(new Products(3, cardLayout, content), "productos");
        
        cardLayout.show(content, "productos");

        main_panel.add(content, BorderLayout.CENTER);

        jf.add(main_panel);
        jf.setSize(390, 844);
        jf.setResizable(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);    

        // Para inicializar los textos 
        language_changer.actionPerformed(new ActionEvent(main_panel, 0, null));

    }
}