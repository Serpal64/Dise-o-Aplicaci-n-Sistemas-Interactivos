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

public class FormCorrect extends JPanel {
    
    private JButton btnVerProductos;
    
    public FormCorrect(CardLayout cardLayout, JPanel content) {
        
        ChangeLanguage language_changer = ChangeLanguage.getInstance();
        
        setBackground(Color.decode("#F5F5F0"));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(new EmptyBorder(30, 30, 30, 30));
        
        add(Box.createVerticalStrut(10));
        
        // Título - en dos líneas
        JLabel lblTitulo1 = new JLabel("FORMULARIO");
        lblTitulo1.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo1.setForeground(Color.decode("#4CAF50"));
        lblTitulo1.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo1.setAlignmentX(CENTER_ALIGNMENT);
        language_changer.addComponent(lblTitulo1, "FormularioEnviadoCorrectamenteLinea1");
        add(lblTitulo1);
        
        JLabel lblTitulo2 = new JLabel("ENVIADO CORRECTAMENTE");
        lblTitulo2.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitulo2.setForeground(Color.decode("#4CAF50"));
        lblTitulo2.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo2.setAlignmentX(CENTER_ALIGNMENT);
        language_changer.addComponent(lblTitulo2, "FormularioEnviadoCorrectamenteLinea2");
        add(lblTitulo2);
        
        add(Box.createVerticalStrut(20));
        
        // Mensaje - usando JTextArea para mejor wrapping
        JTextArea txtMensaje = new JTextArea("Su formulario ha sido enviado de forma exitosa.");
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
        msgPanel.setMaximumSize(new Dimension(310, 50));
        msgPanel.add(txtMensaje);
        language_changer.addComponent(txtMensaje, "SuFormularioHaSidoEnviadoExitosamente");
        add(msgPanel);
        
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
        language_changer.addComponent(btnVerProductos, "VerProductos");
        btnVerProductos.addActionListener(e -> cardLayout.show(content, "productos"));
        add(btnVerProductos);
        
        add(Box.createVerticalStrut(10));
        
        setPreferredSize(new Dimension(350, 280));
    }
}
