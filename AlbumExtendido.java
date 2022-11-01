public class AlbumExtendido extends AlbumTradicional{
    public AlbumExtendido(int codigoUnico, String nombreDueño, int dniDueño) {
        super(codigoUnico, nombreDueño, dniDueño);
        //TODO Auto-generated constructor stub
    }

    Figurita coleccion20[];
    private int cantExtend=20;
    String tipoAlbum="Extendido";
    //Este constructor esta de mas, porque el padre tiene el mismo.

    public void premioFinal(){};
   
}