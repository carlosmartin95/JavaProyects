package Practica1;

public enum ENUM_COMMAND {

		RUN,
		HELP,
		NEWINST(1),
		QUIT,
		RESET,
		REPLACE(1);
		
		private int numArgs;
		
		ENUM_COMMAND(){
			this(0);}
		
		ENUM_COMMAND(int n){
			numArgs=n;
		}
		public int getNumArgs(){
			return numArgs;
		}
		
		
		
}
