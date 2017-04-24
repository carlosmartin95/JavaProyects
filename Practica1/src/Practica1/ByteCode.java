package Practica1;
import Practica1.ENUM_BYTECODE;

public class ByteCode {
		
	private ENUM_BYTECODE name;
	private int param;
	private ByteCode instruccion;

	public ByteCode(ENUM_BYTECODE opcion,int parametro) {	
		this.name = opcion;
		this.param=parametro;
				
	}

	// estp nose si vale es para que no de error en ByteCodePARSe
	public ByteCode(ENUM_BYTECODE instruccion ) {
		// TODO Auto-generated constructor stub
		this.name=instruccion;
	}

	public void Ejecuta(){
			
		CPU.execute(instruccion);
	}

	public ENUM_BYTECODE getName() {
		return name;
	}

	public void setName(ENUM_BYTECODE name) {
		this.name = name;
	}

	public int getParametro() {
		return param;
	}

	public String toString(){
		
		String s = null;
		
		return s;
	}

}



