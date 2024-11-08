import java.util.Scanner;
import java.util.HashMap;

public class App {
    private static Scanner scanner = new Scanner(System.in);
    private static AController articuloController = new AController();
    private static UController usuarioController = new UController();
    public static Usuario usuarioLogeado = null;

    public static void main(String[] args) {
        Articulo art1 = new Articulo(1, "art1", 5, 1, "general", 2);
        Articulo art2 = new Articulo(2, "art2", 5, 2, "general", 2);
        Articulo art3 = new Articulo(3, "art3", 0, 3, "generico", 2);
        articuloController.crearArticulo(art1);
        articuloController.crearArticulo(art2);
        articuloController.crearArticulo(art3);

        Usuario user1 = new Usuario(1, "Fulanito", 1, "123@gmail.com", "Fulanito123", 0);
        Usuario user2 = new Usuario(2, "Fulanita", 2, "345@gmail.com", "Fulanita123", 1);
        usuarioController.registrar(user1);
        usuarioController.registrar(user2);

        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner
            switch (opcion) {
                case 1:
                    manejarUsuarios();
                    break;
                case 2:
                    manejarArticulos();
                    break;
                case 0:
                    System.out.println("Saliendo de la aplicación...");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } while (opcion != 0);
    }

    // Mostrar menú principal
    private static void mostrarMenu() {
        System.out.println("\n--- Menú Principal ---");
        System.out.println("1. Gestión de Usuarios");
        System.out.println("2. Gestión de Artículos");
        System.out.println("0. Salir");
        System.out.print("Elige una opción: ");
    }

    // Submenú para gestionar usuarios
    private static void manejarUsuarios() {
        int opcion;
        do {
            System.out.println("\n--- Menú de Usuarios ---");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Iniciar sesión");
            System.out.println("0. Volver al menú principal");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            switch (opcion) {
                case 1:
                    registrarUsuario();
                    break;
                case 2:
                    loginUsuario();
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } while (opcion != 0);
    }

    // Registrar un nuevo usuario
    private static void registrarUsuario() {
        // Aquí puedes pedir los atributos necesarios para crear un usuario
        System.out.print("Introduce el ID del usuario: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        System.out.print("Introduce el nombre del usuario: ");
        String nombre = scanner.nextLine();
        System.out.print("Introduce el numero de identificacion del usuario: ");
        int numeroIdentificacion = scanner.nextInt();
        System.out.print("Introduce el correo del usuario: ");
        String correo = scanner.nextLine();
        System.out.print("Introduce la contraseña del usuario: ");
        String contrasena = scanner.nextLine();
        System.out.print("Introduce el tipo usuario (0-normal 1-encargado): ");
        int tipo = scanner.nextInt();

        Usuario nuevoUsuario = new Usuario(id, nombre, numeroIdentificacion, correo, contrasena, tipo);
        usuarioController.registrar(nuevoUsuario);
        usuarioLogeado = nuevoUsuario;
        System.out.println("Usuario registrado y logeado exitosamente.");
    }

    // Iniciar sesión con un usuario
    private static void loginUsuario() {
        System.out.print("Introduce tu correo: ");
        String correo = scanner.nextLine();
        System.out.print("Introduce tu contraseña: ");
        String contrasena = scanner.nextLine();

        usuarioLogeado = usuarioController.login(correo, contrasena);
        if (usuarioLogeado != null) {
            System.out.println("Bienvenido, " + usuarioLogeado.getNombre() + "!");
        } else {
            System.out.println("Correo o contraseña incorrectos.");
        }
    }

    // Submenú para gestionar artículos
    private static void manejarArticulos() {
        int opcion;
        do {
            System.out.println("\n--- Menú de Artículos ---");
            System.out.println("1. Crear artículo");
            System.out.println("2. Eliminar artículo");
            System.out.println("3. Actualizar artículo");
            System.out.println("4. Consultar artículo por ID");
            System.out.println("5. Consultar todos los artículos");
            System.out.println("6. Consultar artículos por categoría");
            System.out.println("7. Consultar artículos disponibles");
            System.out.println("8. Prestar artículo");
            System.out.println("9. Retornar artículo");
            System.out.println("10. Ver recientes");
            System.out.println("11. Ver mis articulos");
            System.out.println("0. Volver al menú principal");
            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            switch (opcion) {
                case 1:
                    if(validarEncargado()){
                        crearArticulo();
                    }
                    break;
                case 2:
                    if(validarEncargado()){
                        eliminarArticulo();
                    }
                    break;
                case 3:
                    if(validarEncargado()){
                        actualizarArticulo();
                    }
                    break;
                case 4:
                    consultarArticuloPorId();
                    break;
                case 5:
                    consultarTodosArticulos();
                    break;
                case 6:
                    consultarArticulosPorCategoria();
                    break;
                case 7:
                    consultarArticulosDisponibles();
                    break;
                case 8:
                    prestarArticulo();
                    break;
                case 9:
                    retornarArticulo();
                    break;
                case 10:
                    consultarRecientes();
                    break;
                case 11:
                    if(validarEncargado()){
                        consultarArticulosPorEncargado();
                    }
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...");
                    break;
                default:
                    System.out.println("Opción no válida. Intenta de nuevo.");
            }
        } while (opcion != 0);
    }

    // Crear un nuevo artículo
    private static void crearArticulo() {
        // Aquí puedes pedir los atributos necesarios para crear un artículo
        System.out.print("Introduce el ID del artículo: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        System.out.print("Introduce la descripcion del artículo: ");
        String descripcion = scanner.nextLine();
        System.out.print("Introduce la cantidad de unidades: ");
        int cantidad = scanner.nextInt();
        System.out.print("Introduce el serial del artículo: ");
        int serial = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Introduce la categoría del artículo: ");
        String categoria = scanner.nextLine();
        System.out.print("Introduce el id de encargado: ");
        int idEncargado = scanner.nextInt();

        Articulo nuevoArticulo = new Articulo(id, descripcion, cantidad, serial, categoria, idEncargado);
        articuloController.crearArticulo(nuevoArticulo);
        System.out.println("Artículo creado exitosamente.");
    }

    // Eliminar un artículo
    private static void eliminarArticulo() {
        System.out.print("Introduce el ID del artículo a eliminar: ");
        int id = scanner.nextInt();
        if(validarAcceso(id)){
            articuloController.eliminarArticulo(id);
        }
    }

    // Actualizar un artículo
    private static void actualizarArticulo() {
        // Aquí puedes pedir los atributos necesarios para actualizar el artículo
        System.out.print("Introduce el ID del artículo a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        if(validarAcceso(id)){
            System.out.print("Introduce la descripcion del artículo: ");
            String descripcion = scanner.nextLine();
            System.out.print("Introduce la cantidad de unidades: ");
            int cantidad = scanner.nextInt();
            System.out.print("Introduce el serial del artículo: ");
            int serial = scanner.nextInt();
            System.out.print("Introduce la categoría del artículo: ");
            scanner.nextLine();
            String categoria = scanner.nextLine();
            System.out.print("Introduce el id de encargado: ");
            int idEncargado = scanner.nextInt();
    
            Articulo articuloActualizado = new Articulo(id, descripcion, cantidad, serial, categoria, idEncargado);
            articuloController.actualizarArticulo(articuloActualizado);
            System.out.println("Artículo actualizado exitosamente.");
        }
    }

    // Consultar artículo por ID
    private static void consultarArticuloPorId() {
        System.out.print("Introduce el ID del artículo: ");
        int id = scanner.nextInt();
        Articulo articulo = articuloController.consultarPorId(id);
        if (articulo != null) {
            if(usuarioLogeado != null){
                usuarioLogeado.agregarConsulta(id);
            }
            System.out.println(articulo);
        } else {
            System.out.println("Artículo no encontrado.");
        }
    }

    // Consultar todos los artículos
    private static void consultarTodosArticulos() {
        HashMap<Integer, Articulo> todosArticulos = articuloController.consultarTodos();
        todosArticulos.forEach((id, articulo) -> System.out.println(articulo));
    }

    // Consultar artículos por categoría
    private static void consultarArticulosPorCategoria() {
        System.out.print("Introduce la categoría: ");
        String categoria = scanner.nextLine();
        HashMap<Integer, Articulo> articulosPorCategoria = articuloController.consultarCategoria(categoria);
        if (!articulosPorCategoria.isEmpty()) {
            articulosPorCategoria.forEach((id, articulo) -> System.out.println(articulo));
        } else {
            System.out.println("No se encontraron artículos en esta categoría.");
        }
    }

    //Consultar artículos por encargado
    private static void consultarArticulosPorEncargado() {
        HashMap<Integer, Articulo> articulosPorCategoria = articuloController.consultarPorEncargado(usuarioLogeado.getId());
        if (!articulosPorCategoria.isEmpty()) {
            articulosPorCategoria.forEach((id, articulo) -> System.out.println(articulo));
        } else {
            System.out.println("Aún no tienes artículos");
        }
    }

    // Consultar artículos disponibles
    private static void consultarArticulosDisponibles() {
        HashMap<Integer, Articulo> articulosDisponibles = articuloController.consultarDisponibles();
        if (!articulosDisponibles.isEmpty()) {
            articulosDisponibles.forEach((id, articulo) -> System.out.println(articulo));
        } else {
            System.out.println("No hay artículos disponibles.");
        }
    }

    //Consultar artículos recientes
    private static void consultarRecientes(){
        if(usuarioLogeado != null){
            HashMap<Integer, Articulo> articulosRecientes = articuloController.consultarEnLista(usuarioLogeado.getRecientes());
            if (!articulosRecientes.isEmpty()) {
                articulosRecientes.forEach((id, articulo) -> System.out.println(articulo));
            } else {
                System.out.println("Aún no hay artículos recientes.");
        }
        }
    }
    private static void prestarArticulo(){
        System.out.print("Introduce el ID del artículo: ");
        int id = scanner.nextInt();
        Articulo articulo = articuloController.consultarPorId(id);
        if (articulo != null) {
            System.out.println("Introduce la cantidad que deseas prestar. max: " + articulo.getDisponibles());
            int cantidad = scanner.nextInt();
            articulo.prestarArticulo(cantidad);
        } else {
            System.out.println("Artículo no encontrado.");
        }
    }
    private static void retornarArticulo(){
        System.out.print("Introduce el ID del artículo: ");
        int id = scanner.nextInt();
        Articulo articulo = articuloController.consultarPorId(id);
        if (articulo != null) {
            System.out.println("Introduce la cantidad que deseas retornar. max: " + (articulo.getCantidad() - articulo.getDisponibles()));
            int cantidad = scanner.nextInt();
            articulo.retornarArticulo(cantidad);
        } else {
            System.out.println("Artículo no encontrado.");
        }
    }

    private static boolean validarEncargado(){
        if(usuarioLogeado != null && usuarioLogeado.getTipo() == 1){
            return true;
        }else{
            System.out.println("No tienes acceso a esta función");
            return false;
        }
    }
    private static boolean validarAcceso(int idArticulo){
        if(validarEncargado()){
            Articulo articulo = articuloController.consultarPorId(idArticulo);
            if(articulo.getEncargado() == usuarioLogeado.getId()){
                System.out.println("No tienes acceso a este artículo");
                return true;
            }
        }
        return false;
    }
}

