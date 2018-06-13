package modelo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class Libro implements Legible {

	private String lectura;
	private int actual = 0;
	private int marca = 0;
	private ArrayList<Pagina> paginas; 

	// 37 caracteres, 21 lineas

	public Libro() {
		super();
		lectura = "JuegoTronos.txt";
		paginas = new ArrayList<>();
	}

	@Override
	public void avanzarPagina() {
		if (!comprobarUltimaPagina()) {
			if (cargarSiguientePagina()) {
				actual++;
			}
		}

	}
	
	private boolean cargarSiguientePagina() {
		try (BufferedReader buffer = new BufferedReader(new InputStreamReader
				(new FileInputStream(lectura), "UTF-8"))) {
			// Comprobamos que al menos estamos en la primera pagina
			if (metodoParaComprobarQueElArrayListPaginasNoEsteVacio()) {
				buffer.skip(paginas.get(actual - 1).getUltimo());
				int puntero = 0;
				int posicion = metodoParaCargarLaSiguientePagina(buffer, puntero);
				// Almacenamos la pagina
				paginas.add(new Pagina(paginas.get(actual - 1).getUltimo(), (posicion + paginas.get(actual - 1).getUltimo()) ));
			} else {
				// Si no hay paginas, entonces debemos comenzar desde cero
				int puntero = 0;
				int posicion = metodoParaCargarLaSiguientePagina(buffer, puntero);
				// Almacenamos la pagina
				paginas.add(new Pagina(0, posicion));
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

	

	

	private boolean comprobarUltimaPagina() {
		// TODO
//		boolean retorno = true;
//		try (BufferedReader buffer = new BufferedReader(new InputStreamReader
//				(new FileInputStream(lectura), "UTF-8"))) {
//			if (buffer.skip(paginas.get(actual).getUltimo()) != -1) {
//				retorno = false;
//			}
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return retorno;
		return false;
	}

	@Override
	public void retrocederPagina() {
		if (!comprobarPrimeraPagina()) {
			actual--;
		}
	}

	private boolean comprobarPrimeraPagina() {
		boolean retorno = true;
		if (actual != 1) {
			retorno = false;
		}
		return retorno;
	}

	@Override
	public void marcarPagina() {
		marca = actual;
	}

	@Override
	public void irAPagina() {
		if (marca != 0) {
			actual = marca;
		}
	}

	/**
	 * Obtener la ruta en la que se encuentra el libro
	 * 
	 * @return lectura
	 */
	public String getLectura() {
		return lectura;
	}
	/**
	 * Obtener la pagina en la que estamos actualmente
	 * 
	 * @return actual
	 */
	public int getActual() {
		return actual;
	}
	/**
	 * Obtener el numero de pagina marcado
	 * 
	 * @return marca
	 */
	public int getMarca() {
		return marca;
	}
	/**
	 * Obtener el ArrayList de paginas
	 * 
	 * @return paginas
	 */
	public ArrayList<Pagina> getPaginas() {
		return paginas;
	}
	
	/**
	 * Envia un String con el contenido de la pagina
	 * 
	 * @return pagina
	 */
	public String mostrarPagina() {
		String pagina = "";
		System.out.println("Primera " + paginas.get(actual - 1).getPrimer());
		System.out.println("Ultima " + (paginas.get(actual - 1).getUltimo() - paginas.get(actual - 1).getPrimer()));
		System.out.println(actual);
		try (BufferedReader buffer = new BufferedReader(new InputStreamReader
				(new FileInputStream(lectura), "UTF-8"))) {
			buffer.skip(paginas.get(actual - 1).getPrimer());
			long tamano = 1;
			int caracter = buffer.read();
			while (caracter != -1 && tamano < (paginas.get(actual - 1).getUltimo() - paginas.get(actual - 1).getPrimer())) {
				pagina += Character.toString((char) caracter);
				caracter = buffer.read();
				tamano++;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return pagina;
	}
	
	
	/**
	 * Envia la posicion en la que debe terminar la pagina
	 * 
	 * @param buffer
	 * @param puntero
	 * @throws IOException 
	 */
	private Integer metodoParaCargarLaSiguientePagina(BufferedReader buffer, int puntero) throws IOException {
		// Creamos los atributos necesarios
		int caracter = buffer.read(); // El caracter (char)
		int tamano = 1; // Un puntero para controlar el tamano de la pagina
		String pagina = ""; // Guardara 854 caracteres de la pagina
		// Rellenamos la pagina
		while (caracter != -1 && tamano < 854) {
			pagina += Character.toString((char) caracter);
			caracter = buffer.read();
			tamano++;
		}
		// Buscando los limites
		puntero = 0; // Puntero que recorrera el string
		int cursor = 0; // Puntero que almacenara la ultima posicion valida
		// i = cada una de los saltos de linea
		for (int i = 0; i < 23; i++) {
			boolean saltoDeLinea = false; // Para detectar salto de linea
			// j = cada caracter maximo que compone una linea
			for (int j = 0; j < 37; j++) {
				// Mientras no haya salto de linea (13)...
				if (pagina.charAt(cursor) != 13 && !saltoDeLinea) {
					puntero++;
				} else {
					saltoDeLinea = true;
				}
				cursor++;
			}
			// Recorremos a la inversa para buscar el ultimo espacio disponible en la linea
			while (pagina.charAt(cursor) != 32 && !saltoDeLinea) {
				cursor--;
				puntero = cursor;
			}
			cursor = puntero;
			puntero++;
			cursor++;
		}
		return cursor;
	}
	
	
	/**
	 * Comprueba que el ArrayList de paginas no este vacio
	 * 
	 * @return TRUE si no esta vacio, FALSE si lo esta
	 */
	private boolean metodoParaComprobarQueElArrayListPaginasNoEsteVacio() {
		return !paginas.isEmpty();
	}
	
}
