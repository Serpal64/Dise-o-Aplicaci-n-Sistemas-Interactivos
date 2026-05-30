
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import net.miginfocom.swing.MigLayout;

public class ShoppingCartPanel extends JPanel {

    private static ShoppingCartPanel instance;
    private final CardLayout cardLayout;
    private final JPanel content;
    private final JPanel main_panel;

    public static ShoppingCartPanel getInstance(CardLayout cardLayout, JPanel content){

        if (instance == null){
            instance = new ShoppingCartPanel(cardLayout, content);
        }
        return instance;
    }

    public ShoppingCartPanel(CardLayout cardLayout, JPanel content){
        this.cardLayout = cardLayout;
        this.content = content;

        setLayout(new BorderLayout());
        setBackground(Color.decode("#F3EDDF"));

        main_panel = new JPanel();
        main_panel.setBackground(Color.decode("#F3EDDF"));

        JScrollPane scroll = new JScrollPane(main_panel);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().setUnitIncrement(10);
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(scroll, BorderLayout.CENTER);

        refresh();
    }

    // Para actualizar el panel del carrito
    public void refresh(){

        main_panel.removeAll();

        ShoppingCart cart = ShoppingCart.getInstance();
        ChangeLanguage language_manager = ChangeLanguage.getInstance();

        // Si el carrito está vacío, lo indica con un mensaje
        if (cart.getTotalPrice() == 0){

            main_panel.setLayout(new MigLayout(
                "insets 20, fillx, wrap 1",
                "[grow]",
                "[center]"
            ));

            JTextPane no_products = new JTextPane();
            no_products.setEditable(false);
            no_products.setPreferredSize(new Dimension(290, 140));
            no_products.setBackground(Color.decode("#F3EDDF"));
            no_products.setForeground(Color.decode("#583E35"));
            no_products.setFont(new Font("Fraunces", Font.PLAIN, 20));

            StyledDocument doc = no_products.getStyledDocument();
            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);

            language_manager.addComponent(no_products, "SinProductos");
            main_panel.add(no_products, "align center");

        }
        // Si no, se recorren los productos del carrito y se muestran
        else{

            main_panel.setLayout(new MigLayout(
                "insets 35, fillx, wrap 1, gapy 30",
                "[center, grow]",
                "[center]15[]"
            ));

            List<ProductDetails> lista_productos = cart.getProducts();
            List<Integer> cantidades = cart.getAmounts();

            for(int i=0; i<lista_productos.size(); i++){

                ProductDetails p = lista_productos.get(i);
                Integer cantidad = cantidades.get(i);

                JPanel fila = new JPanel(new MigLayout(
                "insets 0, fillx",
                "[]70[grow]",
                "[]5[]"
                ));
                fila.setBackground(Color.decode("#F3EDDF"));

                // Nombre del producto encima de la imagen
                JLabel nombre = new JLabel(p.getNombre());
                nombre.setFont(new Font("Fraunces", Font.PLAIN, 16));
                nombre.setForeground(Color.decode("#E73331"));
                language_manager.addComponent(nombre, p.getNombre());

                Image img = p.getImagen().getImage().getScaledInstance(110, 90, Image.SCALE_SMOOTH);
                JLabel imagen = new JLabel(new ImageIcon(img));

                JPanel derecho = new JPanel(new MigLayout(
                    "insets 0, fillx, wrap 1",
                    "[grow]",
                    "[]10[]"
                ));
                derecho.setBackground(Color.decode("#F3EDDF"));

                JLabel precio = new JLabel(p.getPrecio() + " €/kg");
                precio.setFont(new Font("Fraunces", Font.PLAIN, 15));
                precio.setForeground(Color.decode("#583E35"));

                JSpinner spinner = new JSpinner(new SpinnerNumberModel((int)cantidad, 1, 99, 1));
                // Para que no se pueda escribir texto en el spinner
                ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setEditable(false);
                spinner.setPreferredSize(new Dimension(120, 35));
                spinner.addChangeListener(e -> {
                    cart.addProduct(p, (int)spinner.getValue());
                    refresh();
                    language_manager.printComponents();
                });

                derecho.add(precio, "");
                derecho.add(spinner, "growx");

                fila.add(nombre,  "span 2, wrap");   // span para que ocupe 2 columnas
                fila.add(imagen,  "");
                fila.add(derecho, "");

                main_panel.add(fila, "growx, align center");
        }

        JSeparator separador = new JSeparator();
        separador.setForeground(Color.decode("#C8BFB0"));
        main_panel.add(separador, "growx, gaptop 10");

        JLabel total = new JLabel("Total: " + String.format("%.2f", cart.getTotalPrice()) + "€");
        total.setFont(new Font("Fraunces", Font.PLAIN, 18));
        total.setForeground(Color.decode("#583E35"));
        total.setHorizontalAlignment(SwingConstants.CENTER);
        main_panel.add(total, "growx, gaptop 10");

        AppButton boton_comprar = new AppButton("Comprar", "#E73331", "#FFFFFF");
        boton_comprar.setPreferredSize(new Dimension(135, 40));
        boton_comprar.putClientProperty("JButton.buttonType", "roundRect");
        boton_comprar.addActionListener(e -> {

            cart.buyProducts();

            JDialog dialog = new JDialog();
            dialog.setTitle("");
            dialog.setSize(300, 90);
            dialog.setLocationRelativeTo(this); // Se pone donde está la app principal
            dialog.setModal(true);              // Hasta que no se cierre se bloquea la app
            dialog.setResizable(false);

            JPanel panel = new JPanel(new MigLayout(
                "insets 20, fillx, wrap 1",
                "[grow, center]",
                "[][15][]"
            ));
            panel.setBackground(Color.decode("#F3EDDF"));

            JLabel mensaje = new JLabel("¡Compra realizada con éxito!");
            mensaje.setFont(new Font("Fraunces", Font.PLAIN, 16));
            mensaje.setForeground(Color.decode("#583E35"));
            mensaje.setHorizontalAlignment(SwingConstants.CENTER);

            AppButton boton_cerrar = new AppButton("Aceptar", "#E73331", "#FFFFFF");
            boton_cerrar.setPreferredSize(new Dimension(120, 38));
            boton_cerrar.putClientProperty("JButton.buttonType", "roundRect");
            boton_cerrar.addActionListener(ev -> dialog.dispose());

            panel.add(mensaje,   "growx");
            panel.add(boton_cerrar, "align center");

            dialog.setContentPane(panel);
            dialog.setVisible(true);

            refresh();
        });

        language_manager.addComponent(boton_comprar, "Comprar");
        main_panel.add(boton_comprar, "align center, gaptop 10");
        }

        main_panel.revalidate();
        main_panel.repaint();
    }
}
