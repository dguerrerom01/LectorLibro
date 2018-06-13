package modelo;

public interface Legible {

	/**
	 * Avanza una pagina. Debe detectar que hay al menos una pagina
	 */
	public void avanzarPagina();

	/**
	 * Retrocede una pagina. Debe detectar que estamos en la primera pagina
	 */
	public void retrocederPagina();

	/**
	 * Marcar una pagina para volver a ella
	 */
	public void marcarPagina();

	/**
	 * Ir a la pagina marcada. Si no hay pagina marcada, no hara nada
	 */
	public void irAPagina();
}
