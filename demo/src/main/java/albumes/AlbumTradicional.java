//si se modifica
package albumes;

import java.util.Map;

public class AlbumTradicional extends Album{
 //String tipoAlbum="Tradicional";
 /*public String getTipoAlbum() {
   System.out.println("se ejecuto el metodo del tradicional");
   return this.tipoAlbum;
}
/* */
   public AlbumTradicional(int codigoUnico,String nombreDueño,int dniDueño,String paisesParticipantes[]){
        this.codigoUnico=codigoUnico;
        this.nombreDueño=nombreDueño;
        this.dniDueño=dniDueño;
        this.tipoAlbum="Tradicional";
        generarPaisesClasificados(paisesParticipantes);
       
        }
        
     


private void generarPaisesClasificados(String paisesParticipantes[]) {
   for(int i=0;i<paisesParticipantes.length;i++){
      this.paisesCompletos.put(paisesParticipantes[i], false);

   }
      
   }




public void premioFinal(){};
 
}