class MainClass {
    public static void main(String[] a) {
        System.out.println(0);
    }
}

class MethodCallArgumentsIncorrectOrder {
    public int a(int x, boolean y) {
        return 0;
    }

    public int b() {
        int result;

        result = this.a(true, 0);
        return 0;
    }
}
