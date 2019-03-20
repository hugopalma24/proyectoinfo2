import java.io.*;
import java.util.*;
/*
	Utilice esta clase para guardar la informacion de su
	AFD. NO DEBE CAMBIAR LOS NOMBRES DE LA CLASE NI DE LOS 
	METODOS que ya existen, sin embargo, usted es libre de 
	agregar los campos y metodos que desee.
*/
public class AFD{
	
	/*
		Implemente el constructor de la clase AFD
		que recibe como argumento un string que 
		representa el path del archivo que contiene
		la informacion del afd (i.e. "Documentos/archivo.afd").
		Puede utilizar la estructura de datos que desee
	*/
	ArrayList<String> afdPos = new ArrayList<String>();
	String simbolosTerminales;
	int cantidadPeliculas;
	String finalesE;
	
	public AFD(String path){	
		File archivo = null;
    	FileReader filer = null;
      	BufferedReader buffer = null;

		try {
			archivo = new File (path);
			filer = new FileReader(archivo);
			buffer = new BufferedReader(filer);
			String linea;
			int count = 0;
			while((linea=buffer.readLine())!=null) {
				count = count + 1;
				if(count == 1) {
					simbolosTerminales = linea;

				} else if (count == 2) {

					cantidadPeliculas = Integer.parseInt(linea);
				
				} else if (count == 3) {

					finalesE = linea.replace(",", " ");
				} else if (count >= 4) {

					String limOne = linea.replace(",", " ");
					String setVal = limOne.replace("->", " ");
					afdPos.add(setVal);	

					/* for (int j = 0; j < afdPos.size(); j++) {							
						System.out.println("_________");
						System.out.println(afdPos.get(j));
						System.out.println("_________");
					} */
				
				} 
			}
		} catch(Exception e) {
			System.out.println(e);
		}finally {
			try {

			}catch(Exception etwo) {
				System.out.println(etwo);
			}
		}
	}

	/*
		Implemente el metodo transition, que recibe de argumento
		un entero que representa el estado actual del AFD y un
		caracter que representa el simbolo a consumir, y devuelve 
		un entero que representa el siguiente estado
	*/
	public int getTransition(int currentState, char symbol){
		for (int j = 0; j < afdPos.size(); j++) {
			String aux = afdPos.get(j);

			if(Character.getNumericValue(aux.charAt(0)) == currentState) {
				if(aux.charAt(2) == symbol) {
					int valRet = Character.getNumericValue(aux.charAt(4));
					return valRet;
				}
			}
		}
		return 0;
	}

	/*
		Implemente el metodo accept, que recibe como argumento
		un String que representa la cuerda a evaluar, y devuelve
		un boolean dependiendo de si la cuerda es aceptada o no 
		por el afd
	*/
	public boolean accept(String string){
		int count = 1;
		for(int j = 0; j < string.length(); j++) {
			count = getTransition(count, string.charAt(j));
			if(count == 0) {
				return false;
			}
		}
		for(int j = 0; j < finalesE.length(); j++) {
			if(Character.getNumericValue(finalesE.charAt(j)) == count) {
				return true;
			}
		}
		return false;
	}

	/*
		El metodo main debe recibir como primer argumento el path
		donde se encuentra el archivo ".afd", como segundo argumento 
		una bandera ("-f" o "-i"). Si la bandera es "-f", debe recibir
		como tercer argumento el path del archivo con las cuerdas a 
		evaluar, y si es "-i", debe empezar a evaluar cuerdas ingresadas
		por el usuario una a una hasta leer una cuerda vacia (""), en cuyo
		caso debe terminar. Tiene la libertad de implementar este metodo
		de la forma que desee. 
	*/
	public static void main(String[] args) throws Exception{
		String bandera;
		boolean fin = true;
		
		if(args.length > 0) {
			if(args.length == 2 && args[1].equals("-i")) {
				
				AFD cargar = new AFD(args[0]);
				bandera = args[1];
				Scanner input = new Scanner(System.in);

				while(fin) {
					System.out.println("ingrese una cuerda: ");
					String caracteres;
					caracteres = input.nextLine();
					boolean ac = cargar.accept(caracteres);
					System.out.println(ac);
					//por definir
					if(caracteres.equals(" ") || caracteres.equals("")) {
						fin = false;
					}
				}

			} else if (args.length == 3 && args[1].equals("-f")) {
				File archivo = null;
    			FileReader filer = null;
      			BufferedReader buffer = null;
				AFD cargar = new AFD(args[0]);
				bandera = args[1];
				try {
					archivo = new File (args[2]);
					filer = new FileReader(archivo);
					buffer = new BufferedReader(filer);
					String linea;
					int count = 1;
					System.out.println("LEYENDO CUERDAS...");					
					while((linea=buffer.readLine())!=null) {
						boolean ac = cargar.accept(linea);
						System.out.println("------------------------------------------------");					
						System.out.println("cuerda No." +count+ " aceptada: "  +ac);
						System.out.println("------------------------------------------------");					
						count = count + 1;
					}
				} catch(Exception e) {
					System.out.println(e);
				}finally {
					try {

					}catch(Exception etwo) {
						System.out.println(etwo);
					}
				}

			} else {
				System.out.println("Error en parametros vuelva a intentarlo!");	
			}
		} else {
			System.out.println("La clase debe tener argumentos");
		}
	}
}