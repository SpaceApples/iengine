package iengine;


public class Literal {

    private Variable[] implies; // list of variables implying variable
    private Variable implied; // implied variable

    // ttValue - truth table TBD

    public Literal(Variable[] vars) {
        implies = new Variable[vars.length-1];
    	for(int i = 0; i < vars.length-1; i++) {
            implies[i] = vars[i];
        }
        implied = vars[vars.length-1];
    }
    
    public boolean literalTrue() {
    	for(Variable v : implies) {
    		if(v.getActive() == false) {
    			return false;
    		}
    	}
    	return true;
    }
    
    public Variable getImplied() {
    	return implied;
    }
    
    public Variable[] getImplies() {
    	return implies;
    }
}
