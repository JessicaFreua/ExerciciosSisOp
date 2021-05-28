package com.company;

import java.util.Random;


import static com.company.SortAuxClass.getClockSec;

public class Filosofo extends Thread{
    //region Attributes
    public int idFilosofo;
    private Mesa mesa;
    private final Random gerador = new Random();
    int contador=0;
    int tentouPegar=0;
    boolean satisfeito = false;
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
        while(mesa.tempoDeJanta > getClockSec()) {
            think();
            eat();
        }
        satisfeito=true;
        System.out.println("# Filosofo " + idFilosofo + " ja esta satisfeito");
    }


    private void think() throws InterruptedException {
        System.out.println("O Filosofo "+idFilosofo+ " esta pensando...");
        sleep(gerador.nextInt(1000));
    }


    private void eat() throws InterruptedException {
        System.out.println("Filosofo " + idFilosofo + " esta procurando garfos >:/");

        synchronized (this){
            mesa.pegarGarfos(idFilosofo);
        }

        System.out.println("Filosofo " + idFilosofo + " esta comendo :P");
        sleep(gerador.nextInt(3000));
        mesa.retornaGarfos(idFilosofo);
        System.out.println("Filosofo " + idFilosofo + " largou seus garfos.");
        contador++;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    //endregion

}

