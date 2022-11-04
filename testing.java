
import IAlbumDelMundial;
import java.util.ArrayList;
import java.util.Iterator;
import Figurita;
public class testing{
    
    public static void main(String[] args) {
        
            IAlbumDelMundial sistema = new IAlbumDelMundial();
            
            sistema.registrarParticipante(222222, "Christian", "Web");
            System.out.print("..\n");
            sistema.comprarFiguritas(222222);
            System.out.print("Figuritas sin pegar:\n");
            sistema.mostrarSinpegar(222222);
            System.out.println("figus pegadas: --->"+ sistema.pegarFiguritas(222222));


/* 
        // Creating an empty ArrayList
        ArrayList<Integer> list
            = new ArrayList<Integer>();
  
        // Use add() method to add
        // elements into the list
        list.add(10);
        list.add(15);
        list.add(30);
        list.add(20);
        list.add(5);
  
        // Displaying the list
        System.out.println("The list is: \n"
                           + list);
  
        // Create an iterator for the list
        // using iterator() method
        Iterator<Integer> iter = list.iterator();
  
        // Displaying the values
        // after iterating through the list
        System.out.println("\nThe iterator values"
                           + " of list are: ");
        while (iter.hasNext()) {
          
            System.out.print(iter.next() + " ");
        }

        */
    }


    }
