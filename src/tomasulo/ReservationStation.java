package tomasulo;

public class ReservationStation {

	
//	int[] reservStationNum;
	public String [][] content;
	
	int n;
//	String slots ;
//	String name;
//	String busy;
//	String Op;
//	String Vj;
//	String Vk;
//	String Qj;
//	String Qk;
//	String A;

	

	public ReservationStation() {
		// TODO Auto-generated constructor stub
	}


	public  String[][] stations(int n/*,String slots ,String name,String busy,String Op,String Vj,String Vk,String Qj,String Qk,String A*/) {
		
		//filling the space 
//		this.n=n;
//		this.slots=slots;
//		this.name=name;
//		this.busy=busy;
//		this.Op=Op;
//		this.Vj=Vj;
//		this.Vk=Vk;
//		this.Qj=Qj;
//		this.A=A;
		content=new String [n][9];
	//to be parsed parseint methode
		
			content[0][0]="slots "; //to be parsed parseint methode
			content[0][1]="name";
			content[0][2]="busy";
			content[0][3]="Op";
			content[0][4]="Vj";
			content[0][5]=" Vk";
			content[0][6]="Qj";
			content[0][7]="Qk";
			content[0][8]=" A";

		for (int i = 1; i < content.length; i++) {
			for (int j = 0; j < 9; j++) {
					content[i][j]="0";
			}
		}
		
		
		//first entry is the number of the stations for that ins 
		//[number of load stations |ld +two places for the operands.. and so on  |mul|div|mod||||||||||]
		return content;
	}
	public  void print() {
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <9; j++) {
				System.out.print(content[i][j]+"  ");
			}
			System.out.println();
		}
		
	}
	
	
	
//	public static void main (String []args) {
//		int v=9;
//		ReservationStation r =new ReservationStation(v);
//		
//	}


}
