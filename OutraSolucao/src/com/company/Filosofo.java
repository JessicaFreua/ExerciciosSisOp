package com.company;

import java.util.ArrayList;
import java.util.Random;

import static java.lang.Thread.*;

public class Filosofo {
    //region Attributes
    Random gerador = new Random();
    public int idFilosofo;
    //endregion

    //region Constructor

    public Filosofo(int idFilosofo) {
        this.idFilosofo = idFilosofo;
    }

    //endregion

    //region Methods
    public void exist(Mesa mesa) throws InterruptedException {
        think(mesa);
        eat(mesa);
    }

    private void think(Mesa mesa) throws InterruptedException {
        System.out.println("O Filosofo "+idFilosofo+ " esta pensando...");
        sleep(gerador.nextInt()%5000);
    }

    private void eat(Mesa mesa) throws InterruptedException {
        int garfoEsquerdo = idFilosofo;
        int garfoDireito = idFilosofo+1;

        System.out.println("Filosofo " + idFilosofo + " esta procurando garfos >:/");
        synchronized (this){
            holdsLock(mesa.garfos.get(garfoEsquerdo));
            holdsLock(mesa.garfos.get(garfoDireito));
        }

        System.out.println("Filosofo " + idFilosofo + " esta comendo :P");
        sleep(gerador.nextInt()%10000);
        System.out.println("Filosofo " + idFilosofo + " largou seus garfos.");
    }

    public void getBoolean(Boolean bool){

    }

    //endregion

}

