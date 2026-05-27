import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class ProductCarousel extends JPanel {
    
    private static final int BORDER_RADIUS = 15;
    private int currentIndex = 0;
    private ImageIcon[] images;
    private JLabel imageLabel;
    
    public ProductCarousel(ImageIcon[] imagenes) {
        
        this.images = imagenes;
        
        setLayout(new MigLayout(
            "insets 0, gap 0, fillx, filly",
            "[22!][grow][22!]",
            "[grow]"
        ));
        
        setBackground(Color.decode("#E73331"));
        setOpaque(false);
        
        // Panel central para la imagen
        JPanel imagenPanel = new JPanel(new MigLayout(
            "insets 0",
            "[grow]",
            "[grow]"
        ));
        imagenPanel.setBackground(Color.decode("#E73331"));
        imagenPanel.setOpaque(false);
        
        // Etiqueta para la imagen actual
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        
        actualizarImagen();
        
        imagenPanel.add(imageLabel, "grow");
        
        // Botón izquierdo
        JButton btnIzquierda = crearBotonNavegacion("<");
        btnIzquierda.addActionListener(e -> {
            currentIndex = (currentIndex - 1 + images.length) % images.length;
            actualizarImagen();
        });
        
        // Botón derecho
        JButton btnDerecha = crearBotonNavegacion(">");
        btnDerecha.addActionListener(e -> {
            currentIndex = (currentIndex + 1) % images.length;
            actualizarImagen();
        });
        
        add(btnIzquierda, "grow");
        add(imagenPanel, "grow");
        add(btnDerecha, "grow");
    }
    
    private JButton crearBotonNavegacion(String texto) {
        JButton boton = new JButton(texto);
        boton.setBackground(Color.decode("#E73331"));
        boton.setForeground(Color.WHITE);
        boton.setFont(boton.getFont().deriveFont(28f));
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setContentAreaFilled(true);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setMargin(new Insets(0, 0, 0, 0));
        boton.setOpaque(true);
        
        // Agregar efecto hover
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                boton.setBackground(Color.decode("#C92321"));
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                boton.setBackground(Color.decode("#E73331"));
            }
        });
        
        return boton;
    }
    
    private void actualizarImagen() {
        if (images != null && images.length > 0) {
            // Obtener dimensiones actuales
            int panelWidth = getWidth();
            int panelHeight = getHeight();
            
            // Calcular tamaño de la imagen (ancho total - 2 botones)
            int imgWidth = Math.max(100, panelWidth - 44);
            int imgHeight = panelHeight > 0 ? panelHeight : 280;
            
            Image imgEscalada = images[currentIndex].getImage()
                    .getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(imgEscalada));
        }
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Dibujar fondo redondeado
        g2d.setColor(Color.decode("#E73331"));
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), BORDER_RADIUS, BORDER_RADIUS);
        
        g2d.dispose();
        super.paintComponent(g);
    }
}
