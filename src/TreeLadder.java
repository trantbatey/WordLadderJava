
// This is a tree structure to help find the shortest word ladder.
import java.util.*;
import static java.lang.System.*;

public class TreeLadder {

	String word;
	ArrayList<TreeLadder> nextLevel;
	ArrayList<String> myDictionary;
	LinkedList<TreeLadder> leafs;
	TreeLadder previous;
	int level;
	
	static int numFound;
	static int numRequested;
	static String endWord;
		
	private TreeLadder(String newWord, TreeLadder prevNode, 
			int prevLevel, ArrayList<String> prevDictionary) {
		word = newWord;
		previous = prevNode;
		level = prevLevel+1;
		nextLevel = new ArrayList<TreeLadder>();
		leafs = new LinkedList<TreeLadder>();
		myDictionary = new ArrayList<String>(prevDictionary);
		myDictionary.remove(word);
	}
	
	private void parseTree()
	{
		int level = 1;
		while (!this.parseLevelX(level)) level++;
		return;
	}
	
	private Boolean parseLevelX(int levelX)
	{
		// find level
		if (this.level < levelX) {
			for (TreeLadder tree : nextLevel) {
				if (tree.parseLevelX(levelX)) return true;
			}
			return false;
		}

		// build next level list
		ArrayList<String>nextLevelList = new ArrayList<String>();
		for (String dWord : myDictionary) {
			int count = 0;
			for (int i=0; i<dWord.length(); ++i) {
				if (word.charAt(i) != dWord.charAt(i)) count++;
				if (count > 1) break;
			}
			if (count == 1) nextLevelList.add(dWord);
		}
		
		// add each word to tree and test if it is a solution
		// when number of solutions is found, stop
		for (String newWord : nextLevelList) {
			TreeLadder leaf = new TreeLadder(newWord, this, level, myDictionary);
			nextLevel.add(leaf);
			if (leaf.isCompleteLadder(endWord)) numFound++;
			if (numFound == numRequested) return true;
		}
		return false;
	}
	
	public void printLadder (LinkedList<TreeLadder> list)
	{
		for (TreeLadder node : list) {
			if (node == this) {
				out.print(node.word);
				break;
			}
			else out.print(node.word+" -> ");
			if (node.level%5==0) out.println();			
		}
		out.println("\n\n");
	}
	
	private LinkedList<TreeLadder> addPrevNode (
			TreeLadder node, 
			LinkedList<TreeLadder> list)
	{
		list.add(0,node);
		if (node.previous == null) return list;
		return addPrevNode(node.previous, list);
	}
	
	private LinkedList<TreeLadder> buildLinkedLatter ()
	{
		LinkedList<TreeLadder> list = new LinkedList<TreeLadder>();
		list.add(0,this);
		if (this.previous == null) return list;
		return addPrevNode(previous, list);
	}
	
	private Boolean isCompleteLadder (String endWord)
	{
		if (word.equals(endWord)) {
			out.println("Ladder #"+(numFound+1)+" is "+level+" levels deep");
			LinkedList<TreeLadder> list = buildLinkedLatter();
			printLadder(list);
			return true;
		}
		return false;
	}
	
	public static void printFirstXSolutions(
			int numLaddersRequested, String firstWord, 
			String lastWord, ArrayList<String> allWords) {
		numFound = 0;
		numRequested = numLaddersRequested;
		endWord = lastWord;
		int prevLevel=0;
		TreeLadder prevLeaf = null;
		
		// State goal for console
		out.println("Find the first "+numLaddersRequested+" word long "
				   +"word ladders from \""+firstWord+"\" to \""
				   +lastWord+"\".\n\n");
		
		// Create the tree
		TreeLadder tree = 
				new TreeLadder(firstWord, prevLeaf, prevLevel, allWords);
		tree.parseTree();
		return;
	}
}
