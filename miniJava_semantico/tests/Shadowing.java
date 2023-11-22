class MainClass {
    public static void main(String[] a) {
        System.out.println(0);
    }
}

class Shadowing {
    public String[] main(String[] a) {
        System.out.println(a);
        return a;
    }
}

class Foo {
    public int myMethod(int x) {
        int x;
        return x;
    }
}
