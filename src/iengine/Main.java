package iengine;

public class Main {

    public static void main(String[] args) {

        // testing methods/input
        FileInput file = new FileInput("src/iengine/test2.txt");
        ForwardChaining fc = new ForwardChaining(file.getKB(), file.getQuery());
        BackwardChaining bc = new BackwardChaining(file.getKB(), file.getQuery());
        //fc.askQuery();
        bc.askQuery();

        // Final Main Implementation
        //FileInput file = new FileInput(args[1]);
        //TruthTable tt = new TruthTable(file.getKB(), file.getQuery());
        //ForwardChaining fc = new ForwardChaining(file.getKB(), file.getQuery());
        //BackwardChaining bc = new BackwardChaining(file.getKB(), file.getQuery());

        switch (args[0])
        {
            case "TT":
                break;

            case "FC":
                fc.askQuery();
                break;

            case "BC":
                bc.askQuery();
                break;

            default:
                System.out.println("Invalid algorithm. Valid algorithms are:");
                System.out.println("TT");
                System.out.println("FC");
                System.out.println("BC");
                break;
        }
    }
}
