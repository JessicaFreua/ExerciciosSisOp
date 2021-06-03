package LivrariaLeitoresEscritores;

public class ControladorLivraria {
	
	private int nLeitores = 0;
	private boolean existeEscritor = false;
	
	public synchronized void entraLeitor(int id) throws InterruptedException {
		while(existeEscritor) {
			wait();
		}
		nLeitores++;
		System.out.println("Leitor " + id + " entra no controlador da livraria");
	}
	
	public synchronized void saiLeitor(int id) {
		System.out.println("Leitor " + id + " sai do controlador da livraria");
		nLeitores--;
		if(nLeitores==0) notify();
	}
	
	public synchronized void entraEscritor(int id) throws InterruptedException {
		while(existeEscritor || nLeitores>0) {
			wait();
		}
		existeEscritor = true;
		System.out.println("Escritor " + id + " entra no controlador da livraria");
	}
	
	public synchronized void saiEscritor(int id) {
		System.out.println("Escritor " + id + " sai do controlador da livraria");
		existeEscritor = false;
		notifyAll();
	}

}
