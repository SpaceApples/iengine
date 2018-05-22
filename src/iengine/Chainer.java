package iengine;

import java.util.ArrayList;
import java.util.List;

public abstract class Chainer {
    //contains all variables that are true
    protected List<Variable> trueVars = new ArrayList<Variable>();
    //contains a list of all equations that infer
    protected List<Literal> literals = new ArrayList<Literal>();
    //contains a list of completed equations that infer
    protected List<Literal> solvedLiterals = new ArrayList<Literal>();
    //query to be found
    protected String query;

    // parent class for backwards/forwards chaining
    public Chainer() {}

    // output message for when KB has been solved
    protected abstract void solved();

    // loops through KB in attempt to prove query with given chaining method
    public abstract boolean askQuery();

    // allocates KB data into appropriate variables for chaining
    public Variable[] initVariable(String[] vars) {
        Variable[] temp = new Variable[vars.length];
        for(int v = 0; v < vars.length; v++) {
            for(int i = 0; i < trueVars.size(); i++) {
                if(trueVars.get(i).getValue() == vars[v]) {
                    temp[v] = trueVars.get(i);
                }
                else {
                    temp[v] = new Variable(vars[v]);
                }
            }
        }
        return temp;
    }

    public void interpretKB(List<String[]> KB){
        //split the kb
        //stop null pointer error
        if(trueVars == null || trueVars.isEmpty()) {
            trueVars.add(new Variable(KB.get(KB.size()-1)[0]));
        }
        for(int i = KB.size()-2; i > 0; i--){
            //if array size is 1 it's variable is true
            if(KB.get(i).length == 1) {
                Variable temp = initVariable(KB.get(i))[0];
                temp.setActive(true);
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