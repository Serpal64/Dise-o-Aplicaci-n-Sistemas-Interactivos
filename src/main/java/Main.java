import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
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

        // Panel donde se irán cambiando el contenido porque el header se queda igual
        CardLayout cardLayout = new CardLayout();
        JPanel content = new JPanel(cardLayout);

        Header header = new Header(cardLayout, content);
        main_panel.add(header, BorderLayout.NORTH);

        // Lista con los datos de los productos que vamos a utilizar
        List<ProductDetails> productos = new ArrayList<>();

        for(int i=1; i<=3; i++){
            productos.add(new ProductDetails("Producto" + i, new ImageIcon(Main.class.getResource("images/product/Producto" + i + ".png")), "Descripcion" + i, (float)1.75));
            content.add(new Product(productos.get(i-1), cardLayout, content), "Producto" + i);
        }

        // Añadimos todas las ventanas al cardLayout y cuando queramos cambiar de ventana hacemos un .show
        content.add(new Products(3, cardLayout, content, productos), "productos");
        content.add(ShoppingCartPanel.getInstance(cardLayout, content), "carrito");
        
        cardLayout.show(content, "productos");

        main_panel.add(content, BorderLayout.CENTER);

        jf.add(main_panel);
        jf.setSize(390, 844);
        jf.setResizable(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);    

        // Para inicializar los textos 
        language_changer.printComponents();
        language_changer.actionPerformed(new ActionEvent(main_panel, 0, null));

    }
}