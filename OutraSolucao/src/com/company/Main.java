package com.company;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        ArrayList<Filosofo> filosofos = new ArrayList<>();
        filosofos.add(new Filosofo(1));
        filosofos.add(new Filosofo(2));
        filosofos.add(new Filosofo(3));
        filosofos.add(new Filosofo(4));
        filosofos.add(new Filosofo(5));
        Mesa mesa = new Mesa(filosofos);

    }
}
