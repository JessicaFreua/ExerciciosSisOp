package jantar;

public class Filosofo extends Thread {
	
	int idSituacao;
	String nome;

    //situa��es
    final int PENSANDO = 0;
    final int FOME = 1;
    final int COMENDO = 2;

    public Filosofo(String nome, int idSituacao) {
        this.nome = nome;
        this.idSituacao = idSituacao;
    }
    
    
    public String getNome() {
		return nome;
	}



	public void Fome(){
        App.situacao[this.idSituacao] = 1;
        System.out.println("O Fil�sofo " + getNome() + " est� com fome.");
    }

    public void Comendo(){
        App.situacao[this.idSituacao] = 2;
        System.out.println("O Fil�sofo " + getNome() + " est� comendo.");
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException ex) {
            System.out.println("ERRO" + ex.getMessage());
        }
    }

    public void Pensando(){
        App.situacao[this.idSituacao] = 0;
        System.out.println("O Fil�sofo " + getNome() + " est� pensando.");
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException ex) {
            System.out.println("ERRO" + ex.getMessage());

        }
    }

    public void LargaOGarfo() throws InterruptedException {
        App.mutex.acquire();
        Pensando();

        //Quando o fil�sofo larga os garfos, os outros fil�sofos, tanto o da direita, quanto o da esquerda, tem a possibilidade de pegarem os garfos.
        App.filosofos[FilosofoEsquerda()].TentarPegarGarfos();
        App.filosofos[FilosofoDireita()].TentarPegarGarfos();
        App.mutex.release();
    }

    public synchronized void PegarOGarfo() throws InterruptedException {
        App.mutex.acquire();
        Fome();

        //Se semaforo(1), ent�o o fil�sofo consegue pegar o garfo
        TentarPegarGarfos();
        App.mutex.release();

        //Caso a condi��o seja falsa, ent�o o fil�sofo n�o conseguir� pegar o garfo e ter� que aguardar at� chegar a sua vez novamente
        App.semaforos[this.idSituacao].acquire();
    }

    public synchronized void TentarPegarGarfos() {
        
        //caso o fil�sofo atual estiver com fome, e seus vizinhos n�o estiverem comendo, ent�o � chamado o m�todo comendo para o fil�sofo atual.
        if(App.situacao[this.idSituacao] == 1 
           && App.situacao[FilosofoEsquerda()] !=2 
           && App.situacao[FilosofoDireita()] !=2){
        	
            Comendo();
            
            App.semaforos[this.idSituacao].release();
        } else {
            System.out.println(getNome() + " n�o conseguiu comer desta vez!");
        }
    }

    @Override
    public void run() {
        try {
            Pensando();
            System.out.println(" ");
            do {
                PegarOGarfo();
                Thread.sleep(1000L);
                LargaOGarfo();
            } while (true);
        } catch (InterruptedException ex) {
            System.out.println("ERRO" + ex.getMessage());
            return;
        }
    }

    public int FilosofoDireita() {
        return (this.idSituacao + 1) % 5;
    }

    public int FilosofoEsquerda() {
        //A esquerda do fil�sofo 0, estar� o fil�sofo 4
        if(this.idSituacao == 0) {
            return 4;
        } else {
            return (this.idSituacao - 1) % 5;
        }
    }

}
