import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class AppButton extends JButton{
    
    // Quitar estilo feo del java swing normal
    private void removeStyle(){

        setBorderPainted(false);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setMargin(new Insets(0, 0, 0, 0));
        setCursor(new Cursor(Cursor.HAND_CURSOR));

    }

    // Constructor para botones con sólo imágenes o iconos
    public AppButton(ImageIcon icon, int width, int height){

        super();
        Image scaled = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(scaled));
        removeStyle();

    }

    // Constructor para botones normales con texto
    public AppButton(String txt, String back_color, String font_color){

        super(txt);
        setBackground(Color.decode(back_color));
        setForeground(Color.decode(font_color));
        setFocusPainted(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        putClientProperty("JButton.buttonType", "roundRect");

    }

}
