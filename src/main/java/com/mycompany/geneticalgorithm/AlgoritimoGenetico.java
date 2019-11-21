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
public class AlgoritimoGenetico {

    public static int[] CROMOSSOMO_DESEJADO = {1, 1, 0, 1, 0, 0, 1, 1, 1, 0};
    public static final int TAMANHO_CROMOSSOMO = 8;
    public static final int ELITISMO = 1;
    public static final int SELECAO_TORNEIO_TAMANHO = 4;
    public static final double MUTACAO_RATE = 0.25;

    public Populacao evolucao(Populacao population) {

        return crossoverPopulacao(mutacaoPopulacao(population));
    }

    public Populacao crossoverPopulacao(Populacao population) {
        Populacao populacaoDeCrossover = new Populacao(population.getCromossomo().length);
        for (int x = 0; x < ELITISMO; x++) {
            populacaoDeCrossover.getCromossomo()[x] = population.getCromossomo()[x];
        }

        for (int x = ELITISMO; x < population.getCromossomo().length; x++) {
            Cromossomo cromossomo1 = selecaoTorneio(population).getCromossomo()[0];
            Cromossomo cromossomo2 = selecaoTorneio(population).getCromossomo()[0];

            populacaoDeCrossover.getCromossomo()[x] = crossoverCromossomo(cromossomo1, cromossomo2);
        }

        return populacaoDeCrossover;
    }

    public Cromossomo mutacaoCromossomo(Cromossomo cromossomo) {
        Cromossomo mutacaoCromossomo = new Cromossomo(CROMOSSOMO_DESEJADO.length);
        for (int x = 0; x < cromossomo.getGenes().length; x++) {
            if (Math.random() < MUTACAO_RATE) {
                if (Math.random() < 0.5) {
                    mutacaoCromossomo.getGenes()[x] = 1;
                } else {
                    mutacaoCromossomo.getGenes()[x] = 0;
                }
            } else {
                mutacaoCromossomo.getGenes()[x] = cromossomo.getGenes()[x];
            }

        }
        return mutacaoCromossomo;
    }

    public Populacao mutacaoPopulacao(Populacao population) {
        Populacao populacaoDeMutacao = new Populacao(population.getCromossomo().length);
        for (int x = 0; x < ELITISMO; x++) {
            populacaoDeMutacao.getCromossomo()[x] = population.getCromossomo()[x];
        }
        for (int x = ELITISMO; x < population.getCromossomo().length; x++) {
            populacaoDeMutacao.getCromossomo()[x] = mutacaoCromossomo(population.getCromossomo()[x]);

        }
        return populacaoDeMutacao;
    }

    public Cromossomo crossoverCromossomo(Cromossomo cromossomo1, Cromossomo cromossomo2) {
        Cromossomo crossoverCromossomo = new Cromossomo(CROMOSSOMO_DESEJADO.length);
        for (int x = 0; x < cromossomo1.getGenes().length; x++) {
            if (Math.random() > 0.5) {
                crossoverCromossomo.getGenes()[x] = cromossomo1.getGenes()[x];
            } else {
                crossoverCromossomo.getGenes()[x] = cromossomo2.getGenes()[x];
            }
        }
        return crossoverCromossomo;
    }

    public Cromossomo tournamentSelection(Populacao population) {
        Populacao populacaoTorneio = new Populacao(SELECAO_TORNEIO_TAMANHO);
        double probabilidade = 0.95;
        int flag = 0;
        System.arraycopy(population.getCromossomo(), 0, populacaoTorneio.getCromossomo(), 0, SELECAO_TORNEIO_TAMANHO);

        for (int x = 0; x < SELECAO_TORNEIO_TAMANHO; x++) {
            if (Math.random() < probabilidade) {
                return populacaoTorneio.getCromossomo()[x];
            } else {
                flag++;
                return population.getCromossomo()[SELECAO_TORNEIO_TAMANHO - flag];
            }
        }
        populacaoTorneio.ordenarCromossomosFitness();
        return null;
       
    }
    
    public Populacao selecaoTorneio(Populacao population){
       Populacao populacaoTorneio = new Populacao(SELECAO_TORNEIO_TAMANHO);
       for(int x = 0; x < SELECAO_TORNEIO_TAMANHO; x++){
           populacaoTorneio.getCromossomo()[x] = population.getCromossomo()[(int) (Math.random() * population.getCromossomo().length)];
           //System.out.println("Valor Torneio: "+(int) (Math.random() * population.getCromossomo().length));
       }
       populacaoTorneio.ordenarCromossomosFitness();
       return populacaoTorneio;
    }

}
