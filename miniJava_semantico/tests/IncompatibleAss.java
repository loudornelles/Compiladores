class MainClass {
    public static void main(String[] a) {
        System.out.println(0);
    }
}
class IncompatibleAss {
    public int foo() {
        int x;
        x = true;
        return 0;
    }
}