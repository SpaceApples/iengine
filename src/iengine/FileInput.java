package iengine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileInput{
	//stores contents of txt file
	List<String[]> KB = new ArrayList<String[]>();
	String query = "";
	
	//constructor
	public FileInput(String filePath){
		readMap(filePath);
	}
	
	void termSplitter(String line) {
		//split string using delimiters special characters require
		String[] strArray = line.split(";");
		for(int i = 0; i < strArray.length; i++) {
			String[] term = strArray[i].replaceAll("\\s+", "").split("=>|&");
			KB.add(term);
		}
		
	}
	
	void readMap(String filePath) {
		try {
			String line = null;
			
			//file reader
			FileReader reader = new FileReader(filePath);
			
			//buffered reader
			BufferedReader buffered = new BufferedReader(reader);

			//read-in lines
            line = buffered.readLine();
			
			//removing white spaces and comparing to "TELL"
            if (line.replaceAll("\\s+", "").equals("TELL")){
			    line = buffered.readLine();
            	do {
				    termSplitter(line);
				    line = buffered.readLine();
			    } while (!line.replaceAll("\\s+", "").equals("ASK"));
			    
			    query = buffered.readLine();
            	
			} else {
			    	System.out.println("Wrong file format");
            }
			
            //print terms
            for(int y = 0; y < KB.size(); y++) {
            	for(int x = 0; x < KB.get(y).length; x++) {
            		System.out.print(KB.get(y)[x] + " ");
            	}
            	System.out.println("");
            }
            
            //print queries
            System.out.println("Query: " + query);
            
			//close buffered reader	
			buffered.close();
		}
		catch(IOException ex) {
			System.out.println("Error reading file");
		}
	}
	
	//returns Horn Knowledge base
	List<String[]> getKB(){
		return KB;
	}
}