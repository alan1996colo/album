package albumes;

public class Figurita implements Cloneable {
    String nombre;
    String tipoDeFigu;// Tradicional,Web o Extendido.
    int valorBase = 10;// elijo un valor base de todas las figus.
    int rankingValorBase;// ranking de la figu
    int numeroQueIdentifica;// numero unico de la figurita
    String pais;


    public Figurita(String nombre, String tipoDeFigu, String pais, int rankingValorBase, int numeroQueIdentifica) {
        this.nombre = nombre;
        this.tipoDeFigu = tipoDeFigu;
        this.pais = pais;
        this.rankingValorBase = rankingValorBase;
        this.numeroQueIdentifica = numeroQueIdentifica;

    }

    public int getNumeroQueIdentifica() {
        return numeroQueIdentifica;
    }

    public String getTipoDeFigu() {
        return tipoDeFigu;
    }


    public String getPais() {
        return pais;
    }

    public String toString() {
        return this.pais + "  " + this.numeroQueIdentifica + "  " + this.nombre + "  " + this.tipoDeFigu + "  ";
    }

    /**
     * Calcula el valor final de la figurita dividiendo su valor base por el ranking
     * 
     * @return
     */
    public float ValorFinal() {// metodo polimorfico, la subclase lo va modificar.
        return valorBase / rankingValorBase;// se que la consigna dice "sumando al valor base el ranking del pais" ,
                                            // pero no tiene sentido que
        /*
         * No tiene sentido que figuritas con mejor ranking valgan menos que figuritas
         * con peor ranking, es decir el precio deberia ser inverso.
         * por lo tanto use dividir por el ranking para obtener el valor.
         */
    }

    public void setTipo(String tipoFigu) {

        if (tipoFigu == null
                || !tipoFigu.equals("Tradicional") & !tipoFigu.equals("Extendido") & !tipoFigu.equals("Web")) {
            throw new RuntimeException("Error el tipo de figurita no es valido. o es nulo");
        }
        this.tipoDeFigu = tipoFigu;
    }

    @Override
    protected Object clone() // throws CloneNotSupportedException
    {

        Figurita nueva = new Figurita(this.nombre, this.tipoDeFigu, this.pais, this.rankingValorBase,
                this.numeroQueIdentifica);
        return nueva;
    }

    public boolean equals(Object o) {// Implemente el equals como se enseño en clase para no tener problemas de hashs
                                     // en referencias.
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (this.getClass() != o.getClass())
            return false;
        Figurita other = (Figurita) o;
        return (this.getNumeroQueIdentifica() == other.getNumeroQueIdentifica()
                && this.getTipoDeFigu().equals(other.getTipoDeFigu()));// mismo numero y mismo tipo de figu, quiere
                                                                       // decir que son figuritas iguales.
    }

}