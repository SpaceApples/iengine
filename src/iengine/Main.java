package iengine;

public class Main {

    public static void main(String[] args) {
    	if(args.length > 1) {
        	// Final Main Implementation
        	FileInput file = new FileInput(args[1]);
        	TruthTable tt = new TruthTable(file.getKB(), file.getQuery());
        	ForwardChaining fc = new ForwardChaining(file.getKB(), file.getQuery());
        	BackwardChaining bc = new BackwardChaining(file.getKB(), file.getQuery());
        
        	switch (args[0].toLowerCase())
        	{
            	case "tt":
            		tt.askQuery();
            		break;

            	case "fc":
            		fc.askQuery();
            		break;

            	case "bc":
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
        else {
        	System.out.println("Not enough Arguments: (method) (filename)");
        }
    }
}
