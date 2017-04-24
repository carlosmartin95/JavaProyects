package Practica1;

public class CPU {

	private static Memory mem = new Memory();
	private static OperandStack pila = new OperandStack();
	private static boolean parada = false;
	
	public static  boolean execute(ByteCode instr){
		int operando;
		if (instr.getName() != ENUM_BYTECODE.HALT){
			
			if(instr.getName() == ENUM_BYTECODE.ADD){
				operando=pila.pop()+pila.pop();
				pila.push(operando);
			}
			else if (instr.getName() == ENUM_BYTECODE.SUB){//Se pueden dar casos de numeros negativos?
				operando=pila.pop()-pila.pop();
				pila.push(operando);
			}
			else if(instr.getName() == ENUM_BYTECODE.MUL){
				operando=pila.pop()*pila.pop();
				pila.push(operando);
			}
			else if(instr.getName() == ENUM_BYTECODE.DIV){
				operando=pila.pop()/pila.pop();
				pila.push(operando);
			}
			else if(instr.getName() == ENUM_BYTECODE.PUSH){
				pila.push(instr.getParametro());
			}
			else if(instr.getName() == ENUM_BYTECODE.LOAD){
				pila.push(mem.read(instr.getParametro()));
			}			
			else if(instr.getName() == ENUM_BYTECODE.STORE){
				mem.write(instr.getParametro(),pila.pop());
			}
			else if(instr.getName() == ENUM_BYTECODE.OUT){//Que s esupone que hay que hacer aqui??
				//preguntar al profe
			}
		
			parada = true;
		}
			
		return parada;
	}
	
	public String toString(){
		
		String s = null;
		String s2 = null;
		
		s = "MEMORIA: " + mem.toString();
		s2 = "PILA: " + pila.toString();
		
		return s+s2;
	
	}
}
