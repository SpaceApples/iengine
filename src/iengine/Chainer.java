package iengine;

import java.util.ArrayList;
import java.util.List;

public abstract class Chainer {
	protected List<Variable> variables = new ArrayList<Variable>();
    //contains all variables that are true
    protected List<Variable> trueVars = new ArrayList<Variable>();
    //contains a list of all equations that infer
    protected List<Literal> literals = new ArrayList<Literal>();
    //contains a list of completed equations that infer
    protected List<Literal> solvedLiterals = new ArrayList<Literal>();
    // array of strings for variables proven for printing
    protected List<String> provenVars = new ArrayList<>();
    // contains all variables in a KB with no duplicates
    protected List<Variable> allVars = new ArrayList<>();
    //query to be found
    protected String query;

    // parent class for backwards/forwards chaining
    public Chainer() {}

    // output message for when KB has been solved
    protected abstract void solved(boolean result);

    // loops through KB in attempt to prove query with given chaining method
    public abstract void askQuery();

    public boolean hasTrueVar(String string) {

        boolean result = false;
        for(Variable trueVar : trueVars){
            if(trueVar.getValue() == string)
                result = true;
        }
        return result;
    }

    // allocates KB data into appropriate variables for chaining
    public Variable[] initVariable(String[] vars) {
        Variable[] temp = new Variable[vars.length];
        //foreach variable to be initialised
        for(int v = 0; v < vars.length; v++) {
        	boolean varExist = false;
        	//Does variable exist?
            for(int i = 0; i < variables.size(); i++) {
                if(variables.get(i).getValue().equals(vars[v])) {
                    temp[v] = variables.get(i);
                    varExist = true;
                    break;
                }
            }
            //if Variable doesn't exist create one.
            if(!varExist) {
            	temp[v] = new Variable(vars[v]);
                variables.add(temp[v]);
            }
        }
        return temp;
    }

    public void interpretKB(List<String[]> KB){
        //split the kb
        //stop null pointer error
        if(variables == null || variables.isEmpty()) {
            variables.add(new Variable(KB.get(KB.size()-1)[0]));
        }

        for(int i = 0; i < KB.size(); i++){
            //if array size is 1 it's variable is true
            if(KB.get(i).length == 1) {
                Variable temp = initVariable(KB.get(i))[0];
                //temp.setActive(true); Not useful for the TT
                trueVars.add(temp);
            }
            else {
                //else it's an a&b => x
                Variable[] temp = initVariable(KB.get(i));
                literals.add(new Literal(temp));
            }
        }
    }

}