package avionss;

import java.util.Scanner;

public class Avions {
	
	private String marca ="";
	private String model ="";
	private String matricula ="";
	private int coordenadaX=0;
	private int coordenadaY=0;
	private int coordenadaH=0;
	private double velocitat=0;
	private boolean trenAterratge=true;
	private boolean motor=false;
	private boolean aparcat=false;
	private int passatge=0;
	static Scanner sc;
	//metode constructor dels nostres objectes de la calse avions
	public Avions(String model, String marca, String matricula, int coordenadaX, int coordenadaY,int passatge){
		
		this.model=model;
		this.marca=marca;
		this.matricula=matricula;
		this.coordenadaX=coordenadaX;
		this.coordenadaY=coordenadaY;
		this.passatge=passatge;
	}
	//Metode que nomes mostras sysos amb dades sobre el objecte/clase
	public void mostrarInformacioAvio(){
		
		System.out.print("Marca: "+this.marca+"\t");
		System.out.print("Model: "+this.model+"\t");
		System.out.print("Matricula: "+this.matricula+"\t");
		System.out.print("X: "+this.coordenadaX+"\t");
		System.out.print("Y: "+this.coordenadaY+"\t");
		System.out.print("Alçada: "+this.coordenadaH+"\t");
		System.out.print("Velocitat: "+this.velocitat+"\t");
		System.out.print("Tren aterratge: "+booleanToString(this.trenAterratge)+"\t");
		System.out.print("Motor: "+booleanToString(this.motor)+"\t");
		System.out.print("Aparcat: "+booleanToString(this.aparcat));
		
	}
	//metode crear per fer mes visibles els true y false de booleans sobre el avio
	public String booleanToString(boolean control){
		
		String mostrar;
		
		if(control==false){
			mostrar="OFF";
		}
		else{
			mostrar="ON";
		}
		
		return mostrar;
	}
	//METODES DE CAMBIS A PROPIETATS ATRAVES DEL MENU DEL METODE opcionsTractamentAvio (en la clase espaiaeri)
	//son petits medos condicionats per restricions y amb menus per que l'usuari marqui que vol fer
	
	/*Metode amb sub menu per saber que vol er l'usuari amb el motor del objecte avio
	 tenim un tractament de l'escaner y condicions necesarias per donar mes fidelitat al simulador*/
	public void controlDelMotor() {
		
		int accio;		

		do{
			System.out.println("El motor esta "+booleanToString(this.motor)+", tria opcio");
			System.out.println("1.- Motors apagats");
			System.out.println("2.- Motors encesos");
			System.out.println("0.- Finalitzar tractament motor");
			
			sc = new Scanner(System.in);
			try{
				accio = sc.nextInt();
		    } catch(Exception e) { 
		    	System.out.println("Error tenis que introduir un numero");
		    	accio=-1;
		    }				
		    
			switch (accio)
		    {
		    case 1:	
		    	//condicio per poder apagar el motor es que estigui a terra i parat
		    	if(this.velocitat==0 && this.coordenadaH==0){			    	
		    		this.motor=false;
		    	}
		    	else{
		    		System.out.println("Per aturar el motor necesitas que la velocitat y l'alçada siguin 0");
		    	}
		    accio=0;
		    break;
		    	
		    case 2:	
		    	this.motor=true;
		    	accio=0;
		    break;
		    }
			System.out.println("El motor ara esta "+booleanToString(this.motor));
		}while (accio!=0);		
		
	}
	/*metode amb sub menu per el qual el usuari pot treballar amb la velocitat
	  augmentan o disminint aquesta, mitjaçant metodes de calcul matematic
	  a mes de tenir certes restriscions per ser mes realista*/
	public void controlVelocitat() {
		int accio;
		double velocitat;
		//control del motor estiqui ences sino no farem res
		if(this.motor==true){
			do{
				System.out.println("El avio es mou a una velocitat "+this.velocitat+", tria opcio");
				System.out.println("1.- Mantenir la velocitat");
				System.out.println("2.- Augmentar la velocitat");
				System.out.println("3.- Disminuir la velocitat");
				System.out.println("0.- Finalitzar tractament motor");
				
				sc = new Scanner(System.in);
				try{
					accio = sc.nextInt();
			    } catch(Exception e) { 
			    	System.out.println("Error tenis que introduir un numero");
			    	accio=-1;
			    }	
				    switch (accio)
				    {
				    case 1:	
				    	System.out.println("Velocitat no variada");
				    	accio=0;			    
				    break;
				    	
				    case 2:		    	
				    	System.out.println("En quans Km vol aumentar la velocitat del avio");
				    	try{
				    		velocitat = sc.nextDouble();
				    		this.velocitat=this.velocitat+velocitat;
				    	}catch (Exception e){
				    		System.out.println("Volors no numerics, no podemo variar la velocitat");
				    	}
				    	accio=0;
				    break;
				    
				    case 3:		
				    	System.out.println("En quans Km vol disminuir la velocitat del avio");				    	
				    	try{
				    		velocitat = sc.nextDouble();
				    		//condicio per tal que la velocitat no quedi negativa!
				    		if(this.velocitat-velocitat>=0){
					    		this.velocitat=this.velocitat-velocitat;
					    	}
					    	else{
					    		System.out.println("No pots disminuir tant la velocitat");
					    	}
				    	}catch (Exception e){
				    		System.out.println("Volors no numerics, no podemo variar la velocitat");
				    	}		    	
				    	accio=0;
				    break;
				    }
				    System.out.println("El avio ara te una velocitat de "+this.velocitat);
			}while (accio!=0);
		}
		else{
			System.out.println("El motor esta: "+booleanToString(this.motor)+" hi ha que encendre'l primer");
			accio=0;
		}
		
	}
	/*metode amb sub menu per el tractament del tren d'aterratge, del objecte avio
	 conte restricions per donar mes realisme al simulador*/
	public void controlTrenAterratge() {
		int accio;		

		do{
			System.out.println("El tren d'aterratge esta "+booleanToString(this.trenAterratge)+", tria opcio");
			System.out.println("1.- Amagar");
			System.out.println("2.- Treure");
			System.out.println("0.- Finalitzar");
			
			sc = new Scanner(System.in);
			try{
				accio = sc.nextInt();
		    } catch(Exception e) { 
		    	System.out.println("Error tenis que introduir un numero");
		    	accio=-1;
		    }
		    switch (accio)
		    {
		    case 1:	
		    	//per guardar el ten tindrem que tindre una altura minima
		    	if(this.coordenadaH>=500){
		    		System.out.println("Tren aterratge guardat");
		    		this.trenAterratge=false;	
		    	}else{
		    		System.out.println("Necesites guanyar altura per amagar el tren d'atteratge");
		    	}
		    	accio=0;
		    break;
		    	
		    case 2:	
		    	//per poder treue el tren necesitaremm unes caracteristiques de velocitat y de altura maxima
		    	if(this.coordenadaH<=1000 && this.velocitat<=220){
		    		System.out.println("Tren aterratge en posicio exterior");
		    		this.trenAterratge=true;
		    	}else{
		    		System.out.println("Necesites disminuir altura per treure el tren d'atteratge i disminuir la velocitat");
		    	}
		    	accio=0;
		    break;
		    }
		    System.out.println("El tren d'aterratge esta "+booleanToString(this.trenAterratge));
		}while (accio!=0);
		
	}
	/*metode amb sub menu per el tractament d'augment disminucio de l'alçada
	 * amb diverses restricions per donar realisme*/
	public void controlAlçada() {
		int accio,canvisAlçada;		
		if(this.motor==true){
			do{
				System.out.println("El avio es troba a l'alçada de "+this.coordenadaH+", tria opcio");
				System.out.println("1.- Mantenir alçada");
				System.out.println("2.- Augment alçada");
				System.out.println("3.- Disminuir alçada");
				System.out.println("0.- Finalitzar canvis d'alçada");
				
				sc = new Scanner(System.in);
				try{
					accio = sc.nextInt();
			    } catch(Exception e) { 
			    	System.out.println("Error tens que introduir un numero");
			    	accio=-1;
			    }	
			    switch (accio)
			    {
			    case 1:	
			    	System.out.println("L'alçada no varia");	
			    	accio=0;
			    break;
			    	
			    case 2:
			    	try{
			    	System.out.println("Quan vols augmentar l'alçada");
			    	canvisAlçada = sc.nextInt();
			    	//control de velocitat per tal que el tren tingui un minim per poder ser tret
			    	if(this.velocitat>=180){
			    	this.coordenadaH=this.coordenadaH+canvisAlçada;
			    	}else{
			    		System.out.println("Necessites més velocitat");
			    	}
			    	}catch(Exception e){
			    		System.out.println("Error tens que introduir un numero");
			    	}
			    	accio=0;
			    break;
			    case 3:	
			    	try{
				    	System.out.println("Quan vols disminuir l'alçada");
				    	canvisAlçada = sc.nextInt();
				    	//control de disminuir peque no s'estrelli contra el terra
				    	if(this.coordenadaH-canvisAlçada>=0){
				    		this.coordenadaH=this.coordenadaH-canvisAlçada;
				    	}else{
				    		System.out.println("No pots disminuir tant l'alçada!");
				    	}
				    	}catch(Exception e){
				    		System.out.println("Error tens que introduir un numero");
				    	}
			    	accio=0;
			    break;
			    }
			    System.out.println("El avio ara es troba a l'alçada de "+this.coordenadaH+", tria opcio");
			}while (accio!=0);
		}
		else{
			System.out.println("El motor esta: "+booleanToString(this.motor)+" hi ha que encendre'l primer");
			accio=0;
		}		
	}
	/* Metode amb sub menu, en el qual el usuari ens dira les noves directius de moviment
	 del avio, auqestes estan subjectes a restricions previes per donar mes realisme
	 */
	public void controlPosicio() {
		int accio,novaPoss;		
		//motor ences si o si per poder variar
		if(this.motor==true){
			do{
				System.out.println("X["+this.coordenadaX+"]--Y["+this.coordenadaY+"]");
				System.out.println("Tria opcio");
				System.out.println("1.- Mantenir posicio");
				System.out.println("2.- Cambi posicio");
				System.out.println("0.- Finalitzar");
				
				sc = new Scanner(System.in);
				try{
					accio = sc.nextInt();
			    } catch(Exception e) { 
			    	System.out.println("Error tenis que introduir un numero");
			    	accio=-1;
			    }	
			    switch (accio)
			    {
			    case 1:	
			    	System.out.println("Posicio no variada");	
			    	accio=0;
			    break;
			    	
			    case 2:	
			    	//millorable per mes try per cada punt
			    	try{
				    	System.out.println("Nova X");
				    	novaPoss = sc.nextInt();
				    	this.coordenadaX=novaPoss;
				    	System.out.println("Nova Y");
				    	novaPoss = sc.nextInt();
				    	this.coordenadaY=novaPoss;
			    	}
			    	catch(Exception e){
			    		System.out.println("No has introduir numeros en les coordenades");
			    	}
			    	accio=0;
			    break;			    	
			    }
			    System.out.println("Nova posicio --> X["+this.coordenadaX+"]--Y["+this.coordenadaY+"]");
			}while (accio!=0);
		}
		else{
			System.out.println("El motor esta: "+booleanToString(this.motor)+" hi ha que encendre'l primer");
			accio=0;
		}
		
	}
	/*metode amb sub menu per el control de si el avio esta aparcat o no
	 cumplin certes restricion per donar realisme a el metode*/
	public void controlAparcar() {
		int accio;		

		do{
			System.out.println("El estat d'aparcat esta "+booleanToString(this.aparcat)+", tria opcio");
			System.out.println("1.- Treure del estar aparcat");
			System.out.println("2.- Aparcat");
			System.out.println("0.- Finalitzar tractament");
			
			sc = new Scanner(System.in);
			try{
				accio = sc.nextInt();
		    } catch(Exception e) { 
		    	System.out.println("Error tenis que introduir un numero");
		    	accio=-1;
		    }	
		    switch (accio)
		    {
		    case 1:	
		    	this.aparcat=false;
		    	accio=0;
		    break;
		    	
		    case 2:
		    	//per poder dir que esta aparcat el motor te que esta off y no tindre altura
		    	if(this.coordenadaH==0 && this.motor==false){
		    		System.out.println("El avio esta aparcat");
		    		this.aparcat=true;	
		    	}
		    	else{
		    		System.out.println("El avio te que esta completament aturat y ha terra");
		    	}
		    	accio=0;
		    break;		    	
		    }
		}while (accio!=0);
		
	}	
	
	//metodes accesos per la clase de avio
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public int getCoordenadaX() {
		return coordenadaX;
	}
	public void setCoordenadaX(int coordenadaX) {
		this.coordenadaX = coordenadaX;
	}
	public int getCoordenadaY() {
		return coordenadaY;
	}
	public void setCoordenadaY(int coordenadaY) {
		this.coordenadaY = coordenadaY;
	}
	public int getCoordenadaH() {
		return coordenadaH;
	}
	public void setCoordenadaH(int coordenadaH) {
		this.coordenadaH = coordenadaH;
	}
	public double getVelocitat() {
		return velocitat;
	}
	public void setVelocitat(double velocitat) {
		this.velocitat = velocitat;
	}
	public boolean getTrenAterratge() {
		return trenAterratge;
	}
	public void setTrenAterratge(boolean trenAterratge) {
		this.trenAterratge = trenAterratge;
	}
	public boolean getMotor() {
		return motor;
	}
	public void setMotor(boolean motor) {
		this.motor = motor;
	}
	public boolean getAparcat() {
		return aparcat;
	}
	public void setAparcat(boolean aparcat) {
		this.aparcat = aparcat;
	}
	public int getPassatge() {
		return passatge;
	}
	public void setPassatge(int passatge) {
		this.passatge = passatge;
	}
	
}
