import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Customer extends Thread{

    //region Attributes
    int id;
    //endregion

    //region Constructor
    public Customer(int id){
        this.id = id;
    }
    //endregion

    //region Methods
    @Override
    public void run() {
        try{
            if (Hall.cadeirasEspera.tryAcquire(1,2, TimeUnit.SECONDS)) {
                want_a_haircut();
                Hall.clientes.release();
                Hall.barbeiros.acquire();
                System.out.println("Cliente "+ this.id + " foi embora satisfeito :)");
            }
            else {
                System.out.println("Barbearia Cheia! Cliente " + this.id + " foi embora.");

            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public void want_a_haircut(){
        System.out.println("Cliente " + this.id + " está esperando para cortar o cabelo");
    }
    //endregion
}
