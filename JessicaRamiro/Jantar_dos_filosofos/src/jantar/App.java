package jantar;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
	
	static Semaphore mutex = new Semaphore(0);

    static Semaphore[] semaforos = new Semaphore[5]; // Criando um sem�foro para cada fil�sofo.

    static Filosofo[] filosofos = new Filosofo[5]; // Criando os fil�sofos.
    static int[] situacao = new int[5];

	public static void main(String[] args) {
		
		//A situa��o inicial de todos os fil�sofos � PENSANDO
        for(int i = 0; i < situacao.length; i++){
            situacao[i] = 0;
        }

        //Inicializando os fil�sofos
        filosofos[0] = new Filosofo("Plat�o", 0);
        filosofos[1] = new Filosofo("Arist�teles", 1);
        filosofos[2] = new Filosofo("S�crates", 2);
        filosofos[3] = new Filosofo("Immanuel", 3);
        filosofos[4] = new Filosofo("Ren�", 4);       
    
        //Descobrir quais garfos pertence a quais fil�sofos
        for(int i = 0; i<filosofos.length; i++){
            System.out.println("O garfo " + i + " est� com o fis�sofo " + i + " e tamb�m o garfo " + (i + 1) % 5);

        } 

        System.out.println(" ");

        for(int i = 0; i<semaforos.length; i++){
            semaforos[i] = new Semaphore(0);
        }

        for(int i = 0; i<filosofos.length; i++){
            filosofos[i].start();
        }

        /*try {
            Thread.sleep(10000);
            System.exit(0);
        } catch (InterruptedException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }*/

	}

}
