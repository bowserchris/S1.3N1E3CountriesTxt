package n1e3ejercicioCountries;
import java.io.*;
import java.util.*;	

public class App {
	
	final static String filePath = "C:\\Users\\userBard64\\Documents\\countries.txt";	//el input del texto countries.txt. esta fuera del main para que lo tiene accesso todo el app
	final static String outputFilePath = "C:\\Users\\userBard64\\Documents\\classificacio.txt"; //espero que esto sea el output del texto classificacion con el nombre y score. esta fuera del main para que lo tiene accesso todo el app

	public static void main(String[] args) {
		
		//creando objeto hashmap con el metodo cojiendo el texto countries.txt
		HashMap<String, String> countriesTxt = HashMapFromTextFile();
		
		System.out.println("Let´s play a game.");
		
		String userName = inputString("What is your name? ");
		int score = 0;
		int counter = 10;
		
		////iterating thru the countries text.
		/*for (HashMap.Entry<String, String> entry : countriesTxt.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());	
		}*/
		
		//un while loop que itera 10 veces y reduciendo cada vez. 
		while (counter > 0) {
			Object[] keyArray = countriesTxt.keySet().toArray();							//coje el texto y lo mete en un array
			Object keyRandom = keyArray[new Random().nextInt(keyArray.length)];				//coje el key al random para saber que pais va a preguntar
			String userCapital = inputString("What is the capital of " + keyRandom + "?");
			counter--;																		//contador reduce por 1
			if (userCapital.equalsIgnoreCase(countriesTxt.get(keyRandom))) {				//compara el valor del array ".get(keyrandom)" y lo compara con el del usuario ignorando si es mayuscula o no, si acierta entra en el for
				score += 1;
				System.out.println("Correct. You have " + counter + " tries left.\r");
			} else {
				System.out.println("Incorrect. You have " + counter + " tries left.\r");
			}
		}
		
		HashMap<String, Integer> classificacioTxt = HashMapToTextFile(userName, score);		//crea un texto de output
		
		System.out.println("The file with your score has been created.\r");

	}
	
	static int inputNumber(String message) {				//request a number input
		Scanner input = new Scanner(System.in);
		System.out.println(message);
		return input.nextInt();
	}
	
	static String inputString(String message) {				//request a string input
		Scanner input = new Scanner(System.in);
		System.out.println(message);
		return input.nextLine();
	}
	
	
	//metodo que recibe el archivo desde fuera del app y lo crea en un hashmap
	public static HashMap<String, String> HashMapFromTextFile() {		//from https://www.geeksforgeeks.org/reading-text-file-into-java-hashmap/
        HashMap<String, String> mapInput = new HashMap<String, String>();
        BufferedReader br = null;
 
        try {
        	
            // create file object
            File file = new File(filePath);
            // create BufferedReader object from the File
            br = new BufferedReader(new FileReader(file));
            String line = null;
            // read file line by line
            while ((line = br.readLine()) != null) {
                // split the line by " "
                String[] parts = line.split(" ");
                // first part is country, second is capital
                String country = parts[0].trim();
                String capital = parts[1].trim();
                // put country, capital in HashMap if they are  not empty
                if (!country.equals("") && !capital.equals("")) {
                	mapInput.put(country, capital);
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
  
            // Always close the BufferedReader
            if (br != null) {
                try {
                    br.close();
                }
                catch (Exception e) {
                }
            }
        }
  
        return mapInput;
    }
	
	//metodo que recibe el nombre del usuario y el total de acertados para luego meterlo en un archivo text
	public static HashMap<String, Integer> HashMapToTextFile(String name, int score) {		//from https://www.geeksforgeeks.org/write-hashmap-to-a-text-file-in-java/
		HashMap<String, Integer> mapOutput = new HashMap<String, Integer>();
		BufferedWriter bw = null;
		
		try {
			
			File file = new File(outputFilePath);
			bw = new BufferedWriter( new FileWriter(file));
			bw.write("Name: " + name + " Score: " + score);
	        /*for (Map.Entry<String, Integer> entry : mapOutput.entrySet()) {	
	        	bw.newLine();
	        }*/
	        bw.flush();
	        
		} catch (IOException e) {
			e.printStackTrace();
	    } finally {
	    	try {
	    		bw.close();
	    	} catch (Exception e) {
	        }
	    }
		return mapOutput;
	}

	
}
/*- Exercise 3

Given the file countries.txt (check the resources section) that contains countries and capitals. 
The program must read the file and store the data in a HashMap<String, String>. The program asks 
for the name of the user, and then it must show a random country, saved in the HashMap. It is about 
what the user has to write the name of the capital of the country in question. If you get it right, 
you get a point. This action is repeated 10 times. Once the capitals of 10 countries have been requested
randomly, then the name of the user and their score must be saved in a file called classificacio.txt.
 * 
 * 
 * Albania Tirana
Andorra Andorra_la_Vella
Armenia Ereván
Austria Vienna
Azerbaijan Baku
Belarus Minsk
Belgium Brussels
Bosnia_and_Herzegovina Sarajevo
Bulgaria Sofia
Croatia Zagreb
Cyprus Nicosia
Czechia Prague
Denmark Copenhagen
Estonia Tallin
Finland Helsinki
France Paris
Georgia Atlanta	//Tbilisi es el capital del pais, esto es el capital del estado en USA
Germany Berlin
Greece Athens
Hungary Budapest
Iceland Reikiavik
Ireland Dublin
Italy Rome
Kazakhstan Nur-Sultan
Kosovo Pristina
Latvia Riga
Liechtenstein Vaduz
Lithuania Vilnius
Luxembourg Luxembourg  
Malta Valletta
Moldova Chisinau
Monaco Monte_Carlo
Montenegro Podgorica
Netherlands Amsterdam
North_Macedonia Skpje
Norway Oslo
Poland Warsaw
Portugal Lisbon
Romania Bucharest
Russia Moscow
San_Marino San_Marino
Serbia Belgrade
Slovakia Bratislava
Slovenia Ljubljana
Spain Madrid
Sweden Stockholm
Switzerland Bern 
Turkey Ankara 
Ukraine Kyiv
United_Kingdom London
Vatican_City Vatican_City
 * 
 * 
 * 
 */

