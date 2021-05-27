package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


import static java.lang.Thread.*;

public class Filosofo extends Thread{
    //region Attributes
    public int idFilosofo;
    private Mesa mesa;
    private final Random gerador = new Random();
    int contador=0;
    //endregion

    //region Constructor

    public Filosofo(int idFilosofo) {
        this.idFilosofo = idFilosofo;
    }

    //endregion

    //region Methods


    @Override
    public void run() {
        try{
            exist();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public void exist() throws InterruptedException {
        while(true) {
            output_garfos();
            think(mesa);
            eat(mesa);
        }
    }

    private void output_garfos() {
        for (int i = 0; i < mesa.garfos.size(); i++){
            System.out.print(" garfo"+ i +  "= " + mesa.garfos.get(i));
        }
        System.out.println();
    }

    private void think(Mesa mesa) throws InterruptedException {
        System.out.println("O Filosofo "+idFilosofo+ " esta pensando...");
        sleep(gerador.nextInt(5));
    }


    private void eat(Mesa mesa) throws InterruptedException {
        System.out.println("Filosofo " + idFilosofo + " esta procurando garfos >:/");

        synchronized (this){
            mesa.pegarGarfos(idFilosofo);
        }

        System.out.println("Filosofo " + idFilosofo + " esta comendo :P");
        sleep(gerador.nextInt(10));
        mesa.retornaGarfos(idFilosofo);
        System.out.println("Filosofo " + idFilosofo + " largou seus garfos.");
        contador++;

    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    //endregion

}

