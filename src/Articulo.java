public class Articulo {
    private int idArticulo;
    private String descripcion;
    private int cantidad;
    private int serial;
    private String categoria;
    private int disponibles;
    private int idEncargado;
    
    public Articulo(int idArticulo, String descripcion, int cantidad, int serial, String categoria, int idEncargado){
        this.idArticulo = idArticulo;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.serial = serial;
        this.categoria = categoria;
        this.disponibles = cantidad;
        this.idEncargado = idEncargado;
    }

    public int getId(){
        return this.idArticulo;
    }
    public String getDescripcion(){
        return this.descripcion;
    }
    public int getCantidad(){
        return this.cantidad;
    }
    public int getSerial(){
        return this.serial;
    }
    public String getCategoria(){
        return this.categoria;
    }
    public int getDisponibles(){
        return this.disponibles;
    }
    public int getEncargado(){
        return this.idEncargado;
    }

    public void prestarArticulo(int cantidad){
        if(this.disponibles - cantidad > 0){
            this.disponibles -= cantidad;
        }else{
            System.out.println("Inventario insuficiente | " + this.disponibles + " unidades disponibles.");
        }
    }

    public void retornarArticulo(int cantidad){
        this.disponibles += cantidad;
    }
}
