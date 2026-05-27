
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
    
    public Product(String nombre, ImageIcon imagen, String descripcion, float precio){

        ChangeLanguage language_changer = ChangeLanguage.getInstance();

        setLayout(new BorderLayout());
        setBackground(Color.decode("#F5F0E8"));

        JPanel contenido = new JPanel(new MigLayout(
            "insets 0, fillx, wrap 1",  // 1 columna, sin márgenes
            "[grow, center]",           // todo centrado
            "15[]15[]20[]20[]"              // filas: título, imagen, descripción, acciones
        ));
        contenido.setBackground(Color.decode("#F5F0E8"));

        // ── ZONA ROJA (título + imagen) ──────────────────────
        JPanel zonaRoja = new JPanel(new MigLayout(
            "insets 30, fillx, wrap 1",
            "[grow, center]",
            "[]"
        ));
        zonaRoja.setBackground(Color.decode("#E73331"));

        // Título
        JLabel titulo = new JLabel(nombre);
        titulo.setFont(new Font("Fraunces", Font.PLAIN, 36));
        titulo.setForeground(Color.decode("#E73331"));
        titulo.setBackground(Color.decode("#F5F0E8"));
        titulo.setOpaque(true);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        language_changer.addComponent(titulo, nombre);

        contenido.add(titulo);
        // Imagen del producto
        Image imgEscalada = imagen.getImage().getScaledInstance(220, 180, Image.SCALE_SMOOTH);
        JLabel imagen_label = new JLabel(new ImageIcon(imgEscalada));

        zonaRoja.add(imagen_label, "");

        // ── ZONA BEIGE (descripción + acciones) ─────────────
        JPanel zonaBeige = new JPanel(new MigLayout(
            "insets 20, fillx, wrap 1",
            "[grow, center]",
            "[]50[]"
        ));
        zonaBeige.setBackground(Color.decode("#F5F0E8"));


        // Descripción
        JTextPane descripcion_text_area = new JTextPane();
        descripcion_text_area.setEditable(false);
        descripcion_text_area.setBackground(Color.decode("#F5F0E8"));
        descripcion_text_area.setFont(new Font("Fraunces", Font.PLAIN, 16));
        descripcion_text_area.setForeground(Color.decode("#583E35"));

        language_changer.addComponent(descripcion_text_area, descripcion);

        // Centrar el texto
        StyledDocument doc = descripcion_text_area.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        descripcion_text_area.setText(descripcion);
        descripcion_text_area.setFocusable(false);
        descripcion_text_area.setPreferredSize(new Dimension(290, 168));

        // ── FILA INFERIOR: cantidad + precio + botón ─────────
        JPanel acciones = new JPanel(new MigLayout(
            "insets 0, fillx",
            "[grow]50[grow]",   // cantidad label | spinner | espacio | precio | botón
            "[]5[]"
        ));
        acciones.setBackground(Color.decode("#F5F0E8"));

        JLabel lblCantidad = new JLabel("Cantidad");
        lblCantidad.setFont(new Font("Fraunces", Font.PLAIN, 13));

        language_changer.addComponent(lblCantidad, "Cantidad");

        JSpinner spinner = new JSpinner(new SpinnerNumberModel(1, 1, 99, 1));
        spinner.setPreferredSize(new Dimension(70, 35));

        JLabel precio_label = new JLabel(precio + " €/kg");
        precio_label.setHorizontalTextPosition(SwingConstants.CENTER);
        precio_label.setSize(132, 22);
        precio_label.setFont(new Font("Fraunces", Font.PLAIN, 14));

        AppButton btnAnadir = new AppButton("Añadir a la cesta", "#E73331", "#FFFFFF");
        btnAnadir.setPreferredSize(new Dimension(136, 40));

        language_changer.addComponent(btnAnadir, "BotonAnadir");

        acciones.add(lblCantidad, "align left");  // cantidad encima del spinner
        acciones.add(precio_label, "align right, wrap");
        acciones.add(spinner,     "align left");
        acciones.add(btnAnadir,   "align right");

        zonaBeige.add(descripcion_text_area, "");
        zonaBeige.add(acciones,    "");

        contenido.add(zonaRoja,   "growx");
        contenido.add(zonaBeige,  "growx");

        // Scroll por si el contenido no cabe
        JScrollPane scroll = new JScrollPane(contenido);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().setUnitIncrement(10);
        scroll.setAutoscrolls(true);

        add(scroll, BorderLayout.CENTER);

    }

}
