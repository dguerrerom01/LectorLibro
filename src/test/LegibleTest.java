package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.*;

import modelo.Legible;
import modelo.Libro;

class LegibleTest {
	Legible instancia;
	
	@Before
	public void setUp() {
		instancia = new Libro();
	}
	
	
	@Test
	void testAvanzarPagina() {
		int paginaActual = ((Libro)instancia).getActual();
		instancia.avanzarPagina();
		assertEquals(paginaActual + 1, ((Libro)instancia).getActual());
		// Nos queda por probar que la ultima página no permite avanzar
	}

	@Test
	void testRetrocederPagina() {
		fail("No implementado aun");
	}

	@Test
	void testMarcarPagina() {
		fail("No implementado aun");
	}

	@Test
	void testIrAPagina() {
		fail("No implementado aun");
	}

}
