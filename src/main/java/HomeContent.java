import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;

public class HomeContent extends JPanel {
    
    public HomeContent() {
        
        setLayout(new BorderLayout());
        setBackground(Color.decode("#F3EDDF"));
        
        // Panel principal con contenido
        JPanel mainContent = new JPanel(new MigLayout(
            "insets 20, gap 15, fillx, wrap 1",
            "[grow]",
            "[]15[]15[]15[]20[]"
        ));
        mainContent.setBackground(Color.decode("#F3EDDF"));
        
        // Título principal "SALMOREJO SHOP"
        JLabel titulo = new JLabel("SALMOREJO SHOP");
        titulo.setFont(new Font("Fraunces", Font.BOLD, 20));
        titulo.setForeground(Color.decode("#E73331"));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        
        mainContent.add(titulo, "grow");
        
        // Subtítulo "LOS MEJORES TOMATES DE TODA LA CIUDAD"
        JLabel subtitulo = new JLabel("LOS MEJORES TOMATES DE TODA LA CIUDAD");
        subtitulo.setFont(new Font("Fraunces", Font.BOLD, 14));
        subtitulo.setForeground(Color.decode("#679A3C"));
        subtitulo.setHorizontalAlignment(SwingConstants.CENTER);
        
        mainContent.add(subtitulo, "grow");
        
        // Tarjeta promocional
        try {
            ImageIcon imgHuerto = new ImageIcon(HomeContent.class.getResource("images/home/huerto.png"));
            String textoPromo = "Tenemos los huertos\nmás ecológicos y sin\nutilizar pesticidas. ¿a\nqué esperas?";
            PromotionalCard tarjetaPromo = new PromotionalCard(imgHuerto, textoPromo);
            mainContent.add(tarjetaPromo, "grow, h 204!");
        } catch (Exception e) {
            // Si no existe la imagen, crear un placeholder
            JPanel placeholderPromo = new JPanel();
            placeholderPromo.setBackground(Color.decode("#E73331"));
            mainContent.add(placeholderPromo, "grow, h 204!");
        }
        
        // Sección "Nuestros mejores productos"
        JLabel seccionProductos = new JLabel("Nuestros mejores productos");
        seccionProductos.setFont(new Font("Fraunces", Font.BOLD, 16));
        seccionProductos.setForeground(Color.decode("#583E35"));
        seccionProductos.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Separador visual
        JPanel separador = new JPanel();
        separador.setBackground(Color.decode("#E73331"));
        
        mainContent.add(seccionProductos, "grow");
        mainContent.add(separador, "grow, h 2!");
        
        // Carrusel de productos
        try {
            ImageIcon[] imagenes = new ImageIcon[]{
                new ImageIcon(HomeContent.class.getResource("images/home/tomate1.png")),
                new ImageIcon(HomeContent.class.getResource("images/home/tomate2.png")),
                new ImageIcon(HomeContent.class.getResource("images/home/tomate3.png")),
                new ImageIcon(HomeContent.class.getResource("images/home/tomate4.png"))
            };
            ProductCarousel carrusel = new ProductCarousel(imagenes);
            mainContent.add(carrusel, "grow, h 280!");
        } catch (Exception e) {
            // Si no existen las imágenes, crear un placeholder
            JPanel placeholderCarrusel = new JPanel();
            placeholderCarrusel.setBackground(Color.decode("#E73331"));
            mainContent.add(placeholderCarrusel, "grow, h 280!");
        }
        
        // ScrollPane para contenido que no cabe
        JScrollPane scroll = new JScrollPane(mainContent);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        scroll.getVerticalScrollBar().setUnitIncrement(10);
        scroll.setAutoscrolls(true);
        
        add(scroll, BorderLayout.CENTER);
    }
}
