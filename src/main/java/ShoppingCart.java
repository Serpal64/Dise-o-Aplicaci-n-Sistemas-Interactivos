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

        boolean new_product = true;

        for(int i=0; i<lista_productos.size(); i++){

            if(producto.getNombre().equals(lista_productos.get(i).getNombre())){
                
                cantidades.set(i, cantidad);
                new_product = false;
                break;

            }

        }

        if(new_product == true){
            lista_productos.add(producto);
            cantidades.add(cantidad);
        }

    }

    public List<ProductDetails> getProducts(){
        return lista_productos;
    }

    public List<Integer> getAmounts(){
        return cantidades;
    }

    public float getTotalPrice(){

        float sum=0;

        for(int i=0; i<lista_productos.size(); i++){

            sum += lista_productos.get(i).getPrecio() * cantidades.get(i);

        }

        return sum;

    }

    public void buyProducts(){
        lista_productos = new ArrayList<>();
        cantidades = new ArrayList<>();
    }

}
