import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JTextPane;

public class ChangeLanguage implements ActionListener{

    private static ChangeLanguage instance;
    private final Locale[] locales;
    private int currentLocaleIndex;
    private final Map<JComponent, String> components;

    public ChangeLanguage(){

        this.locales = new Locale[]{new Locale("es", "ES"), new Locale("en", "GB")};
        this.components = new LinkedHashMap<>();
        this.currentLocaleIndex = 1;        // Idioma por defecto en español

    }

    public void printComponents(){

        components.forEach((comp, key) -> {System.out.println(key);});

        System.out.println();

    }

    public static ChangeLanguage getInstance() {
        if (instance == null) instance = new ChangeLanguage();
        return instance;
    }

    public void addComponent(JComponent comp, String key){

        Locale locale = locales[currentLocaleIndex];
        ResourceBundle bundle_text = ResourceBundle.getBundle("bundle.Bundle", locale);
        
        components.putIfAbsent(comp, key);

        applyText(comp, key, bundle_text);
    }

    public void removeComponent(JComponent comp){

        components.remove(comp);
        
    }

    private void applyText(JComponent comp, String key, ResourceBundle bundle_text) {


        if(comp instanceof JLabel jLabel){
            jLabel.setText(bundle_text.getString(key));
        }
        else if(comp instanceof JButton jButton) {
            jButton.setText(bundle_text.getString(key));
        }
        else if(comp instanceof JMenuItem jMItem){
            jMItem.setText(bundle_text.getString(key));
            ImageIcon icon;

            if("Idioma".equals(key)){
                if(currentLocaleIndex == 0)
                    icon = new ImageIcon(Main.class.getResource("images/header_menu/es.png"));
                else
                    icon = new ImageIcon(Main.class.getResource("images/header_menu/en.png"));
            }else{
                icon = new ImageIcon(Main.class.getResource("images/header_menu/contact.png"));
            }

            jMItem.setIcon(icon);
        }
        else if(comp instanceof JTextPane jTextPane){
            jTextPane.setText(bundle_text.getString(key));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){

        currentLocaleIndex = (currentLocaleIndex + 1) % locales.length;
        Locale nextLocale = locales[currentLocaleIndex];
        ResourceBundle bundle_text = ResourceBundle.getBundle("bundle.Bundle", nextLocale);

        components.forEach((comp, key) -> applyText(comp, key, bundle_text));
    }


}
