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
    public void solved() {}

    // loops through KB in attempt to prove query with given chaining method
    @Override
    public boolean askQuery() {
        boolean KBCompleted = false;
        boolean varIsTrue = false;
        List<String> proven = new ArrayList<>();
        Queue<String> toProves = new LinkedList<>(); // Queue of variables to prove
        toProves.add(query);

        while(toProves.size() > 0) {
            String toProve = toProves.remove();
            System.out.println(literals.size());
            for(int e = literals.size()-1; e >= 0; e--) {
                Literal lit = literals.get(e);
                String implicitVal = lit.getImpliedValue();
                Variable[] litImpliers = lit.getImpliers();

                // if value already known to be true...
                if (trueVars.contains(toProve)) {
                    varIsTrue = true;
                    proven.add(toProve); // add proven value to list
                    break;
                }
                else {
                    if(implicitVal == toProve) {

                        // for every implier of given literal...
                        for (int i = 0; i < litImpliers.length; i++) {
                            // add to toProves queue
                            toProves.add(litImpliers[i].getValue());
                        }
                    }
                }
            }
        }
        return varIsTrue;
    }
}

// TT return yes and number of models
// number of models is 2^N (N
// counter for number of valid models
