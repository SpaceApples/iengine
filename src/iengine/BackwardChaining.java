package iengine;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BackwardChaining extends Chainer {

//    //contains all variables that are true
//    List<String> trueVar = new ArrayList<String>();
//    //contains a list of all equations that infer
//    List<String[]> equations = new ArrayList<String[]>();
//    //contains a list of completed equations that infer
//    List<String[]> solvedEquations = new ArrayList<String[]>();
//    //query to be found
//    String query;

    public BackwardChaining(List<String[]> KB, String _query) {
        query = _query;
        interpretKB(KB);
    }

    // output message for when KB has been solved
    public void solved() {}

    // loops through KB in attempt to prove query with given chaining method
    public void askQuery() {
        boolean KBCompleted = false;
        Queue<String> toProves; // Queue of variables to prove
        toProves.add(query);

        while(!KBCompleted) {
            String toProve = toProves.remove();
            for(int e = 0; e < equations.size())
        }
    }

    // allocates KB data into appropriate variables for chaining
    public void interpretKB(List<String[]> KB) {}
}

// TT return yes and number of models
// number of models is 2^N (N
// counter for numebr of valid models
