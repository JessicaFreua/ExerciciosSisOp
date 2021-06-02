import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Barber extends Thread{
    //region Attributes
    int id;
    Random random = new Random();
    private boolean sleeping = true;
    //endregion

    //region Contructor
    public Barber(int id){
        this.id = id;
    }
    //endregion


    //region Methods
    @Override
    public void run() {
        while (true) {
            try {
                if (Hall.clientes.tryAcquire(1,2, TimeUnit.SECONDS)) {
                    if (this.sleeping){
                        this.sleeping = false;
                        System.out.println("Barbeiro " + this.id + " acordou");
                    }
                    Hall.cadeirasEspera.release();
                    cut_hair();
                    Hall.barbeiros.release();
                }
                else {
                    if (!this.sleeping) {
                        this.sleeping= true;
                        System.out.println("Barbeiro " + this.id + " vai dar uma cochilada até o proximo cliente chegar");
                    }
                }
            }
            catch (Exception e){
                System.out.println(e);
            }
        }
    }


    private void cut_hair() throws Exception{
        System.out.println("O barbeiro "+id+" está trabalhando");
        sleep(random.nextInt(2000)+3000);
        System.out.println("O barbeiro "+this.id+" terminou o corte!");
    }
    //endregion

}
