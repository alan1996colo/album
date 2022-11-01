import java.util.ArrayList;
import java.util.Map;

public abstract class Album {
    String tipoAlbum;
    int cantPaises=32;//esto quizas lo saquemos por lo de la fabrica
    String codigoUnico;
    int dniDueño;//esto quizas despues lo saquemos
    String nombreDueño;
    Figurita coleccion[];
    ArrayList<Figurita> figuritasSinpegar;
    int cantJugadores=12;//esto quizas lo quitamos por lo de la fabrica.
    Map<String ,Boolean> paisesCompletos=new HashMap<String,Boolean>() ;
    String premioFinal;
    boolean AlbumCompleto;
    
    public boolean isAlbumCompleto() {
        return AlbumCompleto;
    }
    public abstract void premioFinal();
    public String toString(){return "";}
    public void agregarFigu(){};
    public void verificarYpegarFigus(){};
    public void mostrarSinpegar(){};
    public void mostrarRepetidas(){};




     public static void main(String[] args) {System.out.println("jajaja");
        
    }

}
