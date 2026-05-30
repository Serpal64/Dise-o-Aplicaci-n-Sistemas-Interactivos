import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Home implements NavigationListener {
    
    private JFrame jf;
    private JPanel mainPanel;
    private HomeContent homeContent;
    private ContactForm contactForm;
    private FormCorrect formCorrect;
    private FormError formError;
    
    public Home() {
        jf = new JFrame("Home");

        BorderLayout bl = new BorderLayout(5, 5);

        // Panel principal de la vista
        mainPanel = new JPanel(bl);
        mainPanel.setBackground(Color.decode("#F3EDDF"));

        Header header = new Header(this);
        mainPanel.add(header, BorderLayout.NORTH);

        homeContent = new HomeContent();
        mainPanel.add(homeContent, BorderLayout.CENTER);

        jf.add(mainPanel);
        jf.setSize(390, 844);
        jf.setResizable(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);    
    }
    
    @Override
    public void goToHome() {
        mainPanel.removeAll();
        mainPanel.add(new Header(this), BorderLayout.NORTH);
        mainPanel.add(homeContent, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    
    @Override
    public void goToContact() {
        if (contactForm == null) {
            contactForm = new ContactForm(this);
        }
        mainPanel.removeAll();
        mainPanel.add(new Header(this), BorderLayout.NORTH);
        mainPanel.add(contactForm, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    
    @Override
    public void showFormCorrect() {
        if (formCorrect == null) {
            formCorrect = new FormCorrect(this);
        }
        mainPanel.removeAll();
        mainPanel.add(new Header(this), BorderLayout.NORTH);
        mainPanel.add(formCorrect, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
    
    @Override
    public void showFormError(String errorMessage) {
        if (formError == null) {
            formError = new FormError(errorMessage, this);
        } else {
            // Actualizar con el nuevo mensaje de error
            formError = new FormError(errorMessage, this);
        }
        mainPanel.removeAll();
        mainPanel.add(new Header(this), BorderLayout.NORTH);
        mainPanel.add(formError, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public static void main(String[] args) throws Exception {
        new Home();
    }
}