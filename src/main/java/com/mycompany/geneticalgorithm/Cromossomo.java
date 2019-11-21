/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.geneticalgorithm;

import java.util.Arrays;

/**
 *
 * @author felipe
 */
public class Cromossomo {

    private int[] genes;
    private int fitness = 0;
    private boolean mudancaFitness = true;

    public Cromossomo(int tamanho) {
        genes = new int[tamanho];

    }

    public Cromossomo inicialiarCromossomo() {
        for (int x = 0; x < genes.length; x++) {
            if (Math.random() > 0.5) {
                genes[x] = 1;
            } else {
                genes[x] = 0;
            }
        }
        return this;
    }

    public int calculoFitness() {
        int novoFitness = 0;
        for (int x = 0; x < genes.length; x++) {
            if (genes[x] == AlgoritimoGenetico.CROMOSSOMO_DESEJADO[x]) {
                novoFitness++;
            }
        }
        return novoFitness;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.genes);
    }

    public int[] getGenes() {
        mudancaFitness = true;
        return genes;
    }

    public int getFitness() {
        if (mudancaFitness) {
            fitness = calculoFitness();
            mudancaFitness = false;
        }
        return fitness;
    }

}
