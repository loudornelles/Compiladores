class MainClass {
    public static void main(String[] a) {
        System.out.println(0);
    }
}

class MethodCallOfCurrentClass {
    public int a() {
        return this.b();
    }

    public int b() {
        return 0;
    }
}
