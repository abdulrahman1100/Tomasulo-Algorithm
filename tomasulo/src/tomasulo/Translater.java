package tomasulo;

import java.util.Dictionary;
import java.util.Hashtable;

public class Translater {
	
	Dictionary<String, String> Labels = new Hashtable<String, String>(); 
	
	public String[] translate(String assembly) {
		
		assembly = assembly.replaceAll("(?m)^[ \t]*\r?\n", "");
		
		String[] s = assembly.split("\n");
		
		s = addlabels(s);
		
		for (int i = 0; i<s.length;i++) {
			String[] line = s[i].split("\\s+");
			switch(line[0]) { 
	            case "ld": 
	                s[i]= "ld,"+regfinder(line[1])+","+offsetregfinder(line[2]);
	                break; 
	            case "st": 
	            	 s[i]= "st,"+regfinder(line[1])+","+offsetregfinder(line[2]);
	                break;
	            case "jmp": 
	            	s[i]= "jmp,"+ Labeloffset(line[1], i);
	                break;
	            case "brne": 
	            	s[i]= "brne,"+ regfinder(line[1])+","+regfinder(line[2])+","+Labeloffset(line[3], i);
	                break;
	            case "breq": 
	            	s[i]= "breq,"+ regfinder(line[1])+","+regfinder(line[2])+","+Labeloffset(line[3], i);
	                break;
	            case "brl": 
	            	s[i]= "brl,"+ regfinder(line[1])+","+regfinder(line[2])+","+Labeloffset(line[3], i);
	                break;
	            case "brg": 
	            	s[i]= "brg,"+ regfinder(line[1])+","+regfinder(line[2])+","+Labeloffset(line[3], i);
	                break;
	            case "call": 
	            	s[i]= "call,"+ Labeloffset(line[1], i);
	                break;
	            case "ret": 
	            	s[i]= "ret";
	                break;
	            case "add": 
	            	s[i]= "add,"+ regfinder(line[1])+","+regfinder(line[2]);
	                break;
	            case "addi": 
	            	s[i]= "addi,"+ regfinder(line[1])+","+line[2];
	                break;
	            case "sub": 
	            	s[i]= "sub,"+ regfinder(line[1])+","+regfinder(line[2]);
	                break;
	            case "mul": 
	            	s[i]= "mul,"+ regfinder(line[1])+","+regfinder(line[2]);
	                break;
	            case "div": 
	            	s[i]= "div,"+ regfinder(line[1])+","+regfinder(line[2]);
	                break;
	            case "mod": 
	            	s[i]= "mod,"+ regfinder(line[1])+","+regfinder(line[2]);
	                break;
	            default: 
	                System.out.println("this command doesn't exist"); 
	                System.exit(0);
	        }
			
		}
		
		
		return s;
	}
	
	public String[] addlabels(String[] s) {
		
		for (int i = 0; i<s.length;i++) {
			String ss = "";
			s[i] = s[i].toLowerCase();
			s[i] = s[i].trim();
			if(s[i].indexOf(':') != -1) {
				ss = s[i].substring(0, s[i].indexOf(':'));
				
				if(checkLabelexists(ss)) {
					System.out.println("Error: This declaration already exists.");
					System.exit(0);
				}
				this.Labels.put(ss, Integer.toString(i));
				
				s[i] = s[i].substring(s[i].indexOf(':')+1);
				s[i] = s[i].trim();
			}
		}
		
		return s;
		
	}
	
	public boolean checkLabelexists(String s) {
		if(Labels.get(s) == null) {
			return false;
		}
		return true;	
	}
	
	public String Labeloffset(String s, int n) {
		if(!checkLabelexists(s)) {
			System.out.println("Error: The declaration ("+s+") doesn't exists.");
			System.exit(0);
		}
		
		s = Integer.toString(Integer.parseInt(Labels.get(s))-n);
		return s;
	}
	
	public static String offsetregfinder(String s) {
		int offset = 0;
		String reg = "";
		if(s.indexOf('(') != -1) {
			offset = Integer.parseInt(s.substring(1,s.indexOf(')')));
			reg = regfinder(s.substring(s.indexOf(')')+1));
		}else {
			reg = regfinder(s);
		}
		
		if(offset>63 || offset<-64) {
			System.out.println("Error: The offset is bigger than 7 bits");
			System.exit(0);
		}
		
		return offset + "," + reg;
	}
	
	public static String regfinder(String s) {
		int n = Integer.parseInt(s.substring(1));
		if(n>7) {
			System.out.println("Error: R" + n + " doesn't exist.");
			System.exit(0);
		}
		return Integer.toString(n);
	}

	public static void main(String[] args) {
		
	}

}
