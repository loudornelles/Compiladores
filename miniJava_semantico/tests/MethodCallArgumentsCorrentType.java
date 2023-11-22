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
        int x;
        int result;

        x = 0;
        result = this.a(x);

        return 0;
    }
}
