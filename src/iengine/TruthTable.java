package iengine;

import java.util.List;

public class TruthTable extends Chainer{
	//count of TT rows where query is true
	int models = 0;

	
	TruthTable(List<String[]> KB, String _query){
		query = _query;
		interpretKB(KB);
	}

	@Override
	protected void solved(boolean result) {
		if(result) {
			System.out.println("YES: " + models);
		}
		else {
			System.out.println("NO");
		}
	}

	@Override
	public void askQuery() {
		//recursive construction of the TT
		recursiveConstructTT(literals, variables, trueVars, 0);
		//if Query is true
		if(models > 0) {
			solved(true);
		}
		else {
			solved(false);
		}
		
	}
	
	private boolean checkTTStatement(Literal lit) {
		//checks to see if the literal is true
		//if left side is true
		for(int i = 0; i < lit.getImpliers().length; i++) {
			if(lit.getImpliersAt(i).getActive() == false) {
				return true;
			}
		}
		// but right side if false then literal is invalid
		if(lit.getImplied().getActive() == false) {
			return false;
		}
		return true;
	}
	
	private void recursiveConstructTT(List<Literal> _literals, List<Variable> _variables, List<Variable> _trueVars, int n) {
		//if current we have reached the end of the current row
		if(n < _variables.size()) {
			_variables.get(n).setActive(true);
			
			//set literals to valid or not
			for(Literal lit : _literals) {
				if (checkTTStatement(lit)) {
					lit.setIsTrue(true);
				} else {
					lit.setIsTrue(false);
				}
			}
			
			//recursive construction of the TT(new row)
			recursiveConstructTT(_literals, _variables, _trueVars, n+1);
			
			_variables.get(n).setActive(false);
			
			//set literals to valid or not
			for(Literal lit : _literals) {
				if(checkTTStatement(lit)) {
					lit.setIsTrue(true);
				}
				else {
					lit.setIsTrue(false);
				}
			}
			
			//recursive construction of the TT(new row)
			recursiveConstructTT(_literals, _variables, _trueVars, n+1);
		}
		else {
			//checks if the TT row constructed is valid or not
			boolean TTValid = true;
			
			//is literal valid
			for(Literal lit : _literals) {
				if(!lit.getIsTrue()) {
					TTValid = false;
				}
			}
			
			//are the true variables in TT set to true
			for(Variable var : _trueVars) {
				if(!var.getActive()) {
					TTValid = false;
				}
			}
			
			//is the query true
			if(TTValid) {
				for(Variable var : _variables) {
					if((var.getValue().equals(query)) && (var.getActive())) {
						//if so +1 possibility of query being true
						models += 1;
					}
				}
			}
		}
	}
}


