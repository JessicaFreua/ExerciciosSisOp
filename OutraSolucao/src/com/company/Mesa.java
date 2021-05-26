package com.company;

import java.util.ArrayList;

public class Mesa {
    //region Attributes
    private final static int NUMERO_GARFOS = 5;
    public ArrayList<Integer> garfos = new ArrayList<>();
    public ArrayList<Filosofo> filosofos = new ArrayList<>();
    //endregion

    //region Constructor

    public Mesa(ArrayList<Filosofo> filosofos) {
        for (int i = 1; i <= NUMERO_GARFOS; i++) {
            garfos.add(i);
        }
        this.filosofos.addAll(filosofos);
    }

    //endregion

    //region Methods

    //endregion
}
