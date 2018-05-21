package iengine;

import java.util.ArrayList;
import java.util.List;

public abstract class Chainer {

    //contains all variables that are true
    List<String> trueVar = new ArrayList<String>();
    //contains a list of all equations that infer
    List<String[]> equations = new ArrayList<String[]>();
    //contains a list of completed equations that infer
    List<String[]> solvedEquations = new ArrayList<String[]>();
    //query to be found
    String query;

    // parent class for backwards/forwards chaining
    public Chainer() {}

    // output message for when KB has been solved
    public abstract void solved();

    // loops through KB in attempt to prove query with given chaining method
    public abstract void askQuery();

    // allocates KB data into appropriate variables for chaining
    public abstract void interpretKB(List<String[]> KB);

}
