package albumes;


public class AlbumExtendido extends AlbumTradicional{
    public AlbumExtendido(int codigoUnico, String nombreDueño, int dniDueño,String paisesParticipantes[]) {
        super(codigoUnico, nombreDueño, dniDueño,paisesParticipantes);
        //TODO Auto-generated constructor stub
        this.tipoAlbum="Extendido";

    }

    Figurita coleccion20[];
    private int cantExtend=20;
  //  String tipoAlbum="Extendido";
    //Este constructor esta de mas, porque el padre tiene el mismo.

    public String darpremioFinal(){
      return "Se gano una pelota y un viaje";
    };
   
}