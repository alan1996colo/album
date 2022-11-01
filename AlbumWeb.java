public class AlbumWeb extends Album{
String codigoWeb;
public String getCodigoWeb() {
    return codigoWeb;
}
String tipoAlbum="Web";

public AlbumWeb(String codigoWeb,int codigoUnico,String nombreDueño, int dniDueño){
    this.codigoWeb=codigoWeb;
    this.codigoUnico=codigoUnico;
    this.nombreDueño=nombreDueño;
    this.dniDueño=dniDueño;

}
public void premioFinal(){};
public void verCodigoCompFigusTrad(){};

}