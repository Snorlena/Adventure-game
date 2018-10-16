import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;

public class Adventure {

	
	
	public static void main(String[] args) throws IOException {
		
		Spelare Äventyrare = new Spelare();
		Äventyrare.hp = 100;
		Äventyrare.skada = 5;
		
		Björn bear = new Björn();
		bear.hp = 100;
		bear.namn = "Björn";
		bear.dödad = false;
		bear.skada = 20;
		
		Varg wolf = new Varg();
		wolf.hp = 100;
		wolf.dödad = false;
		wolf.namn = "Varg";
		wolf.skada = 50;
		
		
		PrintWriter keys = new PrintWriter
						(new BufferedWriter
						(new FileWriter("Keys.txt")));
		Scanner hasKeys = new Scanner(new File("Keys.txt")); //Scanner för att se om man har rätt nyckel.
		Scanner areas = new Scanner(new File("Areas.txt"));  //Läser in filen Areas.txt för kordinater.
		Scanner keyboard = new Scanner(System.in);			 //Scanner för att gå.
		Scanner story = new Scanner(new File("Story.txt"));  //Scanner för att läsa in Story.
		Scanner fight = new Scanner(System.in);				 //Scanner för fighter. 
		Scanner drick = new Scanner(System.in);				 //Scanner för att dricka ur bäcken.
		
		String[] area = new String[34];						
		Area[] Areas = new Area[34];
		
		String[] storY = new String[40];
		Story[] gameStory = new Story[40];
		
		String hasGoldenKey;
		
		int n = 0;
				
		while (areas.hasNextLine()) {						//Så länge filen har en ny rad så fortsätter loopen.
			area[n] = areas.nextLine();						//Läser in en rad från textfilen och lägger den i an array.
		
		String[] splits = area[n].split(" ");				//Splittar upp arrayen i flera olika delar så att vi kan ta ut värden för alla neighbours.
			
		List<String> väderstreck = Arrays.asList(splits);
					
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
		
		Area currentArea = Areas[0];  //Sätter startzon till Areas[0]
		
		//Här har jag valt att lägga storyn i en egen array för att lättare kunna hanterna storyn.
		//Jag har också med hjälp av detta fått bort tidigare fel där samma areatext kom upp igen om man gick åt ett felaktigt håll.
		System.out.println(gameStory[currentArea.getId()].getprintStory());
		
		boolean hasgoldkey = false;
		boolean hasbronzekey = false;
		boolean hasendkey = false;		
				
		//Loop som håller koll på Zon och vilken text som ska skrivas ut.
		while (true) {
					
			//Sätter nextArea till 0, som ska vara startzonen.
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
			
			//Om man står på area 17 och björnen lever
			if (currentArea.getId() == 17 && !bear.dödad) {
				System.out.println("Du möter en stor björn. Vill du slåss eller springa iväg?\n\tSlå(1)\n\tSpring(2)");
				while (true) {
				String fightorrun = fight.next();
				if (fightorrun.equals("1")) {
					System.out.println("Du slår björnen och gör " + Äventyrare.skada + " i skada");
					bear.hp -= Äventyrare.skada;
					System.out.println("Björnen har " + bear.hp + "% liv kvar");
				if (bear.hp > 0) {
					System.out.println("Björnen slår tillbaka och gör " + bear.skada + " skada på dig");
					Äventyrare.hp -= bear.skada;
					System.out.println("Du har " + Äventyrare.hp +"% liv kvar");
			}
				if (Äventyrare.hp <= 0) {
						System.out.println("Du slogs tappert, men du var för vek och dog en smärtsam död.\n\nSpelet är slut.");
						System.exit(0);
					}
				if (bear.hp <= 0) {
						bear.dödad = true;
						hasbronzekey = true;
						System.out.println("Du slog ihjäl björnen och hittade en nyckel.");
						break;
						
			} else {
				System.out.println("Vill du slåss eller springa iväg?\n\tSlå(1)\n\tSpring(2)");
		}
	}
				if (fightorrun.equals("2")) {
					currentArea = Areas[16];
					System.out.println(gameStory[currentArea.getId()].getprintStory());
					break;
			}
		} 
			} else if (currentArea.getId() == 17 && bear.dödad) {
				System.out.println("En död björn är allt som finns här.");
		}
			
			//Om man står på area 26 och Vargen lever
			if (currentArea.getId() == 26 && !wolf.dödad) {
				System.out.println("Du möter en ilsken Varg. Vill du slåss eller springa iväg?\n\tSlå(1)\n\tSpring(2)");
				while (true) {
				String fightorrun = fight.next();
				if (fightorrun.equals("1")) {
					System.out.println("Du slår Vargen och gör " + Äventyrare.skada + " i skada");
					wolf.hp -= Äventyrare.skada;
				if(wolf.hp <= 0) {
					wolf.hp = 0;
				}
					System.out.println("Vargen har " + wolf.hp + "% liv kvar");
				if (wolf.hp >= 0) {
					System.out.println("Vargen biter dig och gör " + wolf.skada + " skada på dig");
					Äventyrare.hp -= wolf.skada;
				if (Äventyrare.hp < 0) {
					Äventyrare.hp = 0;
				}
					System.out.println("Du har " + Äventyrare.hp +"% liv kvar");
				}
					if (Äventyrare.hp <= 0) {
						System.out.println("Du slogs tappert, men du var för vek och dog en smärtsam död.\n\nSpelet är slut.");
						System.exit(0);
					}
					if (wolf.hp <= 0) {
						wolf.hp = 0;
						wolf.dödad = true;
						hasendkey = true;
						System.out.println("Du slog ihjäl vargen och hittar sista nyckeln.");
						break;
					} else {
				System.out.println("Vill du slåss eller springa iväg?\n\tSlå(1)\n\tSpring(2)");
					}
				}
				if (fightorrun.equals("2")) {
					currentArea = Areas[25];
					System.out.println(gameStory[currentArea.getId()].getprintStory());
					break;
				}
			} 
						
		} else if (currentArea.getId() == 26 && wolf.dödad) {
				System.out.println("En död varg är allt som finns här.");
				
			}
			
			if (currentArea.getId() == 8) {
				Äventyrare.skada = 25;
			}
			
			if (currentArea.getId() == 27) {
				Äventyrare.skada = 60;
			}
			
			System.out.println("Vart vill du gå?\nNorr(N), Söder(S), Väster(V), Öster(Ö)");
			
			//Scanner som tar input på användaren.
			String vald = keyboard.next();
			
			//Om Användaren skriver N
			if (vald.equalsIgnoreCase("N")) {
				nextArea = currentArea.getNorthNeighbourId();
				if (nextArea == 12 && !hasgoldkey || nextArea == 28 && !hasendkey) { 
					System.out.println("Du har inte rätt nyckel.");
				} else if (nextArea != -1) { 
					currentArea = Areas[nextArea]; 
					System.out.println(gameStory[currentArea.getId()].getprintStory());
					} else {
						System.out.print("Du kan inte gå åt norr\n"); 
					}
				}
			//Om användaren skriver S				
			if (vald.equalsIgnoreCase("S")) {
					nextArea = currentArea.getSouthNeighbourId(); 
				if(nextArea != -1) { 
						currentArea = Areas[nextArea]; 
						System.out.println(gameStory[currentArea.getId()].getprintStory());
						} else { 
							System.out.print("Du kan inte gå åt söder\n"); 
				}
			}
					//Om användaren skiver V					
					if (vald.equalsIgnoreCase("V")) {
						nextArea = currentArea.getWestNeighbourId(); 
						if(nextArea != -1) { 
							currentArea = Areas[nextArea]; 
							System.out.println(gameStory[currentArea.getId()].getprintStory());
							} else { 
								System.out.print("Du kan inte gå åt väst\n"); 
				}
			}
						//Om användaren skriver Ö					
						if (vald.equalsIgnoreCase("Ö")) {
							nextArea = currentArea.getEastNeighbourId();
							if (nextArea == 20 && !hasbronzekey) {
								System.out.println("Du har inte bronsnyckeln");
							} else if (nextArea != -1) { 
								currentArea = Areas[nextArea]; 
								System.out.println(gameStory[currentArea.getId()].getprintStory());
								} else { 
									System.out.print("Du kan inte gå åt öster\n"); 
				}
			}
						if (currentArea.getId() == 4) {
							System.out.println("Vill du dricka av vattnet?\n\tJa(1)\n\tNej(2)");
							String dricka = drick.next();
							if (dricka.equals("1")) {
								Äventyrare.hp = 100;
								System.out.println("Du dricker av vattnet och återställer ditt liv till " + Äventyrare.hp + "%");
							}
						}
						
						if (!vald.equalsIgnoreCase("N") && 
								!vald.equalsIgnoreCase("S") && 
								!vald.equalsIgnoreCase("V") && 
								!vald.equalsIgnoreCase("Ö")) {
						System.out.println("Du måste skriva N, S, V eller Ö");	
			}
						
		}
		
	}
	
}

		

		
	





 
	
			
    

	  
	    	
	
	    
	


