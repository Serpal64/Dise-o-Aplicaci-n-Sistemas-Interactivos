import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import net.miginfocom.swing.MigLayout;

public class FormCorrect extends JPanel {
    
    private final JButton boton_productos;
    
    public FormCorrect(CardLayout cardLayout, JPanel content) {
        
        ChangeLanguage language_changer = ChangeLanguage.getInstance();
        
        setBackground(Color.decode("#F3EDDF"));
        setLayout(new MigLayout(
            "insets 20, fillx, wrap 1",
            "[center]",
            "[]40[]20[]"
        ));
        
        // Título - en dos líneas
        JTextPane titulo_label = new JTextPane();
        titulo_label.setFont(new Font("Fraunces", Font.BOLD, 20));
        titulo_label.setForeground(Color.decode("#679A3C"));
        titulo_label.setAlignmentX(CENTER_ALIGNMENT);
        titulo_label.setBackground(Color.decode("#F3EDDF"));

        // Centrar el texto como en el word
        StyledDocument doc1 = titulo_label.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc1.setParagraphAttributes(0, doc1.getLength(), center, false);
        language_changer.addComponent(titulo_label, "FormularioEnviadoCorrectamente");

        add(titulo_label, "align center");
        
        // Mensaje de error general - usando JTextArea para mejor wrapping
        JTextPane txtMensaje = new JTextPane();
        txtMensaje.setPreferredSize(new Dimension(290, 140));
        txtMensaje.setFont(new Font("Fraunces", Font.PLAIN, 16));
        txtMensaje.setForeground(Color.decode("#583E35"));
        txtMensaje.setBackground(Color.decode("#F3EDDF"));
        txtMensaje.setEditable(false);
        txtMensaje.setBorder(null);
        txtMensaje.setAlignmentX(CENTER_ALIGNMENT);


        // Centrar el texto como en el word
        StyledDocument doc2 = txtMensaje.getStyledDocument();
        doc2.setParagraphAttributes(0, doc2.getLength(), center, false);
        language_changer.addComponent(txtMensaje, "SuFormularioHaSidoEnviadoExitosamente");

        add(txtMensaje, "align center");
        
        // Botón Reintentar
        boton_productos = new AppButton("VER PRODUCTOS", "#583E35", "#FFFFFF");
        boton_productos.setFont(new Font("Fraunces", Font.BOLD, 16));
        boton_productos.setFocusPainted(false);
        boton_productos.setBorderPainted(false);
        boton_productos.setPreferredSize(new Dimension(220, 60));
        boton_productos.setMaximumSize(new Dimension(220, 60));
        boton_productos.setAlignmentX(CENTER_ALIGNMENT);
        language_changer.addComponent(boton_productos, "VerProductos");
        boton_productos.addActionListener(e -> cardLayout.show(content, "productos"));
        add(boton_productos, "align center");
    }
}



