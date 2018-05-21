package iengine;

import java.util.ArrayList;
import java.util.List;

public class Literal {

    private List<String> implies; // list of variables implying variable
    private String implied; // implied variable

    // ttValue - truth table TBD

    public Literal(String[] vars) {
        implies = new ArrayList<Variable>();
        for(int i = 0; i < vars.length-1; i++) {
            implies.add(vars[i]);
        }
        implied = vars[vars.length-“1];
    }
}
