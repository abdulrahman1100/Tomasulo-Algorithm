package tomasulo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;




public class Tomasulo {
	

	
	
	 //the programm counter to count 1 after every inst .
	static Translater t;
	static ROB rob;
//	static Memory memory=new Memory();
	static RegisterFiles regFiles=new RegisterFiles();
	static InstructionBuffer ib;
	static Registers regs;
	static ReservationStation rs;
	static String [][] cyclNumFuncUnit;// this is for getting the number of cycles each inst needs 
	static String [][]SlotsForEveryInst;//array of strings to get the number of every slot for each func
	static String [][]clockForEveryInst;//to keep track of every inst`s counter to be able to increment the counters 
	static LinkedList<String[]>ROB=new LinkedList<String[]>();
	static String[] Memory=new String[4096];
	static String SREGS[][]=new String[2][8];
	
	static int PC=1;
	static int programeClock=1;
	static int PCTemp=1;
	static int notTakenBranch=1;
	static int takenBranch=1;
	static double IPC;
	
	
	static boolean negativ;
	//we can initilaze flaags to indicate wether a specific reg finished its excution or not to be used in
	//the other instruction to avoid hazards ..
	public Tomasulo() {
		
	}

	public Tomasulo(int n) throws IOException, InterruptedException {
		
		initializingTheMemory();
//		readFile("s");
//		instructionsplitting();
		
//		memprint();
//		totalCycles=n;
//		addToInsBuff("s");
		
		///filling the reservation stations 
//		fillRStations();
		////////////////////////////////
		
		//fill the cyclNumFuncUnit
//		setCycForFu();
		//////////
		
		//ceating the instruction buffer 
//		fillInstructionBuffer();
		/////////////////////////////
		
		//whole prog 
		tomasuloAlgorithm1();
		//
		
//		createSREG();
//		printSREG();
//		createRegStations();
//		createBuffers(5);
		
		
//		SlotsForEveryInst();
//		createCycForFu();
//		countersForEveryInst();
		//create stations 
//		createRStations();
		}
	public static String [][] createCycForFu() {
		cyclNumFuncUnit=new String[2][7];
		
		cyclNumFuncUnit[0][0]="ld";
		cyclNumFuncUnit[0][1]="add";
		cyclNumFuncUnit[0][2]="mul";
		cyclNumFuncUnit[0][3]="sub";
		cyclNumFuncUnit[0][4]="mod";
		cyclNumFuncUnit[0][5]="st";
		cyclNumFuncUnit[0][6]="div";
		
		
		
		@SuppressWarnings("resource")
		Scanner sc	=new Scanner(System.in);
		String 	s1	=sc.nextLine();
		String 	s2	=sc.nextLine();
		String 	s3	=sc.nextLine();
		String 	s4	=sc.nextLine();
		String 	s5	=sc.nextLine();
		String 	s6	=sc.nextLine();
		String 	s7	=sc.nextLine();
		
		for (int i = 1; i < 2; i++) {
			
			cyclNumFuncUnit[i][0]=s1;
			cyclNumFuncUnit[i][1]=s2;
			cyclNumFuncUnit[i][2]=s3;
			cyclNumFuncUnit[i][3]=s4;
			cyclNumFuncUnit[i][4]=s5;
			cyclNumFuncUnit[i][5]=s6;
			cyclNumFuncUnit[i][6]=s7;
		}
		
		System.out.print(cyclNumFuncUnit[0][0]+"  ");
		System.out.print(cyclNumFuncUnit[0][1]+"  ");
		System.out.print(cyclNumFuncUnit[0][2]+"  ");
		System.out.print(cyclNumFuncUnit[0][3]+"  ");
		System.out.print(cyclNumFuncUnit[0][4]+"  ");
		System.out.print(cyclNumFuncUnit[0][5]+"  ");
		System.out.print(cyclNumFuncUnit[0][6]+"  ");
		System.out.println();
		
		for (int i = 1; i < 2; i++) {
			for (int j = 0; j < 7; j++) {
				System.out.print(cyclNumFuncUnit[i][j]+"    ");
			}
			System.out.println();
		}
		
		return cyclNumFuncUnit;
		
		
	}
	
	public static String [][] SlotsForEveryInst() {
		SlotsForEveryInst=new String[2][7];
		
		SlotsForEveryInst[0][0]="ld";
		SlotsForEveryInst[0][1]="add";
		SlotsForEveryInst[0][2]="mul";
		SlotsForEveryInst[0][3]="sub";
		SlotsForEveryInst[0][4]="mod";
		SlotsForEveryInst[0][5]="st";
		SlotsForEveryInst[0][6]="div";
		
		
		
		@SuppressWarnings("resource")
		Scanner sc	=new Scanner(System.in);
		String 	s1	=sc.nextLine();
		String 	s2	=sc.nextLine();
		String 	s3	=sc.nextLine();
		String 	s4	=sc.nextLine();
		String 	s5	=sc.nextLine();
		String 	s6	=sc.nextLine();
		String 	s7	=sc.nextLine();
		
		for (int i = 1; i < 2; i++) {
			
			SlotsForEveryInst[i][0]=s1;
			SlotsForEveryInst[i][1]=s2;
			SlotsForEveryInst[i][2]=s3;
			SlotsForEveryInst[i][3]=s4;
			SlotsForEveryInst[i][4]=s5;
			SlotsForEveryInst[i][5]=s6;
			SlotsForEveryInst[i][6]=s7;
		}
		System.out.print(SlotsForEveryInst[0][0]+"  ");
		System.out.print(SlotsForEveryInst[0][1]+"  ");
		System.out.print(SlotsForEveryInst[0][2]+"  ");
		System.out.print(SlotsForEveryInst[0][3]+"  ");
		System.out.print(SlotsForEveryInst[0][4]+"  ");
		System.out.print(SlotsForEveryInst[0][5]+"  ");
		System.out.print(SlotsForEveryInst[0][6]+"  ");
		System.out.println();
		for (int i = 1; i < 2; i++) {
			for (int j = 0; j < 7; j++) {
				System.out.print(SlotsForEveryInst[i][j]+"    ");
				
				
			}
			System.out.println();
		}
		return SlotsForEveryInst;
		
		
	}
	
	public static String [][] clockForEveryInst() {
		@SuppressWarnings("resource")
		Scanner sc	=new Scanner(System.in);
		
		int n=sc.nextInt();
		clockForEveryInst=new String[2][n];
		

		for (int i = 0; i <2; i++) {
			for (int j = 0; j < n; j++) {
				
				clockForEveryInst[i][j]="0";
			}
			
		}
		
		for (int i = 0; i <2; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(clockForEveryInst[i][j]+"  ");
			}
			System.out.println("  ");
			}
		return clockForEveryInst;
		
		
	}
	public static String[][] createBuffers(int n) throws IOException{
		
		
		
		readFile("s");
		
		ib=new InstructionBuffer();
		String [][]s=ib.bufferStations(n);
		
		ArrayList<String> inst=new ArrayList<String>();
		
		inst=instructionsplitting();
		
		
		//now fill the buffer 
//		System.out.println(inst.size());
		int z=1;
		
		//to fill the funcs////////////////////
		for (int i =0; i <n*3; i+=4) {
			if(!inst.get(i).equals("0")) {
			s[z][0]=inst.get(i);
			z++;
			}
		}
		//to fill the operands////////////////
		
		int x=1;
		for (int i = 1; i <n*3; i+=4) {
			if(!inst.get(i).equals("0")) {
			s[x][1]=inst.get(i);
			x++;
			}
		}
		
		int m=1;
		for (int i = 2; i <n*3; i+=4) {
			if(!inst.get(i).equals("0")) {
			s[m][2]=inst.get(i);
			m++;
			}
		}
		
		int u=1;
		for (int i = 3; i <n*3; i+=4) {
			if(!inst.get(i).equals("0")) {
			s[u][3]=inst.get(i);
			u++;
			}
		}
		
		//fill the memory with the inst
		
		
//		
		for (int i = 0; i < s.length; i++) {
			for (int j = 0; j < 6; j++) {
				System.out.print(s[i][j]+"  ");
			}
			System.out.println();
		}
		
//		for (int i = 0; i < Memory.size(); i++) {
//			System.out.println(Memory.get(i));
//		}
		
		
		
		
		return s;
		
	}
	public void createSREGS() {
		
	}

	public static String[][] createRStations(int n ) {
		
//		Scanner sc =new Scanner (System.in);
//		System.out.println("Enter your values ,Press enter after evry value as follows,if you need 4 stations you need to write 5  : "
//				+ "int n , String slots , String name, String busy , String Op , String Vj , String Vk , String Qj , String Qk , String A ");
//		n=sc.nextInt();
//		String string1=sc.nextLine();
//		String string2=sc.nextLine();
//		String string3=sc.nextLine();
//		String string4=sc.nextLine();
//		String string5=sc.nextLine();
//		String string6=sc.nextLine();
//		String string7=sc.nextLine();
//		String string8=sc.nextLine();
//		String string9=sc.nextLine();
		
		
		rs=new ReservationStation();
		
		String [][] vr=rs.stations(n/*,string1,string2,string3,string4,string5,string6,string7,string8,string9*/);
		
		rs.print();
		return vr;
		
	}
	public static String[][] createRegStations() {
		
		regs=new Registers();
		String [][] rs=regs.regStations();
//		regs.print();
		return rs;
	}

	public static void createSREG() {
		
		SREGS[0][0]="I";
		SREGS[0][1]="T";
		SREGS[0][2]="H";
		SREGS[0][3]="S";
		SREGS[0][4]="V";
		SREGS[0][5]="N";
		SREGS[0][6]="Z";
		SREGS[0][7]="C";
		
		for (int i = 0; i < 8; i++) {
			SREGS[1][i]="0";
			}
		
		
	}
	
	public static void printSREG() {
		
		System.out.println(" ");
		System.out.println("SREG values:   ");
		
		for (int i = 0; i < SREGS.length; i++) {
			for (int j = 0; j <8; j++) {
				System.out.print(SREGS[i][j]+" ");
			}
			System.out.println("  ");
		}
	}
	
	static String 	[][] 	reservationStations1;
	static String 	[][]	instructionBuffer1;
	static String	[][] 	cycles1;
	static String	[][]	availableSlots1;
	static String	[][]	regStations1;
	
	@SuppressWarnings("unused")
	public static void  tomasuloAlgorithm1() throws IOException, InterruptedException {
		@SuppressWarnings("resource")
		Scanner sc=new Scanner(System.in);

		//initializing the SREG
		createSREG();
		
		System.out.println("please, enter the total number of instructions to create the instruction buffer"
				+ " if you need 4 inst enter 5 and so on .. ");
		int n =sc.nextInt();//number of buffers in the inst buffer 
		//create inst buffer 
		instructionBuffer1=createBuffers(n);
		
		//now fill the buffer 
		
	
		//now start to fill the reservation stations 
		System.out.println("now input the all total number of inst again to create the reservation stations !!");
		int m1=sc.nextInt();
		reservationStations1=createRStations( m1 );
//		st[1][5]="yes";
		
		//create number of cycles for each inst arr to now when should every inst finishes execu
		System.out.println("pleas, enter the number of cyc for every FU as follows"
				+ " ld, add, mul, sub, mod, st, div");
		
		cycles1=createCycForFu();
		
		//create the available slots
		System.out.println("pleas, enter the number of available slots for every inst as follows "
				+ "ld , add ,mul ,sub ,mod , st, div");
		availableSlots1=SlotsForEveryInst();
		
		//create the counters for every inst ;
//		System.out.println("now for counting clocks for every inst you should insert the total"
//				+ "number of inst that you hav  ");
//		String[][] clocks=clockForEveryInst();
//		
		//create the regs slots 
		
		regStations1=createRegStations();
		
//		for (int i = 0; i <regStations.length ; i++) {
//			for (int j = 0; j <8; j++) {
//				System.out.print(regStations[i][j]+"  ");
//			}
//			System.out.println("");
//		}
		
//		first try to fill the regs to test 
		ldi((byte)3,"r1");
		ldi((byte)2,"r2");
		ldi((byte)3,"r3");
		ldi((byte)3,"r4");
		ldi((byte)6,"r5");
		ldi((byte)5,"r6");
		
		
		//now we need to check if the operands is ready !!
		//fill signature 
		//fill (String [][] instBuffer,String [][]rs,String [][]availableSlots
		fill(cycles1,regStations1,instructionBuffer1,reservationStations1,availableSlots1);
		
		
		
//		commit(instructionBuffer);
		// the loop to check the completed inst to start excution and write to regs 
		
//		Excute(reservationStations);
		
				
				
	
				//let us see if the value of the reg had changed !
				
				
				//
		System.out.println();
		System.out.println("-------------------------------------------------");
		for (int i = 0; i <reservationStations1.length ; i++) {
			for (int j = 0; j <9; j++) {
				System.out.print(reservationStations1[i][j]+"  ");
			}
			System.out.println("");
		}
		System.out.println("-------------------------------------------------");
		
		for (int i = 0; i < instructionBuffer1.length; i++) {
			for (int j = 0; j < 7; j++) {
				System.out.print(instructionBuffer1[i][j]+"  ");
			}
			System.out.println("");
		}
		System.out.println("");
		System.out.println("current register status values are : ");
		System.out.println("-------------------------------------------------");
		
		for (int i = 0; i < regStations1.length; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(regStations1[i][j]+"  ");
			}
			System.out.println("");
		}
		
		
		regFiles.print();
//		for (int i = 0; i <regStations.length ; i++) {
//			for (int j = 0; j <8; j++) {
//				System.out.print(regStations[i][j]+"  ");
//			}
//			System.out.println("");
//		}
		
		//printing the avilable slots 
//		for (int i = 0; i <2; i++) {
//			for (int j = 0; j <7; j++) {
//				System.out.print(availableSlots[i][j]+"  ");
//			}
//			System.out.println("");
//		}
		
		
//		
//		TimeUnit.SECONDS.sleep(2);
		IPC=(double)n/programeClock;
		System.out.println("The Total Excution Time is :  "+ programeClock);
		System.out.println("---------------------------------------------");
		System.out.println("The IPC For The Prog =  "+IPC);
		System.out.println("---------------------------------------------");
		System.out.println("Branch Miss Percentage  =  " +notTakenBranch/(takenBranch+notTakenBranch));
	
	}
	

	
	public static boolean checkTheRegs(String operand,String[][]regs) {
		if(operand.equals("r0")&&regs[1][0].equals("0")) {
			
			return true;
		}
		if(operand.equals("r1")&&regs[1][1].equals("0")) {
			return true;
		}
		if(operand.equals("r2")&&regs[1][2].equals("0")) {
			return true;
		}
		if(operand.equals("r3")&&regs[1][3].equals("0")) {
			return true;
		}
		if(operand.equals("r4")&&regs[1][4].equals("0")) {
			return true;
		}
		if(operand.equals("r5")&&regs[1][5].equals("0")) {
			return true;
		}
		if(operand.equals("r6")&&regs[1][6].equals("0")) {
			return true;
		}
		if(operand.equals("r7")&&regs[1][7].equals("0")) {
			return true;
		}
		
		return false;
		
	}
	
	
	//now filling specified places in the reservation stations 
	public static  void fill (String cycles [][],String[][] regStations,String [][] instBuffer,String [][]rs,String [][]availableSlots/*slots to be parsint*/) throws InterruptedException {
			

					for (int i = 2; i < instBuffer.length; i++) {
						
						 System.out.println(i);
						
					
					String s =instBuffer[PC][0];
					
		//			System.out.println(s);
					
					if(s.equals("ld")&&instBuffer[PC][4].equals("0")) {
						int x=Integer.parseInt(availableSlots[1][0]);
						if(x>0) {
							x--;
							availableSlots[1][0]=x+"";
							rs[PC][0]=s;
							rs[PC][2]="Yes";
//							clocks[1][i]=instBuffer[i][0];
							programeClock+=1;
							
							}
					}else {
						if(s.equals("add")&&instBuffer[PC][4].equals("0")) {
							int x=Integer.parseInt(availableSlots[1][1]);
							if(x>0) {
								x--;
								availableSlots[1][1]=x+"";
								rs[PC][0]=s;
								rs[PC][2]="Yes";
//								clocks[1][i]=instBuffer[i][0];
								programeClock+=1;
								
								}
						}else {
							if(s.equals("sub")&&instBuffer[PC][4].equals("0")) {
								int x=Integer.parseInt(availableSlots[1][3]);
								if(x>0) {
									x--;
									availableSlots[1][3]=x+"";
									rs[PC][0]=s;
									rs[PC][2]="Yes";
//									clocks[1][i]=instBuffer[i][0];
									programeClock+=1;
									}
							}else {
								if(s.equals("mul")&&instBuffer[PC][4].equals("0")) {
									int x=Integer.parseInt(availableSlots[1][2]);
									if(x>0) {
										x--;
										availableSlots[1][2]=x+"";
										rs[PC][0]=s;
										rs[PC][2]="Yes";
//										clocks[1][i]=instBuffer[i][0];
										programeClock+=1;
										
										}
								}else {
									if(s.equals("mod")&&instBuffer[PC][4].equals("0")) {
										int x=Integer.parseInt(availableSlots[1][4]);
										if(x>0) {
											x--;
											availableSlots[1][4]=x+"";
											rs[PC][0]=s;
											rs[PC][2]="Yes";
//											clocks[1][i]=instBuffer[i][0];
											programeClock+=1;
											
											}
									}else {
										if(s.equals("st")&&instBuffer[PC][4].equals("0")) {
											int x=Integer.parseInt(availableSlots[1][5]);
											if(x>0) {
												x--;
												availableSlots[1][5]=x+"";
												rs[PC][0]=s;
												rs[PC][2]="Yes";
//												clocks[1][i]=instBuffer[i][0];
												programeClock+=1;
												
												}
										}else {
											if(s.equals("div")&&instBuffer[PC][4].equals("0")) {
												int x=Integer.parseInt(availableSlots[1][2]);
												if(x>0) {
													x--;
													availableSlots[1][6]=x+"";
													rs[PC][0]=s;
													rs[PC][2]="Yes";
//													clocks[1][i]=instBuffer[i][0];
													programeClock+=1;
													
													}
											}else {
												if((s.equals("breq")&&instBuffer[PC][4].equals("0"))) {
													
													System.out.println("here we wrteeeee#@#@#@");
													//here we need to check is the branch taken or not .
													if((instBuffer[PC][1].equals("p"))&& SREGS[1][6].equals("1")) {
														int x=Integer.parseInt(instBuffer[PC][2]);
														PC+=x;
														System.out.println("the pc = :"+PC+ " at  i: "+i);
														i+=x;
														SREGS[1][6].equals("0");
														System.out.println("branch p succeded !! ");
														notTakenBranch++;
														//g=false;//you should implement the sreg to control the branches 
												
												}
													if((instBuffer[PC][1].equals("n"))&& SREGS[1][6].equals("1")) {
														
														int x=Integer.parseInt(instBuffer[PC][2]);
														PC-=x;
														System.out.println("the pc = :"+PC+ " at  i: "+i);
														i-=x;
														SREGS[1][6].equals("0");
														System.out.println("branch n succeded !! ");
														takenBranch++;
													}
												
											}else {
												if((s.equals("brsh")&&instBuffer[PC][4].equals("0"))) {
													
													System.out.println("here we wrteeeee#@#@#@");
													//here we need to check is the branch taken or not .
													if((instBuffer[PC][1].equals("p"))&& SREGS[1][7].equals("1")) {
														int x=Integer.parseInt(instBuffer[PC][2]);
														PC+=x;
														System.out.println("the pc = :"+PC+ " at  i: "+i);
														i+=x;
														SREGS[1][6].equals("0");
														System.out.println("branch p succeded !! ");
														notTakenBranch++;
														//g=false;//you should implement the sreg to control the branches 
												
												}
													if((instBuffer[PC][1].equals("n"))&& SREGS[1][7].equals("1")) {
														
														int x=Integer.parseInt(instBuffer[PC][2]);
														PC-=x;
														System.out.println("the pc = :"+PC+ " at  i: "+i);
														i-=x;
														SREGS[1][6].equals("0");
														System.out.println("branch n succeded !! ");
														takenBranch++;
													}
												
											}else {
												if((s.equals("brlo")&&instBuffer[PC][4].equals("0"))) {
													
													System.out.println("here we wrteeeee#@#@#@");
													//here we need to check is the branch taken or not .
													if((instBuffer[PC][1].equals("p"))&& SREGS[1][7].equals("0")) {
														int x=Integer.parseInt(instBuffer[PC][2]);
														PC+=x;
														System.out.println("the pc = :"+PC+ " at  i: "+i);
														i+=x;
														SREGS[1][6].equals("0");
														System.out.println("branch p succeded !! ");
														notTakenBranch++;
														//g=false;//you should implement the sreg to control the branches 
												
												}
													if((instBuffer[PC][1].equals("n"))&& SREGS[1][7].equals("0")) {
														
														int x=Integer.parseInt(instBuffer[PC][2]);
														PC-=x;
														System.out.println("the pc = :"+PC+ " at  i: "+i);
														i-=x;
														SREGS[1][6].equals("0");
														System.out.println("branch n succeded !! ");
														takenBranch++;
													}
												
											}else {
												if((s.equals("brmi")&&instBuffer[PC][4].equals("0"))) {
													
													System.out.println("here we wrteeeee#@#@#@");
													//here we need to check is the branch taken or not .
													if((instBuffer[PC][1].equals("p"))&& SREGS[1][5].equals("1")) {
														int x=Integer.parseInt(instBuffer[PC][2]);
														PC+=x;
														System.out.println("the pc = :"+PC+ " at  i: "+i);
														i+=x;
														SREGS[1][6].equals("0");
														System.out.println("branch p succeded !! ");
														notTakenBranch++;
														//g=false;//you should implement the sreg to control the branches 
												
												}
													if((instBuffer[PC][1].equals("n"))&& SREGS[1][5].equals("1")) {
														
														int x=Integer.parseInt(instBuffer[PC][2]);
														PC-=x;
														System.out.println("the pc = :"+PC+ " at  i: "+i);
														i-=x;
														SREGS[1][6].equals("0");
														System.out.println("branch n succeded !! ");
														takenBranch++;
													}
												
											}else {
												if((s.equals("call")&&instBuffer[PC][4].equals("0"))) {
													// now you need to save the current pc 
													
													
													if((instBuffer[PC][1].equals("p"))) {
														PCTemp=PC+1;
														negativ=false;
														int x=Integer.parseInt(instBuffer[PC][2]);
														PC+=x;
														System.out.println("the pc = :"+PC+ " at  i: "+i);
														i+=x;
														System.out.println("call p succeded !! ");
														notTakenBranch++;
														//g=false;//you should implement the sreg to control the branches 
												
												}
													if((instBuffer[PC][1].equals("n"))) {
														
														negativ=false;
														int x=Integer.parseInt(instBuffer[PC][2]);
														PC-=x;
														System.out.println("the pc = :"+PC+ " at  i: "+i);
														i-=x;
														System.out.println("call n succeded !! ");
														takenBranch++;
													}
													
											}else {
												if((s.equals("return"))) {
													PC=PCTemp;
													i=PCTemp;
												}else {
													if(s.equals("exit")) {
														System.out.println("Prog is done !");
														System.exit(0);
													}else {
														if((s.equals("loop")&&instBuffer[PC][4].equals("0"))) {
															
															System.out.println("inside the looooooooooop !!!");
															//here we need to check is the branch taken or not .
															if((instBuffer[PC][1].equals("p"))) {
																int x=Integer.parseInt(instBuffer[PC][2]);
																PC+=x;
																System.out.println("the pc = :"+PC+ " at  i: "+i);
																i+=x;
																
																System.out.println("branch p succeded !! ");
																notTakenBranch++;
																//g=false;//you should implement the sreg to control the branches 
														
														}
															if((instBuffer[PC][1].equals("n"))) {
																
																int x=Integer.parseInt(instBuffer[PC][2]);
																PC-=x;
																System.out.println("the pc = :"+PC+ " at  i: "+i);
																i-=x;
																
																System.out.println("branch n succeded !! ");
																takenBranch++;
															}
														
													}
													}
												}
											}
												
											}
											}
											}
												
											}
											}
											
										}
										
									}
									
								}
							}
							
						}
					}
					
					 
					String s0 =instBuffer[PC][2];
					String s1 =instBuffer[PC][3];
					if(checkTheRegs(s0,regStations)) {
						rs[PC][4]=s0;
						fillregStations(rs,s0,regStations,PC,0);
						
					}
					if(rs[PC][4].equals("0")) {
						int value = 0;
						for (int j = 0; j < regStations.length; j++) {
							if(regStations[0][j].equals(s1)) {
								value=j;
								break;
							}
						}
						rs[PC][6]=regStations[1][value];
					}
					if(checkTheRegs(s1,regStations)) {
						rs[PC][5]=s1;
						fillregStations(rs,s1,regStations,PC,0);
						programeClock+=1;
						
					}
					if(rs[PC][5].equals("0")) {
						int value = 0;
						for (int j = 0; j < regStations.length; j++) {
							if(regStations[0][j].equals(s1)) {
								value=j;
								break;
							}
						}
						rs[PC][7]=regStations[1][value];
					}
					
					if(!rs[PC][4].equals("0")&&!rs[PC][5].equals("0")) {
						//here the issue for some inst is finished ,thus we need to increment the pc 
						
						instBuffer[PC][4]="1";
						programeClock+=1;
					}
					
					Excute(instructionBuffer1,cycles1);
					//here we should write in the memory first...
					Write(instructionBuffer1);
					printROB();
					Memory[8]="115";
					
					commit(instructionBuffer1);
					System.out.println("now the place mem[9] = : "+Memory[9]+" which is true ");
					regFiles.print();
					printSREG();
					isExcuted();
					
					
					
					PC++;
					}
	

		}
	public static void Excute(String instructionBuffer[][],String [][]instCycles) throws InterruptedException {
		// we need to check first if it is ready
		
		for (int i = 1; i < instructionBuffer.length; i++) {
				
			if(instructionBuffer[i][4].equals("1")) {// here we check the issuing 
				instructionBuffer[i][7]=programeClock+"";
				int c=Integer.parseInt(instructionBuffer[i][7]);
				if(instructionBuffer[i][0].equals("add")&&!instructionBuffer[i][5].equals("1")) {
					
					int e=Integer.parseInt(instCycles[1][1]); //here we get the number of cycles to finish excution 
					
					for (int j = 0; j < 200; j++) {
						
						if((programeClock-c)==e) {
							System.out.println("Done!!");
							System.out.println("the inst :"+instructionBuffer[i][0]
									+" "+instructionBuffer[i][1]+" "+instructionBuffer[i][2]
											+" "+instructionBuffer[i][3]+"  finishes excu at : "+programeClock);
							instructionBuffer[i][5]="1";
							System.out.println();
							break;
						}else {
							programeClock++;
						}
					}
				}else {
					
					if(instructionBuffer[i][0].equals("sub")&&!instructionBuffer[i][5].equals("1")) {
						
						int e=Integer.parseInt(instCycles[1][3]);
						
						for (int j = 0; j < 200; j++) {
							
							if((programeClock-c)==e) {
								System.out.println("Done!!");
								System.out.println("the inst :"+instructionBuffer[i][0]
										+" "+instructionBuffer[i][1]+" "+instructionBuffer[i][2]
												+" "+instructionBuffer[i][3]+"  finishes excu at : "+programeClock);
								instructionBuffer[i][5]="1";
								System.out.println();
								break;
							}else {
								programeClock++;
							}
						}
					}else {
						if(instructionBuffer[i][0].equals("mul")&&!instructionBuffer[i][5].equals("1")) {
							
							int e=Integer.parseInt(instCycles[1][2]);
							
							for (int j = 0; j < 200; j++) {
								
								if((programeClock-c)==e) {
									System.out.println("Done!!");
									System.out.println("the inst :"+instructionBuffer[i][0]
											+" "+instructionBuffer[i][1]+" "+instructionBuffer[i][2]
													+" "+instructionBuffer[i][3]+"  finishes excu at : "+programeClock);
									instructionBuffer[i][5]="1";
									System.out.println();
									break;
								}else {
									programeClock++;
								}
							}
						}else {
							if(instructionBuffer[i][0].equals("mod")&&!instructionBuffer[i][5].equals("1")) {
								
								int e=Integer.parseInt(instCycles[1][4]);
								
								for (int j = 0; j < 200; j++) {
									
									if((programeClock-c)==e) {
										System.out.println("Done!!");
										System.out.println("the inst :"+instructionBuffer[i][0]
												+" "+instructionBuffer[i][1]+" "+instructionBuffer[i][2]
														+" "+instructionBuffer[i][3]+"  finishes excu at : "+programeClock);
										instructionBuffer[i][5]="1";
										System.out.println();
										break;
									}else {
										programeClock++;
									}
								}
							}else {
								if(instructionBuffer[i][0].equals("div")&&!instructionBuffer[i][5].equals("1")) {
									
									int e=Integer.parseInt(instCycles[1][6]);
									
									for (int j = 0; j < 200; j++) {
										
										if((programeClock-c)==e) {
											System.out.println("Done!!");
											System.out.println("the inst :"+instructionBuffer[i][0]
													+" "+instructionBuffer[i][1]+" "+instructionBuffer[i][2]
															+" "+instructionBuffer[i][3]+"  finishes excu at : "+programeClock);
											instructionBuffer[i][5]="1";
											System.out.println();
											break;
										}else {
											programeClock++;
										}
									}
								}else {
									if(instructionBuffer[i][0].equals("ld")&&!instructionBuffer[i][5].equals("1")) {
										
										int e=Integer.parseInt(instCycles[1][0]);
										
										for (int j = 0; j < 200; j++) {
											
											if((programeClock-c)==e) {
												System.out.println("Done!!");
												System.out.println("the inst :"+instructionBuffer[i][0]
														+" "+instructionBuffer[i][1]+" "+instructionBuffer[i][2]
																+" "+instructionBuffer[i][3]+"  finishes excu at : "+programeClock);
												instructionBuffer[i][5]="1";
												System.out.println();
												break;
											}else {
												programeClock++;
											}
										}
									}else {
										if(instructionBuffer[i][0].equals("st")&&!instructionBuffer[i][5].equals("1")) {
											
											int e=Integer.parseInt(instCycles[1][5]);
											
											for (int j = 0; j < 200; j++) {
												
												if((programeClock-c)==e) {
													System.out.println("Done!!");
													System.out.println("the inst :"+instructionBuffer[i][0]
															+" "+instructionBuffer[i][1]+" "+instructionBuffer[i][2]
																	+" "+instructionBuffer[i][3]+"  finishes excu at : "+programeClock);
													instructionBuffer[i][5]="1";
													System.out.println();
													break;
												}else {
													programeClock++;
												}
											}
										}
									}
								}
							}
						}
					}
				}
				
				//here the pc should be less than the number of inst 
				
//				fill(cycles1,regStations1,instructionBuffer1,reservationStations1,availableSlots1);
				
				
			}
				
				
				
			 			
		}
		
		
		
	}
	
	public static void isExcuted() {
		
		for (int i = 0; i < instructionBuffer1.length; i++) {
		// here we should check if it is written 
			if(instructionBuffer1[i][5].equals("1")) {
				flushRegStatus(instructionBuffer1[i][2]);
				flushRegStatus(instructionBuffer1[i][3]);
				instructionBuffer1[i][5]="0";
				instructionBuffer1[i][4]="0";
				
			}
		}
		
		
		
	}
	
	public static void flushRegStatus(String s) {
		for (int i = 0; i <8; i++) {
			if(s.equals(regStations1[0][i])) {
				regStations1[1][i]="0";
			}
		}
		System.out.println("reg status cleared for excuted inst ..");
		System.out.println(" ");
		for (int i = 0; i < regStations1.length; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(regStations1[i][j]+" ");
			}
			System.out.println("  ");
		}  
		System.out.println("  ");
	}
	
	public static void Write(String instructionBuffer[][]) {
		
		//we need to check if it is excuted before entering the buffer 
		
		for (int i = 0; i < instructionBuffer.length; i++) {
			
			//here to check if its excuted 
			if(instructionBuffer[i][5].equals("1")) {
				ROB.add(instructionBuffer[i]);
			}
			
		}
		programeClock++;
		
	}
	
	public static void printROB() {
		System.out.println("----------------------");
		System.out.println("ROB inst  :    ");
		System.out.println("----------------------");
		for (int i = 0; i < ROB.size(); i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(ROB.get(i)[j]);
			}
			System.out.println("  ");
		}
		System.out.println("----------------------");
	}

	
	public static void commit(String instructionBuffer[][]) {
		
		for (int i = 1; i < instructionBuffer.length; i++) {
			
			if(instructionBuffer[i][5].equals("1")) {
				String s=instructionBuffer[i][0];
				
				
					if(s.equals("sub")) {
						sub(instructionBuffer[i][2],instructionBuffer[i][3],instructionBuffer[i][1]);
						if(regFiles.getR(instructionBuffer[i][1])==0) {
							SREGS[1][6]="1";
						}
						if(regFiles.getR(instructionBuffer[i][1])<0) {
							SREGS[1][5]="1";
						}
						
						}else {
							if(s.equals("add")) {
								add(instructionBuffer[i][2],instructionBuffer[i][3],instructionBuffer[i][1]);
								
								if(regFiles.getR(instructionBuffer[i][1])>127) {
									SREGS[1][7]="1";
								}
								if(regFiles.getR(instructionBuffer[i][1])<127) {
									SREGS[1][7]="0";
								}
								
								}else {
									if(s.equals("mod")) {
										mod(instructionBuffer[i][2],instructionBuffer[i][3],instructionBuffer[i][1]);
										
										}else {
											if(s.equals("mul")) {
												mul(instructionBuffer[i][2],instructionBuffer[i][3],instructionBuffer[i][1]);
												if(regFiles.getR(instructionBuffer[i][1])>127) {
													SREGS[1][7]="1";
												}
												if(regFiles.getR(instructionBuffer[i][1])<127) {
													SREGS[1][7]="0";
												}
												}else {
													if(s.equals("div")) {
														div(instructionBuffer[i][2],instructionBuffer[i][3],instructionBuffer[i][1]);
														if(regFiles.getR(instructionBuffer[i][1])>127) {
															SREGS[1][7]="1";
														}
														if(regFiles.getR(instructionBuffer[i][1])<127) {
															SREGS[1][7]="0";
														}
														}else {
															if(s.equals("com")) {
																compare(instructionBuffer[i][2],instructionBuffer[i][1]);
															}else {
																if(s.equals("ld")) {
																	//here we got the offset and the place to load in memory 
																	int x=(regFiles.getR(instructionBuffer[i][2])+regFiles.getR(instructionBuffer[i][3]));
																	regFiles.setR((byte) Integer.parseInt(Memory[x]), instructionBuffer[i][1]);
																}else {
																	if(s.equals("st")) {
																		//here we got the offset and the place to load in memory 
																		int x=(regFiles.getR(instructionBuffer[i][2])+regFiles.getR(instructionBuffer[i][3]));
																		Memory[x]=regFiles.getR(instructionBuffer[i][1])+"";
																	}
																}
															}
														}
												}
										}
								}
							
						}
					
			}
			
		}
		programeClock++;
		
		
	}
	

		
		public static void clocksIncr(String[][]rs,String [][]clocks) {
			
			int x=0;
			for (int i = 1; i < rs.length; i++) {
				
				for (int j = 0; j <rs[i].length; j++) {
					
					if(rs[i][j].equals(clocks[x][j])) {
						int y =Integer.parseInt(clocks[i][j]);
						y++;
						clocks[i][j]=y+"";
					}
				}
			}
		}
		public static void decCycles(String[][]rs,String [][]clocks) {
			
			int x=0;
			for (int i = 1; i < rs.length; i++) {
				
				for (int j = 0; j <rs[i].length; j++) {
					
					if(rs[i][j].equals(clocks[x][j])) {
						int y =Integer.parseInt(clocks[i][j]);
						y++;
						clocks[i][j]=y+"";
					}
				}
			}
		}
		
		public static void clearingRs(String instBuffer[][],String[][] rs,String[][] clocks,String [][]cycles) {
			
			for (int i = 1; i < rs.length; i++) {
				
				for (int j = 0; j < cycles.length; j++) {
					
					if(clocks[i][j].equals(cycles[i][j])) {
						
						instBuffer[i][6]=clocks[i][j];
						rs[i][0]="0";
						rs[i][1]="0";
						rs[i][2]="0";
						rs[i][3]="0";
						rs[i][4]="0";
						rs[i][5]="0";
						rs[i][6]="0";
						rs[i][7]="0";
						rs[i][8]="0";
					}
				}
				
			}
		}
			

	
	///function to increment the clock cycles for every inst 
	//you should create a bool to detect if you should increment or not 
	
	
	public static void fillregStations(String[][]rs,String operand,String[][] regStations,int i,int j){
		if(operand.equals("r0")&& regStations[1][0].equals("0")) {
			regStations[1][0]=rs[i][j];
		}
		if(operand.equals("r1")&& regStations[1][1].equals("0")) {
			regStations[1][1]=rs[i][j];
		}
		if(operand.equals("r2")&& regStations[1][2].equals("0")) {
			regStations[1][2]=rs[i][j];
		}
		if(operand.equals("r3")&& regStations[1][3].equals("0")) {
			regStations[1][3]=rs[i][j];
		}
		if(operand.equals("r4")&& regStations[1][4].equals("0")) {
			regStations[1][4]=rs[i][j];
		}
		if(operand.equals("r5")&& regStations[1][5].equals("0")) {
			regStations[1][5]=rs[i][j];
		}
		if(operand.equals("r6")&& regStations[1][6].equals("0")) {
			regStations[1][6]=rs[i][j];
		}
		if(operand.equals("r7")&& regStations[1][7].equals("0")) {
			regStations[1][7]=rs[i][j];
		}
		
		
	}
	
//	//we need to check if the inst are ready to start excution 
//	
//	public static boolean isReady(String [][] s,int i) {
//		if(s[i][0].equals("0")&&s[i][4].equals("0")&&s[i][5].equals("0")) {
//			return false;
//		}
//		return true;
//	}
	
	
	
//	public static String loadFromMem(Short address) {
//		
//		return memory.load(address);
//		
//	}
//	
//	public static void storeInMem(Short address,String word) {
//		
//		 memory.store(address,word);
//		
//	}
//	
	public static void ldi(byte value,String Reg) {
		
		regFiles.setR(value, Reg);
		
	}
	public static boolean emptyReg(String Reg) {
		
		if(regFiles.getR(Reg)==0) {
			return true;
		}
		return false;
	}
	
	public static void add(String r1,String r2,String r3) {
		
		regFiles.setR((byte)(regFiles.getR(r1)+regFiles.getR(r2)), r3);
		
	}
	public static void mul(String r1,String r2,String r3) {
		
		regFiles.setR((byte)(regFiles.getR(r1)*regFiles.getR(r2)), r3);
		
	}


	public static void sub(String r1,String r2,String r3) {
		
		regFiles.setR((byte)(regFiles.getR(r1)-regFiles.getR(r2)), r3);
		
	}
	public static void mod(String r1,String r2,String r3) {
		
		regFiles.setR((byte)(regFiles.getR(r1)%regFiles.getR(r2)), r3);
		
	}
	public static void div(String r1,String r2,String r3) {
		
		regFiles.setR((byte)(regFiles.getR(r1)/regFiles.getR(r2)), r3);
		
	}
	
	public static void compare(String r2,String r3) {
		
		byte x=(byte)Integer.parseInt(r2);
		byte y=(byte)Integer.parseInt(r3);
		
		if(x>y) {
			regFiles.setR(x, r2);
		}else {
			if(y>x) {
				regFiles.setR(y, r3);
			}else {
				System.out.println("you entered two equal numbers !!  ");
			}
			
		}
	}
	
	
	
	public void initializingTheMemory() {
		for (int i = 0; i < Memory.length; i++) {
			Memory[i]="0";
		}
	}
	
	
	
	public static ArrayList<String> instructionsplitting( ) throws IOException {
		ArrayList<String> inst=new ArrayList<String>();
		
		for (int i = 0; i < Memory.length; i++) {
			String s=Memory[i];
//			System.out.println(s);
			String[] st=s.split(" ");
			
			for (int j = 0; j < st.length; j++) {
				if(st[j]!=null)
				inst.add(st[j]);
				
			}
//			System.out.println(inst.get(1));
			
		}
		
		//here you need to search to add with a looop limited with the rob entries as you have the code separated
//		for (int i = 0; i <inst.size(); i+=3) {
//			
//			if(inst.get(i)=="add") {
//				
//				
//			}
//		}
		
//for (int i = 0; i < inst.size(); i++) {
//	System.out.println(inst.get(i));
//}
//		
		
		return inst;
		
	}
	
	
	//read every line of inst 
	public static void readFile(String fileName) throws IOException {
		 File file = new File("C:\\Users\\AbDo\\Desktop\\Recovered "
		 		+ "data 12-07 21_10_10\\Deep Scan result\\Existing "
		 		+ "Partition(NTFS)\\proJects\\Microprocessor sem7\\"
		 		+ "test_for_regstatus_stallForCycles_filling_allStations_printingthePC.txt"); 
		 
//		 File file = new File("C:\\Users\\AbDo\\Desktop\\Recovered data 12-07 21_10_10\\Deep"
//		 		+ " Scan result\\Existing Partition(NTFS)\\proJects\\"
//		 		+ "Microprocessor sem7\\test-looping.txt");
		 
//		File file = new File("C:\\Users\\AbDo\\Desktop\\Recovered data 12-07 21_10_10\\Deep Scan result\\"
//				+ "Existing Partition(NTFS)\\proJects\\Microprocessor sem7\\test_bracnes.txt");
		
//		File file = new File("C:\\Users\\AbDo\\Desktop\\Recovered data 12-07 21_10_10\\Deep Scan result\\"
//				+ "Existing Partition(NTFS)\\proJects\\Microprocessor sem7\\test_call_return.txt");
		
//		File file = new File("C:\\Users\\AbDo\\Desktop\\Recovered data 12-07 21_10_10\\Deep Scan result\\"
//				+ "Existing Partition(NTFS)\\proJects\\Microprocessor sem7\\test_load_store.txt");
//		
		 @SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(file));
		 String st;
//		 ArrayList<String>instructionArr = new ArrayList<String>();
		 for(int i=0;i<100;i++) {
			 if((st=br.readLine())!=null) {
				 
				 Memory[i]=st;
				 
			 }else {
				 break;
			 }
			 
			 
		 }
//		System.out.println(Memory[3]);
		
		
	}
	
//	public void memprint() {
//		for (int i = 0; i < Memory.size(); i++) {
//			System.out.println(Memory.get(i));
//		}
//	}
//	
	
	
	
	
	
	//filiing the instruction buffer with our assemply code 
	
	
//	public void addToInsBuff(String fileName) throws IOException {
//		//instructionBuffer=new LinkedList<String>();
//		 ArrayList<String>instructionArr = readFile(fileName);
//		for (int i = 0; i <instructionArr.size() ; i++) {
//			instructionBuffer.add(instructionArr.get(i));
////			System.out.print(instructionBuffer.poll());
//		}
////		System.out.print(instructionBuffer.peek());
//	}
	
	
	
	//now you should create amethode to load in the reservation station and ....through getting the data from instruction buffer 

	
	//func to seperate the ins and the regs  every ins ==== need to be modified to get a queue;
	//now you have evry words seperated
//	public static String[][]  fillInstructionBuffer() {
//		
//		Scanner sc=new Scanner(System.in);
//		System.out.println("please, enter the total number of instructions to create the instruction buffer"
//				+ " if you need 4 inst enter 5 and so on .. ");
//		int n=sc.nextInt();
////		ib=new InstructionBuffer(n);
//		
//		String [][]s=ib.bufferStations(n);
////		ib.print();
//		for (int i = 0; i < s.length; i++) {
//			for (int j = 0; j < s.length; j++) {
//				System.out.print(s[i][j]);
//			}
//			System.out.println();
//		}
//		return s;
//	}
	
	
	
	
	public static void main (String args[]) throws IOException, InterruptedException {
		int n=5;
		@SuppressWarnings("unused")
		Tomasulo t=new Tomasulo(n);
	}

}
