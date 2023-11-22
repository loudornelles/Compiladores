class MainClass {
    public static void main(String[] a) {
        System.out.println(0);
    }
}

class Shadowing {
    public int c(int x) {
        int x;
        return x;
    }
}
