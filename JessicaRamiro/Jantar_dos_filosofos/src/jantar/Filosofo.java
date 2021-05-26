package jantar;

public class Filosofo extends Thread {
	
	int idSituacao;
	String nome;

    //situações
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
        System.out.println("O Filósofo " + getNome() + " está com fome.");
    }

    public void Comendo(){
        App.situacao[this.idSituacao] = 2;
        System.out.println("O Filósofo " + getNome() + " está comendo.");
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException ex) {
            System.out.println("ERRO" + ex.getMessage());
        }
    }

    public void Pensando(){
        App.situacao[this.idSituacao] = 0;
        System.out.println("O Filósofo " + getNome() + " está pensando.");
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException ex) {
            System.out.println("ERRO" + ex.getMessage());

        }
    }

    public void LargaOGarfo() throws InterruptedException {
        App.mutex.acquire();
        Pensando();

        //Quando o filósofo larga os garfos, os outros filósofos, tanto o da direita, quanto o da esquerda, tem a possibilidade de pegarem os garfos.
        App.filosofos[FilosofoEsquerda()].TentarPegarGarfos();
        App.filosofos[FilosofoDireita()].TentarPegarGarfos();
        App.mutex.release();
    }

    public void PegarOGarfo() throws InterruptedException {
        App.mutex.acquire();
        Fome();

        //Se semaforo(1), então o filósofo consegue pegar o garfo
        TentarPegarGarfos();
        App.mutex.release();

        //Caso a condição seja falsa, então o filósofo não conseguirá pegar o garfo e terá que aguardar até chegar a sua vez novamente
        App.semaforos[this.idSituacao].acquire();
    }

    public void TentarPegarGarfos() {
        
        //caso o filósofo atual estiver com fome, e seus vizinhos não estiverem comendo, então é chamado o método comendo para o filósofo atual.
        if(App.situacao[this.idSituacao] == 1 
           && App.situacao[FilosofoEsquerda()] !=2 
           && App.situacao[FilosofoDireita()] !=2){
        	
            Comendo();
            
            App.semaforos[this.idSituacao].release();
        } else {
            System.out.println(getNome() + " não conseguiu comer desta vez!");
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
        //A esquerda do filósofo 0, estará o filósofo 4
        if(this.idSituacao == 0) {
            return 4;
        } else {
            return (this.idSituacao - 1) % 5;
        }
    }

}
