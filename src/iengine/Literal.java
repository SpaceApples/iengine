package iengine;


public class Literal {

    private Variable[] impliers; // list of variables implying variable
    private Variable implied; // implied variable
    private boolean isTrue = false;

    public Literal(Variable[] vars) {
        impliers = new Variable[vars.length-1];
        for(int i = 0; i < vars.length-1; i++) {
            impliers[i] = vars[i];
        }
        implied = vars[vars.length-1];
    }

    public boolean literalTrue() {
        for(Variable v : impliers) {
            if(v.getActive() == false) {
                return false;
            }
        }
        return true;
    }

    public Variable getImplied() {
        return implied;
    }

    public String getImpliedValue() {
        return implied.getValue();
    }

    public Variable[] getImpliers() {
        return impliers;
    }

    public Variable getImpliersAt(int index) {
        return impliers[index];
    }

    public String getImpliersValueAt(int index) {
        return impliers[index].getValue();
    }
    
    public void setIsTrue(boolean _isTrue) {
    	isTrue = _isTrue;
    }
    
    public boolean getIsTrue() {
    	return isTrue;
    }
}
