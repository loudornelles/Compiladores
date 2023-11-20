// Method Parameter Shadowing:

class Err8 {
    public static void main(String[] a) {
        System.out.println(a);
    }
}

class Foo {
    public int myMethod(int x) {
        int x;
        return x;
    }
}
