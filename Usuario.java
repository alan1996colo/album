

//Diseño actual
//AlbumDelMundial<--->Usuario-->Album{Tradicional,Web,Extendido} 



import java.util.Set;

import javax.swing.table.TableRowSorter;

public class Usuario <T extends Album>{//el Atributo album propio puede ser cualquier tipo de album
int dni;
String nombre;

T albumpropio5;
public T getAlbumpropio(){return albumpropio5;}
public void setAlbumpropio5(T alb){this.albumpropio5=alb;}



public Usuario(int dni, String nombre){
    if(dni<1|99999999<dni){throw new ArithmeticException("No se pudo registrar el Participante, razon: Dni invalido");}
    this.dni=dni;
    this.nombre=nombre;
}

boolean solicitarAlbum(String tipoAlbum){return false;}
void comprarFigus(String tipoDeFigu){}
void vermicodigoweb(){}
void comprarfigusconcodigoweb(){}
List<String>  solicitarPegarFigus(){
    return albumpropio.verificarYpegarFigus();
}
/**
 * Le pregunta al album si esta completo, True =Si, False=No.
*/
boolean completeElAlbum(){
    return this.albumpropio.isAlbumCompleto();
}
void solicitarPremio(){}
public String toString()
{return this.nombre +" "+String.valueOf(this.dni);}
boolean solicitarIntercambio(String nombreFigu){return false;}
void verMisFigusRepet(){}
void mostrarSinpegar(){}
void quienCompletoElPais(String pais){}

}