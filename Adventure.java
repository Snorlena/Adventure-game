import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;

public class Adventure {

	
	
	public static void main(String[] args) throws IOException {
		
		Spelare �ventyrare = new Spelare();
		�ventyrare.hp = 100;
		�ventyrare.skada = 5;
		
		Bj�rn bear = new Bj�rn();
		bear.hp = 100;
		bear.namn = "Bj�rn";
		bear.d�dad = false;
		bear.skada = 20;
		
		Varg wolf = new Varg();
		wolf.hp = 100;
		wolf.d�dad = false;
		wolf.namn = "Varg";
		wolf.skada = 50;
		
		
		PrintWriter keys = new PrintWriter
						(new BufferedWriter
						(new FileWriter("Keys.txt")));
		Scanner hasKeys = new Scanner(new File("Keys.txt")); //Scanner f�r att se om man har r�tt nyckel.
		Scanner areas = new Scanner(new File("Areas.txt"));  //L�ser in filen Areas.txt f�r kordinater.
		Scanner keyboard = new Scanner(System.in);			 //Scanner f�r att g�.
		Scanner story = new Scanner(new File("Story.txt"));  //Scanner f�r att l�sa in Story.
		Scanner fight = new Scanner(System.in);				 //Scanner f�r fighter. 
		Scanner drick = new Scanner(System.in);				 //Scanner f�r att dricka ur b�cken.
		
		String[] area = new String[34];						
		Area[] Areas = new Area[34];
		
		String[] storY = new String[40];
		Story[] gameStory = new Story[40];
		
		String hasGoldenKey;
		
		int n = 0;
				
		while (areas.hasNextLine()) {						//S� l�nge filen har en ny rad s� forts�tter loopen.
			area[n] = areas.nextLine();						//L�ser in en rad fr�n textfilen och l�gger den i an array.
		
		String[] splits = area[n].split(" ");				//Splittar upp arrayen i flera olika delar s� att vi kan ta ut v�rden f�r alla neighbours.
			
		List<String> v�derstreck = Arrays.asList(splits);
					
		int myId = Integer.parseInt(splits[0]);
	    int neighbourNorthId = Integer.parseInt(splits[1]);
	    int neighbourSouthId = Integer.parseInt(splits[2]);
	    int neighbourWestId = Integer.parseInt(splits[3]);
	    int neighbourEastId = Integer.parseInt(splits[4]);
	    
		
	    Areas[n] = new Area(myId, neighbourNorthId, neighbourSouthId, neighbourWestId, neighbourEastId);
		
	   n = n + 1;
	   	   
	   	}
		
		int s = 0;
		
		while (story.hasNextLine()) {
			storY[s] = story.nextLine();
			
		String[] Storys = storY[s].split("#");
		
		List<String> handling = Arrays.asList(Storys);
		
		String nextStory = Storys[0];
		
		gameStory[s] = new Story(nextStory);
		
		s = s + 1;
		
		}
		
		Area currentArea = Areas[0];  //S�tter startzon till Areas[0]
		
		//H�r har jag valt att l�gga storyn i en egen array f�r att l�ttare kunna hanterna storyn.
		//Jag har ocks� med hj�lp av detta f�tt bort tidigare fel d�r samma areatext kom upp igen om man gick �t ett felaktigt h�ll.
		System.out.println(gameStory[currentArea.getId()].getprintStory());
		
		boolean hasgoldkey = false;
		boolean hasbronzekey = false;
		boolean hasendkey = false;		
				
		//Loop som h�ller koll p� Zon och vilken text som ska skrivas ut.
		while (true) {
					
			//S�tter nextArea till 0, som ska vara startzonen.
			int nextArea = 0;
			
			
			//Om man tar sig till Area 11.
			if (currentArea.getId() == 11) {
				keys.println("goldenKey");
				keys.close();
				while (hasKeys.hasNextLine()) {
					hasGoldenKey = hasKeys.nextLine();
					if (hasGoldenKey.equals("goldenKey")) {
						hasgoldkey = true;
					}
				}
			}
			
			//Om man st�r p� area 17 och bj�rnen lever
			if (currentArea.getId() == 17 && !bear.d�dad) {
				System.out.println("Du m�ter en stor bj�rn. Vill du sl�ss eller springa iv�g?\n\tSl�(1)\n\tSpring(2)");
				while (true) {
				String fightorrun = fight.next();
				if (fightorrun.equals("1")) {
					System.out.println("Du sl�r bj�rnen och g�r " + �ventyrare.skada + " i skada");
					bear.hp -= �ventyrare.skada;
					System.out.println("Bj�rnen har " + bear.hp + "% liv kvar");
				if (bear.hp > 0) {
					System.out.println("Bj�rnen sl�r tillbaka och g�r " + bear.skada + " skada p� dig");
					�ventyrare.hp -= bear.skada;
					System.out.println("Du har " + �ventyrare.hp +"% liv kvar");
			}
				if (�ventyrare.hp <= 0) {
						System.out.println("Du slogs tappert, men du var f�r vek och dog en sm�rtsam d�d.\n\nSpelet �r slut.");
						System.exit(0);
					}
				if (bear.hp <= 0) {
						bear.d�dad = true;
						hasbronzekey = true;
						System.out.println("Du slog ihj�l bj�rnen och hittade en nyckel.");
						break;
						
			} else {
				System.out.println("Vill du sl�ss eller springa iv�g?\n\tSl�(1)\n\tSpring(2)");
		}
	}
				if (fightorrun.equals("2")) {
					currentArea = Areas[16];
					System.out.println(gameStory[currentArea.getId()].getprintStory());
					break;
			}
		} 
			} else if (currentArea.getId() == 17 && bear.d�dad) {
				System.out.println("En d�d bj�rn �r allt som finns h�r.");
		}
			
			//Om man st�r p� area 26 och Vargen lever
			if (currentArea.getId() == 26 && !wolf.d�dad) {
				System.out.println("Du m�ter en ilsken Varg. Vill du sl�ss eller springa iv�g?\n\tSl�(1)\n\tSpring(2)");
				while (true) {
				String fightorrun = fight.next();
				if (fightorrun.equals("1")) {
					System.out.println("Du sl�r Vargen och g�r " + �ventyrare.skada + " i skada");
					wolf.hp -= �ventyrare.skada;
				if(wolf.hp <= 0) {
					wolf.hp = 0;
				}
					System.out.println("Vargen har " + wolf.hp + "% liv kvar");
				if (wolf.hp >= 0) {
					System.out.println("Vargen biter dig och g�r " + wolf.skada + " skada p� dig");
					�ventyrare.hp -= wolf.skada;
				if (�ventyrare.hp < 0) {
					�ventyrare.hp = 0;
				}
					System.out.println("Du har " + �ventyrare.hp +"% liv kvar");
				}
					if (�ventyrare.hp <= 0) {
						System.out.println("Du slogs tappert, men du var f�r vek och dog en sm�rtsam d�d.\n\nSpelet �r slut.");
						System.exit(0);
					}
					if (wolf.hp <= 0) {
						wolf.hp = 0;
						wolf.d�dad = true;
						hasendkey = true;
						System.out.println("Du slog ihj�l vargen och hittar sista nyckeln.");
						break;
					} else {
				System.out.println("Vill du sl�ss eller springa iv�g?\n\tSl�(1)\n\tSpring(2)");
					}
				}
				if (fightorrun.equals("2")) {
					currentArea = Areas[25];
					System.out.println(gameStory[currentArea.getId()].getprintStory());
					break;
				}
			} 
						
		} else if (currentArea.getId() == 26 && wolf.d�dad) {
				System.out.println("En d�d varg �r allt som finns h�r.");
				
			}
			
			if (currentArea.getId() == 8) {
				�ventyrare.skada = 25;
			}
			
			if (currentArea.getId() == 27) {
				�ventyrare.skada = 60;
			}
			
			System.out.println("Vart vill du g�?\nNorr(N), S�der(S), V�ster(V), �ster(�)");
			
			//Scanner som tar input p� anv�ndaren.
			String vald = keyboard.next();
			
			//Om Anv�ndaren skriver N
			if (vald.equalsIgnoreCase("N")) {
				nextArea = currentArea.getNorthNeighbourId();
				if (nextArea == 12 && !hasgoldkey || nextArea == 28 && !hasendkey) { 
					System.out.println("Du har inte r�tt nyckel.");
				} else if (nextArea != -1) { 
					currentArea = Areas[nextArea]; 
					System.out.println(gameStory[currentArea.getId()].getprintStory());
					} else {
						System.out.print("Du kan inte g� �t norr\n"); 
					}
				}
			//Om anv�ndaren skriver S				
			if (vald.equalsIgnoreCase("S")) {
					nextArea = currentArea.getSouthNeighbourId(); 
				if(nextArea != -1) { 
						currentArea = Areas[nextArea]; 
						System.out.println(gameStory[currentArea.getId()].getprintStory());
						} else { 
							System.out.print("Du kan inte g� �t s�der\n"); 
				}
			}
					//Om anv�ndaren skiver V					
					if (vald.equalsIgnoreCase("V")) {
						nextArea = currentArea.getWestNeighbourId(); 
						if(nextArea != -1) { 
							currentArea = Areas[nextArea]; 
							System.out.println(gameStory[currentArea.getId()].getprintStory());
							} else { 
								System.out.print("Du kan inte g� �t v�st\n"); 
				}
			}
						//Om anv�ndaren skriver �					
						if (vald.equalsIgnoreCase("�")) {
							nextArea = currentArea.getEastNeighbourId();
							if (nextArea == 20 && !hasbronzekey) {
								System.out.println("Du har inte bronsnyckeln");
							} else if (nextArea != -1) { 
								currentArea = Areas[nextArea]; 
								System.out.println(gameStory[currentArea.getId()].getprintStory());
								} else { 
									System.out.print("Du kan inte g� �t �ster\n"); 
				}
			}
						if (currentArea.getId() == 4) {
							System.out.println("Vill du dricka av vattnet?\n\tJa(1)\n\tNej(2)");
							String dricka = drick.next();
							if (dricka.equals("1")) {
								�ventyrare.hp = 100;
								System.out.println("Du dricker av vattnet och �terst�ller ditt liv till " + �ventyrare.hp + "%");
							}
						}
						
						if (!vald.equalsIgnoreCase("N") && 
								!vald.equalsIgnoreCase("S") && 
								!vald.equalsIgnoreCase("V") && 
								!vald.equalsIgnoreCase("�")) {
						System.out.println("Du m�ste skriva N, S, V eller �");	
			}
						
		}
		
	}
	
}

		

		
	





 
	
			
    

	  
	    	
	
	    
	


