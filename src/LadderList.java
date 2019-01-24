

import java.util.*;
import static java.lang.System.*;

public class LadderList extends ArrayList<String> implements Comparable<LadderList>  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LadderList (ArrayList<String> sourceList) {
		for (String word : sourceList) this.add(word);
	}

	public LadderList () {
		super();
	}

	@Override
	public int compareTo(LadderList l) {
		int comparedSize = l.size();
		if (this.size() > comparedSize) {
			return 1;
		} else if (this.size() == comparedSize) {
			return 0;
		} else {
			return -1;
		}
	}
	
	public void print() {
		int i=0;
		for (String word : this) {
			out.print(word); ++i;
			if (i < this.size()) out.print(" -> ");
			else out.println("\n\n");
			if (i%5==0) out.println();
		}
	}
}
