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

public class FormCorrect extends JPanel {
    
    private JButton btnVerProductos;
    
    public FormCorrect(NavigationListener navigator) {
        setBackground(Color.decode("#F5F5F0"));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(30, 30, 30, 30));
        
        add(Box.createVerticalStrut(10));
        
        // Título
        JLabel lblTitulo = new JLabel("<html><center>FORMULARIO<br>ENVIADO CORRECTAMENTE</center></html>");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo.setForeground(Color.decode("#4CAF50"));
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setAlignmentX(CENTER_ALIGNMENT);
        add(lblTitulo);
        
        add(Box.createVerticalStrut(20));
        
        // Mensaje
        JLabel lblMensaje = new JLabel("<html><center>Su formulario ha sido enviado de forma exitosa.</center></html>");
        lblMensaje.setFont(new Font("Arial", Font.PLAIN, 12));
        lblMensaje.setForeground(Color.decode("#333333"));
        lblMensaje.setHorizontalAlignment(SwingConstants.CENTER);
        lblMensaje.setAlignmentX(CENTER_ALIGNMENT);
        add(lblMensaje);
        
        add(Box.createVerticalGlue());
        
        // Botón Ver Productos
        btnVerProductos = new JButton("VER PRODUCTOS");
        btnVerProductos.setBackground(Color.decode("#4A4A4A"));
        btnVerProductos.setForeground(Color.WHITE);
        btnVerProductos.setFont(new Font("Arial", Font.BOLD, 12));
        btnVerProductos.setFocusPainted(false);
        btnVerProductos.setBorderPainted(false);
        btnVerProductos.setPreferredSize(new Dimension(140, 35));
        btnVerProductos.setMaximumSize(new Dimension(140, 35));
        btnVerProductos.setAlignmentX(CENTER_ALIGNMENT);
        btnVerProductos.addActionListener(e -> navigator.goToHome());
        add(btnVerProductos);
        
        add(Box.createVerticalStrut(10));
        
        setPreferredSize(new Dimension(350, 280));
    }
}
