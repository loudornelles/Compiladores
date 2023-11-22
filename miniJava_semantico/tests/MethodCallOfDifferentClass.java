class MainClass {
    public static void main(String[] a) {
        System.out.println(0);
    }
}

class MethodCallOfDifferentClass {
    Foo foo;

    public int a() {
        return foo.bar();
    }
}

class Foo {
    public int bar() {
        return 0;
    }
}