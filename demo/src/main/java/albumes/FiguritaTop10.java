package albumes;

public class FiguritaTop10 extends Figurita{
    String sedeMundialYanio;
    int anio;
    String balonde;



    public FiguritaTop10(String nombre,String tipoDeFigu, String pais,int rankingValorBase,  int numeroQueIdentifica,String sedeMundialYanio, String balonde){
        super(nombre, tipoDeFigu, pais, rankingValorBase, numeroQueIdentifica);
        this.sedeMundialYanio=sedeMundialYanio;
        this.balonde=balonde;     
    }

    public float ValorFinal(){//metodo polimorfico, la subclase lo va modificar.
        if(balonde.equals("oro"))
        return (float)((valorBase/rankingValorBase)*1.2); 
        if(balonde.equals("plata"))
        return (float)((valorBase/rankingValorBase)*1.1);
        else throw new RuntimeException("Error, la figurita no es balon de plata ni de oro");
    

    }


    @Override
protected Object clone() //throws CloneNotSupportedException 
{
  
    FiguritaTop10 nueva= new FiguritaTop10(this.nombre, this.tipoDeFigu, this.pais, this.rankingValorBase, this.numeroQueIdentifica, this.sedeMundialYanio, this.balonde) ;
    return nueva;
  }



public String toString(){
    return this.pais+"  "+this.numeroQueIdentifica+"  "+this.nombre + "  "+this.tipoDeFigu+"  " +this.sedeMundialYanio+" "+ this.anio+ " "+this.balonde;
    }

}