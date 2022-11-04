public class AlbumTradicional extends Album{
 String tipoAlbum="Tradicional";
 public String getTipoAlbum() {
   System.out.println("se ejecuto el metodo del tradicional");
   return this.tipoAlbum;
}

   public AlbumTradicional(int codigoUnico,String nombreDueño,int dniDueño){
        this.codigoUnico=codigoUnico;
        this.nombreDueño=nombreDueño;
        this.dniDueño=dniDueño;
        for(int i=0;i<generarPaisesClasificados().length;i++){//construyo el maps de paises con el array de fabrica 
         this.paisesCompletos.put(generarPaisesClasificados()[i],false);
        }
        
     }


public void premioFinal(){};
 
}