package com.company;

import java.util.ArrayList;
import java.util.Scanner;

import static com.company.SortAuxClass.getClockSec;
import static com.company.SortAuxClass.initClock;
import static java.lang.Thread.onSpinWait;
import static java.lang.Thread.sleep;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("-- Jantar dos Filósofos --");
        ArrayList<Filosofo> filosofos = new ArrayList<>();

        filosofos.add(new Filosofo(0));
        filosofos.add(new Filosofo(1));
        filosofos.add(new Filosofo(2));
        filosofos.add(new Filosofo(3));
        filosofos.add(new Filosofo(4));
        Mesa mesa = new Mesa(filosofos);

        // SETTAR TEMPO EM QUE A MESA VAI FICAR RODANDO
        System.out.println("Até quantos segundos os filosófos poderão se servir?");
        Scanner scanner = new Scanner(System.in);
        mesa.tempoDeJanta = scanner.nextDouble();
        //
        initClock();
        for (Filosofo f : filosofos) {
            f.setMesa(mesa);
            f.start();
        }

        while(mesa.tempoDeJanta>getClockSec()){
            onSpinWait();
        }
        System.out.println("-- Os filósofos não podem mais se servir -- ");

        while (mesa.fimDaJanta()){
            onSpinWait();
        }
        System.out.println("-- janta encerrada -- tempo: " + getClockSec());
        System.out.println("## Resumo da janta ##");
        for (Filosofo f : mesa.filosofos) {
            System.out.println("Filosofo " + f.idFilosofo);
            System.out.println("    Comeu " + f.contador + " vezes.");
        }


    }
}
