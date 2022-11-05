package albumes;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import javax.management.RuntimeErrorException;

import java.util.ArrayList;
import java.util.List;
import java.lang.Package;

public class IAlbumDelMundial implements InterfazPublicaAlbumDelMundial{
	Fabrica factory=new Fabrica();
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
	Map<Integer,Boolean> SorteoDado= new HashMap<>();//Se usa para saber si a X participante ya le dieron el sorteoInstantaneo.
		

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
		if(DniToHash.containsKey(dni)){//reviso el maps, si el hashcode ya esta en uso, el participante ya esta registrado.
			throw new RuntimeException("El participante, ya se encuentra registrado");
		}
		else{ Album nuevoAlb; //checkPoint--- por el momento lo voy a dejar asi, pero creo que deberia cambiar los metodos factory para que creen los albumes de cada tipo y no el Album abstracto.
		//Primero elegimos el tipo de album:
			if(tipoAlbum.equals("Tradicional")){ nuevoAlb= factory.crearAlbumTradicional(codigoUnico, nombre, dni);
				nuevoPart.setAlbumPropio(nuevoAlb);
				SorteoDado.put(codigoUnico,false);//agregamos su codigo al diccionario en estado false, porque no ha sido usado
			}
			
			else if(tipoAlbum.equals("Web")){
				//creamos el codigo web en el momento.
				String codigoPromocional=crearStringRandom(6);
				if(codigoWeb.containsKey(codigoPromocional)){//Si hay una remota posibilidad de que el codigo ya se haya inventado, creamos otro con un caracter mas.
					codigoPromocional=crearStringRandom(7);
					}
			
				codigoWeb.put(codigoPromocional, false);
				nuevoAlb= factory.crearAlbumWeb(codigoPromocional, codigoUnico, nombre, dni);
				nuevoPart.setAlbumPropio(nuevoAlb);}
			else if(tipoAlbum.equals("Extendido")){ nuevoAlb= factory.crearAlbumExtendido(codigoUnico, nombre, dni);
				nuevoPart.setAlbumPropio(nuevoAlb);}
			else {throw new RuntimeException("El tipo de album no es valido");}

			//si llego a este punto significa que no tiro la excepcion.Significa que el usuario no estaba registrado.
		
		}
		Integer dniinteger= Integer.valueOf(dni);
		Integer codigoUnicoInteger= Integer.valueOf(codigoUnico);
		DniToHash.put(dniinteger,codigoUnicoInteger);
		participantesConAlbumes.put(codigoUnicoInteger,nuevoPart);
		
		return codigoUnico;
		

	}
	/**
	 * Busca el tipo de album de un participante dado un dni.
	 * @param dni
	 * @return
	 */
private String saberTipoDeAlbum(int dni){
	Integer dniInteger= Integer.valueOf(dni);//esto de castear dni int a Integer realmente no se si es necesario.
	if(participantesConAlbumes.get(DniToHash.get(dniInteger)).getAlbumpropio()!=null){
		return participantesConAlbumes.get(DniToHash.get(dniInteger)).getAlbumpropio().getTipoAlbum();
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
		ArrayList<Figurita> sobre=new ArrayList<>();
		sobre=factory.generarSobre(4);
for (Figurita nueva : sobre) {
	nueva.setTipo(saberTipoDeAlbum(dni));
	participantesConAlbumes.get(DniToHash.get(dni)).getAlbumpropio().agregarFigu(nueva);
}
	}
	
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

		Usuario usr= participantesConAlbumes.get(DniToHash.get(Integer.valueOf(dni) ));
		AlbumWeb val=(AlbumWeb) usr.getAlbumpropio();
		System.out.println("el codig web es chan..."+ val.getCodigoWeb()+" <--");
		//que estupido perdi 30 minutos en esto y era que lo registraba en false.
		if(!Boolean.valueOf(codigoWeb.get(val.getCodigoWeb()))){//Si esta disponible lo hago no disponible y entrego las figuritas.
			codigoWeb.put(val.getCodigoWeb(), true);


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
		else{ throw new RuntimeException("Lo sentimos, el codigo web ya ha sido utilizado.");}


	}

public void mostrarSinpegar(int dni){
	participantesConAlbumes.get(DniToHash.get(dni)).getAlbumpropio().mostrarSinpegar();
}




	/**
	 * Busca entre las figuritas del participante cuales aún no están en el 
	 * album y las asocia.
	 * Devuelve una lista con las figuritas asociadas. 
	 * De cada figurita se devuelve un string "$pais-$numeroJugador"
	 * 
	 * Si el participante no está registrado, se debe lanzar una excepción.
	 */
	public List<String> pegarFiguritas(int dni){//Excepcion a implementar..
		//probando si era necesario castear a Integer
		Integer dniInteger=Integer.valueOf(dni);
		return participantesConAlbumes.get(DniToHash.get(dniInteger)).solicitarPegarFigus();




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
	public String aplicarSorteoInstantaneo(int dni){
		if(!DniToHash.containsKey(dni)){throw new RuntimeException("El participante no se encuentra registrado");}
		String premios[]={"una pelota","un paquete de cigarrillos","una camiseta firmada por el tucu","un saludo de el dibu"};
		if(!SorteoDado.get(DniToHash.get(Integer.valueOf(dni)))){
			int index=(int) (premios.length*Math.random());
			SorteoDado.put(DniToHash.get(dni), true); //cambiamos el estado ha true, significa dado.
			return premios[index];
		}
		else{throw new RuntimeException("El premio ya ha sido sorteado");
			 }
	
	}

	/**
	 * Busca si el participante tiene alguna figurita repetida y devuelve 
	 * el codigo de la primera que encuentre.
	 * Si no encuentra ninguna, devuelve el codigo -1. 
	 *  
	 * Si el participante no está registrado, se debe lanzar una excepción.
	 */
	public int buscarFiguritaRepetida(int dni){
if(!DniToHash.containsKey(dni)){throw new RuntimeException("El participante no se encuentra registrado");}
else{ return participantesConAlbumes.get(DniToHash.get(dni)).getAlbumpropio().buscarRepetidas();

}}

	/**
	 * Dado el dni de un participante A y el codigo de una figurita, 
	 * se busca entre los participantes con mismo tipo de album 
	 * ---si alguno tiene repetida alguna figurita que le falte al participante A 
	 * con un valor menor o igual al de la figurita a cambiar.
	 * En caso de encontrar alguno, realiza el intercambio y devuelve true.
	 * Si no se encuentra ninguna para cambiar, devuelve false.
	 * 
	 * 
	 * Si el participante no está registrado o no es dueño de la figurita a 
	 * cambiar, se debe lanzar una excepción.
	 */
	public boolean intercambiar(int dni, int codFigurita){
		if(!DniToHash.containsKey(dni)){throw new RuntimeException("participante no registrado");}
		if(!participantesConAlbumes.get(DniToHash.get(dni)).getAlbumpropio().containsFiguSinPegar(codFigurita)){throw new RuntimeException("El participante miente, no tiene la figurita que dice tener.");}
		Set<Integer> setOfKeys = participantesConAlbumes.keySet();//aca voy a guardar las keys
		for (Integer key : setOfKeys) {
			
			if(	DniToHash.get(dni)!=key && participantesConAlbumes.get(key).getAlbumpropio().getTipoAlbum().equals(participantesConAlbumes.get(DniToHash.get(dni)).getAlbumpropio().getTipoAlbum())){//si no son el mismo participante Y tienen el mismo tipo de album:
				//revisar su lista, checkear que este repetida y que le falte a "A"
				Album partA=participantesConAlbumes.get(DniToHash.get(dni)).getAlbumpropio();
				Album partB=participantesConAlbumes.get(key).getAlbumpropio();
				for (Figurita iter : partB.figusRepetidas()) {
					if(!partA.contains(iter)&&iter.getTipoDeFigu().equals(partA.getTipoAlbum())&&partA.getFigurita(codFigurita).ValorFinal()>=iter.ValorFinal()){
						//en caso de encontrar realiza el intercambio y devuelve true.
						partA.agregarFigu(iter);
						partB.agregarFigu(partA.getFigurita(codFigurita));
						partA.quitarFigu(partA.getFigurita(codFigurita));
						partB.quitarFigu(iter);
						//espero funciones xxd
						return true;

					}
				}

			}

	}return false;
}
	
	/**
	 * Dado el dni de un participante, busca una figurita repetida e intenta 
	 * intercambierla
	 * Devuelve true si pudo intercambiarla. Sino, devuelve false.
	 * 
	 * Si el participante no está registrado, se debe lanzar una excepción.
	 */
	public boolean intercambiarUnaFiguritaRepetida(int dni){
		if(!DniToHash.containsKey(dni)){throw new RuntimeException("participante no registrado");}
		if(participantesConAlbumes.get(DniToHash.get(dni)).getAlbumpropio().buscarRepetidas()==-1){return false;}//esto lo puse por el test jaja
		return intercambiar(dni,participantesConAlbumes.get(DniToHash.get(dni)).getAlbumpropio().buscarRepetidas());

	}

	/**
	 * Dado el dni de un participante, se devuelve el nombre del mismo.
	 * 
	 * Si el participante no está registrado, se debe lanzar una excepción.
	 */
	public String darNombre(int dni){
		if(!DniToHash.containsKey(dni)){throw new RuntimeException("participante no registrado");}
		else{return participantesConAlbumes.get(DniToHash.get(dni)).getNombre();

		}
	}

	/**
	 * Dado el dni de un participante, devuelve el premio correspondiente 
	 * por llenar el album.
	 * 
	 * Si el participante no está registrado, se debe lanzar una excepcion.
	 * Si no tiene el album completo, se debe lanzar una excepcion.
	 */
	public String darPremio(int dni){
		if(!DniToHash.containsKey(dni)){throw new RuntimeException("Lo sentimos, el participante no se encuentra registrado");}
		if(participantesConAlbumes.get(DniToHash.get(dni)).getAlbumpropio().isAlbumCompleto()){
			return participantesConAlbumes.get(DniToHash.get(dni)).getAlbumpropio().darpremioFinal();

		}
		else{throw new RuntimeException("El album aun no esta completo");}
	}

	/**
	 * Devuelve un string con la lista de todos los participantes que 
	 * tienen su album completo y el premio que les corresponde.
	 * El listado debe respetar el siguiente formato para cada ganador:
	 *     " - ($dni) $nombre: $premio"
	 */
	public String listadoDeGanadores(){

		
	}
	
	/**
	 * Devuelve una lista con todos los participantes que llenaron el pais
	 * pasado por parametro.
	 * 
	 * De cada participante se devuelve el siguiente String: 
	 *     "($dni) $nombre: $tipoAlbum"
	 */
	 public List<String> participantesQueCompletaronElPais(String nombrePais){}

   
}
