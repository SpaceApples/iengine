package iengine;

import java.util.ArrayList;
import java.util.List;

public class ForwardChaining {
	//contains all variables that are true
	List<String> trueVar = new ArrayList<String>();
	//contains a list of all equations that infer
	List<String[]> equations = new ArrayList<String[]>();
	//contains a list of completed equations that infer
	List<String[]> solvedEquations = new ArrayList<String[]>();
	//query to be found
	String query;
	
	ForwardChaining(List<String[]> KB, String _query){
		query = _query;
		tellKB(KB);
	}
	
	void solved(){
		System.out.println("Query " + query + " is true");
	}
	
	void askQuery() {
		//track whether the KB cannot be completed any further
		boolean KBCompleted = false;
		//is the equation true?
		boolean varIsTrue = false;
		while(!KBCompleted) {
			KBCompleted = true;
			//foreach equation
			for(String[] e : equations) {
				//foreach variable in equation excluding the inferred
				for(int i = 0; i < e.length-1; i++) {
					if(trueVar.contains(e[i])) {
						//if variable is true
						varIsTrue = true;
					} else {
						//if variable is not true, equation cannot be inferred exit for loop
						varIsTrue = false;
						break;
					}
				}
				//if the equation is true
				if(varIsTrue) {
					//is the equation the query?
					if(e[e.length-1] == query) {
						solved();
					}
					// add the inferred to true variables
					trueVar.add(e[e.length-1]);
					//add equation to solved
					solvedEquations.add(e);
					//remove equation from the list because it's solved
					equations.remove(e);
					//KB hasn't been solved
					KBCompleted = false;
				}
			}
		}
		//KB could not be completed any further so query is false
		System.out.println("Query " + query + " is false");
	}
	
	void tellKB(List<String[]> KB){
		//split the kb 
		for(String[] s : KB){
			//if array size is 1 it's the clause is true
			if(s.length == 1) {
				trueVar.add(s[0]);
			} 
			else {
				//else it's an a&b => x
				equations.add(s);
			}
		}
	}
	
}