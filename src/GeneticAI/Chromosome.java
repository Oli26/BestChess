package GeneticAI;

import java.util.Random;

public class Chromosome {
	
	Gene[] genes;
	
	public Chromosome(){
		int numberOfGenes = 100;
		genes = new Gene[numberOfGenes];
		for(int i = 0; i<numberOfGenes;i++){
			genes[i] = Gene.generate();
		}
		
	}

	
	public Gene getGene(int index){
		return genes[index];
	}
	
	
	
	
	
}
