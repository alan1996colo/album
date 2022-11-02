public class Figurita implements Cloneable{
String nombre;
String tipoDeFigu;//Tradicional,Web o Extendido.
String pais;
private int valorBase=10;//elijo un valor base de todas las figus.

int rankingValorBase;//ranking de la figu
int numeroQueIdentifica;//numero unico de la figurita
public Figurita(String nombre,String tipoDeFigu, String pais,int rankingValorBase,  int numeroQueIdentifica){
    this.nombre=nombre;
    this.tipoDeFigu=tipoDeFigu;
    this.pais=pais;
    this.rankingValorBase=rankingValorBase;
    this.numeroQueIdentifica=numeroQueIdentifica;


}
/** Calcula el valor final de la figurita dividiendo su valor base por el ranking
 * 
 * @return
 */
public float ValorFinal(){//metodo polimorfico, la subclase lo va modificar.
    return valorBase/rankingValorBase;// se que la consigna dice "sumando al valor base el ranking del pais" , pero no tiene sentido que
    /*No tiene sentido que figuritas con mejor ranking valgan menos que figuritas con peor ranking, es decir el precio deberia ser inverso.
     * por lo tanto use dividir por el ranking para obtener el valor.
     */
}
public void setTipo(String tipoFigu){
    if(tipoFigu!="Tradicional" & tipoFigu!="Extendido"&tipoFigu!="Web"){throw Exception("Error el tipo de figurita no es valido.");}
    this.tipoDeFigu=tipoFigu;
}

@Override
protected Object clone() throws CloneNotSupportedException {
  
    Figurita nueva= new Figurita (this.nombre,this.tipoDeFigu,this.pais,this.rankingValorBase, this.numeroQueIdentifica);
    return nueva;
  }

}