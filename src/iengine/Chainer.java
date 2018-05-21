package iengine;

public abstract class Chainer {

    // parent class for backwards/forwards chaining
    public Chainer() {}

    // output message for when KB has been solved
    public abstract void solved();

    // loops through KB in attempt to prove query with given chaining method
    public abstract void askQuery();

    // allocates KB data into appropriate variables for chaining
    public abstract void interpretKB();

}
