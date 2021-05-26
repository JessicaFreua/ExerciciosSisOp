package jantar;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {
	
	static Semaphore mutex = new Semaphore(0);

    static Semaphore[] semaforos = new Semaphore[5]; // Criando um semáforo para cada filósofo.

    static Filosofo[] filosofos = new Filosofo[5]; // Criando os filósofos.
    static int[] situacao = new int[5];

	public static void main(String[] args) {
		
		//A situação inicial de todos os filósofos é PENSANDO
        for(int i = 0; i < situacao.length; i++){
            situacao[i] = 0;
        }

        //Inicializando os filósofos
        filosofos[0] = new Filosofo("Platão", 0);
        filosofos[1] = new Filosofo("Aristóteles", 1);
        filosofos[2] = new Filosofo("Sócrates", 2);
        filosofos[3] = new Filosofo("Immanuel", 3);
        filosofos[4] = new Filosofo("René", 4);       
    
        //Descobrir quais garfos pertence a quais filósofos
        for(int i = 0; i<filosofos.length; i++){
            System.out.println("O garfo " + i + " está com o fisósofo " + i + " e também o garfo " + (i + 1) % 5);

        } 

        System.out.println(" ");

        for(int i = 0; i<semaforos.length; i++){
            semaforos[i] = new Semaphore(0);
        }

        for(int i = 0; i<filosofos.length; i++){
            filosofos[i].start();
        }

        try {
            Thread.sleep(10000);
            System.exit(0);
        } catch (InterruptedException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

	}

}
