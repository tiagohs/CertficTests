
public class Conteudo {
	private volatile static Conteudo instance;
	
	private String[][] alternativas;
	private String[][] perguntas;
	
	private Conteudo() {
		alternativas = new String[10][5];
		perguntas = new String[10][2];
		
		preenxerAlternativas();
		preenxerPerguntas();
	}
	
	public static Conteudo getInstance() {
		
		if (instance == null) {
			synchronized (Conteudo.class) {
				if (instance == null) {
					instance = new Conteudo();
				}
			}
		}
		
		return instance;
	}
		
	public String[][] getAlternativas() {
		return alternativas;
	}

	public String[][] getPerguntas() {
		return perguntas;
	}

	private void preenxerAlternativas() {
		
		alternativas[0][0] = "How to run Java program on the command prompt?";
		alternativas[0][1] = "javac JavaProgram";
		alternativas[0][2] = "java JavaProgram";
		alternativas[0][3] = "javac JavaProgram.java";
		alternativas[0][4] = "No one";

		alternativas[1][0] = "What is the use of the println method?";
		alternativas[1][1] = "It is used to print text on the screen.";
		alternativas[1][2] = "It is used to print text on the screen with the line break.";
		alternativas[1][3] = "It is used to read text from keyboard.";
		alternativas[1][4] = "It is used to read text from a file.";

		alternativas[2][0] = "How to read a character from the keyboard?";
		alternativas[2][1] = "char c=System.read()";
		alternativas[2][2] = "char c=System.in.read()";
		alternativas[2][3] = "char c=(char)System.read()";
		alternativas[2][4] = "char c=(char)System.in.read()";

		alternativas[3][0] = "Which one is a single-line comment?";
		alternativas[3][1] = "/...";
		alternativas[3][2] = "//...";
		alternativas[3][3] = "/*...";
		alternativas[3][4] = "/*...*/";

		alternativas[4][0] = "How do you declare an integer variable x?";
		alternativas[4][1] = "int x";
		alternativas[4][2] = "x as Integer";
		alternativas[4][3] = "Int[] x";
		alternativas[4][4] = "No one is correct.";

		alternativas[5][0] = "How do you convert a string of number to a number?";
		alternativas[5][1] = "int num=Integer.parseInt(str_num)";
		alternativas[5][2] = "int num=str_num.toInteger()";
		alternativas[5][3] = "int num=(int)str_num";
		alternativas[5][4] = "int num=(Integer)str_num";

		alternativas[6][0] = "What is the value of x? int x=3>>2";
		alternativas[6][1] = "1";
		alternativas[6][2] = "0";
		alternativas[6][3] = "3";
		alternativas[6][4] = "-3";

		alternativas[7][0] = "How to do exit a loop?";
		alternativas[7][1] = "Using exit";
		alternativas[7][2] = "Using break";
		alternativas[7][3] = "Using continue";
		alternativas[7][4] = "Using terminate";

		alternativas[8][0] = "What is the correct way to allocate one-dimensional array?";
		alternativas[8][1] = "int[size] arr=new int[]";
		alternativas[8][2] = "int arr[size]=new int[]";
		alternativas[8][3] = "int[size] arr=new int[size]";
		alternativas[8][4] = "int[] arr=new int[size]";

		alternativas[9][0] = "What is the correct way to allocate two-dimensional array?";
		alternativas[9][1] = "int[size][] arr=new int[][]";
		alternativas[9][2] = "int arr=new int[rows][cols]";
		alternativas[9][3] = "int arr[rows][]=new int[rows][cols]";
		alternativas[9][4] = "int[][] arr=new int[rows][cols]";
	}
	
	public void preenxerPerguntas() {
		
		perguntas[0][0] = "How to run Java program on the command prompt?";
		perguntas[0][1] = "java JavaProgram";

		perguntas[1][0] = "What is the use of the println method?";
		perguntas[1][1] = "It is used to print text on the screen with the line break.";

		perguntas[2][0] = "How to read a character from the keyboard?";
		perguntas[2][1] = "char c=(char)System.in.read()";

		perguntas[3][0] = "Which one is a single-line comment?";
		perguntas[3][1] = "//...";

		perguntas[4][0] = "How do you declare an integer variable x?";
		perguntas[4][1] = "int x";

		perguntas[5][0] = "How do you convert a string of number to a number?";
		perguntas[5][1] = "int num=Integer.parseInt(str_num)";

		perguntas[6][0] = "What is the value of x? int x=3>>2";
		perguntas[6][1] = "0";

		perguntas[7][0] = "How to do exit a loop?";
		perguntas[7][1] = "Using break";

		perguntas[8][0] = "What is the correct way to allocate one-dimensional array?";
		perguntas[8][1] = "int[] arr=new int[size]";

		perguntas[9][0] = "What is the correct way to allocate two-dimensional array?";
		perguntas[9][1] = "int[][] arr=new int[rows][cols]";
	}
}
