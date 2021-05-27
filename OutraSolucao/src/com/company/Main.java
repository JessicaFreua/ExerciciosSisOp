package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("-- Jantar dos Filósofos --");
        ArrayList<Filosofo> filosofos = new ArrayList<>();

        filosofos.add(new Filosofo(0));
        filosofos.add(new Filosofo(1));
        filosofos.add(new Filosofo(2));
        filosofos.add(new Filosofo(3));
        filosofos.add(new Filosofo(4));
        Mesa mesa = new Mesa(filosofos);
        for (Filosofo f : filosofos) {
            f.setMesa(mesa);
            f.start();
        }

    }
}
