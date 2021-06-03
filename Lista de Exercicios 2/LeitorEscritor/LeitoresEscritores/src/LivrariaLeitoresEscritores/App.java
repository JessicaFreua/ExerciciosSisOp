package LivrariaLeitoresEscritores;

public class App {

	public static void main(String[] args) {
		
		ControladorLivraria controlador = new ControladorLivraria();
		Escritor[] escritor = new Escritor[5];
		Leitor[] leitor = new Leitor[10];
		
		for(int i=0; i<escritor.length; i++) {
			escritor[i] = new Escritor(controlador, i);
		}
		
		for(int i=0; i<leitor.length; i++) {
			leitor[i] = new Leitor(controlador, i);
		}
		
		for(int i=0; i<escritor.length; i++) {
			escritor[i].start();
		}
		
		for(int i=0; i<leitor.length; i++) {
			leitor[i].start();
		}

	}

}
