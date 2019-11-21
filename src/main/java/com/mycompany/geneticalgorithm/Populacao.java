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
public class Populacao {

    private Cromossomo[] cromossomos;

    public Populacao(int tamanho) {
        cromossomos = new Cromossomo[tamanho];

    }

    public Populacao inicializarPopulacao() {
        for (int x = 0; x < cromossomos.length; x++) {
            cromossomos[x] = new Cromossomo(AlgoritimoGenetico.CROMOSSOMO_DESEJADO.length).inicialiarCromossomo();
        }
        ordenarCromossomosFitness();
        return this;
    }

    public Cromossomo[] getCromossomo() {
        return cromossomos;
    }

    public void ordenarCromossomosFitness() {
        Arrays.sort(cromossomos, (cromossomo1, cromossomo2) ->{
            int flag = 0;
            if(cromossomo1.getFitness() > cromossomo2.getFitness())flag = -1;
                    else  flag =1;
            return flag;
           });

    }
    
    public void novaPopulacao(Populacao population ,Cromossomo cromossomo){
        for(int x = 0; x< population.getCromossomo().length; x++){
            this.getCromossomo()[x] = cromossomo;
        }
        ordenarCromossomosFitness();
    
    }

}
