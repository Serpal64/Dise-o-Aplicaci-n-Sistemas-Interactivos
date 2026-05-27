
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import net.miginfocom.swing.MigLayout;

public class Header extends JPanel{
    
    public Header(){

        // Para cambiar el idioma
        ChangeLanguage language_changer = ChangeLanguage.getInstance();

        // El navbar de arriba
        setLayout(new MigLayout("insets 20 20 5 15, fillx, gap 0", "[][grow][]", "[center]"));
        setPreferredSize(new Dimension(390, 85));
        setBackground(Color.decode("#E73331"));

        // Botón menú desplegable
        ImageIcon img_menu = new ImageIcon(Header.class.getResource("images/header/menu.png"));
        AppButton menu = new AppButton(img_menu, img_menu.getIconWidth(), img_menu.getIconHeight());

        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem contact = new JMenuItem("Contacto");
        language_changer.addComponent(contact, "Contacto");

        // Este menú cambia el idioma
        JMenuItem language = new JMenuItem("Idioma");
        language_changer.addComponent(language, "Idioma");
        language.addActionListener(e -> language_changer.actionPerformed(e));

        popupMenu.add(contact);
        popupMenu.add(language);

        // Abrir el menú al hacer clic en el botón
        menu.addActionListener(e -> {
            popupMenu.show(menu, 0, menu.getHeight()); // aparece justo debajo del botón
        });

        // Etiqueta de buscador (meramente estético)
        ImageIcon img_search = new ImageIcon(Header.class.getResource("images/header/search_bar.png"));
        JLabel search = new JLabel(img_search);

        // Panel derecho con icono home, productos y carrito
        JPanel header_right = new JPanel(new MigLayout("insets 0, gap 5", "[][][]", "[center]"));
        header_right.setBackground(Color.decode("#E73331"));

        ImageIcon img_home   = new ImageIcon(Header.class.getResource("images/header/home.png"));
        ImageIcon img_tomato = new ImageIcon(Header.class.getResource("images/header/tomato.png"));
        ImageIcon img_basket = new ImageIcon(Header.class.getResource("images/header/basket.png"));

        AppButton home   = new AppButton(img_home,   img_home.getIconWidth(),   img_home.getIconHeight());
        AppButton tomato = new AppButton(img_tomato, img_tomato.getIconWidth(), img_tomato.getIconHeight());
        AppButton basket = new AppButton(img_basket, img_basket.getIconWidth(), img_basket.getIconHeight());

        header_right.add(home);
        header_right.add(tomato);
        header_right.add(basket);

        add(menu,         "");
        add(search,       "growx, gapright 0");   // se estira para ocupar el centro
        add(header_right, "");

        // Para inicializar los idiomas
        language_changer.actionPerformed(new ActionEvent(basket, 0, null));
    }

}
