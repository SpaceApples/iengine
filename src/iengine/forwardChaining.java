package iengine;

import java.util.ArrayList;
import java.util.List;

public class forwardChaining {
	List<String> trueVar = new ArrayList<String>();
	List<String[]> equations = new ArrayList<String[]>();
	List<String[]> solvedEquations = new ArrayList<String[]>();
	String query;
	
	forwardChaining(List<String[]> KB, String _query){
		query = _query;
		tellKB(KB);
	}
	
	void solved(){
		System.out.println("Query " + query + " is true");
	}
	
	void askQuery() {
		boolean KBCompleted = false;
		boolean varIsTrue = false;
		while(!KBCompleted) {
			KBCompleted = true;
			for(String[] e : equations) {
				for(int i = 0; i < e.length-1; i++) {
					if(trueVar.contains(e[i])) {
						varIsTrue = true;
					} else {
						varIsTrue = false;
						break;
					}
				}
				if(varIsTrue) {
					if(e[e.length-1] == query) {
						solved();
					}
					trueVar.add(e[e.length-1]);
					solvedEquations.add(e);
					equations.remove(e);
					KBCompleted = false;
				}
			}
		}
		System.out.println("Query " + query + " is false");
	}
	
	void tellKB(List<String[]> KB){
		for(String[] s : KB){
			if(s.length == 1) {
				trueVar.add(s[0]);
			} 
			else {
				equations.add(s);
			}
		}
	}
	
}