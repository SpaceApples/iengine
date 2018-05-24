package iengine;

import java.util.ArrayList;
import java.util.List;

public class TruthTable extends Chainer{
	//count of TT rows where query is true
	private int models = 0;
    boolean isAnyRowTrue = false;
    boolean isQueryTrue = false;


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

	// TT Version 1

	@Override
	public void askQuery() {
		//recursive construction of the TT
		recursiveConstructTT(literals, variables, trueVars, 0);
		//if Query is true
		if(isQueryTrue) {
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
						isQueryTrue = true;
					}
				}
			}
		}
	}

	// TT Version 2

    public void askQuery2() {
        truthTable(literals, variables, trueVars, 0);
    }


    private boolean calcTTLiteral(Literal lit, List<Variable> vars) {
        //calculates if a given literal is true based on TT row data
        Variable[] impliers = lit.getImpliers();
        Variable implied = lit.getImplied();
        boolean isImpliers = true;
        boolean isImplied = true;

        // set impliers to TT row values
        for(Variable imp : impliers) {
            for (Variable v: vars){
                // if variable found, set boolean to TT row equiv
                if(imp.getValue().equals(v.getValue())) {
                    imp.setActive(v.getActive());
                }

            }
            if (imp.getActive() == false) // AND booleans together
                isImpliers = false;
        }
        // set implied to TT row value
        for (Variable v: vars){
            if(implied.getValue().equals(query) && isImpliers)
                isQueryTrue = true;
            // if variable found, set boolean to TT row equiv
            if(implied.getValue().equals(v.getValue())) {
                implied.setActive(v.getActive());
            }
        }
        if (implied.getActive() == false) // AND booleans together
            isImplied = false;
        // return literal boolean value
        boolean checker = (isImpliers == isImplied);
        return (isImpliers == isImplied);
    }

    private boolean calcTTVariable(Variable var, List<Variable> vars) {
        for (Variable v : vars) {
            if (v.getValue().equals(var.getValue()))
                var.setActive(v.getActive());
        }
        // return variable boolean value
        return (var.getActive());
    }

    private void truthTable(List<Literal> _literals, List<Variable> _variables, List<Variable> _trueVars, int n) {

        int varNum = _variables.size();
        int ttRows = (int)Math.pow(2, varNum) - 1;

        for(int i = 0; i <= ttRows; i++) {

            boolean isLiterals = true;
            boolean isTrueVars = true;
            isQueryTrue = false;

            // create binary string for enumeration of variables
            String[] rowBinary = Integer.toBinaryString(i).split("");
            List<String> binaryVals = new ArrayList<>();

            // copy values into ArrayList
            for (String binary : rowBinary) {
                binaryVals.add(binary);
            }

            // leftpad with 0's if number of bits not equal to varNum
            while(binaryVals.size() != varNum) {
                binaryVals.add(0, "0");
            }

            for (int j = 0; j < binaryVals.size(); j++) {
                if (binaryVals.get(j).equals("1"))
                    _variables.get(j).setActive(true);
                if (binaryVals.get(j).equals("0"))
                    _variables.get(j).setActive(false);
            }

            // AND all literals together
            for (Literal lit : _literals) {
                if (!calcTTLiteral(lit, _variables))
                    isLiterals = false;
            }

            // AND all single variables
            for (Variable v : _trueVars) {
                if (!calcTTVariable(v, _variables))
                    isTrueVars = false;
            }

            if (isLiterals && isTrueVars) {
                models += 1;
                isAnyRowTrue = true;
            }
        }
        solved(isQueryTrue);
    }
}