
public class AlbumWeb extends Album{
String codigoWeb;
//String tipoAlbum="Web";

public String getCodigoWeb() {
    return codigoWeb;
}



/*public String getTipoAlbum() {
    System.out.println("se ejecuto el metodo del Web");
    return this.tipoAlbum;
 }*/
public AlbumWeb(String codigoWeb,int codigoUnico,String nombreDueño, int dniDueño){
    this.codigoWeb=codigoWeb;
    this.codigoUnico=codigoUnico;
    this.nombreDueño=nombreDueño;
    this.dniDueño=dniDueño;
    this.tipoAlbum="Web";

}
public void premioFinal(){};
public void verCodigoCompFigusTrad(){};

}