import java.util.HashMap;
public class UController {
    private HashMap<Integer, Usuario> usuarios = new HashMap<>();

    public void registrar(Usuario usuario){
        usuarios.put(usuario.getId(), usuario);
    }

    public Usuario login(String correo, String contrasena){
        for(Usuario u : usuarios.values()){
            if(u.getCorreo() == correo && u.verificarContrasena(contrasena)){
                return u;
            }
        }
        return null;
    }
}
