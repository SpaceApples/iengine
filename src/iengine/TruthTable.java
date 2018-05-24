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
        System.out.println("YES: " + models + result);
	}

	@Override
	public void askQuery() {
		recursiveConstructTT(literals, variables, trueVars, 0);
		if(models > 0) {
			solved(true);
		}
		else {
			solved(false);
		}
		
	}
	
	private boolean checkTTStatement(Literal lit) {
		boolean check = true;
		for(int i = 0; i < lit.getImpliers().length; i++) {
			if(lit.getImpliersAt(i).getActive() == false) {
				return true;
			}
		}
		if(lit.getImplied().getActive() == false) {
			return false;
		}
		return true;
	}
	
	private void recursiveConstructTT(List<Literal> _literals, List<Variable> _variables, List<Variable> _trueVars, int n) {
		if(n < _variables.size()) {
			_variables.get(n).setActive(true);
			
			for(Literal lit : _literals) {
				if (checkTTStatement(lit)) {
					lit.setIsTrue(true);
				} else {
					lit.setIsTrue(false);
				}
			}
			
			recursiveConstructTT(_literals, _variables, _trueVars, n+1);
			
			_variables.get(n).setActive(false);
			
			for(Literal lit : _literals) {
				if(checkTTStatement(lit)) {
					lit.setIsTrue(true);
				}
				else {
					lit.setIsTrue(false);
				}
			}
			
			recursiveConstructTT(_literals, _variables, _trueVars, n+1);
		}
		else {
			boolean TTValid = true;
			
			for(Literal lit : _literals) {
				if(!lit.getIsTrue()) {
					TTValid = false;
				}
			}
			
			for(Variable var : _trueVars) {
				if(!var.getActive()) {
					TTValid = false;
				}
			}
			
			if(TTValid) {
				for(Variable var : _variables) {
					if((var.getValue().equals(query)) && (var.getActive())) {
						models += 1;
					}
				}
			}
		}
	}
}


