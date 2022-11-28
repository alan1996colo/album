

//Diseño actual
//AlbumDelMundial<--->Usuario-->Album{Tradicional,Web,Extendido} 
package albumes;

import java.util.ArrayList;


public class Usuario <T extends Album> implements Comparable<Usuario<Album>>{//el Atributo album propio puede ser cualquier tipo de album
int dni;

String nombre;
String premio="";
T albumpropio;

public void setPremio(String premio) {
    this.premio = premio;
}
public String getNombre() {
    return nombre;
}
public int getDni() {
    return dni;
}
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
ArrayList<String>  solicitarPegarFigus(){
    return this.albumpropio.verificarYpegarFigus();
}
/**
 * Le pregunta al album si esta completo, True =Si, False=No.
*/
boolean completeElAlbum(){
    return this.albumpropio.isAlbumCompleto();
}
public int compareTo(Usuario<Album> otro){//no creo que lo llegue a usar pero, no esta de mas(?)
    return (otro.getDni()-this.getDni());
}
public boolean equals(Object o){//Implementamos el equals como se enseño en clase para no tener problemas de hashs en referencias.
    if(this==o) return true;
    if(o==null) return false;
    if(this.getClass()!=o.getClass())return false;
    Usuario<Album> other=(Usuario<Album>) o;  
    return (this.getDni()==other.getDni());
}
void solicitarPremio(){}
public String toString(){return "("+String.valueOf(this.dni)+") "+this.nombre +" :"+this.premio;}
boolean solicitarIntercambio(String nombreFigu){return false;}
void verMisFigusRepet(){}
void mostrarSinpegar(){}
void quienCompletoElPais(String pais){}

}