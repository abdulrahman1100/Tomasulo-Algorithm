package tomasulo;

public class RegisterFiles {
	
	byte r0;
	byte r1;
	byte r2;
	byte r3;
	byte r4;
	byte r5;
	byte r6;
	byte r7;
	
	
	public RegisterFiles() {
		
		r0=0;
		r1=0;
		r2=0;
		r3=0;
		r4=0;
		r5=0;
		r6=0;
		r7=0;
	}
	

	public byte getR(String r) {
		
		if(r.equals("r0")) {
			return r0 ;
		}
		if(r.equals("r1")) {
			return r1 ;
		}
		if(r.equals("r2")) {
			return r2 ;
		}
		if(r.equals("r3")) {
			return r3 ;
		}
		if(r.equals("r4")) {
			return r4 ;
		}
		if(r.equals("r5")) {
			return r5 ;
		}
		if(r.equals("r6")) {
			return r6 ;
		}
		if(r.equals("r7")) {
			return r7 ;
		}
		return -1;
	}

	public void setR(byte r,String Reg) {
		
		if(Reg.equals("r0")) {
			this.r0 = r;
		}
		if(Reg.equals("r1")) {
			this.r1 = r;
		}
		if(Reg.equals("r2")) {
			this.r2 = r;
		}
		if(Reg.equals("r3")) {
			this.r3 = r;
		}
		if(Reg.equals("r4")) {
			this.r4 = r;
		}
		if(Reg.equals("r5")) {
			this.r5 = r;
		}
		if(Reg.equals("r6")) {
			this.r6 = r;
		}
		if(Reg.equals("r7")) {
			this.r7 = r;
		}
		
	}
	public void erase(String Reg) {
		
		if(Reg.equals("r0")) {
			this.r0 = 0;
		}
		if(Reg.equals("r1")) {
			this.r1 = 0;
		}
		if(Reg.equals("r2")) {
			this.r2 = 0;
		}
		if(Reg.equals("r3")) {
			this.r3 = 0;
		}
		if(Reg.equals("r4")) {
			this.r4 = 0;
		}
		if(Reg.equals("r5")) {
			this.r5 = 0;
		}
		if(Reg.equals("r6")) {
			this.r6 = 0;
		}
		if(Reg.equals("r7")) {
			this.r7 = 0;
		}
	}
	
	public void print() {
		System.out.println("Current regs value are : ");
		System.out.println("  ");
		System.out.print("r0 ");
		System.out.print("r1 ");
		System.out.print("r2 ");
		System.out.print("r3 ");
		System.out.print("r4 ");
		System.out.print("r5 ");
		System.out.print("r6 ");
		System.out.println("r7 ");
		
		System.out.print(" "+r0+" ");
		System.out.print(" "+r1+" ");
		System.out.print(" "+r2+" ");
		System.out.print(" "+r3+" ");
		System.out.print(" "+r4+" ");
		System.out.print(" "+r5+" ");
		System.out.print(" "+r6+" ");
		System.out.print(" "+r7+" ");
	}

	

}
