package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.*;

public class Matriz {
    //region Attributes
    public int x = 0;
    public int y = -1;
    public int size = 0;
    public ArrayList<ArrayList<Integer>> matriz = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> matriz_aux = new ArrayList<>();
    public CyclicBarrier um_para_dois;
    public CyclicBarrier dois_para_um;
    public Semaphore mutex = new Semaphore(1);
    //endregion

    //region Constructor
    public Matriz(int n){
        this.size = n;
        Random rnd = new Random();
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> vetor = new ArrayList<>();
            ArrayList<Integer> vetor_aux = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                vetor.add(rnd.nextInt(99));
                vetor_aux.add(0);
            }
            matriz.add(vetor);
            matriz_aux.add(vetor_aux);
        }
        um_para_dois = new CyclicBarrier(n*n, parte2);
        dois_para_um = new CyclicBarrier(1, parte1);

    }
    //endregion

    //region Methods
    public void matrizToString(ArrayList<ArrayList<Integer>> m){
        for (int i = 0; i < m.size(); i++) {
            System.out.print("# "+(i)+ " # - ");
            for (int j = 0; j < m.get(i).size(); j++) {
                if (m.get(j).get(i).toString().length()>1) {
                    System.out.print(" " + m.get(j).get(i) + " ");
                }else {
                    System.out.print(" " + m.get(j).get(i) + "  ");
                }
            }
            System.out.println();
        }
    }

    Runnable parte1 = () -> {
        try {
            mutex.acquire();
            int x = getNextX();
            int y = getNextY();
            mutex.release();
            int resultado_soma = 0;
            ArrayList<Integer> resultado = new ArrayList<>();
            //sul
            if ((y + 1) < matriz.get(x).size()){
                resultado.add(matriz.get(x).get(y+1));
            }
            //norte
            if ((y-1) >= 0){
                resultado.add(matriz.get(x).get(y-1));
            }
            //leste
            if(x+1 < matriz.size()){
                resultado.add(matriz.get(x+1).get(y));
            }
            //oeste
            if ((x-1) >= 0){
                resultado.add(matriz.get(x-1).get(y));
            }
            for (Integer int_ : resultado) {
                resultado_soma+=int_;
            }
            matriz_aux.get(x).set(y,(resultado_soma/resultado.size()));
            await(um_para_dois);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    };

    private synchronized int getNextY() {
        if (y == -1){
            y++;
            return y;
        }
        if (y == this.size-1){
            y= 0;
            return 0;
        }
        y++;
        return y;

    }

    private synchronized int getNextX() {
        if(y==this.size-1){
            if(x==this.size-1){
                x=0;
            }else {
                x++;
            }
        }
        return x;
    }

    Runnable parte2 = () -> {
        matriz = matriz_aux;
        System.out.println("-------");
        System.out.println("Resultado média");
        matrizToString(matriz);
        System.out.println("-------");


    };


    private static void await(CyclicBarrier cyclicBarrier) {
        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public void run() throws InterruptedException {
        ExecutorService executor;
        while(true) {
            executor = Executors.newFixedThreadPool(size*size);
            for (int i = 0; i < (size * size) + 10; i++) {
                executor.submit(parte1);
            }
            executor.shutdown();
            Thread.sleep(1000L);
        }
    }


    //endregion
}
