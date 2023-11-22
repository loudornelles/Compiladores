class MainClass {
    public static void main(String[] a) {
        System.out.println(0);
    }
}

class TestLengthOnIncorrectType {
    public int a() {
        int x;
        int len = x.length; // Error: Only arrays have a length property
        return 0;
    }
}
