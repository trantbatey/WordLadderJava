

import java.util.*;
import static java.lang.System.*;

public class WordLadder {
	
	public static void main(String args[])
	{
		// call tree solution
		getShortestLadders();
	}
	
	public static void getShortestLadders()
	{
		String firstWord="pet";
		String lastWord="tin";
		
		// set up dictionary word list
		LadderDictionary ladderList = new LadderDictionary();
		LadderList sourceList = ladderList.filterByLenght(firstWord.length());
		
		// find ladders using a tree
		int numRequested = 10;
		TreeLadder.printFirstXSolutions(numRequested, firstWord, lastWord, sourceList);
		
		// the list should have printed
		out.println("\n\n *** Application Completed *** \n");
		return;
	}
	
	public static void getAllLadders()
	{
		String firstWord="pow";
		String lastWord="fat";
		
		LadderDictionary ladderList = new LadderDictionary();
		LadderList sourceList = ladderList.filterByLenght(firstWord.length());

		// create the result list
		LinkedList<LadderList> resultList = new LinkedList<LadderList>();
		
		// Build the list of valid word ladders
		LadderList currentList = new LadderList();
		currentList.add(firstWord);
		buildLadders(firstWord, lastWord, currentList, sourceList, resultList);

		// sort the resulting list
		Collections.sort(resultList);
		
		// print result
		for (LadderList list : resultList) list.print();
		
		out.println("\n\n *** Application Completed *** \n");
	}
	
	public static LadderList filterByOneOff(
				LadderList list,
				LadderList dictionary)
	{
		LadderList newWordList = new LadderList();
		String previousWord = list.get(list.size()-1);
		for (String word : dictionary) {
			if (list.contains(word)) continue;
			int count = 0;
			for (int i=0; i<word.length(); ++i) {
				if (previousWord.charAt(i) != word.charAt(i)) count++;
				if (count > 1) break;
			}
			if (count == 1) newWordList.add(word);
		}
		return newWordList;
	}

	public static void buildLadders(String currentWord, 
			 String lastWord, 
			 LadderList currentList, 
			 LadderList dictionary,
			 LinkedList<LadderList> resultList)
	{
		if (currentWord.equals(lastWord)) {
			resultList.add(currentList);
			currentList.print();
			return;
		}
		LadderList poppedList = new LadderList();
		ArrayList<String> oneOffList = filterByOneOff(currentList, dictionary);
		for (String nextWord : oneOffList) {
			currentList.add(nextWord);
			poppedList.add(nextWord);
			dictionary.remove(nextWord);
			buildLadders(nextWord, lastWord, currentList, dictionary, resultList);
			dictionary.add(poppedList.get(poppedList.size()-1));
			poppedList.remove(poppedList.size() - 1);
			currentList.remove(currentList.size() - 1);
		}
		return;
	}
}
