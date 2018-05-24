|-------------------------------------------------------------------------------------|
/:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\
/::::::::::::::::::    COS30019 Assignment 2 - INFERENCE ENGINE    :::::::::::::::::::\
/::::::::::::::::::              Tyler Barker 100495787            :::::::::::::::::::\
/::::::::::::::::::               Jack Douglas 7874856             :::::::::::::::::::\
/:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::\
|-------------------------------------------------------------------------------------|


GROUP ALLOCATION: COS30019_A02_T022


\_-_-_-_-_-_-_ FEATURES _-_-_-_-_-_-_/

- File Input Interpreter:

The file input interpreter takes in a given KB .txt file and parses it into the following variables:

    * List<String[]> KB: This stores each literal, separated by ';' into individual string arrays.
    Where an array is equal to one, is it later interpreted as a true variable. Otherwise, the
    final index of each array is the implied variable, with prior indexes storing the impliers.

    * String query: Simply the string value indicated after the ASK line for the program to test.

- Chainer Class:

The Chainer class is the parent class to TruthTable, ForwardChaining, and BackwardChaining.
It contains two abstract methods required by all children:

    * solved(boolean result): Takes in the result of askQuery and prints the appropriate output message.
    * askQuery(): Takes in a query variable and tests it using an algorithm (TT/FC/BC)

    Extended by:
    - Truth Table Algorithm
    - Forward Chaining Algorithm
    - Backward Chaining Algorithm


- Literal/Variable Class:

The Literal Class holds horn clauses with the following variables:
    * Variable[] impliers: Array of variables implying another variable.
    * Variable implied: Implied variable.
    * boolean isTrue: Boolean value of literal.

The Variable class simply stores a string symbol for the variable in question,
along with a boolean value associated with it.

\_-_-_-_-_-_-_ MISSING _-_-_-_-_-_-_/

- Generic Knowledge-Based Capability (Optional Component)


\_-_-_-_-_-_-_ KNOWN BUGS _-_-_-_-_-_-_/

- We have implemented two versions of the TruthTable algorithm as across both of our two
attempts we still found some bugs:
    * The calculations of version 2 (truthTable method) are off by one.
    * Version 1 (recursiveConstructTT method) prints YES on test2.txt despite
    BC/FC proving the query to be false.


\_-_-_-_-_-_-_ TEST CASES _-_-_-_-_-_-_/

FC/BC:
- test1.txt: No bugs detected, output as given.
- test2.txt: No bugs detected, output as anticipated.
- test3.txt: No bugs detected, output as anticipated.
- test4.txt: No bugs detected, output as anticipated.

TT v1:
- test1.txt: YES: 3
- test2.txt: YES: 3
- test3.txt: YES: 3
- test4.txt: YES: 1

TT v2:
- test1.txt: YES: 2
- test2.txt: NO
- test3.txt: YES: 2
- test4.txt: YES: 1

- test_genericKB.txt: Not applicable, capability not implemented.


\_-_-_-_-_-_-_ ACKNOWLEDGEMENTS _-_-_-_-_-_-_/

- For our Forward/Backward Chaining implementation, we utilised a pseudo-code example provided by Luis E. Ortiz,
which can be found here: http://www-personal.umd.umich.edu/~leortiz/teaching/6.034f/Fall05/rules/fwd_bck.pdf


\_-_-_-_-_-_-_ TEAMWORK SUMMARY _-_-_-_-_-_-_/

OVERVIEW:
Throughout the development of this project, we did a great deal of pair programming.
This proved to be really beneficial as it allowed us to discuss our solutions in more detail than online.
Where this wasn't possible, we utilised Slack to discuss ideas, as well as bugs we had found in one another's code.
We also made use of Github in order to more easily commit and track our changes across both machines.

BREAKDOWN:
TB - Tyler Barker
JD - Jack Douglas
                                SPLIT
FILENAME.format -----------> [ TB / JD ] ---> WORTH

- Main.java ---------------> [ 80 / 20 ] ---> 5%

- FileInput.java ----------> [ 0 / 100 ] ---> 15%

- Chainer.java ------------> [ 50 / 50 ] ---> 5%

- Literal.java ------------> [ 40 / 60 ] ---> 5%

- Variable.java -----------> [ 100 / 0 ] ---> 5%

- TruthTable.java ---------> [ 50 / 50 ] ---> 25%

- ForwardChaining.java ----> [ 0 / 100 ] ---> 15%

- BackwardChaining.java ---> [ 100 / 0 ] ---> 15%

- README.txt --------------> [ 90 / 10 ] ---> 10%

TOTALS:
- Tyler [ 50% ]
- Jack [ 50% ]