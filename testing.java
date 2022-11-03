
import IAlbumDelMundial;
import Figurita;
public class testing{
    
    public static void main(String[] args) {
        
            IAlbumDelMundial sistema = new IAlbumDelMundial();
            
            sistema.registrarParticipante(222222, "Christian", "Tradicional");
            System.out.print("..\n");
            sistema.comprarFiguritas(222222);
            System.out.print("llego a ejecutarse\n");
            sistema.mostrarSinpegar(222222);
            System.out.println("figus pegadas: --->"+ sistema.pegarFiguritas(222222));

    }
}