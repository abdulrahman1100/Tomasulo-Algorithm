package tomasulo;


public class Registers {
//	Dictionary<String,String> FU = new Hashtable<String,String>();
	String[] []RegQj;
	

	
	public Registers() {
		
		
//		regSlots("e","r","e","r","e","r","e","r");
//		print();
		
		
	}
	public  String[][] regStations(){
		RegQj=new String[2][8];
		
		
		RegQj[0][0]="r0";
		RegQj[0][1]="r1";
		RegQj[0][2]="r2";
		RegQj[0][3]="r3";
		RegQj[0][4]="r4";
		RegQj[0][5]="r5";
		RegQj[0][6]="r6";
		RegQj[0][7]="r7";
		
		
		
		for (int i = 1; i < 2; i++) {
			for (int j = 0; j < 8; j++) {
				RegQj[i][j]="0";
			}
			 //to be parsed parseint methode
			
			
			
			}
		return RegQj;
	}
	public void print() {
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j <8; j++) {
				System.out.print(RegQj[i][j]+"  ");
			}
			System.out.println();
		}
	}
//	public static void main (String []args) {
//		Registers r=new Registers();
//	}
}
