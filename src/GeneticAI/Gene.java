package GeneticAI;






// 1st-2nd are value 1
// 3rd-4th are value 2
// 5-6th are value 3
// 7th-8th are value 4 






import java.util.Random;

public class Gene {
	char[] sequence = new char[8];
	public Gene(char[] seq){
		sequence = seq;
	}
	public Gene(String seq){
		sequence = convertString(seq);
	}
	
	public char[] getSequence(){
		return sequence;
	}
	
	public void setSequence(char[] seq){
		sequence = seq;
	}
	
	
	public char[] convertString(String s){
		char[] returnArray = new char[s.length()];
		for(int i = 0; i<s.length(); i++){
			returnArray[i] = s.charAt(i);
			
		}
		return returnArray;
	}
	
	public void mutate(){
		Random r = new Random();
		
		int randomInt = r.nextInt(10);
		int secondRandom;
		if(randomInt < 9){
			
			
			randomInt = r.nextInt(7);
			secondRandom = r.nextInt(9);
			sequence[randomInt] = (char)(secondRandom+'0');
			
		}else{
			randomInt = r.nextInt(8);
			secondRandom = r.nextInt(9);
			sequence[randomInt] = (char)(secondRandom+'0');
			randomInt = r.nextInt(8);
			secondRandom = r.nextInt(9);
			sequence[randomInt] = (char)(secondRandom+'0');
		}	
	}
	
	public void print(){
		for(int i=0; i<8; i++){
			System.out.print(sequence[i]);
		}
		System.out.println("");
	}
	
	
	
	public static Gene generate(){
		Random r = new Random();
		char[] sequence = new char[8];
		
		for(int i =0; i<8;i++){
			int number = r.nextInt(9);
			sequence[i] = (char)(number+'0');
		}
		return new Gene(sequence);
	}
	
	
	
	public int getAtomicValue(int valueIndex){
		int offset = (valueIndex-1)*2;
		
		int valueOne = Integer.parseInt(sequence[0+offset]+"");
		int valueTwo = Integer.parseInt(sequence[1+offset]+"");
		int value = valueOne*10 + valueTwo;
		return value;
	}
	
	public float convertToFloat(){
		float value = 0;
		
		value += getAtomicValue(1);
		value += getAtomicValue(2);
		
		return value;
		
	}
	
	
	
}
