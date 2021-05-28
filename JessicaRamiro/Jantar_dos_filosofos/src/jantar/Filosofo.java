package jantar;

public class Filosofo extends Thread
{

    private int ID;

    final int PENSANDO = 0;
    final int COMFOME  = 1;
    final int COMENDO  = 2;

    public Filosofo (String nome, int ID)
    {
        super(nome);
        this.ID = ID;
    }

   public void ComFome ()
    {
       
        Mesa.situacao[this.ID] = 1;
      
        System.out.println("O Filosofo " + getName() + " esta com fome!");
    }

    public void Comendo ()
    {
        
        Mesa.situacao[this.ID] = 2;
        
        System.out.println("O Filosofo " + getName() + " esta comendo!");

        
        try
        {
            
            Thread.sleep(1000L);
        }
        catch (InterruptedException ex)
        {
           
            System.out.println("ERROR>" + ex.getMessage());
        }
    }

    
    public void Pensando ()
    {
        
        Mesa.situacao[this.ID] = 0;
      
        System.out.println("O Filosofo " + getName() + " esta pensando!");

      
        try
        {
            
            Thread.sleep(1000L);
        }
        catch (InterruptedException ex)
        {
            
            System.out.println("ERROR>" + ex.getMessage());
        }
    }

    
    public void LargaOGarfo ()
    {
        
        Mesa.mutex.decrementar();

        
        Pensando();

        
        Mesa.filosofo[FilosofoEsquerda()].TentarPegarGarfos();
        Mesa.filosofo[FilosofoDireita()].TentarPegarGarfos();

       
        Mesa.mutex.incrementar();
    }

    public void PegarOGarfo ()
    {
       
        Mesa.mutex.decrementar();

        ComFome();

        
        TentarPegarGarfos();

        
        Mesa.mutex.incrementar();
        
        Mesa.semaforos[this.ID].decrementar();
    }

    
    public void TentarPegarGarfos()
    {

        
        if (Mesa.situacao[this.ID] == 1 &&
            Mesa.situacao[FilosofoEsquerda()] != 2 && 
            Mesa.situacao[FilosofoDireita()] != 2)
        {
            
            Comendo();
            
            Mesa.semaforos[this.ID].incrementar();
        }

    }

    
    @Override
    public void run ()
    {

        try
        {
          
            Pensando();

           do
            {
                PegarOGarfo();
                Thread.sleep(1000L);
                LargaOGarfo();
            }
            while (true);
        }
        catch (InterruptedException ex)
        {
            
            System.out.println("ERROR>" + ex.getMessage());
           
            return;
        }

    }

    
    public int FilosofoDireita ()
    {
       
        return (this.ID + 1) % 5;
    }

    
    public int FilosofoEsquerda ()
    {
        if (this.ID == 0)
        {
            
            return 4;
        }
        else
        {
            
            return (this.ID - 1) % 5;
        }
    }

}
