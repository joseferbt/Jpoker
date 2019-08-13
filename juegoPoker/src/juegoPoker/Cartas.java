package juegoPoker;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Cartas extends JLabel {
	public final ImageIcon defoutl = new ImageIcon("src/recursos/reverso.png");
	private ImageIcon imagen;
	private int idCarta;
	private String valor, palo;

	// palo toma valores de S(),C(),H(),D(diamond)
	public Cartas(String valor) {

		this.valor = valor;
		this.imagen = new ImageIcon("src/recursos/" + valor + ".png");
		this.setIcon(defoutl);
		if (valor.length() > 2) {
			idCarta = Integer.valueOf(valor.substring(0, 2));
			palo = valor.substring(2);
		} else {
			idCarta = Integer.valueOf(valor.substring(0, 1));
			palo = valor.substring(1);
		}
	}

	public String getValor() {
		return valor;
	}

	public String getPalo() {

		return palo;
	}

	public int getId() {
		return idCarta;
	}

	public ImageIcon getImagen() {
		return imagen;
	}

	public void setIcono() {
		this.setIcon(imagen);
	}

}
