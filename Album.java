import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
public abstract class Album {
    //String tipoAlbum;
    
    String tipoAlbum;
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

    public Album(){
        
    };

    public String getTipoAlbum() {
        System.out.println("se ejecuto el metodo get delpadre");
      return this.tipoAlbum;
   }
    
    public boolean isAlbumCompleto() {
        return AlbumCompleto;
    }
    public abstract void premioFinal();
    public String toString(){return "";}
/**
 * Revisa el album en las secciones que aun no esta completo y si se encuentran completas cambia el estado del map paises completos a 
 * True,y si ademas toda los paises estan completos cambia el estado de AlbumCompleto a True;
 */
private void verificar(){


}


    /**
     * Agrega una figurita a la lista de figuritas sin pegar
     * @param figu
     */
    public void agregarFigu(Figurita figu){figuritasSinpegar.add(figu);}
    /**Revisa si el album esta completo   
     * si no revisa las figuritas de la lista de figuritas sin pegar que sean del mismo tipo del álbum actual,
     *  y revisa si es posible colocarlas en la coleccion[], si es posible, “pega” 
     * (las quita de la lista sin pegar y agrega a la coleccion[]), cambia el booleano de los países completos,
     *  si todos están completos cambia a true el valor albumCompleto y finalmente devuelve la lista de las que se pudieron pegar
     * @return
     */
    public List<String>  verificarYpegarFigus(){//bueno aca use Iterator asi que no se pueden quejar
        ArrayList<String> ret =new ArrayList<String>();//esta es la lista a retornar.
        ListIterator<Figurita> algo=figuritasSinpegar.listIterator();// el iterador
        //int checkPoint=0;
        Figurita actual;// va almacenar el iterador actual.

        if (this.AlbumCompleto==true){System.out.println("Album lleno, nada para agregar.");return null;}
        else{ 
            
            while (algo.hasNext()) {
              //  checkPoint=checkPoint+1;
                actual=algo.next();//uso esto porque si llamo a algo.next() mas de una vez pasa al siguiente en el mismo ciclo
            if(coleccion[actual.getNumeroQueIdentifica() ]==null&& actual.getTipoDeFigu().equals(this.getTipoAlbum())){//si la posicion es null, quiere decir que la figu no esta pegada.
                //o la figu esta pegada mal o la figu no esta pegada. ademas las figuritas deben ser del mismo tipo
                coleccion[actual.getNumeroQueIdentifica()]=actual;//agrego al array, espero no me de problema de ejecucion.
                    ret.add(actual.toString());//Agrego la figu a la lista de retorno
                    algo.remove();//y lo quito de la lista, espero no de problemas y tenga que usar iterator xdxdxd
                             
                                }
                else if (!coleccion[actual.getNumeroQueIdentifica()].equals(actual)){//si esta pegada pero es distinta.
                    System.out.println("ups, lo sentimos algo salio mal y pegaron una figurita en el lugar equivocado.");}//Esperemos que esto nunca pase.
                     //Si esta pegada pero es igual no se hace nada   
            }
            
            
        }
        //System.out.println("Cant veces entro en while = "+checkPoint);
        return ret;

            //falta implementar la parte de verificar si el album esta completo y cambiar el estado booleano de AlbumCOmpleto
        }




    
    public void mostrarSinpegar(){
        System.out.println(figuritasSinpegar.toString());
    };
    public void mostrarRepetidas(){};




     public static void main(String[] args) {System.out.println("jajaja");
        
    }

}
