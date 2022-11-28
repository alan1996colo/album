//si se modifica
package albumes;

public class AlbumTradicional extends Album{
/* */
   public AlbumTradicional(int codigoUnico,String nombreDueno,int dniDueno,String paisesParticipantes[]){
        this.codigoUnico=codigoUnico;
        this.nombreDueno=nombreDueno;
        this.dniDueno=dniDueno;
        this.tipoAlbum="Tradicional";
        generarPaisesClasificados(paisesParticipantes);
       
        }
        
     


private void generarPaisesClasificados(String paisesParticipantes[]) {
   for(int i=0;i<paisesParticipantes.length;i++){
      this.paisesCompletos.put(paisesParticipantes[i], false);

   }
      
   }

public String darpremioFinal(){
    return "Se gano una pelota";}
 
}