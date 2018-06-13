package control.accion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import modelo.Libro;
import modelo.Pagina;
import vista.LectorUI;

public class LectorBridge extends LectorUI {

	private Libro libro;

	public LectorBridge() {
		this.libro = new Libro();
		libro.avanzarPagina();
		textArea.setText(libro.mostrarPagina());
		lblNumeroPagina.setText(String.valueOf(libro.getActual()));
		
		//
		
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
			}
		});

		btnIrAMarca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
	}

}
