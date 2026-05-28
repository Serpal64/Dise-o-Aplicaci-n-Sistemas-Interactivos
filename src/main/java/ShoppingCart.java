import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    
    private static ShoppingCart instancia;
    private List<ProductDetails> lista_productos;
    private List<Integer> cantidades;

    public ShoppingCart(){
        lista_productos = new ArrayList<>();
        cantidades = new ArrayList<>();
    }

    public static ShoppingCart getInstance(){

        if(instancia == null){
            instancia = new ShoppingCart();
        }

        return instancia;
    }

    public void addProduct(ProductDetails producto, Integer cantidad){

        lista_productos.add(producto);
        cantidades.add(cantidad);

    }

    public void buyProducts(){
        lista_productos = new ArrayList<>();
        cantidades = new ArrayList<>();
    }

}
