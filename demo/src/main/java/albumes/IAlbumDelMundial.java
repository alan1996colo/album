package albumes;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;

public class IAlbumDelMundial implements InterfazPublicaAlbumDelMundial {
	Fabrica factory ;
	Hashtable<Integer, Usuario<Album>> participantesConAlbumes;
	Map<String, Boolean> codigoWeb;
	Map<Integer, Integer> DniToHash;// La idea es hacer un diccionario, con los dnis y hash
														// correspondientes, despues dado un dni consigo el hash y puedo
														// buscar en el otro map.
	Map<Integer, Boolean> SorteoDado ;// Se usa para saber si a X participante ya le dieron el
														// sorteoInstantaneo.
	ArrayList<String> ganadores ;

	public IAlbumDelMundial() {
		this.factory= new Fabrica();
		this.participantesConAlbumes= new Hashtable<>();
		this.codigoWeb = new HashMap<>();
		this.DniToHash = new HashMap<>();
		this.SorteoDado= new HashMap<>();
		this.ganadores= new ArrayList<>();

	}

	/**
	 * Crea una string al azar del largo n, usa StringBuilder y recursion
	 * 
	 */
	private String crearStringRandom(int n) {
		// elige un caracter al azar de la String
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
				+ "0123456789"
				+ "abcdefghijklmnopqrstuvxyz";
		StringBuilder sb = new StringBuilder(n);
		// 0 y AlphaNumericString variable length
		int index = (int) (AlphaNumericString.length()
				* Math.random());
		sb.append(AlphaNumericString
				.charAt(index));
		if (n <= 1) {
			return sb.toString();
		} // caso base
		else
			return sb.append(crearStringRandom(n - 1)).toString();
	}

	/**
	 * Registra un nuevo participante y devuelve el codigo unico del album
	 * asociado.
	 * 
	 * Si el participante ya está registrado o el tipo de album es invalido,
	 * se debe lanzar una excepcion.
	 */
	public int registrarParticipante(int dni, String nombre, String tipoAlbum) {

		Usuario<Album> nuevoPart = new Usuario<Album>(dni, nombre);
		int codigoUnico = nuevoPart.toString().hashCode();
		if (DniToHash.containsKey(dni)) {// reviso el maps, si el hashcode ya esta en uso, el participante ya esta
											// registrado.
			throw new RuntimeException("El participante, ya se encuentra registrado");
		} else {
			Album nuevoAlb; // checkPoint--- por el momento lo voy a dejar asi, pero creo que deberia
							// cambiar los metodos factory para que creen los albumes de cada tipo y no el
							// Album abstracto.
			// Primero elegimos el tipo de album:
			if (tipoAlbum.equals("Tradicional")) {
				nuevoAlb = factory.crearAlbumTradicional(codigoUnico, nombre, dni);
				nuevoPart.setAlbumPropio(nuevoAlb);
				SorteoDado.put(codigoUnico, false);// agregamos su codigo al diccionario en estado false, porque no ha
													// sido
													// usado
			}

			else if (tipoAlbum.equals("Web")) {
				// creamos el codigo web en el momento.
				String codigoPromocional = crearStringRandom(6);
				if (codigoWeb.containsKey(codigoPromocional)) {// Si hay una remota posibilidad de que el codigo ya se
																// haya
																// inventado, creamos otro con un caracter mas.
					codigoPromocional = crearStringRandom(7);
				}

				codigoWeb.put(codigoPromocional, false);
				nuevoAlb = factory.crearAlbumWeb(codigoPromocional, codigoUnico, nombre, dni);
				nuevoPart.setAlbumPropio(nuevoAlb);
			} else if (tipoAlbum.equals("Extendido")) {
				nuevoAlb = factory.crearAlbumExtendido(codigoUnico, nombre, dni);
				nuevoPart.setAlbumPropio(nuevoAlb);
			} else {
				throw new RuntimeException("El tipo de album no es valido");
			}

			// si llego a este punto significa que no tiro la excepcion.Significa que el
			// usuario no estaba registrado.

		}
		Integer dniinteger = Integer.valueOf(dni);
		Integer codigoUnicoInteger = Integer.valueOf(codigoUnico);
		DniToHash.put(dniinteger, codigoUnicoInteger);
		participantesConAlbumes.put(codigoUnicoInteger, nuevoPart);

		return codigoUnico;

	}

	/**
	 * Busca el tipo de album de un participante dado un dni.
	 * 
	 * @param dni
	 * @return
	 */
	private String saberTipoDeAlbum(int dni) {
		Integer dniInteger = Integer.valueOf(dni);// esto de castear dni int a Integer realmente no se si es necesario.
		if (participantesConAlbumes.get(DniToHash.get(dniInteger)).getAlbumpropio() != null) {
			return participantesConAlbumes.get(DniToHash.get(dniInteger)).getAlbumpropio().getClass().getSimpleName();
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
	public void comprarFiguritas(int dni) {// falta implementar lo de la excepcion.

		// elige 4 al azar de la coleccion completa, los clona, les cambia el tipo de
		// figurita por el mismo del solicitante y agrega a la
		// lista de figuritas sin pegar.
		ArrayList<Figurita> sobre = new ArrayList<>();
		sobre = factory.generarSobre(4);
		for (Figurita nueva : sobre) {
			String set;
			if(saberTipoDeAlbum(dni).equals("AlbumTradicional")){set="Tradicional";}
			else if(saberTipoDeAlbum(dni).equals("AlbumWeb")){set="Web";}
			else if(saberTipoDeAlbum(dni).equals("AlbumExtendido")){set="Extendido";}
			else{set=null;}
			nueva.setTipo(set);
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
	public void comprarFiguritasTop10(int dni) {// falta implementar lo de la excepcion.
		if (!DniToHash.containsKey(dni)) {
			throw new RuntimeException("Error, el participante no se encuentra registrado");
		}
		if (!participantesConAlbumes.get(DniToHash.get(dni)).getAlbumpropio().getClass().getSimpleName().equals("AlbumExtendido")) {
			throw new RuntimeException("Error el tipo de album no es el correcto");
		}
		ArrayList<FiguritaTop10> sobre = new ArrayList<>();
		sobre = factory.generarSobreTop10(4);
		for (FiguritaTop10 nueva : sobre) {
			String set;
			if(saberTipoDeAlbum(dni).equals("AlbumTradicional")){set="Tradicional";}
			else if(saberTipoDeAlbum(dni).equals("AlbumWeb")){set="Web";}
			else if(saberTipoDeAlbum(dni).equals("AlbumExtendido")){set="Extendido";}
			else{set=null;}
			nueva.setTipo(set);
			participantesConAlbumes.get(DniToHash.get(dni)).getAlbumpropio().agregarFigu(nueva);
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
	public void comprarFiguritasConCodigoPromocional(int dni) {// falta implementar la excepcion

		Usuario<Album> usr = participantesConAlbumes.get(DniToHash.get(Integer.valueOf(dni)));
		AlbumWeb val = (AlbumWeb) usr.getAlbumpropio();
		// System.out.println("el codig web es chan..."+ val.getCodigoWeb()+" <--");
		// que estupido perdi 30 minutos en esto y era que lo registraba en false.
		if (!Boolean.valueOf(codigoWeb.get(val.getCodigoWeb()))) {// Si esta disponible lo hago no disponible y entrego
																	// las figuritas.
			codigoWeb.put(val.getCodigoWeb(), true);

			ArrayList<Figurita> sobre = new ArrayList<>();
			sobre = factory.generarSobre(4);
			for (Figurita nueva : sobre) {
				nueva.setTipo("Tradicional");
				participantesConAlbumes.get(DniToHash.get(dni)).getAlbumpropio().agregarFigu(nueva);
			}

		} else {
			throw new RuntimeException("Lo sentimos, el codigo web ya ha sido utilizado.");
		}

	}

	public void mostrarSinpegar(int dni) {
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
	public ArrayList<String> pegarFiguritas(int dni) {// Excepcion a implementar..
		// probando si era necesario castear a Integer
		Integer dniInteger = Integer.valueOf(dni);
		ArrayList<String> ret = participantesConAlbumes.get(DniToHash.get(dniInteger)).solicitarPegarFigus();
		// aca deberia implementar lo de agregar ganadores
		if (participantesConAlbumes.get(DniToHash.get(dniInteger)).getAlbumpropio().isAlbumCompleto()
				&& !ganadores.contains(participantesConAlbumes.get(DniToHash.get(dniInteger)).toString())) {
			participantesConAlbumes.get(DniToHash.get(dniInteger)).setPremio(
					participantesConAlbumes.get(DniToHash.get(dniInteger)).getAlbumpropio().darpremioFinal());
			this.ganadores.add(participantesConAlbumes.get(DniToHash.get(dniInteger)).toString());

		}
		return ret;

	}

	public void mostrarPegadas(int dni) {
		participantesConAlbumes.get(DniToHash.get(dni)).getAlbumpropio().mostrarPegadas();
	}

	/**
	 * Verifica si el participante identificado por dni ya completó el album.
	 * Devuelve true si está completo, sino false.
	 * Este metodo debe resolverse en O(1)
	 * 
	 * Si el participante no está registrado, se debe lanzar una excepción.
	 */
	public boolean llenoAlbum(int dni) {
		if (DniToHash.get(dni) == null) {
			throw new RuntimeException("El participante no se encuentra registrado");
		}
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
	public String aplicarSorteoInstantaneo(int dni) {
		if (!DniToHash.containsKey(dni)) {
			throw new RuntimeException("El participante no se encuentra registrado");
		}
	//	System.out.println(participantesConAlbumes.get(DniToHash.get(dni)).getAlbumpropio().getClass().getSimpleName());
		if (participantesConAlbumes.get(DniToHash.get(dni)).getAlbumpropio().getClass().getSimpleName().equals("AlbumTradicional") == false) {
			throw new RuntimeException("Error su Album es incorrecto : "
					+ participantesConAlbumes.get(DniToHash.get(dni)).getAlbumpropio().getClass().getSimpleName());
		} // cuando el album no sea tradicional, mostramos esto.
		String premios[] = { "una pelota", "un paquete de cigarrillos", "una camiseta firmada por el tucu",
				"un saludo de el dibu" };
		if (SorteoDado.get(DniToHash.get(Integer.valueOf(dni))) != null
				&& SorteoDado.get(DniToHash.get(Integer.valueOf(dni))) == false) {// primera vez que entra, lo damos y
																					// ponemos algo.
			int index = (int) (premios.length * Math.random());
			SorteoDado.put(DniToHash.get(dni), true);
			return premios[index];

		} else {
			if (SorteoDado.get(DniToHash.get(Integer.valueOf(dni))) != null
					&& DniToHash.get(Integer.valueOf(dni)) != null
					&& SorteoDado.get(DniToHash.get(Integer.valueOf(dni))).equals(Boolean.valueOf(true))) {
				throw new RuntimeException("El premio ya ha sido sorteado");// si no esta en null significa que ya fue
																			// sorteado
			}
		}
		return "";
	}

	/**
	 * Busca si el participante tiene alguna figurita repetida y devuelve
	 * el codigo de la primera que encuentre.
	 * Si no encuentra ninguna, devuelve el codigo -1.
	 * 
	 * Si el participante no está registrado, se debe lanzar una excepción.
	 */
	public int buscarFiguritaRepetida(int dni) {
		if (!DniToHash.containsKey(dni)) {
			throw new RuntimeException("El participante no se encuentra registrado");
		} else {
			return participantesConAlbumes.get(DniToHash.get(dni)).getAlbumpropio().buscarRepetidas();

		}
	}

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
	public boolean intercambiar(int dni, int codFigurita) {
		if (!DniToHash.containsKey(dni)) {
			throw new RuntimeException("participante no registrado");
		}
		if (!participantesConAlbumes.get(DniToHash.get(dni)).getAlbumpropio().containsFiguSinPegar(codFigurita)) {
			throw new RuntimeException("El participante miente, no tiene la figurita que dice tener.");
		}
		Set<Integer> setOfKeys = participantesConAlbumes.keySet();// aca voy a guardar las keys
		for (Integer key : setOfKeys) {

			if (DniToHash.get(dni) != key && participantesConAlbumes.get(key).getAlbumpropio().getClass().getSimpleName()
					.equals(participantesConAlbumes.get(DniToHash.get(dni)).getAlbumpropio().getClass().getSimpleName())) {// si no son el mismo participante Y tienen el mismo tipo de album:
				// revisar su lista, checkear que este repetida y que le falte a "A"
				Album partA = participantesConAlbumes.get(DniToHash.get(dni)).getAlbumpropio();
				Album partB = participantesConAlbumes.get(key).getAlbumpropio();
				for (Figurita iter : partB.figusRepetidas()) {
					if (!partA.contains(iter) && (     (iter.getTipoDeFigu().equals("Tradicional") &&partA.getClass().getSimpleName().equals("AlbumTradicional") ) ||  (iter.getTipoDeFigu().equals("Web") &&partA.getClass().getSimpleName().equals("AlbumWeb") )||  (iter.getTipoDeFigu().equals("Extendido") &&partA.getClass().getSimpleName().equals("AlbumExtendido") ))



							&& partA.getFigurita(codFigurita).ValorFinal() >= iter.ValorFinal()) {
						// en caso de encontrar realiza el intercambio y devuelve true.
						partA.agregarFigu(iter);
						partB.agregarFigu(partA.getFigurita(codFigurita));
						partA.quitarFigu(partA.getFigurita(codFigurita));
						partB.quitarFigu(iter);

						return true;

					}
				}

			}

		}
		return false;
	}

	/**
	 * Dado el dni de un participante, busca una figurita repetida e intenta
	 * intercambierla
	 * Devuelve true si pudo intercambiarla. Sino, devuelve false.
	 * 
	 * Si el participante no está registrado, se debe lanzar una excepción.
	 */
	public boolean intercambiarUnaFiguritaRepetida(int dni) {
		if (!DniToHash.containsKey(dni)) {
			throw new RuntimeException("participante no registrado");
		}
		if (participantesConAlbumes.get(DniToHash.get(dni)).getAlbumpropio().buscarRepetidas() == -1) {
			return false;
		} // esto lo puse por el test jaja
		return intercambiar(dni, participantesConAlbumes.get(DniToHash.get(dni)).getAlbumpropio().buscarRepetidas());

	}

	/**
	 * Dado el dni de un participante, se devuelve el nombre del mismo.
	 * 
	 * Si el participante no está registrado, se debe lanzar una excepción.
	 */
	public String darNombre(int dni) {
		if (!DniToHash.containsKey(dni)) {
			throw new RuntimeException("participante no registrado");
		} else {
			return participantesConAlbumes.get(DniToHash.get(dni)).getNombre();

		}
	}

	/**
	 * Dado el dni de un participante, devuelve el premio correspondiente
	 * por llenar el album.
	 * 
	 * Si el participante no está registrado, se debe lanzar una excepcion.
	 * Si no tiene el album completo, se debe lanzar una excepcion.
	 */
	public String darPremio(int dni) {
		if (!DniToHash.containsKey(dni)) {
			throw new RuntimeException("Lo sentimos, el participante no se encuentra registrado");
		}
		if (participantesConAlbumes.get(DniToHash.get(dni)).getAlbumpropio().isAlbumCompleto()) {
			return participantesConAlbumes.get(DniToHash.get(dni)).getAlbumpropio().darpremioFinal();

		} else {
			throw new RuntimeException("El album aun no esta completo");
		}
	}

	/**
	 * Devuelve un string con la lista de todos los participantes que
	 * tienen su album completo y el premio que les corresponde.
	 * El listado debe respetar el siguiente formato para cada ganador:
	 * " - ($dni) $nombre: $premio"
	 */
	public String listadoDeGanadores() {

		if (this.ganadores.isEmpty()) {
			System.out.println("la lista de ganadores esta vacia");
			return "";
		} else {
			return this.ganadores.toString();
		}

	}

	/**
	 * Devuelve una lista con todos los participantes que llenaron el pais
	 * pasado por parametro.
	 * 
	 * De cada participante se devuelve el siguiente String:
	 * "($dni) $nombre: $tipoAlbum"
	 */
	public ArrayList<String> participantesQueCompletaronElPais(String nombrePais) {
		Set<Integer> setOfKeys = participantesConAlbumes.keySet();// aca voy a guardar las keys
		ArrayList<String> completaron = new ArrayList<>();
		for (Integer key : setOfKeys) {
			if (participantesConAlbumes.get(key).getAlbumpropio().completePais(nombrePais) != null
					&& (boolean) participantesConAlbumes.get(key).getAlbumpropio().completePais(nombrePais)) {
				completaron.add(participantesConAlbumes.get(key).toString());
			}

		}
		return completaron;
	}

	
	public String toString() {


		StringBuilder ret = new StringBuilder();
		ret.append("~~~Album del mundial~~~ \n Cantidad de participantes que llenaron el Album: " + ganadores.size()
				+ "\n Cantidad que siguen participando(No llenaron): " + (DniToHash.size() - ganadores.size()));

	Set<Integer> setOfKeys = participantesConAlbumes.keySet();

	ret.append("\n Album del mundial, participantes:\n");
	for (Integer key : setOfKeys) {
		ret.append(" "+participantesConAlbumes.get(key).getNombre()+", Dni["+participantesConAlbumes.get(key).getDni()+"] "+participantesConAlbumes.get(key).porcentajeCompleto()+"% completado \n");

		
	}
	return ret.toString();
}
}
