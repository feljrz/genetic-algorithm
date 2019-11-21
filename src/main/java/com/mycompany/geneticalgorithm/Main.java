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
public class Main {
    
    public static void print(String str){
        System.err.println(str);
    }
    
    public static void printPopulacao(Populacao population){
        print("Cromossomo Desejado: "+ Arrays.toString(AlgoritimoGenetico.CROMOSSOMO_DESEJADO));
        print("-----------------------------------------------");
        for(int x = 0; x < population.getCromossomo().length; x++){
            print("Cromossomo # "+x+" : "+Arrays.toString(population.getCromossomo()[x].getGenes())+" | Fitness "+population.getCromossomo()[x].getFitness());
        
        }
    
    }
    
    
    
    public static void main (String [] args){
        Populacao population = new Populacao(AlgoritimoGenetico.TAMANHO_CROMOSSOMO).inicializarPopulacao();
        AlgoritimoGenetico AG = new AlgoritimoGenetico();
        int geracao = 0;
       
        
        
        while(population.getCromossomo()[0].getFitness() < AlgoritimoGenetico.CROMOSSOMO_DESEJADO.length){
            geracao ++;
            print("------------------------------");
            population = AG.evolucao(population);
            population.ordenarCromossomosFitness();
             print("Geração #"+geracao+" | Maior Finess: "+ population.getCromossomo()[0].getFitness());
             printPopulacao(population);
        }
        
   
    
    }
}
