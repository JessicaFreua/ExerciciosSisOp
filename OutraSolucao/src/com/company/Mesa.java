package com.company;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Mesa {
    //region Attributes
    private final static int NUMERO_GARFOS = 5;
    public ArrayList<Boolean> garfos = new ArrayList<>();
    public ArrayList<Filosofo> filosofos = new ArrayList<>();
    public ArrayList<Semaphore> semaphores = new ArrayList<>();
    //endregion

    //region Constructor

    public Mesa(ArrayList<Filosofo> filosofos) {
        for (int i = 1; i <= NUMERO_GARFOS; i++) {
            garfos.add(true);
        }
        this.filosofos.addAll(filosofos);


    }

    //endregion

    //region Methods
    public synchronized void pegarGarfos(int idFilosofo){
        int garfoEsquerdo = getGarfoEsquerdo(idFilosofo);
        int garfoDireito = getGarfoDireito(idFilosofo);
        while (!this.garfos.get(garfoEsquerdo) || !this.garfos.get(garfoDireito)) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        garfos.set(garfoEsquerdo,false);
        System.out.println(idFilosofo + " = pegou o garfo " + garfoEsquerdo + " e o " + garfoDireito);
        garfos.set(garfoDireito,false);
    }

    public synchronized void retornaGarfos(int idFilosofo) {
        int garfoEsquerdo = getGarfoEsquerdo(idFilosofo);
        int garfoDireito = getGarfoDireito(idFilosofo);
        garfos.set(garfoEsquerdo,true);
        System.out.println(idFilosofo + " = Garfo esquerdo " + garfoEsquerdo  + " foi liberado --E");
        garfos.set(garfoDireito,true);
        System.out.println(idFilosofo + " = Garfo direito " + garfoDireito  + " foi liberado --E");
        this.notifyAll();
    }


    public int getGarfoEsquerdo(int idFilosofo){
        if (idFilosofo-1 < 0 ){
            return garfos.size()-1;
        }
        return idFilosofo-1;
    }

    public int getGarfoDireito(int idFilosofo){
        if (idFilosofo+1 == garfos.size()){
           return 0;
        }
        return idFilosofo;
    }

    //endregion
}
