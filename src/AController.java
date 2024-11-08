import java.util.HashMap;
import java.util.ArrayList;
public class AController {
    private HashMap<Integer, Articulo> articulos = new HashMap<>();

    public void crearArticulo(Articulo articulo){
        articulos.put(articulo.getId(), articulo);
    }

    public void eliminarArticulo(int idArticulo){
        if(articulos.remove(idArticulo) != null){
            System.out.println("Articulo eliminado correctamente.");
        }else{
            System.out.println("Articulo no encontrado.");
        }
    }
    
    public void actualizarArticulo(Articulo articuloActualizado){
        if(articulos.containsKey(articuloActualizado.getId())){
            articulos.put(articuloActualizado.getId(), articuloActualizado);
        }
    }
    
    public Articulo consultarPorId(int id){
        return articulos.get(id);
    }

    public HashMap<Integer, Articulo> consultarTodos(){
        return articulos;
    }

    public HashMap<Integer, Articulo> consultarCategoria(String categoria){
        HashMap<Integer, Articulo> resultados = new HashMap<>();
        articulos.forEach((id, art)->{if(art.getCategoria().equals(categoria)){resultados.put(art.getId(), art);}});
        return resultados;
    }

    public HashMap<Integer, Articulo> consultarDisponibles(){
        HashMap<Integer, Articulo> resultados = new HashMap<>();
        articulos.forEach((id, art)->{if(art.getDisponibles()>0){resultados.put(art.getId(), art);}});
        return resultados;
    }

    public HashMap<Integer, Articulo> consultarPorEncargado(int idEncargado){
        HashMap<Integer, Articulo> resultados = new HashMap<>();
        articulos.forEach((id, art)->{if(art.getEncargado()==idEncargado){resultados.put(art.getId(), art);}});
        return resultados;
    }

    public HashMap<Integer, Articulo> consultarEnLista(ArrayList<Integer> ids){
        HashMap<Integer, Articulo> resultados = new HashMap<>();
        articulos.forEach((id, art)->{if(ids.contains(id)){resultados.put(art.getId(), art);}});
        return resultados;
    }
}
