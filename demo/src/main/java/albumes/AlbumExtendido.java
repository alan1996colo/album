package albumes;

import java.util.ArrayList;
import java.util.ListIterator;

public class AlbumExtendido extends AlbumTradicional {

  private int cantExtend = 20;
  FiguritaTop10 coleccion20[] = new FiguritaTop10[cantExtend];

  public AlbumExtendido(int codigoUnico, String nombreDueno, int dniDueno, String paisesParticipantes[]) {
    super(codigoUnico, nombreDueno, dniDueno, paisesParticipantes);
    // TODO Auto-generated constructor stub
   // this.tipoAlbum = "Extendido";

  }

  public String darpremioFinal() {
    return "Se gano una pelota y un viaje";
  };

  private void verificar() {
    boolean albumcompleto = true;// esto va decir si se completo el album
    if (this.coleccion.length < 1) {
      albumcompleto = false;
    } // si por alguna razon no quiere entrar en el array, me aseguro que diga que
      // esta incompleto
    if (this.coleccion20.length < 1) {
      albumcompleto = false;
    } // paso extendido

    int contador = 0;
    for (int i = 0; i < this.coleccion.length; i++) {
      if (i % 12 == 0) {
        contador = 0;
      } // si paso de pagina se resetea el contador
      if (this.coleccion[i] != null) {
        contador = contador + 1;
      } else {
        contador = 0;// si encuentro una figu nula ,se resetea el contador.
        albumcompleto = false;// y va decir que esta incompleto
      }

      if (contador == 12) {
        paisesCompletos.put(this.coleccion[i].getPais(), true);
        contador = 0;// Esto quizas sea redundante porque si es igual a 12 quiere decir que pase de
                     // pagina.
      }
    }

    // reviso la parte de top10

    for (int i = 0; i < this.coleccion20.length; i++) {
      if (this.coleccion20[i] == null) {
        albumcompleto = false;
      }
    }

    // cuando llego a este punto el booleano ya deberia haber cambiado a false, o
    // directamente estar en true como desde el principio.
    this.AlbumCompleto = albumcompleto;
  }

  /**
   * Revisa si el album esta completo
   * si no revisa las figuritas de la lista de figuritas sin pegar que sean del
   * mismo tipo del álbum actual,
   * y revisa si es posible colocarlas en la coleccion[], si es posible, “pega”
   * (las quita de la lista sin pegar y agrega a la coleccion[]), cambia el
   * booleano de los países completos,
   * si todos están completos cambia a true el valor albumCompleto y finalmente
   * devuelve la lista de las que se pudieron pegar
   * 
   * @return
   */
  public ArrayList<String> verificarYpegarFigus() {// bueno aca use Iterator asi que no se pueden quejar
    ArrayList<String> ret = new ArrayList<String>();// esta es la lista a retornar.
    ListIterator<Figurita> algo = figuritasSinpegar.listIterator();// el iterador
    // int checkPoint=0;
    Figurita actual;// va almacenar el iterador actual.

    if (this.AlbumCompleto == true) {
      return null;
    } else {

      while (algo.hasNext()) {
        // checkPoint=checkPoint+1;
        actual = algo.next();// uso esto porque si llamo a algo.next() mas de una vez pasa al siguiente en el
                             // mismo ciclo
        if (actual.getNumeroQueIdentifica() < coleccion.length && coleccion[actual.getNumeroQueIdentifica()] == null
            && actual.getTipoDeFigu().equals("Extendido")
            
            
            ) {// si la posicion es null, quiere decir que la figu
                                                                    // no esta pegada.
          // o la figu esta pegada mal o la figu no esta pegada. ademas las figuritas
          // deben ser del mismo tipo
          coleccion[actual.getNumeroQueIdentifica()] = actual;// agrego al array, espero no me de problema de ejecucion.
          ret.add(actual.toString());// Agrego la figu a la lista de retorno
          algo.remove();// y lo quito de la lista, espero no de problemas y tenga que usar iterator
                        // xdxdxd

        }

        else if (actual.getNumeroQueIdentifica() < coleccion.length
            && !coleccion[actual.getNumeroQueIdentifica()].equals(actual)) {// si esta pegada pero es distinta.
          System.out.println("ups, lo sentimos algo salio mal y pegaron una figurita en el lugar equivocado.");
        } // Esperemos que esto nunca pase.
        // Si esta pegada pero es igual no se hace nada

        // caso 2, es de la parte extendida
        else if (!(actual.getNumeroQueIdentifica() < coleccion.length)
            && coleccion20[actual.getNumeroQueIdentifica() - 384] == null
            && actual.getTipoDeFigu().equals("Extendido")) {

          coleccion20[actual.getNumeroQueIdentifica() - 384] = (FiguritaTop10) actual;// agrego al array, espero no me
                                                                                      // de problema de ejecucion.
          ret.add(actual.toString());// Agrego la figu a la lista de retorno
          algo.remove();

        }

      }

    }
    verificar();// obviamente despues de pegar las figus, verificamos si completamos el album y
                // cambiamos el estado
    return ret;
  }

  /*
   * Este metodo no se pide, pero sirve para llevar el control de lo que va
   * haciendo el programa
   */
  public void mostrarPegadas() {
    for (int i = 0; i < coleccion.length; i++) {
      if (coleccion[i] != null) {
        System.out.println("[" + coleccion[i].toString() + "-" + i + "] ");
      }
    }
    for (int i = 0; i < coleccion20.length; i++) {
      if (coleccion20[i] != null) {
        System.out.println("[" + coleccion20[i].toString() + "-" + i + "]");
      }
    }
  }

  public float porcentajeCompleto(){
    if(this.AlbumCompleto==true){return 100;}
    float count=0;
    for (int i =0;i<coleccion.length;i++) {
        if(coleccion[i]!=null){
            count=count+1;
        }
    }
    for(int i=0;i<coleccion20.length;i++){
      if(coleccion20[i]!=null){
        count=count+1;
      }
    }
    
    return (count/(((float)coleccion.length)+(float)coleccion20.length))*100;
}

}