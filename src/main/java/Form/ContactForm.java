package Form;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import UI.ChangeLanguage;

public class ContactForm extends JPanel {
    
    private final JTextField nombre;
    private final JTextField apellidos;
    private final JTextField correo;
    private final JTextField negocio;
    private final JTextField direccion;
    private final JTextArea mensaje;
    private final JButton boton_enviar;
    private final CardLayout cardLayout;
    private final JPanel content;
    
    public ContactForm(CardLayout cardLayout, JPanel content) {
        this.cardLayout = cardLayout;
        this.content = content;
        
        ChangeLanguage language_changer = ChangeLanguage.getInstance();
        
        setBackground(Color.decode("#E73331"));
        setLayout(new MigLayout("insets 30, fillx, gapy 15", "[grow]", ""));
        
        // Título
        JLabel lblTitulo = new JLabel("Contáctanos");
        lblTitulo.setFont(new Font("Fraunces", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        language_changer.addComponent(lblTitulo, "Contactanos");
        add(lblTitulo, "wrap");
        
        // Nombre*
        JLabel lblNombre = new JLabel("Nombre*");
        lblNombre.setFont(new Font("Fraunces", Font.PLAIN, 12));
        lblNombre.setForeground(Color.WHITE);
        language_changer.addComponent(lblNombre, "Nombre");
        add(lblNombre, "wrap");
        
        nombre = new JTextField(20);
        nombre.setFont(new Font("Fraunces", Font.PLAIN, 12));
        add(nombre, "grow, wrap");
        
        // Apellidos
        JLabel lblApellidos = new JLabel("Apellidos");
        lblApellidos.setFont(new Font("Fraunces", Font.PLAIN, 12));
        lblApellidos.setForeground(Color.WHITE);
        language_changer.addComponent(lblApellidos, "Apellidos");
        add(lblApellidos, "wrap");
        
        apellidos = new JTextField(20);
        apellidos.setFont(new Font("Fraunces", Font.PLAIN, 12));
        add(apellidos, "grow, wrap");
        
        // Correo Electrónico*
        JLabel lblCorreo = new JLabel("Correo Electrónico*");
        lblCorreo.setFont(new Font("Fraunces", Font.PLAIN, 12));
        lblCorreo.setForeground(Color.WHITE);
        language_changer.addComponent(lblCorreo, "CorreoElectronico");
        add(lblCorreo, "wrap");
        
        correo = new JTextField(20);
        correo.setFont(new Font("Fraunces", Font.PLAIN, 12));
        add(correo, "grow, wrap");
        
        // Nombre de negocio
        JLabel lblNegocio = new JLabel("Nombre de negocio");
        lblNegocio.setFont(new Font("Fraunces", Font.PLAIN, 12));
        lblNegocio.setForeground(Color.WHITE);
        language_changer.addComponent(lblNegocio, "NombreNegocio");
        add(lblNegocio, "wrap");
        
        negocio = new JTextField(20);
        negocio.setFont(new Font("Fraunces", Font.PLAIN, 12));
        add(negocio, "grow, wrap");
        
        // Dirección de negocio*
        JLabel lblDireccion = new JLabel("Dirección de negocio*");
        lblDireccion.setFont(new Font("Fraunces", Font.PLAIN, 12));
        lblDireccion.setForeground(Color.WHITE);
        language_changer.addComponent(lblDireccion, "DireccionNegocio");
        add(lblDireccion, "wrap");
        
        direccion = new JTextField(20);
        direccion.setFont(new Font("Fraunces", Font.PLAIN, 12));
        add(direccion, "grow, wrap");
        
        // Mensaje*
        JLabel lblMensaje = new JLabel("Mensaje*");
        lblMensaje.setFont(new Font("Fraunces", Font.PLAIN, 12));
        lblMensaje.setForeground(Color.WHITE);
        language_changer.addComponent(lblMensaje, "Mensaje");
        add(lblMensaje, "wrap");
        
        mensaje = new JTextArea(6, 20);
        mensaje.setFont(new Font("Fraunces", Font.PLAIN, 12));
        mensaje.setLineWrap(true);
        mensaje.setWrapStyleWord(true);
        
        JScrollPane scrollPane = new JScrollPane(mensaje);
        add(scrollPane, "grow, wrap");
        
        // Botón Enviar
        boton_enviar = new JButton("ENVIAR");
        boton_enviar.setBackground(Color.decode("#4A4A4A"));
        boton_enviar.setForeground(Color.WHITE);
        boton_enviar.setFont(new Font("Fraunces", Font.BOLD, 12));
        boton_enviar.setFocusPainted(false);
        boton_enviar.setBorderPainted(false);
        boton_enviar.setPreferredSize(new Dimension(150, 35));
        language_changer.addComponent(boton_enviar, "Enviar");
        boton_enviar.addActionListener(e -> enviarFormulario());
        add(boton_enviar, "align center, gapy 20");
        
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
        return nombre.getText();
    }
    
    public String getApellidos() {
        return apellidos.getText();
    }
    
    public String getCorreo() {
        return correo.getText();
    }
    
    public String getNegocio() {
        return negocio.getText();
    }
    
    public String getDireccion() {
        return direccion.getText();
    }
    
    public String getMensaje() {
        return mensaje.getText();
    }
    
    public void limpiarFormulario() {
        nombre.setText("");
        apellidos.setText("");
        correo.setText("");
        negocio.setText("");
        direccion.setText("");
        mensaje.setText("");
    }
    
    public void enviarFormulario() {
        FormValidator.ValidationResult validation = 
            FormValidator.validateForm(getNombre(), getCorreo(), getDireccion(), getMensaje());
        
        if (validation.isValid) {
            limpiarFormulario();
            cardLayout.show(content, "formCorrect");
        } else {
            cardLayout.show(content, "formError");
        }
    }
}
