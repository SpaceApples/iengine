package iengine;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

public class BackwardChaining extends Chainer {

//    //contains all variables that are true
//    protected List<Variable> trueVar = new ArrayList<Variable>();
//    //contains a list of all equations that infer
//    protected List<Literal> literals = new ArrayList<Literal>();
//    //contains a list of completed equations that infer
//    protected List<Literal> solvedLiterals = new ArrayList<Literal>();
//    //query to be found
//    protected String query;

    public BackwardChaining(List<String[]> KB, String _query) {
        query = _query;
        interpretKB(KB);
    }

    // output message for when KB has been solved
    @Override
    public void solved(boolean result) {
        if (result) {
            // true result
        }
        else {
            // false message
        }
    }

    // loops through KB in attempt to prove query with given chaining method
    @Override
    public void askQuery() {

        boolean KBCompleted = false;
        boolean varIsTrue = false;
        List<String> proven = new ArrayList<>();
        Queue<String> toProves = new LinkedList<>(); // Queue of variables to prove
        toProves.add(query);

        while(toProves.size() > 0) {
            String toProve = toProves.remove();
            //System.out.println(literals.size());
            for(int i = literals.size()-1; i >= 0; i--) {
                Literal lit = literals.get(i);
                String implicitVal = lit.getImpliedValue();
                Variable[] litImpliers = lit.getImpliers();

                // if value already known to be true...
                if (this.hasTrueVar(toProve)) {
                    varIsTrue = true;
                    //System.out.println(toProve);
                    proven.add(toProve); // add proven value to list
                    break;
                }
                else {
                    if(implicitVal.equals(toProve)) {

                        varIsTrue = true;
                        proven.add(toProve);
                        // for every implier of given literal...
                        for (int k = 0; k < litImpliers.length; k++) {
                            // add to toProves queue
                            toProves.add(litImpliers[k].getValue());
                        }
                        break;
                    }
                }
            }
        }
        solved(varIsTrue);
        //for (int i = 0; i < proven.size(); i++)
        //    System.out.println(proven.get(i));
    }
}

// TT return yes and number of models
// number of models is 2^N (N
// counter for number of valid models
