package iengine;

public class Main {

    public static void main(String[] args) {

        // Final Main Implementation
        FileInput file = new FileInput(args[1]);
        TruthTable tt = new TruthTable(file.getKB(), file.getQuery());
        TruthTable tt2 = new TruthTable(file.getKB(), file.getQuery());
        ForwardChaining fc = new ForwardChaining(file.getKB(), file.getQuery());
        BackwardChaining bc = new BackwardChaining(file.getKB(), file.getQuery());

        switch (args[0])
        {
            case "TT":
                tt.askQuery();
                break;

            case "TT2":
                tt2.askQuery2();
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
                System.out.println("TT2");
                System.out.println("FC");
                System.out.println("BC");
                break;
        }
    }
}
