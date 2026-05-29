import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;

public class Products extends JPanel{

    private final CardLayout cardLayout;
    private final JPanel content;
    private final List<ProductDetails> products;
    
    public Products(int num_products, CardLayout cardLayout, JPanel content, List<ProductDetails> products){

        this.cardLayout = cardLayout;
        this.content = content;
        this.products = products;

        ChangeLanguage language_manager = ChangeLanguage.getInstance();

        setLayout(new BorderLayout());
        setBackground(Color.decode("#F3EDDF"));

        // Panel principal
        JPanel main_panel = new JPanel(new MigLayout(
            "insets 0, fillx, wrap 1",
            "[grow, center]",
            "20[]20[]"
        ));
        main_panel.setBackground(Color.decode("#F3EDDF"));

        // Etiqueta que ponga productos
        JLabel productos_label = new JLabel("Productos");
        productos_label.setFont(new Font("Fraunces", Font.PLAIN, 20));
        productos_label.setForeground(Color.decode("#E73331"));
        productos_label.setBackground(Color.decode("#F3EDDF"));
        language_manager.addComponent(productos_label, "Productos");

        // Panel de abajo para ir añadiendo los productos
        JPanel productos_panel = new JPanel(new MigLayout(
            "insets 0, fillx, wrap 1, gapy 40",
            "[grow]"
        ));
        productos_panel.setBackground(Color.decode("#F3EDDF"));

        
        for(ProductDetails p: products){

            RoundedPanel panel_aux = new RoundedPanel(20);
            panel_aux.setLayout(new MigLayout(
                "insets 20, fillx",
                "[grow][grow]",
                "[center]"
            ));
            panel_aux.setPreferredSize(new Dimension(350, 175));
            panel_aux.setBackground(Color.decode("#E73331"));
            panel_aux.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e){
                    cardLayout.show(content, p.getNombre());
                }
            });
            panel_aux.setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Imagen del producto
            Image img_escalada = p.getImagen().getImage().getScaledInstance(155, 135, Image.SCALE_SMOOTH);
            JLabel imagen_label = new JLabel(new ImageIcon(img_escalada));

            // Título del producto
            String titulo = p.getNombre();
            JLabel titulo_label = new JLabel(titulo);
            titulo_label.setForeground(Color.white);
            titulo_label.setFont(new Font("Fraunces", Font.PLAIN, 20));
            language_manager.addComponent(titulo_label, titulo);

            panel_aux.add(imagen_label, "align left");
            panel_aux.add(titulo_label, "align center");

            productos_panel.add(panel_aux, "align center");

        }

        main_panel.add(productos_label, "align center");
        main_panel.add(productos_panel, "align center");

        add(main_panel, BorderLayout.CENTER);

        // Scroll por si el contenido no cabe
        JScrollPane scroll = new JScrollPane(main_panel);
        scroll.setBorder(BorderFactory.createEmptyBorder()); // Quita el borde feo
        scroll.getVerticalScrollBar().setUnitIncrement(10);
        scroll.setAutoscrolls(true);
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0)); // Pa que no se vea
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(scroll, BorderLayout.CENTER);
    }

}
