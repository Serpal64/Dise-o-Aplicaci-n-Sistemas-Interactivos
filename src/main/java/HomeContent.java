import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import net.miginfocom.swing.MigLayout;

public class HomeContent extends JPanel {
    
    public HomeContent() {
        
        ChangeLanguage language_changer = ChangeLanguage.getInstance();
        
        setLayout(new BorderLayout());
        setBackground(Color.decode("#F3EDDF"));
        
        // Panel principal con contenido
        JPanel mainContent = new JPanel(new MigLayout(
            "insets 0, gapy 15, wrap 1",
            "[grow]",
            "20[]15[]20[]35[]5[]20[]"
        ));
        mainContent.setBackground(Color.decode("#F3EDDF"));
        
        // Título principal "SALMOREJO SHOP"
        JLabel titulo = new JLabel("SALMOREJO SHOP");
        titulo.setFont(new Font("Fraunces", Font.BOLD, 20));
        titulo.setForeground(Color.decode("#E73331"));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        language_changer.addComponent(titulo, "SalmorejShop");
        
        mainContent.add(titulo, "grow");
        
        // Subtítulo "LOS MEJORES TOMATES DE TODA LA CIUDAD"
        JTextPane subtitulo = new JTextPane();
        subtitulo.setFont(new Font("Fraunces", Font.BOLD, 14));
        subtitulo.setForeground(Color.decode("#679A3C"));
        subtitulo.setEditable(false);
        subtitulo.setSize(290, 50);
        subtitulo.setBackground(Color.decode("#F3EDDF"));

        // Centrar el texto como en el word
        StyledDocument doc = subtitulo.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        language_changer.addComponent(subtitulo, "MejoresTomates");
        
        mainContent.add(subtitulo, "grow");
        
        // Tarjeta promocional
        ChangeLanguage language_changer_promo = ChangeLanguage.getInstance();
        java.net.URL imageUrl = HomeContent.class.getResource("/images/home/huerto.png");
        ImageIcon imgHuerto = (imageUrl != null) ? new ImageIcon(imageUrl) : new ImageIcon();
        
        JTextPane textoPromoPane = new JTextPane();
        textoPromoPane.setEditable(false);
        textoPromoPane.setBackground(Color.decode("#E73331"));
        textoPromoPane.setForeground(Color.WHITE);
        textoPromoPane.setFont(new Font("Fraunces", Font.PLAIN, 16));
        textoPromoPane.setText("Tenemos los huertos\nmás ecológicos y sin\nutilizar pesticidas. ¿a\nqué esperas?");
        textoPromoPane.setFocusable(false);
        
        language_changer_promo.addComponent(textoPromoPane, "TextoPromocion");
        
        // Centrar el texto
        javax.swing.text.StyledDocument doc2 = textoPromoPane.getStyledDocument();
        javax.swing.text.StyleConstants.setBold(center, true);
        doc2.setParagraphAttributes(0, doc.getLength(), center, false);
        
        PromotionalCard tarjetaPromo = new PromotionalCard(imgHuerto, textoPromoPane, "");
        mainContent.add(tarjetaPromo, "growx, gapright 10, gapleft 10");

        
        // Sección "Nuestros mejores productos"
        JLabel seccionProductos = new JLabel("Nuestros mejores productos");
        seccionProductos.setFont(new Font("Fraunces", Font.BOLD, 16));
        seccionProductos.setForeground(Color.decode("#583E35"));
        seccionProductos.setHorizontalAlignment(SwingConstants.CENTER);
        language_changer.addComponent(seccionProductos, "NuestrosMejoresProductos");
        
        // Separador visual
        JSeparator separador = new JSeparator();
        separador.setBorder(BorderFactory.createLineBorder(Color.decode("#E73331"), 2));
        separador.setForeground(Color.decode("#E73331"));
        
        mainContent.add(seccionProductos, "grow, align left");
        mainContent.add(separador, "grow, align center");
        
        // Carrusel de productos

        ImageIcon[] imagenes = new ImageIcon[]{
            new ImageIcon(HomeContent.class.getResource("/images/home/tomate1.png")),
            new ImageIcon(HomeContent.class.getResource("/images/home/tomate2.png")),
            new ImageIcon(HomeContent.class.getResource("/images/home/tomate3.png")),
            new ImageIcon(HomeContent.class.getResource("/images/home/tomate4.png"))
        };
        ProductCarousel carrusel = new ProductCarousel(imagenes);
        mainContent.add(carrusel, "grow");
        
        add(mainContent, BorderLayout.CENTER);
    }

}
