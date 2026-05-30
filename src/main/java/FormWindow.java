import java.awt.event.ActionEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FormWindow {
    
    private JDialog dialog;
    private ContactForm contactForm;
    private FormCorrect formCorrect;
    private FormError formError;
    private JPanel currentPanel;
    
    public FormWindow(JFrame parent) {
        dialog = new JDialog(parent, "Contacto", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setResizable(false);
        
        contactForm = new ContactForm();
        contactForm.setEnviarListener(this::handleEnviar);
        
        formCorrect = new FormCorrect();
        formCorrect.setVerProductosListener(e -> {
            dialog.dispose();
        });
        
        showContactForm();
    }
    
    private void showContactForm() {
        dialog.getContentPane().removeAll();
        dialog.add(contactForm);
        currentPanel = contactForm;
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
    
    private void showFormCorrect() {
        dialog.getContentPane().removeAll();
        dialog.add(formCorrect);
        currentPanel = formCorrect;
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.repaint();
    }
    
    private void showFormError(String errorMessage) {
        dialog.getContentPane().removeAll();
        formError = new FormError(errorMessage);
        formError.setReintentarListener(e -> showContactForm());
        dialog.add(formError);
        currentPanel = formError;
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.repaint();
    }
    
    private void handleEnviar(ActionEvent e) {
        String nombre = contactForm.getNombre();
        String correo = contactForm.getCorreo();
        String direccion = contactForm.getDireccion();
        String mensaje = contactForm.getMensaje();
        
        FormValidator.ValidationResult validation = 
            FormValidator.validateForm(nombre, correo, direccion, mensaje);
        
        if (validation.isValid) {
            contactForm.limpiarFormulario();
            showFormCorrect();
        } else {
            showFormError(validation.errorMessage);
        }
    }
    
    public void show() {
        if (!dialog.isVisible()) {
            showContactForm();
        }
    }
}
