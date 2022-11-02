public class testing{
    public static void main(String[] args) {
        
            IAlbumDelMundial sistema = new IAlbumDelMundial();
            
            sistema.registrarParticipante(222222, "Christian", "Tradicional");
            System.out.print("llego a ejecutarse\n");
            sistema.comprarFiguritas(222222);
    }
}