import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
public abstract class Album {
    //String tipoAlbum;
    
    String tipoAlbum;
    public String getTipoAlbum() {
        System.out.println("se ejecuto el metodo get delpadre");
      return this.tipoAlbum;
   }



    int cantPaises=32;//esto quizas lo saquemos por lo de la fabrica
    int cantJugadores=12;
    int codigoUnico;
    int dniDueño;//esto quizas despues lo saquemos
    String nombreDueño;
    Figurita coleccion[]=new Figurita[cantPaises*cantJugadores];
    ArrayList<Figurita> figuritasSinpegar=new ArrayList<>();
    //esto quizas lo quitamos por lo de la fabrica.
    Map<String ,Boolean> paisesCompletos=new HashMap<String,Boolean>() ;
    String premioFinal;
    boolean AlbumCompleto=false;
    
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
    /**Revisa si el album esta completo   
     * si no revisa las figuritas de la lista de figuritas sin pegar que sean del mismo tipo del álbum actual,
     *  y revisa si es posible colocarlas en la coleccion[], si es posible, “pega” 
     * (las quita de la lista sin pegar y agrega a la coleccion[]), cambia el booleano de los países completos,
     *  si todos están completos cambia a true el valor albumCompleto y finalmente muestra por pantalla el toString de las figuritas que se pegaron.
     * 
     * @return
     */
    public List<String>  verificarYpegarFigus(){
        if (this.AlbumCompleto==true){System.out.println("Album lleno, nada para agregar.");return null;}
        else{  List<String> ret =new List<String>();//esta es la lista a retornar.
            for (Figurita algo : figuritasSinpegar) {
            if(!coleccion[algo.getNumeroQueIdentifica()].equals(algo)){//si son iguales, la figu ya esta pegada. si son distintos
                //o la figu esta pegada mal o la figu no esta pegada.
                if(coleccion[algo.getNumeroQueIdentifica()]!=null){//si la figu no esta pegada.
                    coleccion[algo.getNumeroQueIdentifica()]=algo;//agrego al array, espero no me de problema de ejecucion.
                    ret.add(algo.toString());//Agrego la figu a la lista de retorno
                    figuritasSinpegar.remove(algo);//y lo quito de la lista, espero no de problemas y tenga que usar iterator xdxdxd
                                }
                else{
                    System.out.println("ups, lo sentimos algo salio mal y pegaron una figurita en el lugar equivocado.");}//Esperemos que esto nunca pase.

            }
            
            
        }
        return ret;

            //falta implementar la parte de verificar si el album esta completo y cambiar el estado booleano de AlbumCOmpleto
        }




    };
    public void mostrarSinpegar(){
        System.out.println(figuritasSinpegar.toString());
    };
    public void mostrarRepetidas(){};




     public static void main(String[] args) {System.out.println("jajaja");
        
    }

}
