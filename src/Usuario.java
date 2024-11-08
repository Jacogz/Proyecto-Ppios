import java.util.ArrayList;

public class Usuario {
    private int idUsuario;
    private String nombreCompleto;
    private int numeroIdentificacion;
    private String correoElectronico;
    private String contrasena;
    private int tipo;
    private ArrayList<Integer> recientes;

    public Usuario(int idUsuario, String nombreCompleto, int numeroIdentificacion, String correoElectronico, String contrasena, int tipo){
        this.idUsuario = idUsuario;
        this.nombreCompleto = nombreCompleto;
        this.numeroIdentificacion = numeroIdentificacion;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
        this.tipo = tipo;
        this.recientes = new ArrayList<Integer>();
    }

    public boolean verificarContrasena(String contrasenas){
        if(this.contrasena.equals(contrasenas)){
            return true;
        }else{
            return false;
        }
    }
    
    public int getId(){
        return this.idUsuario;
    }
    public String getNombre(){
        return this.nombreCompleto;
    }
    public int getNumeroId(){
        return this.numeroIdentificacion;
    }
    public String getCorreo(){
        return this.correoElectronico;
    }
    public int getTipo(){
        return this.tipo;
    }
    public ArrayList<Integer> getRecientes(){
        return this.recientes;
    }
    
    public void agregarConsulta(int idArticulo){
        if(!this.recientes.contains(idArticulo)){
            this.recientes.add(idArticulo);
        }
    }

    @Override
    public String toString() {
        return "id: " + idUsuario + " | " + "nombre: " + nombreCompleto + " | " + "tipo: " + tipo + "\n";
    }
}
