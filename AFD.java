import java.io.*;
import java.util.ArrayList;
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
				count = count++;
				switch (count) {
					case 1:
						simbolosTerminales = linea;
						break;
					case 2: 
						cantidadPeliculas = Integer.parseInt(linea);
						break;
					case 3:
						finalesE = linea;
						break;
					default:
						String limOne = linea.replace(",", " ");
						String setVal = limOne.replace("->", " ");
						afdPos.add(linea);	
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
		return 0;
	}

	/*
		Implemente el metodo accept, que recibe como argumento
		un String que representa la cuerda a evaluar, y devuelve
		un boolean dependiendo de si la cuerda es aceptada o no 
		por el afd
	*/
	public boolean accept(String string){
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
		
	}
}