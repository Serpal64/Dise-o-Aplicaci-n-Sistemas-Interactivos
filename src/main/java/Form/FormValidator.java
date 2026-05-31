package Form;

public class FormValidator {
    
    public static class ValidationResult {
        public boolean isValid;
        public String errorMessage;
        
        public ValidationResult(boolean isValid, String errorMessage) {
            this.isValid = isValid;
            this.errorMessage = errorMessage;
        }
    }
    
    public static ValidationResult validateForm(String nombre, String correo, String direccion, String mensaje) {
        // Validar nombre obligatorio
        if (nombre == null || nombre.trim().isEmpty()) {
            return new ValidationResult(false, "El nombre es obligatorio");
        }
        
        // Validar email obligatorio
        if (correo == null || correo.trim().isEmpty()) {
            return new ValidationResult(false, "El correo electrónico es obligatorio");
        }
        
        // Validar email con @
        if (!correo.contains("@")) {
            return new ValidationResult(false, "El correo electrónico debe contener @");
        }
        
        // Validar dirección obligatoria
        if (direccion == null || direccion.trim().isEmpty()) {
            return new ValidationResult(false, "La dirección de negocio es obligatoria");
        }
        
        // Validar mensaje obligatorio
        if (mensaje == null || mensaje.trim().isEmpty()) {
            return new ValidationResult(false, "El mensaje es obligatorio");
        }
        
        return new ValidationResult(true, "");
    }
}
