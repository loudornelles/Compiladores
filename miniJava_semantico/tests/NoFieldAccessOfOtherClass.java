class MainClass {
    public static void main(String[] a) {
        System.out.println(0);
    }
}

class NoFieldAccessOfOtherClass {
    Foo a;
    public int a() {
        return a.a; // should cause an error
    }
}

class Foo {
    int a;
}
