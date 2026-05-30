import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import net.miginfocom.swing.MigLayout;

public class PromotionalCard extends JPanel {
    
    private static final int BORDER_RADIUS = 24;

    public PromotionalCard(ImageIcon imagen, String texto) {
        this(imagen, null, texto);
    }
    
    public PromotionalCard(ImageIcon imagen, JTextPane textoPane, String texto) {
        
        setLayout(new MigLayout(
            "insets 15, fillx, filly",
            "[100!][grow]",
            "[grow]"
        ));
        
        setBackground(Color.decode("#E73331"));
        setOpaque(false);
        
        // Escalar imagen a tamaño adecuado
        Image imgEscalada = imagen.getImage().getScaledInstance(100, 140, Image.SCALE_SMOOTH);
        JLabel imagenLabel = new JLabel(new ImageIcon(imgEscalada));
        imagenLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imagenLabel.setVerticalAlignment(SwingConstants.CENTER);
        
        // Texto promocional
        if (textoPane == null) {
            textoPane = new JTextPane();
            textoPane.setEditable(false);
            textoPane.setBackground(Color.decode("#E73331"));
            textoPane.setForeground(Color.WHITE);
            textoPane.setFont(textoPane.getFont().deriveFont(14f));
            textoPane.setText(texto);
            textoPane.setFocusable(false);
            textoPane.setMargin(new Insets(5, 5, 5, 5));
            
            // Centrar el texto
            StyledDocument doc = textoPane.getStyledDocument();
            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            StyleConstants.setBold(center, true);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);
        }
        
        add(imagenLabel, "aligny center, gapright 10");
        add(textoPane, "grow, aligny center");
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
