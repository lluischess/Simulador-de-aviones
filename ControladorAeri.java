package avionss;

import java.util.Scanner;


public class ControladorAeri {
	
	static Scanner sc;
	static EspaiAeri espaiAeri1;
	//Metode inicial en el que treballara el usuari conte el metodes mes important del tractament del programa
	//acedin aqueste metodes via teclat utilitzan un menu
	public static void main(String[] args) {
		
		int accio;
		espaiAeri1= new EspaiAeri("MaresmeZone",5);
		
		do{
			System.out.println("Entra una opció de les següents");
			System.out.println("1.- Donar d’alta avió al sistema");
			System.out.println("2.- Gestionar un dels avions");
			System.out.println("3.- Manteniment de l’espai aeri");
			System.out.println("4.- Informació de l’espai aeri");
			System.out.println("0.- Sortir");
			
			sc = new Scanner(System.in);
			try{
				accio = sc.nextInt();
		    } catch(Exception e) { 
		    	System.out.println("Error tenis que introduir un numero");
		    	accio=-1;
		    }	
		    switch (accio)
		    {
		    case 1: donaAltaAvio(); break;
		    	
		    case 2: gestioAvio(); break;
		    	
		    case 3: espaiAeri1.mantenimentEspaiAeri(); break;
		    
		    case 4: espaiAeri1.mostrarAvions(); break;
		    	
		    }
		}while (accio!=0);

	}
	
	//Metode que demana al usuari dades per crear un objecta de la clase avions
	public static void donaAltaAvio(){
		
		String model,marca,matricula;
		int coordenadaX,coordenadaY,passatge;
		//nomes preguntarem al usuari si podem posar mes avions sino ja direm directament no!
		if(controlCapacitatEspaiAeri()==false){
			try{
			System.out.println("Entra el model del avio:");
			model=sc.next();//canvis per agafar be el que entra per teclat
			sc.nextLine();//canvis per agafar be el que entra per teclat
			System.out.println("Entra el nom del fabrican/marca del avio:");
			marca=sc.next();//canvis per agafar be el que entra per teclat
			sc.nextLine();//canvis per agafar be el que entra per teclat
			System.out.println("Entra la matricula del avio:");
			matricula=sc.next();//canvis per agafar be el que entra per teclat
			sc.nextLine();//canvis per agafar be el que entra per teclat
			System.out.println("Entra coordenada X on esta inicialment:");
			coordenadaX=sc.nextInt();//canvis per agafar be el que entra per teclat
			sc.nextLine();//canvis per agafar be el que entra per teclat
			System.out.println("Entra coordenada Y on esta inicialment:");
			coordenadaY=sc.nextInt();//canvis per agafar be el que entra per teclat
			sc.nextLine();//canvis per agafar be el que entra per teclat
			System.out.println("Entra el numero de passatges que porta:");
			passatge=sc.nextInt();//canvis per agafar be el que entra per teclat
			sc.nextLine();//canvis per agafar be el que entra per teclat
			espaiAeri1.afegirAvio(model,marca,matricula,coordenadaX,coordenadaY,passatge);
			}
			catch(Exception e){
				System.out.println("Alguna de les dades introduides no es correcte");
			}
			
		}
		
	}
	
	//Metode que mira per la capacitat de 1 espai aeri en el que treballem i controla que no ens pasem avans d'introduir res
	public static boolean controlCapacitatEspaiAeri(){		
		
		boolean capacitatSobrepasada=false;
		//Control que no pasem la capacitat de la l'espai aeri -- pregunta si el numero avions es mes gran que la capacitat retornarem un no com 
		//opcio per que doni d'alta avions
		if(espaiAeri1.getNumAvions()>=espaiAeri1.getcapacitat()){
			capacitatSobrepasada=true;
			System.out.println("Capacitat de ["+espaiAeri1.getcapacitat()+"] avions en actiu arribada.");
			System.out.println("Aqui no podem donar d'alta mes avions.");
		}			
		return capacitatSobrepasada;		
	}
	//Metode per el qual demanarem al usuari una matricula del avio sobre el que vol treballar y ferli modificacions
	public static void gestioAvio(){
		boolean trobat=false;
		String MatriculaBuscar;
		System.out.println("Entra La matrícula del avio buscar:");
		MatriculaBuscar=sc.next();//Agreiments oscar! per debugar i trobar que agafava un espai en blanc
		sc.nextLine();
		//buscarem si la matricula esta en algun dels nostres objectes avio
		trobat=espaiAeri1.trobarMatricula(MatriculaBuscar);
		//condicio si trobem matricula podem tractar el avio sino informen al usuari
		if(trobat){
			espaiAeri1.opcionsTractamentAvio(MatriculaBuscar);
		}
		else{
			System.out.println("La matricula["+MatriculaBuscar+"] no es de cap avio en linea");
		}
	}

}
