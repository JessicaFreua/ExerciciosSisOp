package LivrariaLeitoresEscritores;

import java.util.*;

public class Leitor extends Thread {
	
	private static Random r = new Random();
	private ControladorLivraria controlador;
	private int id;
	
	public Leitor(ControladorLivraria controlador, int id) {
		this.controlador = controlador;
		this.id = id;
	}
	
	public void run() {
		while(true) {
			try {
				controlador.entraLeitor(id);
				Thread.sleep(r.nextInt(200));
				
				controlador.saiLeitor(id);
				Thread.sleep(r.nextInt(300));
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}

}
