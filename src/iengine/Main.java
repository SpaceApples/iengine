package iengine;

public class Main {

    public static void main(String[] args) {
        FileInput file = new FileInput("src/iengine/test.txt");
        ForwardChaining fc = new ForwardChaining(file.getKB(), file.getQuery());
        fc.askQuery();
    }
}
