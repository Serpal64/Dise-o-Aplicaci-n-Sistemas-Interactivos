package Form;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import net.miginfocom.swing.MigLayout;

import UI.AppButton;
import UI.ChangeLanguage;

public class FormError extends JPanel {
    
    private final JButton boton_reintentar;
    
    public FormError(String errorMessage, CardLayout cardLayout, JPanel content) {
        
        ChangeLanguage language_changer = ChangeLanguage.getInstance();
        
        setBackground(Color.decode("#F3EDDF"));
        setLayout(new MigLayout(
            "insets 20, fillx, wrap 1",
            "[center]",
            "[]40[]40[]"
        ));
        
        // Título - en dos líneas
        JTextPane titulo_label = new JTextPane();
        titulo_label.setFont(new Font("Fraunces", Font.BOLD, 20));
        titulo_label.setForeground(Color.decode("#E73331"));
        titulo_label.setAlignmentX(CENTER_ALIGNMENT);
        titulo_label.setBackground(Color.decode("#F3EDDF"));

        // Centrar el texto como en el word
        StyledDocument doc1 = titulo_label.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc1.setParagraphAttributes(0, doc1.getLength(), center, false);
        language_changer.addComponent(titulo_label, "ErrorAlEnviar");

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
        language_changer.addComponent(txtMensaje, "AsegurateDeIntroducir");

        add(txtMensaje, "align center");
        
        // Mostrar error específico si existe
        if (errorMessage != null && !errorMessage.isEmpty()) {
            JLabel lblErrorEspecifico = new JLabel("<html><center><b>Error: " + errorMessage + "</b></center></html>");
            lblErrorEspecifico.setFont(new Font("Fraunces", Font.BOLD, 11));
            lblErrorEspecifico.setForeground(Color.decode("#E73331"));
            lblErrorEspecifico.setHorizontalAlignment(SwingConstants.CENTER);
            lblErrorEspecifico.setAlignmentX(CENTER_ALIGNMENT);
            add(lblErrorEspecifico);
        }
        
        // Botón Reintentar
        boton_reintentar = new AppButton("REINTENTAR", "#583E35", "#FFFFFF");
        boton_reintentar.setFont(new Font("Fraunces", Font.BOLD, 16));
        boton_reintentar.setFocusPainted(false);
        boton_reintentar.setBorderPainted(false);
        boton_reintentar.setPreferredSize(new Dimension(220, 60));
        boton_reintentar.setMaximumSize(new Dimension(220, 60));
        boton_reintentar.setAlignmentX(CENTER_ALIGNMENT);
        language_changer.addComponent(boton_reintentar, "Reintentar");
        boton_reintentar.addActionListener(e -> cardLayout.show(content, "contacto"));
        add(boton_reintentar, "align center");
    }
}



