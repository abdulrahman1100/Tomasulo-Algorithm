package tomasulo;

public class InstructionBuffer {
	
	String [][] buffer;
	
//	String Operation ;
//	String destReg;
//	String Operand1;
//	String Operand2;
//	String Issue;
//	String Excute;
//	String Write;
	int totalNumberOfinst;
	
	public InstructionBuffer(int totalNumberOfinst) {
		this.totalNumberOfinst=totalNumberOfinst;
		bufferStations(totalNumberOfinst);
		print();
	}
	public InstructionBuffer() {
		
	}
	public String[][] bufferStations(int totalNumberOfinst ){
		
		buffer=new String [totalNumberOfinst][8];
		
//		this.Operation=Operation;
//		this.destReg=destReg;
//		this.Operand1=Operand1;
//		this.Operand2=Operand2;
//		this.Issue=Issue;
//		this.Excute=Excute;
//		this.Write=Write;
		this.totalNumberOfinst=totalNumberOfinst;
		
		buffer[0][0]="Operation";
		buffer[0][1]="Operand1";
		buffer[0][2]="Operand2";
		buffer[0][3]="Operand3";
		buffer[0][4]="Issue";
		buffer[0][5]="Excute";
		buffer[0][6]="Write";
		buffer[0][7]="cycles";
		
		for (int i = 1; i < totalNumberOfinst; i++) {
			for (int j = 0; j < 8; j++) {
				
				buffer[i][j]="0";
				
			}
		}
		
		return buffer;

	}
	
	public void print() {
		
		for (int i = 0; i < totalNumberOfinst; i++) {
			for (int j = 0; j < 8; j++) {
				
				System.out.print(buffer[i][j]+" ");
				
			}
			System.out.println();
		}
		
	}
	
//	public static void main (String []args) {
//		int n=9;
//		InstructionBuffer i=new InstructionBuffer(n);
//	}

}
