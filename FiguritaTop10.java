public class FiguritaTop10 extends Figurita{
    String sedeMundial;
    int año;
    String balonde;
    public FiguritaTop10(String nombre,String tipoDeFigu, String pais,int rankingValorBase,  int numeroQueIdentifica){
        super(nombre, tipoDeFigu, pais, rankingValorBase, numeroQueIdentifica);
    }


    public FiguritasTop10(String nombre,String tipoDeFigu, String pais,int rankingValorBase,  int numeroQueIdentifica,String sedeMundial,int año, String balonde){
        this.nombre=nombre;
        this.tipoDeFigu=tipoDeFigu;
        this.pais=pais;
        this.rankingValorBase=rankingValorBase;
        this.numeroQueIdentifica=numeroQueIdentifica;
        this.sedeMundial=sedeMundial;
        this.año=año;
        this.balonde=balonde;     
    }

}