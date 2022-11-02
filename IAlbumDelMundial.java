import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.ArrayList;
import Figurita;
import AlbumTradicional;
import AlbumWeb;
import AlbumExtendido;
import java.lang.Package;

public class IAlbumDelMundial implements InterfazPublicaAlbumDelMundial{
	public IAlbumDelMundial(){}
    Hashtable<Integer ,Usuario >participantesConAlbumes=new Hashtable<>();

	Figurita a= new Figurita ("leonel messi","Tradicional","Argentina",1, 0);
	Figurita b= new Figurita ("asd ddsd","Tradicional","Argentina",1, 1);
	Figurita c= new Figurita ("asww gbdf","Tradicional","Argentina",1, 2);

	Figurita d= new Figurita ("ewe2 kjghj5","Tradicional","Argentina",1, 3);
	Figurita e = new Figurita ("1wd nsss","Tradicional","Argentina",1, 4);


	Figurita f= new Figurita ("rogelio","Tradicional","Argentina",1, 5);//esto es momentaneo, mas tarde voy hacer que se vayan generando automaticamente.

    Figurita coleccionCompleta[]={
		a,b,c,d,e,f};
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
			throw new RuntimeException("El participante, ya se encuentra registrado");
		}
		else{
		//Primero elegimos el tipo de album:
			if(tipoAlbum.equals("Tradicional")){AlbumTradicional nuevoAlb= new AlbumTradicional(codigoUnico, nombre, dni);
				nuevoPart.setAlbumpropio(nuevoAlb);}
			
			else if(tipoAlbum.equals("Web")){
				//creamos el codigo web en el momento.
				String codigoPromocional=crearStringRandom(6);
				if(codigoWeb.containsKey(codigoPromocional)){//Si hay una remota posibilidad de que el codigo ya se haya inventado, creamos otro con un caracter mas.
					codigoPromocional=crearStringRandom(7);
					}
			
				codigoWeb.put(codigoPromocional, false);
				AlbumWeb nuevoAlb= new AlbumWeb(codigoPromocional, codigoUnico, nombre, dni);
				nuevoPart.setAlbumpropio(nuevoAlb);}
			else if(tipoAlbum.equals("Extendido")){AlbumExtendido nuevoAlb= new AlbumExtendido(codigoUnico, nombre, dni);
				nuevoPart.setAlbumpropio(nuevoAlb);}
			else {throw new RuntimeException("El tipo de album no es valido");}

			//si llego a este punto significa que no tiro la excepcion.Significa que el usuario no estaba registrado.
		
		}
		Integer dniinteger= Integer.valueOf(dni);
		Integer codigoUnicoInteger= Integer.valueOf(codigoUnico);
		DniToHash.put(dniinteger,codigoUnicoInteger);
		participantesConAlbumes.put(codigoUnicoInteger,nuevoPart);
		
		return codigoUnico;
		

	}
private String saberTipoDeAlbum(int dni){
	Integer dniInteger= Integer.valueOf(dni);
	if(participantesConAlbumes.get(DniToHash.get(dniInteger)).getAlbumpropio()!=null){
		return "Tradicional";
	}
	else if((participantesConAlbumes.get(DniToHash.get(dniInteger))).getAlbumpropi()!=null){
		return "Web";
	}
	else if((participantesConAlbumes.get(DniToHash.get(dniInteger))).getAlbumprop()!=null){
		return "Extendido";
	}
	System.out.println("Error en el metodo saberTipoDeAlbum");
return "";
}
	/**
	 * Se generan 4 figuritas al azar y 
	 * se asocia al participante correspondiente identificado por dni
	 * 
	 * Si el participante no está registrado, se debe lanzar una excepción.
	 */
	public void comprarFiguritas(int dni){//falta implementar lo de la excepcion.

		//elige 4 al azar de la coleccion completa, los clona, les cambia el tipo de figurita por el mismo del solicitante y agrega a la
		//lista de figuritas sin pegar.
		


		for (int i = 0; i < 4; i++) {
   
			// crea un numero al azar entre 
			// 0 y coleccion completa length
			int index
			 = (int)(coleccionCompleta.length
			   * Math.random());
			Figurita nueva=(Figurita)coleccionCompleta[index].clone();// Hay que implementar clone en la clase Figurita
			Integer dniInteger= Integer.valueOf(dni);
			System.out.println(DniToHash.get(dniInteger) + " codigo hash del dni pàsado");
			//necesito discernir entre que getAlbumProio usar.
			
			nueva.setTipo(saberTipoDeAlbum(dni)); //Hay que implementar setTipo en la clase Figurita
			   participantesConAlbumes.get(DniToHash.get(dni)).getAlbumpropio().agregarFigu(nueva);//Agregamos la figurita a la lista sin pegar, del album del participante.
			
	}}
	
	/**
	 * Se generan 4 figuritas top 10 al azar y 
	 * se asocia al participante correspondiente identificado por dni
	 * 
	 * Si el participante no está registrado, se debe lanzar una excepción.
	 * Si el participante no tiene album top10, se debe lanzar una excepción.
	 */
	public void comprarFiguritasTop10(int dni){//falta implementar lo de la excepcion.

		for (int i = 0; i < 4; i++) {
   
			// crea un numero al azar entre 
			// 0 y coleccion completa length
			int index
			 = (int)(coleccion20.length
			   * Math.random());
			Figurita nueva=(Figurita)coleccion20[index].clone();// Hay que implementar clone en la clase Figurita
			participantesConAlbumes.get(DniToHash.get(dni)).getAlbumpropio().agregarFigu(nueva);//Agregamos la figurita a la lista sin pegar, del album del participante.
			
	}
	}

	/**
	 * Compra por única vez un grupo de 4 figuritas con el codigo promocional 
	 * asociado a su album web. 
	 * 
	 * Si el participante no está registrado, se debe lanzar una excepción.
	 * Si el participante no tiene codigo de sorteo o el mismo ya fué usado,
	 * se debe lanzar una excepcion.
	 */
	public void comprarFiguritasConCodigoPromocional(int dni){//falta implementar la excepcion
		Usuario usr= participantesConAlbumes.get(DniToHash.get(dni));
		if(codigoWeb.get(usr.getAlbumpropio().getCodigoWeb())){//Si esta disponible lo hago no disponible y entrego las figuritas.
			codigoWeb.put(usr.getAlbumpropio().getCodigoWeb(), false);


			for (int i = 0; i < 4; i++) {
   
				// crea un numero al azar entre 
				// 0 y coleccion completa length
				int index
				 = (int)(coleccionCompleta.length
				   * Math.random());
				Figurita nueva=(Figurita)coleccionCompleta[index].clone();// Hay que implementar clone en la clase Figurita
				nueva.setTipo("Tradicional"); //Hay que implementar setTipo en la clase Figurita
				   participantesConAlbumes.get(DniToHash.get(dni)).getAlbumpropio().agregarFigu(nueva);//Agregamos la figurita a la lista sin pegar, del album del participante.
				
		}

		}
		else throw new RuntimeException("Lo sentimos, el codigo web ya ha sido utilizado.");


	}

	/**
	 * Busca entre las figuritas del participante cuales aún no están en el 
	 * album y las asocia.
	 * Devuelve una lista con las figuritas asociadas. 
	 * De cada figurita se devuelve un string "$pais-$numeroJugador"
	 * 
	 * Si el participante no está registrado, se debe lanzar una excepción.
	 */
	public ArrayList<String> pegarFiguritas(int dni){
		return participantesConAlbumes.get(DniToHash.get(dni)).solicitarPegarFigus();




	}

	/**
	 * Verifica si el participante identificado por dni ya completó el album.
	 * Devuelve true si está completo, sino false.
	 * Este metodo debe resolverse en O(1)
	 *  
	 * Si el participante no está registrado, se debe lanzar una excepción.
	 */
	public boolean llenoAlbum(int dni){
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
	public String aplicarSorteoInstantaneo(int dni){}

	/**
	 * Busca si el participante tiene alguna figurita repetida y devuelve 
	 * el codigo de la primera que encuentre.
	 * Si no encuentra ninguna, devuelve el codigo -1. 
	 *  
	 * Si el participante no está registrado, se debe lanzar una excepción.
	 */
	public int buscarFiguritaRepetida(int dni){}

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
	public boolean intercambiar(int dni, int codFigurita){}
	
	/**
	 * Dado el dni de un participante, busca una figurita repetida e intenta 
	 * intercambierla
	 * Devuelve true si pudo intercambiarla. Sino, devuelve false.
	 * 
	 * Si el participante no está registrado, se debe lanzar una excepción.
	 */
	public boolean intercambiarUnaFiguritaRepetida(int dni){}

	/**
	 * Dado el dni de un participante, se devuelve el nombre del mismo.
	 * 
	 * Si el participante no está registrado, se debe lanzar una excepción.
	 */
	public String darNombre(int dni){}

	/**
	 * Dado el dni de un participante, devuelve el premio correspondiente 
	 * por llenar el album.
	 * 
	 * Si el participante no está registrado, se debe lanzar una excepcion.
	 * Si no tiene el album completo, se debe lanzar una excepcion.
	 */
	public String darPremio(int dni){}

	/**
	 * Devuelve un string con la lista de todos los participantes que 
	 * tienen su album completo y el premio que les corresponde.
	 * El listado debe respetar el siguiente formato para cada ganador:
	 *     " - ($dni) $nombre: $premio"
	 */
	public String listadoDeGanadores(){}
	
	/**
	 * Devuelve una lista con todos los participantes que llenaron el pais
	 * pasado por parametro.
	 * 
	 * De cada participante se devuelve el siguiente String: 
	 *     "($dni) $nombre: $tipoAlbum"
	 */
	 public ArrayList<String> participantesQueCompletaronElPais(String nombrePais){}

   
}
