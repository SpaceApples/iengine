package iengine;

import java.util.ArrayList;
import java.util.List;

public class ForwardChaining extends Chainer {

	ForwardChaining(List<String[]> KB, String _query){
		query = _query;
		interpretKB(KB);
	}

	@Override
	public void solved(){
		String answer = "";
		for(Variable v : trueVars) {
			answer += v.getValue() + " ";
		}
		System.out.println("YES: " + answer);
	}

	@Override
	public boolean askQuery() {
		//track whether the KB cannot be completed any further
		boolean KBCompleted = false;
		//is the equation true?
		boolean varIsTrue = false;
		while(!KBCompleted) {
			KBCompleted = true;
			//foreach equation
			for(int e = 0; e < literals.size(); e++) {
				//literal to be check if true or not
				Literal lit = literals.get(e);
				//foreach variable in equation excluding the inferred
				for(int i = 0; i < lit.getImpliers().length; i++) {
					if(trueVars.contains(lit.getImpliers()[i])) {
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
					// add the inferred to true variables
					trueVars.add(lit.getImplied());
					//is the equation the query?
					if(lit.getImpliedValue().equals(query)) {
						solved();
						return varIsTrue;
					}
					//add equation to solved
					solvedLiterals.add(literals.get(e));
					//remove equation from the list because it's solved
					literals.remove(literals.get(e));
					//KB hasn't been solved
					KBCompleted = false;
				}
			}
		}
		//KB could not be completed any further so query is false
		System.out.println("Query " + query + " is false");
		return varIsTrue;
	}

}