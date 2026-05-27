
import java.awt.BorderLayout;
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

public class Product extends JPanel{
    
    public Product(ProductDetails product){

        ChangeLanguage language_changer = ChangeLanguage.getInstance();

        setLayout(new BorderLayout());
        setBackground(Color.decode("#F5F0E8"));

        JPanel contenido = new JPanel(new MigLayout(
            "insets 0, fillx, wrap 1",
            "[grow, center]", // Una columna
            "15[]15[]20[]20[]" // 4 filas en total con espacios entre ellas
        ));
        contenido.setBackground(Color.decode("#F5F0E8"));


        JPanel zonaRoja = new JPanel(new MigLayout(
            "insets 30, fillx, wrap 1",
            "[grow, center]",
            "[]"
        ));
        zonaRoja.setBackground(Color.decode("#E73331"));

        JLabel titulo = new JLabel(product.getNombre());
        titulo.setFont(new Font("Fraunces", Font.PLAIN, 36));
        titulo.setForeground(Color.decode("#E73331"));
        titulo.setBackground(Color.decode("#F5F0E8"));
        titulo.setOpaque(true);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        language_changer.addComponent(titulo, product.getNombre());

        contenido.add(titulo);


        Image imgEscalada = product.getImagen().getImage().getScaledInstance(220, 180, Image.SCALE_SMOOTH);
        JLabel imagen_label = new JLabel(new ImageIcon(imgEscalada));

        zonaRoja.add(imagen_label, "");


        JPanel zonaBeige = new JPanel(new MigLayout(
            "insets 20, fillx, wrap 1",
            "[grow, center]",
            "[]50[]"
        ));
        zonaBeige.setBackground(Color.decode("#F5F0E8"));


        JTextPane descripcion_text_area = new JTextPane();
        descripcion_text_area.setEditable(false);
        descripcion_text_area.setBackground(Color.decode("#F5F0E8"));
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
            "[]5[]"
        ));
        acciones.setBackground(Color.decode("#F5F0E8"));

        JLabel cantidad_label = new JLabel("Cantidad");
        cantidad_label.setFont(new Font("Fraunces", Font.PLAIN, 13));

        language_changer.addComponent(cantidad_label, "Cantidad");

        JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, 99, 1));
        spinner.setPreferredSize(new Dimension(70, 35));

        JLabel precio_label = new JLabel(product.getPrecio() + " €/kg");
        precio_label.setHorizontalTextPosition(SwingConstants.CENTER);
        precio_label.setPreferredSize(new Dimension(100, 22));
        precio_label.setFont(new Font("Fraunces", Font.PLAIN, 14));

        AppButton boton_anadir = new AppButton("Añadir a la cesta", "#E73331", "#FFFFFF");
        boton_anadir.setPreferredSize(new Dimension(136, 40));

        language_changer.addComponent(boton_anadir, "BotonAnadir");

        acciones.add(cantidad_label, "align left");
        acciones.add(precio_label, "align right, wrap");
        acciones.add(spinner,     "align left");
        acciones.add(boton_anadir,   "align right");

        zonaBeige.add(descripcion_text_area, "");
        zonaBeige.add(acciones,    "");

        contenido.add(zonaRoja,   "growx");
        contenido.add(zonaBeige,  "growx");

        // Scroll por si el contenido no cabe
        JScrollPane scroll = new JScrollPane(contenido);
        scroll.setBorder(BorderFactory.createEmptyBorder()); // Quita el borde feo
        scroll.getVerticalScrollBar().setUnitIncrement(10);
        scroll.setAutoscrolls(true);
        scroll.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0)); // Pa que no se vea
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(scroll, BorderLayout.CENTER);

    }

}
