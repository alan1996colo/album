import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class AlbumDelMundial implements interfazPublicAlbumDelMundial{
    Hashtable<Integer ,Usuario >participantesConAlbumes;
    Figurita coleccionCompleta[];
    Figurita coleccion20[];
	Map<String, Boolean> codigoWeb = new HashMap<>();
	Map<Integer,Integer> DniToHash= new HashMap<>();//La idea es hacer un diccionario, con los dnis y hash correspondientes, despues dado un dni consigo el hash y puedo buscar en el otro map.

		

/**Crea una string al azar del largo n, usa StringBuilder
 * 
*/
private String crearStringRandom(int n){
	// elige un caracter al azar de la String
	String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
		   + "0123456789"
		   + "abcdefghijklmnopqrstuvxyz";
   
	// crea un StringBuffer con el tamaño n pasado por parametro
	StringBuilder sb = new StringBuilder(n);
   
	for (int i = 0; i < n; i++) {
   
	 // crea un numero al azar entre 
	 // 0 y AlphaNumericString variable length
	 int index
	  = (int)(AlphaNumericString.length()
		* Math.random());
   
	 // agrega el caracter uno por uno a la StringBuilder
	 sb.append(AlphaNumericString
		.charAt(index));
	}
   
	return sb.toString();
   }
/**
	 * Registra un nuevo participante y devuelve el codigo unico del album
	 * asociado.
	 * 
	 * Si el participante ya está registrado o el tipo de album es invalido, 
	 * se debe lanzar una excepcion.
	 */
public int registrarParticipante(int dni, String nombre, String tipoAlbum){
	
		Usuario nuevoPart= new Usuario(dni, nombre);
		int codigoUnico= nuevoPart.toString().hashCode();
		if(participantesConAlbumes.containsKey(codigoUnico)){//reviso el maps, si el hashcode ya esta en uso, el participante ya esta registrado.
			throw Exception("El participante, ya se encuentra registrado");
		}
		else{
		//Primero elegimos el tipo de album:
			if(tipoAlbum=="Tradicional"){AlbumTradicional nuevoAlb= new AlbumTradicional(codigoUnico, nombre, dni);}
			else if(tipoAlbum=="Web"){
				//creamos el codigo web en el momento.
				String codigoPromocional=crearStringRandom(6);
				if(codigoWeb.containsKey(codigoPromocional)){//Si hay una remota posibilidad de que el codigo ya se haya inventado, creamos otro con un caracter mas.
					codigoPromocional=crearStringRandom(7);
					}
			
				codigoWeb.put(codigoPromocional, false);
				AlbumWeb nuevoAlb= new AlbumWeb(codigoPromocional, codigoUnico, nombre, dni);}
			else if(tipoAlbum=="Extendido"){AlbumExtendido nuevoAlb= new AlbumExtendido(codigoUnico, nombre, dni);}
			else {throw new Exception("El tipo de album no es valido");}
		}

		//si llego a este punto significa que no tiro la excepcion.Significa que el usuario no estaba registrado.
		nuevoPart.setAlbumpropio(nuevoAlb);
		DniToHash.put(dni,codigoUnico);
		participantesConAlbumes.put(codigoUnico,nuevoPart);
		return codigoUnico;
		

	}

	/**
	 * Se generan 4 figuritas al azar y 
	 * se asocia al participante correspondiente identificado por dni
	 * 
	 * Si el participante no está registrado, se debe lanzar una excepción.
	 */
	void comprarFiguritas(int dni){}
	
	/**
	 * Se generan 4 figuritas top 10 al azar y 
	 * se asocia al participante correspondiente identificado por dni
	 * 
	 * Si el participante no está registrado, se debe lanzar una excepción.
	 * Si el participante no tiene album top10, se debe lanzar una excepción.
	 */
	void comprarFiguritasTop10(int dni){}

	/**
	 * Compra por única vez un grupo de 4 figuritas con el codigo promocional 
	 * asociado a su album web. 
	 * 
	 * Si el participante no está registrado, se debe lanzar una excepción.
	 * Si el participante no tiene codigo de sorteo o el mismo ya fué usado,
	 * se debe lanzar una excepcion.
	 */
	void comprarFiguritasConCodigoPromocional(int dni){}

	/**
	 * Busca entre las figuritas del participante cuales aún no están en el 
	 * album y las asocia.
	 * Devuelve una lista con las figuritas asociadas. 
	 * De cada figurita se devuelve un string "$pais-$numeroJugador"
	 * 
	 * Si el participante no está registrado, se debe lanzar una excepción.
	 */
	List<String> pegarFiguritas(int dni){}

	/**
	 * Verifica si el participante identificado por dni ya completó el album.
	 * Devuelve true si está completo, sino false.
	 * Este metodo debe resolverse en O(1)
	 *  
	 * Si el participante no está registrado, se debe lanzar una excepción.
	 */
	boolean llenoAlbum(int dni){
		//por ahora lo hago asi, despues me fijo lo de lanzar la excepcion.
		return participantesConAlbumes.get(DniToHash.get(dni)).completeElAlbum();
	}

	/**
	 * Realiza el sorteo instantaneo con el codigo asociado al album 
	 * tradicional del participante y devuelve algun premio al azar.
	 *  
	 * Si el participante no está registrado, se debe lanzar una excepción.
	 * Si no tiene codigo para el sorteo o ya fue sorteado, se debe lanzar
	 * una excepcion.
	 */
	String aplicarSorteoInstantaneo(int dni){}

	/**
	 * Busca si el participante tiene alguna figurita repetida y devuelve 
	 * el codigo de la primera que encuentre.
	 * Si no encuentra ninguna, devuelve el codigo -1. 
	 *  
	 * Si el participante no está registrado, se debe lanzar una excepción.
	 */
	int buscarFiguritaRepetida(int dni){}

	/**
	 * Dado el dni de un participante A y el codigo de una figurita, 
	 * se busca entre los participantes con mismo tipo de album 
	 * si alguno tiene repetida alguna figurita que le falte al participante A 
	 * con un valor menor o igual al de la figurita a cambiar.
	 * En caso de encontrar alguno, realiza el intercambio y devuelve true.
	 * Si no se encuentra ninguna para cambiar, devuelve false.
	 * 
	 * 
	 * Si el participante no está registrado o no es dueño de la figurita a 
	 * cambiar, se debe lanzar una excepción.
	 */
	boolean intercambiar(int dni, int codFigurita){}
	
	/**
	 * Dado el dni de un participante, busca una figurita repetida e intenta 
	 * intercambierla
	 * Devuelve true si pudo intercambiarla. Sino, devuelve false.
	 * 
	 * Si el participante no está registrado, se debe lanzar una excepción.
	 */
	boolean intercambiarUnaFiguritaRepetida(int dni){}

	/**
	 * Dado el dni de un participante, se devuelve el nombre del mismo.
	 * 
	 * Si el participante no está registrado, se debe lanzar una excepción.
	 */
	String darNombre(int dni){}

	/**
	 * Dado el dni de un participante, devuelve el premio correspondiente 
	 * por llenar el album.
	 * 
	 * Si el participante no está registrado, se debe lanzar una excepcion.
	 * Si no tiene el album completo, se debe lanzar una excepcion.
	 */
	String darPremio(int dni){}

	/**
	 * Devuelve un string con la lista de todos los participantes que 
	 * tienen su album completo y el premio que les corresponde.
	 * El listado debe respetar el siguiente formato para cada ganador:
	 *     " - ($dni) $nombre: $premio"
	 */
	String listadoDeGanadores(){}
	
	/**
	 * Devuelve una lista con todos los participantes que llenaron el pais
	 * pasado por parametro.
	 * 
	 * De cada participante se devuelve el siguiente String: 
	 *     "($dni) $nombre: $tipoAlbum"
	 */
	 List<String> participantesQueCompletaronElPais(String nombrePais){}

    
}