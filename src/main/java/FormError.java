import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class FormError extends JPanel {
    
    private JButton btnReintentar;
    
    public FormError(String errorMessage, CardLayout cardLayout, JPanel content) {
        
        ChangeLanguage language_changer = ChangeLanguage.getInstance();
        
        setBackground(Color.decode("#F5F5F0"));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(30, 30, 30, 30));
        
        add(Box.createVerticalStrut(10));
        
        // Título - en dos líneas
        JLabel lblTitulo1 = new JLabel("ERROR AL ENVIAR");
        lblTitulo1.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo1.setForeground(Color.decode("#E73331"));
        lblTitulo1.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo1.setAlignmentX(CENTER_ALIGNMENT);
        language_changer.addComponent(lblTitulo1, "ErrorAlEnviarLinea1");
        add(lblTitulo1);
        
        JLabel lblTitulo2 = new JLabel("EL FORMULARIO");
        lblTitulo2.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo2.setForeground(Color.decode("#E73331"));
        lblTitulo2.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo2.setAlignmentX(CENTER_ALIGNMENT);
        language_changer.addComponent(lblTitulo2, "ErrorAlEnviarLinea2");
        add(lblTitulo2);
        
        add(Box.createVerticalStrut(20));
        
        // Mensaje de error general - usando JTextArea para mejor wrapping
        JTextArea txtMensaje = new JTextArea("Asegúrate de introducir todos los campos obligatorios y de comprobar que tu dirección de correo electrónico sea correcta.");
        txtMensaje.setFont(new Font("Arial", Font.PLAIN, 12));
        txtMensaje.setForeground(Color.decode("#333333"));
        txtMensaje.setBackground(Color.decode("#F5F5F0"));
        txtMensaje.setLineWrap(true);
        txtMensaje.setWrapStyleWord(true);
        txtMensaje.setEditable(false);
        txtMensaje.setBorder(null);
        txtMensaje.setAlignmentX(CENTER_ALIGNMENT);
        JPanel msgPanel = new JPanel();
        msgPanel.setBackground(Color.decode("#F5F5F0"));
        msgPanel.setMaximumSize(new Dimension(310, 60));
        msgPanel.add(txtMensaje);
        language_changer.addComponent(txtMensaje, "AsegurateDeIntroducir");
        add(msgPanel);
        
        add(Box.createVerticalStrut(10));
        
        // Mostrar error específico si existe
        if (errorMessage != null && !errorMessage.isEmpty()) {
            JLabel lblErrorEspecifico = new JLabel("<html><center><b>Error: " + errorMessage + "</b></center></html>");
            lblErrorEspecifico.setFont(new Font("Arial", Font.BOLD, 11));
            lblErrorEspecifico.setForeground(Color.decode("#E73331"));
            lblErrorEspecifico.setHorizontalAlignment(SwingConstants.CENTER);
            lblErrorEspecifico.setAlignmentX(CENTER_ALIGNMENT);
            add(lblErrorEspecifico);
            add(Box.createVerticalStrut(15));
        }
        
        add(Box.createVerticalGlue());
        
        // Botón Reintentar
        btnReintentar = new JButton("REINTENTAR");
        btnReintentar.setBackground(Color.decode("#4A4A4A"));
        btnReintentar.setForeground(Color.WHITE);
        btnReintentar.setFont(new Font("Arial", Font.BOLD, 12));
        btnReintentar.setFocusPainted(false);
        btnReintentar.setBorderPainted(false);
        btnReintentar.setPreferredSize(new Dimension(140, 35));
        btnReintentar.setMaximumSize(new Dimension(140, 35));
        btnReintentar.setAlignmentX(CENTER_ALIGNMENT);
        language_changer.addComponent(btnReintentar, "Reintentar");
        btnReintentar.addActionListener(e -> cardLayout.show(content, "contacto"));
        add(btnReintentar);
        
        add(Box.createVerticalStrut(10));
        
        setPreferredSize(new Dimension(350, 380));
    }
}



