package iengine;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

public class BackwardChaining extends Chainer {

    public BackwardChaining(List<String[]> KB, String _query) {
        query = _query;
        interpretKB(KB);
    }

    // output message for when KB has been solved
    @Override
    public void solved(boolean result) {
        String answer = "";
        if (result) {
            // true result
            for(int i = provenVars.size() - 1; i >= 0; i--) {
                answer+= provenVars.get(i) + " ";
            }
            System.out.println("YES: " + answer);
        }
        else {
            // false message
            System.out.println("NO: " + query + " is false.");
        }
    }

    // loops through KB in attempt to prove query with given chaining method
    @Override
    public void askQuery() {

        boolean KBCompleted = false;
        boolean varIsTrue = false;

        Queue<String> toProves = new LinkedList<>(); // Queue of variables to prove
        toProves.add(query); // add query as first in queue

        while(toProves.size() > 0) {
            String toProve = toProves.remove(); // get next variable to prove from queue
            for(int i = literals.size()-1; i >= 0; i--) {
                // for every literal in list...
                Literal lit = literals.get(i);
                String implicitVal = lit.getImpliedValue(); // store implied
                Variable[] litImpliers = lit.getImpliers(); // store impliers

                // if variable already known to be true...
                if (this.hasTrueVar(toProve)) {
                    varIsTrue = true;
                    provenVars.add(toProve); // add provenVars value to list
                    break;
                }
                else {
                    if(implicitVal.equals(toProve)) {
                        varIsTrue = true;
                        provenVars.add(toProve);
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
        // print result
        solved(varIsTrue);
    }
}

// TT return yes and number of models
// number of models is 2^N (N
// counter for number of valid models
