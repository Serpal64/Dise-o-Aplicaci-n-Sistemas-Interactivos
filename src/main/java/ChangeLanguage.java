import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JMenuItem;

public class ChangeLanguage implements ActionListener{

    private final Locale[] locales;
    private int currentLocaleIndex;
    private final List<JComponent> components;
    private final List<String> keys;

    public ChangeLanguage(){

        this.locales = new Locale[]{new Locale("es", "ES"), new Locale("en", "GB")};
        this.components = new ArrayList<>();
        this.keys = new ArrayList<>();
        this.currentLocaleIndex = 1;        // Idioma por defecto en español

    }

    public void addComponent(JComponent comp, String key){
        components.add(comp);
        keys.add(key);
    }

    @Override
    public void actionPerformed(ActionEvent e){

        currentLocaleIndex = (currentLocaleIndex + 1) % locales.length;
        Locale nextLocale = locales[currentLocaleIndex];
        ResourceBundle bundle_text = ResourceBundle.getBundle("bundle.Bundle", nextLocale);

        for(int i=0; i<components.size(); i++){
            
            if(components.get(i) instanceof JLabel jLabel){
                jLabel.setText(bundle_text.getString(keys.get(i)));
            }
            else if(components.get(i) instanceof JButton jButton) {
                jButton.setText(bundle_text.getString(keys.get(i)));
            }
            else if(components.get(i) instanceof JMenuItem jMItem){
                jMItem.setText(bundle_text.getString(keys.get(i)));

                // if("Idioma".equals(keys.get(i))){
                //     System.out.println("images/header_menu/" + nextLocale.getLanguage() + ".png");
                //     ImageIcon img_lang = new ImageIcon(ChangeLanguage.class.getResource("/images/header/" + nextLocale.getLanguage() + ".png"));
                //     jMItem.setIcon(img_lang);
                // }
            }

        }
    }


}
