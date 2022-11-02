

//Diseño actual
//AlbumDelMundial<--->Usuario-->Album{Tradicional,Web,Extendido} 



import java.util.Set;

import javax.swing.table.TableRowSorter;

public class Usuario{
int dni;
String nombre;
AlbumTradicional albumpropio;
AlbumExtendido albumpropio1;
AlbumWeb albumpropio2;
public AlbumTradicional getAlbumpropio() {
    return this.albumpropio;
}
public AlbumExtendido getAlbumpropi() {
    return this.albumpropio1;
}

public AlbumWeb getAlbumprop() {
    return this.albumpropio2;
}//Esto no me gusta, per no encuentro la forma de que almacene un dato de tipo T o tipo Album, y que despues se transforme en lo que quiero
//asi que termino almacenando los 3 tipos de albumes




public void setAlbumpropio(AlbumTradicional alb){this.albumpropio=alb;}
public void setAlbumpropio(AlbumWeb alb){this.albumpropio2=alb;}
public void setAlbumpropio(AlbumExtendido alb){this.albumpropio1=alb;}


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