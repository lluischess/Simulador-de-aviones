package avionss;

import java.util.Scanner;

public class EspaiAeri {

	private Avions LlistaAvio[];
	private int numAvio=0;
	private int capacitat=0;
	private String nomEspaiAeri;
	static Scanner sc;
	//metode per construir el espai aeri on estaran el objectes avions
	public EspaiAeri(String nomEspaiAeri, int Capacitat){

		this.capacitat=Capacitat;
		this.nomEspaiAeri=nomEspaiAeri;
		LlistaAvio= new Avions[capacitat];
	}
	//Metode que donara d'alta el avions si tenim espai disponible en el nostre arrya d'avions
	public void afegirAvio(String model, String marca, String matricula, int coordenadaX, int coordenadaY, int passatge){
			
		Avions avioTemporal =new Avions(model,marca,matricula,coordenadaX,coordenadaY,passatge);
		
		int x=0,posicio=0;
		boolean trobat=false;
		//No tindrem dos avions amb matricula igual.. 
		if(trobarMatricula(matricula)==false){
			//no podem iniciar un avio on ja hi ha un
			if(controlPosicio(coordenadaX, coordenadaY)==false){
				System.out.println("Avio en actiu llest!");
				
				//Buscarem en el array un espai buit on ubicar el avio
				while(trobat!=true && x<this.capacitat){
					if(LlistaAvio[x]==null){
						trobat=true;
						posicio=x;
					}
					x++;
				}
				
				LlistaAvio[posicio]=avioTemporal;
				numAvio++;
			}
		}
		
	}
	
	//Metode que pasat una matricula tornara un boolean per determinar si la trobat o no
	public boolean trobarMatricula(String matricula){
		
		boolean trobat=false;
		int posicio=0;
			
		while(trobat!=true && posicio<this.capacitat){
		//condicio perque no peti si esta buid casella a null/casellas que no tenen  objecte 
			if (LlistaAvio[posicio]!=null){
				//condicio per trobar una matricula igual a la introduida
				if(LlistaAvio[posicio].getMatricula().equalsIgnoreCase(matricula) && trobat==false){
					trobat=true;
					System.out.println("La matricula["+LlistaAvio[posicio].getMatricula()+"] ha sigut trobada.");
				}				
			}
			posicio++;			
		}
				
		return trobat;
		
	}
	
	
	//Metode que pasade un punts retornara un boolean per tal de saber si ja tenim un avio ubicat en aquella posicio
	public boolean controlPosicio(int coordenadaX, int coordenadaY){
		
		boolean trobat=false;
		int posicio=0;
			
		while(trobat!=true && posicio<this.capacitat){
		//condicio perque no peti si esta buid casella a null/casellas que no tenen  objecte 
			if (LlistaAvio[posicio]!=null){
				//condicio per trobar una matricula igual a la introduida
				if(LlistaAvio[posicio].getCoordenadaX()== coordenadaX &&LlistaAvio[posicio].getCoordenadaY()== coordenadaY 
						&&LlistaAvio[posicio].getCoordenadaH()== 0 &&trobat==false){
					trobat=true;
					System.out.println("Aqui ja tenim un avio en actiu.");
				}				
			}
			posicio++;			
		}
				
		return trobat;
		
	}
	
	//Metode que mostra la informacio sobre el espai aeri, propietat + crida a metode mostrar infformcio del avions que mostrara 1 per 1 els avions 
	//que estan en actiu
	public void mostrarAvions()	{
	  System.out.println("Informació espari aeri de: " + nomEspaiAeri);
	  System.out.println("Te capacitat per: "+ capacitat+" avions" );
	  System.out.println("En aquests moments hi ha aquesta avions en actiu: ");
	  System.out.println("+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
	  
	  for (int i=0; i<this.capacitat; i++){
		  
		  //control per nulls si borrem alguna cosa
		  if (LlistaAvio[i]!=null){
			  System.out.print("|||");
			  System.out.print("Avio Nº ["+(i+1)+"]\t");
			  LlistaAvio[i].mostrarInformacioAvio();			  
			  System.out.print("|||");
		  }
		  System.out.println("");
		  System.out.println("+------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------+");
	  }
	  
	  perillsAlEspaiAeri();
	}
	
	/*Metode que controla condicions per saber si els avions tenen algun tipus de perrill*/
	private void perillsAlEspaiAeri() {
		int x,y;
		Avions avioEnTractament = null;
		System.out.println("Control perills espai aeri--->"+this.nomEspaiAeri);
		//bucle per buscar el avions
		for(x=0;x<this.capacitat;x++){
			
			//Condicional per obtenir avio per treballar amb ell
			if(LlistaAvio[x]!=null){
				avioEnTractament=LlistaAvio[x];
			}			
			//Si tenim un avio traballarem amb ell y buscarem posibles riscos
			if(avioEnTractament!=null){
				//per comprovar proximitat amb altres avions
				for(y=x+1;y<this.capacitat;y++){
					
					if(LlistaAvio[y]!=null){
						if((avioEnTractament.getCoordenadaX()-LlistaAvio[y].getCoordenadaX()<=50)||(avioEnTractament.getCoordenadaY()-LlistaAvio[y].getCoordenadaY()<=50)
						&& (avioEnTractament.getCoordenadaH()-LlistaAvio[y].getCoordenadaH()<=150)){
							
							System.out.println("-Perill de colisio per proximitat del avio<"+avioEnTractament.getMatricula()+"> amb el avio<"+LlistaAvio[y].getMatricula()+">");
						}	
					}
				}
				//control de la velocitat
				if(avioEnTractament.getVelocitat()>=1200){
					System.out.println("Velocitat del avio["+avioEnTractament.getMatricula()+"] molt eleveda");
				}
				//control de l'altura
				if(avioEnTractament.getCoordenadaH()>=25000){
					System.out.println("Altures ["+avioEnTractament.getMatricula()+"] perillosa per l'integritat del avio");
				}
			}
			
			
		}
	}
	//METODE, amb el menu d'opcions is l'usuari vol treballar sobre un avio
	/*Conte metodes incluits avio*/
	public void opcionsTractamentAvio(String matricula) {
		
		int accio,casellaDelAvioATractar;
		Avions avioEnTracament;
		//numero de on es troba l'avio obre el que volem treballar
		casellaDelAvioATractar=obtencioPosicioArrayAvioPerMatricula(matricula);
		
		avioEnTracament=LlistaAvio[casellaDelAvioATractar];
		do{
			System.out.println("Entra una opció de les següents");
			System.out.println("1.- Encendra/Apagar motors");
			System.out.println("2.- Acelera/frenar");
			System.out.println("3.- Cambi d'alçada");
			System.out.println("4.- Baixa/pujar tren d'aterratge");
			System.out.println("5.- Poscio [X,Y]");
			System.out.println("6.- Aparca");
			System.out.println("0.- Finalitzar operacions avio seleccionat");
			
			sc = new Scanner(System.in);
			try{
				accio = sc.nextInt();
		    } catch(Exception e) { 
		    	System.out.println("Error tenis que introduir un numero");
		    	accio=-1;
		    }	
		    switch (accio)
		    {
		    case 1: avioEnTracament.controlDelMotor(); 		break;
		    	
		    case 2: avioEnTracament.controlVelocitat();		break;
		    	
		    case 3: avioEnTracament.controlAlçada(); 		break;
		    
		    case 4: avioEnTracament.controlTrenAterratge(); break;
		    
		    case 5: avioEnTracament.controlPosicio(); 		break;
		    
		    case 6: avioEnTracament.controlAparcar(); 		break;
		    	
		    }
		}while (accio!=0);
		mantenimentEspaiAeri();
		mostrarAvions();		
	}
	
	//Metode que elimina aviions que ja no estan en el nostre espai arei, condicions
	public void mantenimentEspaiAeri() {
		int x;
		
		for(x=0;x<this.capacitat;x++){
			if(LlistaAvio[x]!=null){
				if(LlistaAvio[x].getAparcat()==true || LlistaAvio[x].getCoordenadaX()<0 ||
				LlistaAvio[x].getCoordenadaX()>1000 || LlistaAvio[x].getCoordenadaY()<0 ||
				LlistaAvio[x].getCoordenadaY()>1000){
					
					System.out.println("El avio<"+LlistaAvio[x].getModel()+"> amb matricula ["+LlistaAvio[x].getMatricula()+"] esta fora del espai aeri");
					LlistaAvio[x]=null;
					numAvio--;
				}
			}
		}	
	}
	
	//Metode amb el qual atrves d'una matricula obtenims la posicio en l'array on esta l'avio
	public int obtencioPosicioArrayAvioPerMatricula(String matricula) {
		int x,casellaDelAvioATractar=0;
		
		for(x=0;x<this.capacitat;x++){
			
			if(LlistaAvio[x]!=null){
				if(LlistaAvio[x].getMatricula().equalsIgnoreCase(matricula)){
					casellaDelAvioATractar=x;
				}
			}
		}
		return casellaDelAvioATractar;
	}

	//metodes per accedir propietats de el espai aeri 
	public  int getcapacitat() {
		return capacitat;
	}
	public  int getNumAvions() {
		return numAvio;
	}	
	
}