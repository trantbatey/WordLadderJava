

import java.util.*;
import java.io.*;

public class LadderDictionary {
	
	private static ArrayList<String> DictWordList;
	
	public LadderDictionary () {
        
		//Read the dictioanry file
		if (DictWordList == null) {
			Scanner dScan = null; 
			String user = System.getProperty("user.name");
			String DictionaryFile = "data/smallDictionary.txt";
	        try
	        {
	        	dScan = new Scanner(new FileReader(DictionaryFile));
	        }
	        catch (IOException e)
	        {
	            System.out.println("PROBLEM :  the file "+DictionaryFile+" not found.");
	            System.exit(0);
	        }
	            
	        //read the data file
	        DictWordList = new ArrayList<String>();
	        while (dScan.hasNext()) {
	        	String word = dScan.nextLine();
	        	DictWordList.add(word.toLowerCase());
	        }
	        dScan.close();
		}
	}
	
	public LadderList filterByLenght(int length) {
		LadderList newWordList = new LadderList();
		for (String word : DictWordList) {
			if (word.length() == length) newWordList.add(word);
		}
		return newWordList;
	}
}


