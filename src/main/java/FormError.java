import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class FormError extends JPanel {
    
    private JButton btnReintentar;
    
    public FormError(String errorMessage, NavigationListener navigator) {
        setBackground(Color.decode("#F5F5F0"));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(30, 30, 30, 30));
        
        add(Box.createVerticalStrut(10));
        
        // Título
        JLabel lblTitulo = new JLabel("<html><center>ERROR AL ENVIAR<br>EL FORMULARIO</center></html>");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(Color.decode("#E73331"));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setAlignmentX(CENTER_ALIGNMENT);
        add(lblTitulo);
        
        add(Box.createVerticalStrut(20));
        
        // Mensaje de error general
        JLabel lblMensaje = new JLabel("<html><center>Asegúrate de introducir todos los campos obligatorios y de comprobar que tu dirección de correo electrónico sea correcta.</center></html>");
        lblMensaje.setFont(new Font("Arial", Font.PLAIN, 12));
        lblMensaje.setForeground(Color.decode("#333333"));
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        lblMensaje.setAlignmentX(CENTER_ALIGNMENT);
        add(lblMensaje);
        
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
        btnReintentar.addActionListener(e -> navigator.goToContact());
        add(btnReintentar);
        
        add(Box.createVerticalStrut(10));
        
        setPreferredSize(new Dimension(350, 300));
    }
}



