package Practica1;

public class Main {

	public static void main(String[] args) {
		
		//Engine engine = new Engine();
		//engine.start();
		
		
		//pruebas que habia que hacer
		// funcionan
		CPU cpu = new CPU();
		System.out.println(cpu);
		cpu.execute(new ByteCode(ENUM_BYTECODE.PUSH, 7));
		System.out.println(cpu);
		cpu.execute(new ByteCode(ENUM_BYTECODE.PUSH, 8));
		System.out.println(cpu);
		cpu.execute(new ByteCode(ENUM_BYTECODE.ADD));
		System.out.println(cpu);
		
		
	}

}
