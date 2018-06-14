package control.accion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import modelo.Libro;
import vista.LectorUI;

public class LectorBridge extends LectorUI {

	private Libro libro;

	public LectorBridge() {
		this.libro = new Libro();
		libro.avanzarPagina();
		textArea.setText(libro.mostrarPagina());
		lblNumeroPagina.setText(String.valueOf(libro.getActual()));

		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				libro.retrocederPagina();
				textArea.setText(libro.mostrarPagina());
				lblNumeroPagina.setText(String.valueOf(libro.getActual()));
			}
		});

		btnAlante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				libro.avanzarPagina();
				textArea.setText(libro.mostrarPagina());
				lblNumeroPagina.setText(String.valueOf(libro.getActual()));
			}
		});

		btnMarcar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				libro.marcarPagina();
			}
		});

		btnIrAMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				libro.irAPagina();
				textArea.setText(libro.mostrarPagina());
				lblNumeroPagina.setText(String.valueOf(libro.getActual()));
			}
		});
	}

}
