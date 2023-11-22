class MainClass {
    public static void main(String[] a) {
        System.out.println(0);
    }
}

class MethodCallArgumentsCorrectType {
    public int a(int x) {
        return 0;
    }

    public int b() {
        String x;
        int result;

        result = a(x);

        return 0;
    }
}
