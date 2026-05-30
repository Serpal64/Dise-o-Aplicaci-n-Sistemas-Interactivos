import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class ContactForm extends JPanel {
    
    private JTextField txtNombre;
    private JTextField txtApellidos;
    private JTextField txtCorreo;
    private JTextField txtNegocio;
    private JTextField txtDireccion;
    private JTextArea txtMensaje;
    private JButton btnEnviar;
    private ActionListener enviarListener;
    
    public ContactForm() {
        setBackground(Color.decode("#E73331"));
        setLayout(new MigLayout("insets 20, gap 15", "[grow]", ""));
        setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Título
        JLabel lblTitulo = new JLabel("Contáctanos");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        add(lblTitulo, "wrap");
        
        // Nombre*
        JLabel lblNombre = new JLabel("Nombre*");
        lblNombre.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNombre.setForeground(Color.WHITE);
        add(lblNombre, "wrap");
        
        txtNombre = new JTextField(20);
        txtNombre.setFont(new Font("Arial", Font.PLAIN, 12));
        add(txtNombre, "grow, wrap");
        
        // Apellidos
        JLabel lblApellidos = new JLabel("Apellidos");
        lblApellidos.setFont(new Font("Arial", Font.PLAIN, 12));
        lblApellidos.setForeground(Color.WHITE);
        add(lblApellidos, "wrap");
        
        txtApellidos = new JTextField(20);
        txtApellidos.setFont(new Font("Arial", Font.PLAIN, 12));
        add(txtApellidos, "grow, wrap");
        
        // Correo Electrónico*
        JLabel lblCorreo = new JLabel("Correo Electrónico*");
        lblCorreo.setFont(new Font("Arial", Font.PLAIN, 12));
        lblCorreo.setForeground(Color.WHITE);
        add(lblCorreo, "wrap");
        
        txtCorreo = new JTextField(20);
        txtCorreo.setFont(new Font("Arial", Font.PLAIN, 12));
        add(txtCorreo, "grow, wrap");
        
        // Nombre de negocio
        JLabel lblNegocio = new JLabel("Nombre de negocio");
        lblNegocio.setFont(new Font("Arial", Font.PLAIN, 12));
        lblNegocio.setForeground(Color.WHITE);
        add(lblNegocio, "wrap");
        
        txtNegocio = new JTextField(20);
        txtNegocio.setFont(new Font("Arial", Font.PLAIN, 12));
        add(txtNegocio, "grow, wrap");
        
        // Dirección de negocio*
        JLabel lblDireccion = new JLabel("Dirección de negocio*");
        lblDireccion.setFont(new Font("Arial", Font.PLAIN, 12));
        lblDireccion.setForeground(Color.WHITE);
        add(lblDireccion, "wrap");
        
        txtDireccion = new JTextField(20);
        txtDireccion.setFont(new Font("Arial", Font.PLAIN, 12));
        add(txtDireccion, "grow, wrap");
        
        // Mensaje*
        JLabel lblMensaje = new JLabel("Mensaje*");
        lblMensaje.setFont(new Font("Arial", Font.PLAIN, 12));
        lblMensaje.setForeground(Color.WHITE);
        add(lblMensaje, "wrap");
        
        txtMensaje = new JTextArea(6, 20);
        txtMensaje.setFont(new Font("Arial", Font.PLAIN, 12));
        txtMensaje.setLineWrap(true);
        txtMensaje.setWrapStyleWord(true);
        
        JScrollPane scrollPane = new JScrollPane(txtMensaje);
        add(scrollPane, "grow, wrap");
        
        // Botón Enviar
        btnEnviar = new JButton("ENVIAR");
        btnEnviar.setBackground(Color.decode("#4A4A4A"));
        btnEnviar.setForeground(Color.WHITE);
        btnEnviar.setFont(new Font("Arial", Font.BOLD, 12));
        btnEnviar.setFocusPainted(false);
        btnEnviar.setBorderPainted(false);
        btnEnviar.setPreferredSize(new Dimension(150, 35));
        add(btnEnviar, "width 150!");
        
        setPreferredSize(new Dimension(350, 600));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Bordes redondeados
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
    }
    
    public String getNombre() {
        return txtNombre.getText();
    }
    
    public String getApellidos() {
        return txtApellidos.getText();
    }
    
    public String getCorreo() {
        return txtCorreo.getText();
    }
    
    public String getNegocio() {
        return txtNegocio.getText();
    }
    
    public String getDireccion() {
        return txtDireccion.getText();
    }
    
    public String getMensaje() {
        return txtMensaje.getText();
    }
    
    public void limpiarFormulario() {
        txtNombre.setText("");
        txtApellidos.setText("");
        txtCorreo.setText("");
        txtNegocio.setText("");
        txtDireccion.setText("");
        txtMensaje.setText("");
    }
    
    public void setEnviarListener(ActionListener listener) {
        this.enviarListener = listener;
        btnEnviar.addActionListener(listener);
    }
}
