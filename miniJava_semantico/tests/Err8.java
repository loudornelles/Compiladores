// Method Parameter Shadowing:

public class Err8 {
    public static void main(String[] a) {
        int x = 10;
        myMethod(x);
    }

    public static void myMethod(int x) {
        int x = 20;
    }
}
