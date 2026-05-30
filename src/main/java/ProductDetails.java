import javax.swing.ImageIcon;

public class ProductDetails {
    
    private String nombre;
    private ImageIcon imagen;
    private String descripcion;
    private float precio;

    public ProductDetails(String nombre, ImageIcon imagen, String descripcion, float precio){

        this.nombre = nombre;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.precio = precio;

    }

    public String getNombre() {
        return nombre;
    }

    public ImageIcon getImagen() {
        return imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setImagen(ImageIcon imagen) {
        this.imagen = imagen;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    

}
