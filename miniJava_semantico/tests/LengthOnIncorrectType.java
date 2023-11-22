class MainClass {
    public static void main(String[] a) {
        System.out.println(0);
    }
}

class LengthOnIncorrectType {
    public int a() {
        int x;
        int len;
        x = 0;
        len = x.length;// Error: Only arrays have a length property
        return 0;
    }
}
