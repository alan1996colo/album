import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
public abstract class Album {
    //String tipoAlbum;
    
    String tipoAlbum;
    public String getTipoAlbum() {
        System.out.println("se ejecuto el metodo get delpadre");
      return this.tipoAlbum;
   }



    int cantPaises=32;//esto quizas lo saquemos por lo de la fabrica
    int codigoUnico;
    int dniDueño;//esto quizas despues lo saquemos
    String nombreDueño;
    Figurita coleccion[];
    ArrayList<Figurita> figuritasSinpegar=new ArrayList<>();
    int cantJugadores=12;//esto quizas lo quitamos por lo de la fabrica.
    Map<String ,Boolean> paisesCompletos=new HashMap<String,Boolean>() ;
    String premioFinal;
    boolean AlbumCompleto;
    
    public boolean isAlbumCompleto() {
        return AlbumCompleto;
    }
    public abstract void premioFinal();
    public String toString(){return "";}
    /**
     * Agrega una figurita a la lista de figuritas sin pegar
     * @param figu
     */
    public void agregarFigu(Figurita figu){figuritasSinpegar.add(figu);}
    public List<String>  verificarYpegarFigus(){//Falta implementar xd
    };
    public void mostrarSinpegar(){
        System.out.println(figuritasSinpegar.toString());
    };
    public void mostrarRepetidas(){};




     public static void main(String[] args) {System.out.println("jajaja");
        
    }

}
