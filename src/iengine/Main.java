package iengine;

public class Main {

    public static void main(String[] args) {

        // testing methods/input
        FileInput file = new FileInput("src/iengine/test.txt");
        TruthTable tt = new TruthTable(file.getKB(), file.getQuery());
        ForwardChaining fc = new ForwardChaining(file.getKB(), file.getQuery());
        BackwardChaining bc = new BackwardChaining(file.getKB(), file.getQuery());
        tt.askQuery();
        System.out.println(tt.models);
        int no = 1432; // test integer
        String binary = Integer.toBinaryString(no); // convert to binary
        System.out.println(binary);

        // inspect variables list
        for(int i = 0; i < bc.variables.size(); i++) {
            System.out.println(bc.variables.get(i).getValue());
        }
        //fc.askQuery();
        //bc.askQuery();

        // Final Main Implementation
        //FileInput file = new FileInput(args[1]);
        //TruthTable tt = new TruthTable(file.getKB(), file.getQuery());
        //ForwardChaining fc = new ForwardChaining(file.getKB(), file.getQuery());
        //BackwardChaining bc = new BackwardChaining(file.getKB(), file.getQuery());

//        switch (args[0])
//        {
//            case "TT":
//                break;
//
//            case "FC":
//                fc.askQuery();
//                break;
//
//            case "BC":
//                bc.askQuery();
//                break;
//
//            default:
//                System.out.println("Invalid algorithm. Valid algorithms are:");
//                System.out.println("TT");
//                System.out.println("FC");
//                System.out.println("BC");
//                break;
//        }
    }
}
