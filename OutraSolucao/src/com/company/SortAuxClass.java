package com.company;

import java.util.Random;

public class SortAuxClass {
    //Inclua estes atributos e métodos na sua classe de testes

    private static long tempoIni, tempoFim;

    public static  void initClock() {
        tempoIni = tempoFim = System.nanoTime();
    }

    public static double getClockSec() {

        double res;

        tempoFim = System.nanoTime();
        res = ((double)tempoFim - (double)tempoIni) / (double)1000000000.0;

        return res;

    }

    //Antes de chamar o método cujo tempo quer medir, chame

    //        initClock();

    //Após a execução do método a ser medido, chame

    //        double res = getClockSec();

}
