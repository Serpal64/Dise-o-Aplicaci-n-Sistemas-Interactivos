package Products;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import net.miginfocom.swing.MigLayout;

import Cart.ShoppingCart;
import Cart.ShoppingCartPanel;
import UI.AppButton;
import UI.ChangeLanguage;

public class Product extends JPanel{

    private final CardLayout cardLayout;
    private final JPanel content;
    
    public Product(ProductDetails product, CardLayout cardLayout, JPanel content){

        this.cardLayout = cardLayout;
        this.content = content;

        ChangeLanguage language_changer = ChangeLanguage.getInstance();
        ShoppingCart cart = ShoppingCart.getInstance();
        ShoppingCartPanel cart_panel = ShoppingCartPanel.getInstance(cardLayout, content);

        setLayout(new BorderLayout());
        setBackground(Color.decode("#F3EDDF"));

        JPanel main_panel = new JPanel(new MigLayout(
            "insets 0, fillx, wrap 1",
            "[grow, center]", // Una columna
            "15[]15[]20[]20[]" // 4 filas en total con espacios entre ellas
        ));
        main_panel.setBackground(Color.decode("#F3EDDF"));


        JPanel zonaRoja = new JPanel(new MigLayout(
            "insets 30, fillx, wrap 1",
            "[grow, center]",
            "[]"
        ));
        zonaRoja.setBackground(Color.decode("#E73331"));

        JLabel titulo = new JLabel(product.getNombre());
        titulo.setFont(new Font("Fraunces", Font.PLAIN, 36));
        titulo.setForeground(Color.decode("#E73331"));
        titulo.setBackground(Color.decode("#F3EDDF"));
        titulo.setOpaque(true);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        language_changer.addComponent(titulo, product.getNombre());

        main_panel.add(titulo);


        Image imgEscalada = product.getImagen().getImage().getScaledInstance(220, 180, Image.SCALE_SMOOTH);
        JLabel imagen_label = new JLabel(new ImageIcon(imgEscalada));

        zonaRoja.add(imagen_label, "");


        JPanel zonaBeige = new JPanel(new MigLayout(
            "insets 20, fillx, wrap 1",
            "[grow, center]",
            "[]50[]"
        ));
        zonaBeige.setBackground(Color.decode("#F3EDDF"));


        JTextPane descripcion_text_area = new JTextPane();
        descripcion_text_area.setEditable(false);
        descripcion_text_area.setBackground(Color.decode("#F3EDDF"));
        descripcion_text_area.setFont(new Font("Fraunces", Font.PLAIN, 16));
        descripcion_text_area.setForeground(Color.decode("#583E35"));

        language_changer.addComponent(descripcion_text_area, product.getDescripcion());

        // Centrar el texto como en el word
        StyledDocument doc = descripcion_text_area.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        descripcion_text_area.setText(product.getDescripcion());
        descripcion_text_area.setFocusable(false);
        descripcion_text_area.setPreferredSize(new Dimension(290, 168));


        JPanel acciones = new JPanel(new MigLayout(
            "insets 0, fillx",
            "[grow]50[grow]",
            "[]5[]10[]"
        ));
        acciones.setBackground(Color.decode("#F3EDDF"));

        JLabel cantidad_label = new JLabel("Cantidad");
        cantidad_label.setFont(new Font("Fraunces", Font.PLAIN, 13));

        language_changer.addComponent(cantidad_label, "Cantidad");

        JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, 99, 1));
        // Para que no se pueda escribir texto en el spinner
        ((JSpinner.DefaultEditor) spinner.getEditor()).getTextField().setEditable(false);
        spinner.setPreferredSize(new Dimension(70, 35));

        JLabel precio_label = new JLabel(String.format("%.2f", product.getPrecio()) + " €/kg");
        precio_label.setForeground(Color.decode("#583E35"));
        precio_label.setBackground(Color.decode("#F3EDDF"));
        precio_label.setHorizontalTextPosition(SwingConstants.CENTER);
        precio_label.setPreferredSize(new Dimension(100, 22));
        precio_label.setFont(new Font("Fraunces", Font.PLAIN, 14));

        AppButton boton_anadir = new AppButton("Añadir a la cesta", "#E73331", "#FFFFFF");
        boton_anadir.setPreferredSize(new Dimension(136, 40));
        boton_anadir.addActionListener(e -> {

            // Añadimos al carrito el producto y la cantidad
            cart.addProduct(product, (Integer)spinner.getValue());
            cart_panel.refresh();


        });

        language_changer.addComponent(boton_anadir, "BotonAnadir");

        acciones.add(cantidad_label, "align left");
        acciones.add(precio_label, "align right, wrap");
        acciones.add(spinner,     "align left");
        acciones.add(boton_anadir,   "align right");

        zonaBeige.add(descripcion_text_area, "");
        zonaBeige.add(acciones,    "");

        main_panel.add(zonaRoja,   "growx");
        main_panel.add(zonaBeige,  "growx");

        // Scroll por si el main_panel no cabe
        JScrollPane scroll = new JScrollPane(main_panel);
        scroll.setBorder(BorderFactory.createEmptyBorder()); // Quita el borde feo
        scroll.getVerticalScrollBar().setUnitIncrement(10);
        scroll.setAutoscrolls(true);
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0)); // Pa que no se vea
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(scroll, BorderLayout.CENTER);

    }

}
