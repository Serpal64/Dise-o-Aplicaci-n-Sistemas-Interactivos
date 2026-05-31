import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatLightLaf;

public class Main {
    private static final String WINDOW_TITLE = "Salmorejo Shop";
    private static final int WINDOW_WIDTH = 390;
    private static final int WINDOW_HEIGHT = 844;
    private static final int NUM_PRODUCTS = 3;
    private static final Color BG_COLOR = Color.decode("#F3EDDF");
    private static final Color MENU_BG = Color.decode("#E73331");
    private static final Color MENU_HOVER = Color.decode("#C42020");

    public static void main(String[] args) throws Exception {
        setupUI();
        JFrame frame = createAndShowUI();
        frame.setVisible(true);
        ChangeLanguage.getInstance().actionPerformed(null);
    }

    private static void setupUI() {
        FlatLightLaf.setup();
        UIManager.put("Button.arc", 20);
        UIManager.put("MenuItem.background", MENU_BG);
        UIManager.put("MenuItem.foreground", Color.WHITE);
        UIManager.put("MenuItem.selectionBackground", MENU_HOVER);
        UIManager.put("MenuItem.selectionForeground", Color.WHITE);
        UIManager.put("MenuItem.font", new Font("Fraunces", Font.PLAIN, 16));
        UIManager.put("PopupMenu.background", MENU_BG);
    }

    private static JFrame createAndShowUI() {
        JFrame frame = new JFrame(WINDOW_TITLE);
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BG_COLOR);
        
        CardLayout cardLayout = new CardLayout();
        JPanel contentPanel = new JPanel(cardLayout);
        
        List<ProductDetails> productos = loadProducts();
        Header header = new Header(cardLayout, contentPanel);
        mainPanel.add(header, BorderLayout.NORTH);
        
        addPages(contentPanel, cardLayout, productos);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        frame.add(mainPanel);
        return frame;
    }

    private static List<ProductDetails> loadProducts() {
        List<ProductDetails> productos = new ArrayList<>();
        
        for (int i = 1; i <= NUM_PRODUCTS; i++) {
            String resourcePath = "/images/product/Producto" + i + ".png";
            java.net.URL imageUrl = Main.class.getResource(resourcePath);
            
            ImageIcon icon = imageUrl != null ? new ImageIcon(imageUrl) : new ImageIcon();
            if (imageUrl == null) {
                System.err.println("Advertencia: No se pudo cargar imagen: " + resourcePath);
            }
            
            productos.add(new ProductDetails("Producto" + i, icon, "Descripcion" + i, (float)(Math.random() * 2.5) + 1));
        }
        
        return productos;
    }

    private static void addPages(JPanel contentPanel, CardLayout cardLayout, List<ProductDetails> productos) {
        for (int i = 0; i < NUM_PRODUCTS; i++) {
            contentPanel.add(new Product(productos.get(i), cardLayout, contentPanel), "Producto" + (i + 1));
        }
        
        contentPanel.add(new HomeContent(), "home");
        contentPanel.add(new Products(NUM_PRODUCTS, cardLayout, contentPanel, productos), "productos");
        contentPanel.add(ShoppingCartPanel.getInstance(cardLayout, contentPanel), "carrito");
        contentPanel.add(new ContactForm(cardLayout, contentPanel), "contacto");
        contentPanel.add(new FormCorrect(cardLayout, contentPanel), "formCorrect");
        contentPanel.add(new FormError("", cardLayout, contentPanel), "formError");
        
        cardLayout.show(contentPanel, "home");
    }
}