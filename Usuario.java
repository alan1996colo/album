import java.util.Set;

import javax.swing.table.TableRowSorter;

public class Usuario{
int dni;
String nombre;
Album albumpropio;
/*public Album getAlbumpropio() {
    return albumpropio;
}
//espero sea inecesario
*/
public void setAlbumpropio(Album albumpropio) {
    this.albumpropio = albumpropio;
}

public Usuario(int dni, String nombre){
    if(dni<1|99999999<dni){throw ArithmeticException("No se pudo registrar el Participante, razon: Dni invalido");}
    this.dni=dni;
    this.nombre=nombre;
}

boolean solicitarAlbum(String tipoAlbum){return false;}
void comprarFigus(String tipoDeFigu){}
void vermicodigoweb(){}
void comprarfigusconcodigoweb(){}
void solicitarPegarFigus(){}
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