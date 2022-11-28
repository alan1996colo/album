
package albumes;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import java.util.ListIterator;
public abstract class Album {
    //String tipoAlbum;
    //String codigoWeb;
    
    String tipoAlbum;
    int cantPaises=32;//esto quizas lo saquemos por lo de la fabrica
    int cantJugadores=12;
    int codigoUnico;//Esto se usa para sortear el premio instantaneo
    int dniDueño;//esto quizas despues lo saquemos
    String nombreDueño;
    Figurita coleccion[]=new Figurita[cantPaises*cantJugadores];//384
    ArrayList<Figurita> figuritasSinpegar=new ArrayList<>();
    //esto quizas lo quitamos por lo de la fabrica.
    Map<String ,Boolean> paisesCompletos=new HashMap<String,Boolean>() ; 
    
    //descatado:---/Uso un solo map con integers
    // el map me devuelve la posicion del pais y si la posicion es -1 significa que el pais esta completo.

    
    boolean premioFinal=false;
    boolean AlbumCompleto=false;

    public Album(){
        
    };

    public String getTipoAlbum() {
        //System.out.println("se ejecuto el metodo get delpadre");
      return this.tipoAlbum;
   }
    
    public boolean isAlbumCompleto() {
        return AlbumCompleto;
    }
    public abstract String darpremioFinal();
    public String toString(){return "";}
/**
 * Revisa el album en las secciones que aun no esta completo y si se encuentran completas cambia el estado del map paises completos a 
 * True,y si ademas toda los paises estan completos cambia el estado de AlbumCompleto a True;
 */
private void verificar(){
    boolean albumcompleto=true;//esto va decir si se completo el album
    if(this.coleccion.length<1){albumcompleto=false;}//si por alguna razon no quiere entrar en el array, me aseguro que diga que esta incompleto
    int contador=0;
    for(int i=0;i<this.coleccion.length;i++){
        if(i%12==0){contador=0;}//si paso de pagina se resetea el contador
        if(this.coleccion[i]!=null){
            contador=contador+1;
        }
        else{contador=0;//si encuentro una figu nula ,se resetea el contador.
            albumcompleto=false;//y va decir que esta incompleto
        }
       
        if(contador==12){
            paisesCompletos.put(this.coleccion[i].getPais(), true);
            contador=0;//Esto quizas sea redundante porque si es igual a 12 quiere decir que pase de pagina.
        }
    }
    //cuando llego a este punto el booleano ya deberia haber cambiado a false, o directamente estar en true como desde el principio.
    this.AlbumCompleto=albumcompleto;
}


    /**
     * Agrega una figurita a la lista de figuritas sin pegar
     * @param figu
     */
    public void agregarFigu(Figurita figu){figuritasSinpegar.add(figu);}
    public void quitarFigu(Figurita figu){figuritasSinpegar.remove(figu);}
    /**Revisa si el album esta completo   
     * si no revisa las figuritas de la lista de figuritas sin pegar que sean del mismo tipo del álbum actual,
     *  y revisa si es posible colocarlas en la coleccion[], si es posible, “pega” 
     * (las quita de la lista sin pegar y agrega a la coleccion[]), cambia el booleano de los países completos,
     *  si todos están completos cambia a true el valor albumCompleto y finalmente devuelve la lista de las que se pudieron pegar
     * @return
     */
    public ArrayList<String>  verificarYpegarFigus(){//bueno aca use Iterator asi que no se pueden quejar
        ArrayList<String> ret =new ArrayList<String>();//esta es la lista a retornar.
        ListIterator<Figurita> algo=figuritasSinpegar.listIterator();// el iterador
        //int checkPoint=0;
        Figurita actual;// va almacenar el iterador actual.

        if (this.AlbumCompleto==true){//System.out.println("Album lleno, nada para agregar.");
        return null;}
        else{ 
            
            while (algo.hasNext()) {
              //  checkPoint=checkPoint+1;
                actual=algo.next();//uso esto porque si llamo a algo.next() mas de una vez pasa al siguiente en el mismo ciclo
            if(actual.getNumeroQueIdentifica()<coleccion.length && coleccion[actual.getNumeroQueIdentifica() ]==null&& actual.getTipoDeFigu().equals(this.getTipoAlbum())){//si la posicion es null, quiere decir que la figu no esta pegada.
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
        verificar();//obviamente despues de pegar las figus, verificamos si completamos el album y cambiamos el estado
        return ret;

            //falta implementar la parte de verificar si el album esta completo y cambiar el estado booleano de AlbumCOmpleto
        }


public void mostrarPegadas(){
    for(int i=0;i<coleccion.length;i++){
        System.out.println("["+coleccion[i].toString()+"-"+i+"]");
    }
}

    
    public void mostrarSinpegar(){
        System.out.println(figuritasSinpegar.toString());
    };

    private boolean estaRepetidaEnsinPegar(Figurita figu){//revisa si esta repetida entre las sin pegar
        int cont=0;
        for (Figurita iter : figuritasSinpegar) {
            if(figu.equals(iter)){
                cont=cont+1;
            }
            if(cont>1){return true;}
         }
    return false;}


    /**
     * Busca en las figus sin pegar si hay alguna repetida y devuelve el codigo de la primera que encuentre.
     */
    public int buscarRepetidas(){
        for (Figurita iter : figuritasSinpegar) {
            if(this.contains(iter)||estaRepetidaEnsinPegar(iter)){
                return iter.getNumeroQueIdentifica();
            }
            
        }
        return -1;
    };

    /**
     * Muestra si el album tiene la figu pegada.
     * @param figu
     * @return
     */
    public boolean contains(Figurita figu){
        if(coleccion[figu.getNumeroQueIdentifica()]!=null){//si la posicion no es null, esta ocupada por una carta, que deberia ser la correcta.
            return true;
        }

        return false;
    }
    public boolean containsFiguSinPegar(int numFigu){
        for (Figurita figurita : figuritasSinpegar) {
            if(figurita.getNumeroQueIdentifica()==numFigu){
                return true;
            }
            //Se me ocurre un problema en el que el tipo de figuritas sea distinto al de
            //la persona a la que se la voy a cambiar, mas adelante veo como lo soluciono
            //quizas se solucione en otro metodo.
        }
        return false;
    }
    public ArrayList<Figurita> figusRepetidas(){
        ArrayList<Figurita> figuritasRep=new ArrayList<>();
        for (Figurita iter : figuritasSinpegar) {
            if(estaRepetidaEnsinPegar(iter)||this.contains(iter)){
                Figurita clon=(Figurita)iter.clone();
                figuritasRep.add(clon);//por las dudas no vaya a ser que modifique el arrayList original, despues lo veo.
            }
            
        }
        return figuritasRep;


        
    }
    public Figurita getFigurita(int numFig){
        for (Figurita iterable_element : figuritasSinpegar) {
            if(iterable_element.getNumeroQueIdentifica()==numFig){
                return iterable_element;
            }
            
        }
        return null;
    }
    public Boolean completePais(String pais){
        return paisesCompletos.get(pais);
    }




     public static void main(String[] args) {System.out.println("Esto ni me acuerdo porque lo puse");
        
    }

}
