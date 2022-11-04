

//Diseño actual
//AlbumDelMundial<--->Usuario-->Album{Tradicional,Web,Extendido} 
package albumes;

import java.util.List;

import java.util.Set;

import javax.swing.table.TableRowSorter;

public class Usuario <T extends Album> implements Comparable<Usuario>{//el Atributo album propio puede ser cualquier tipo de album
int dni;
public int getDni() {
    return dni;
}
String nombre;

T albumpropio;
public T getAlbumpropio(){return albumpropio;}
public void setAlbumPropio(T alb){this.albumpropio=alb;}



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
    return this.albumpropio.verificarYpegarFigus();
}
/**
 * Le pregunta al album si esta completo, True =Si, False=No.
*/
boolean completeElAlbum(){
    return this.albumpropio.isAlbumCompleto();
}
public int compareTo(Usuario otro){//no creo que lo llegue a usar pero, no esta de mas(?)
    return (otro.getDni()-this.getDni());
}
public boolean equals(Object o){//Implementamos el equals como se enseño en clase para no tener problemas de hashs en referencias.
    if(this==o) return true;
    if(o==null) return false;
    if(this.getClass()!=o.getClass())return false;
    Usuario other=(Usuario) o;  
    return (this.getDni()==other.getDni());
}
void solicitarPremio(){}
public String toString(){return this.nombre +" "+String.valueOf(this.dni);}
boolean solicitarIntercambio(String nombreFigu){return false;}
void verMisFigusRepet(){}
void mostrarSinpegar(){}
void quienCompletoElPais(String pais){}

}