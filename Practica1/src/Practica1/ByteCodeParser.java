package Practica1;
import Practica1.ENUM_BYTECODE;

public class ByteCodeParser {
	
		
	public static ByteCode parse(String line){
			
			line = line.trim();
			String []words =line.split(" +");
			words[0]=words[0].toLowerCase();
			int parametro;
			
			 if(words.length==1){
				if(words[0].equalsIgnoreCase("ADD")){
					return new ByteCode(ENUM_BYTECODE.ADD);
				}
				else if(words[0].equalsIgnoreCase("SUB")){
					return new ByteCode(ENUM_BYTECODE.SUB);
				}
				else if(words[0].equalsIgnoreCase("MUL")){
					return new ByteCode(ENUM_BYTECODE.MUL);
				}
				else if(words[0].equalsIgnoreCase("DIV")){
					return new ByteCode(ENUM_BYTECODE.DIV);
				}
				else if(words[0].equalsIgnoreCase("OUT")){
					return new ByteCode(ENUM_BYTECODE.OUT);
				}
				else{
					return new ByteCode(ENUM_BYTECODE.HALT);
				}	
			 }	
		else if(words.length==2){
			parametro= Integer.parseInt(words[1]);	
			if(words[0].equalsIgnoreCase("PUSH")){
				return new ByteCode(ENUM_BYTECODE.PUSH,parametro);
			}
			else if(words[0].equalsIgnoreCase("LOAD")){
				return new ByteCode(ENUM_BYTECODE.LOAD,parametro);
			}
			else{
				return new ByteCode(ENUM_BYTECODE.STORE,parametro);
			}
		}
		else{
			return null;
		}
	}
}

