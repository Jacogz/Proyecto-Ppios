import java.util.HashMap;

public class App {
    public static void main(String[] args) {
        Usuario sesionUsuario;
        AController articulos = new AController();
        UController usuarios = new UController();

        Articulo art1 = new Articulo(1, "art1", 5, 1, "general", 2);
        Articulo art2 = new Articulo(2, "art2", 5, 2, "general", 2);
        Articulo art3 = new Articulo(3, "art3", 0, 3, "generico", 2);
        articulos.crearArticulo(art1);
        articulos.crearArticulo(art2);
        articulos.crearArticulo(art3);

        Usuario user1 = new Usuario(1, "Fulanito", 1, "123@gmail.com", "Fulanito123", 0);
        Usuario user2 = new Usuario(2, "Fulanita", 2, "345@gmail.com", "Fulanita123", 1);
        usuarios.registrar(user1);
        usuarios.registrar(user2);

        sesionUsuario = usuarios.login("123@gmail.com", "Fulanita123");
        sesionUsuario = usuarios.login("234@gmail.com", "Fulanita123");

        
    }
    
} 
