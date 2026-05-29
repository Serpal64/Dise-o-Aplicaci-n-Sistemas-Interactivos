
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import net.miginfocom.swing.MigLayout;

public class ShoppingCartPanel extends JPanel{
    
    private final CardLayout cardLayout;
    private final JPanel content;

    public ShoppingCartPanel(CardLayout cardLayout, JPanel content){
        
        this.cardLayout = cardLayout;
        this.content = content;

        ChangeLanguage language_manager = ChangeLanguage.getInstance();

        ShoppingCart cart = ShoppingCart.getInstance();

        setLayout(new BorderLayout());
        setBackground(Color.decode("#F3EDDF"));

        JPanel main_panel = new JPanel();
        main_panel.setBackground(Color.decode("#F3EDDF"));

        if(cart.getTotalPrice() == 0){

            main_panel.setLayout(new MigLayout(
                "insets 20, fillx, wrap 1",
                "[grow]",
                "[center]"
            ));

            JTextPane no_products = new JTextPane();
            no_products.setEditable(false);
            no_products.setPreferredSize(new Dimension(290, 140));
            no_products.setBackground(Color.decode("#F3EDDF"));
            no_products.setForeground(Color.decode("#583E35"));

            StyledDocument doc = no_products.getStyledDocument();
            SimpleAttributeSet center = new SimpleAttributeSet();
            StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
            doc.setParagraphAttributes(0, doc.getLength(), center, false);

            no_products.setFont(new Font("Fraunces", Font.PLAIN, 20));
            language_manager.addComponent(no_products, "SinProductos");

            main_panel.add(no_products, "align center");

        }else{

            main_panel.setLayout(new MigLayout());

        }

        add(main_panel, BorderLayout.CENTER);

    }

}
