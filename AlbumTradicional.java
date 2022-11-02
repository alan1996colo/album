public class AlbumTradicional extends Album{
 String tipoAlbum="Tradicional";
 public String getTipoAlbum() {
   return this.tipoAlbum;
}

   public AlbumTradicional(int codigoUnico,String nombreDueño,int dniDueño){
        this.codigoUnico=codigoUnico;
        this.nombreDueño=nombreDueño;
        this.dniDueño=dniDueño;
     }


public void premioFinal(){};
 
}