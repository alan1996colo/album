package albumes;

public class AlbumWeb extends Album {
    String codigoWeb;

    public String getCodigoWeb() {
        return this.codigoWeb;
    }

    public AlbumWeb(String codigoWeb, int codigoUnico, String nombreDueno, int dniDueno) {
        this.codigoWeb = codigoWeb;
        this.codigoUnico = codigoUnico;
        this.nombreDueno = nombreDueno;
        this.dniDueno = dniDueno;
        this.tipoAlbum = "Web";

    }

    public String darpremioFinal() {
        return "Se gano una camiseta oficial de la seleccion";
    };

    public void verCodigoCompFigusTrad() {
    };

}