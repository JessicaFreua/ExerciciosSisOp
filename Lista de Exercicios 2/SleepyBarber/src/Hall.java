import java.util.concurrent.Semaphore;

public class Hall {

    //region Attributes
    static final int QNT_BARBEIROS = 2;
    static final int QNT_CADEIRAS = 5;
    static final int MAXIMO_THREADS = 50;
    static Barber[] barb = new Barber[QNT_BARBEIROS];
    static Customer[] client = new Customer[MAXIMO_THREADS];
    static Semaphore barbeiros = new Semaphore(0);
    static Semaphore clientes = new Semaphore(0);
    static Semaphore cadeirasEspera = new Semaphore(QNT_CADEIRAS);
    //endregion

    //region Methods
    public static void main(String[] args){

        System.out.println("### Bem-vindo a Barbearia! ###");

        for(int i = 0; i < QNT_BARBEIROS; i++){
            barb[i] = new Barber(i);
            barb[i].start();
        }

        for(int i=0; i<MAXIMO_THREADS; i++){
            client[i] = new Customer(i);
            client[i].start();
            try {
                if (i%3 == 0) {
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    //endregion

}
