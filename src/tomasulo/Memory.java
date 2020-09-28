package tomasulo;

public class Memory {
	String[] memory;
	
	
	
	
	
	public Memory() {
		memory=new String[4096];
		print();
	}
	
	
	public String getMemory(short index) {
		return memory[index];
	}
	public void setMemory(String[] memory) {
		this.memory = memory;
	}
	
	public void store(short address,String word) {
		memory[address]=word;
	}
	public String load(short address) {
		
		return memory[address]; 
	}
	public void programData() {
		
	}
	public void print() {
		for (int i = 0; i < memory.length; i++) {
			System.out.println(memory[i]+" ");
		}
		
	}
	
	public static void main (String []args) {
		@SuppressWarnings("unused")
		Memory m=new Memory();
	}
}

