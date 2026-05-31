import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.plaf.basic.BasicButtonUI;

import net.miginfocom.swing.MigLayout;

public class ProductCarousel extends JPanel {
    
    private int currentIndex = 0;
    private ImageIcon[] images;
    private final JLabel imageLabel;
    private int lastWidth = -1;
    private int lastHeight = -1;
    
    public ProductCarousel(ImageIcon[] imagenes) {
        
        this.images = imagenes;
        
        setLayout(new MigLayout(
            "insets 0, gap 0, fillx",
            "[40!][grow][40!]",
            "[grow]"
        ));
        
        setBackground(Color.decode("#E73331"));
        setOpaque(false);
        setPreferredSize(new Dimension(340, 225));
        // Panel central para la imagen
        JPanel imagenPanel = new JPanel(new MigLayout(
            "insets 0",
            "[center]",
            "[center]"
        ));
        imagenPanel.setBackground(Color.decode("#E73331"));
        imagenPanel.setOpaque(false);
        
        // Etiqueta para la imagen actual
        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        
        actualizarImagen(300 - 80, 280);
        
        imagenPanel.add(imageLabel, "grow");
        
        // Botón izquierdo
        JButton btnIzquierda = crearBotonNavegacion("<");
        btnIzquierda.addActionListener(e -> {
            currentIndex = (currentIndex - 1 + images.length) % images.length;
            actualizarImagen(getWidth() - 80, getHeight());
        });
        
        // Botón derecho
        JButton btnDerecha = crearBotonNavegacion(">");
        btnDerecha.addActionListener(e -> {
            currentIndex = (currentIndex + 1) % images.length;
            actualizarImagen(getWidth() - 80, getHeight());
        });
        
        add(btnIzquierda, "grow");
        add(imagenPanel, "grow");
        add(btnDerecha, "grow");
        
        // Actualizar imagen cuando cambia el tamaño del componente
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if (getWidth() > 0 && getHeight() > 0) {
                    if (lastWidth != getWidth() || lastHeight != getHeight()) {
                        lastWidth = getWidth();
                        lastHeight = getHeight();
                        actualizarImagen(getWidth() - 80, getHeight());
                    }
                }
            }
        });
    }
    
    private JButton crearBotonNavegacion(String texto) {

        JButton boton = new JButton(texto);
        boton.setUI(new BasicButtonUI()); // Para quitar el estilo de Flatlaf
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
    
    private void actualizarImagen(int imgWidth, int imgHeight) {
        if (images != null && images.length > 0) {
            // Validar dimensiones
            imgWidth = Math.max(100, imgWidth);
            imgHeight = Math.max(200, imgHeight);
            
            Image imgEscalada = images[currentIndex].getImage()
                    .getScaledInstance(imgWidth, imgHeight, Image.SCALE_SMOOTH);
            imageLabel.setIcon(new ImageIcon(imgEscalada));
        }
    }
    
}

