package com.company;

import java.util.Scanner;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        System.out.println("Digite o tamanho da matriz");
        Scanner teclado = new Scanner(System.in);
        int n = teclado.nextInt();
        Matriz matriz = new Matriz(n);
        matriz.matrizToString(matriz.matriz);

        matriz.run();


    }
}
