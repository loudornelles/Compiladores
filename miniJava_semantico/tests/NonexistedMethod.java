class MainClass {
    public static void main(String[] a) {
        System.out.println(0);
    }
}

class NonexistedMethod {
    public int a(String[] a) {
        MyClass obj = new MyClass();
        obj.someMethod("string", 123);
        return 0;
    }
}

class MyClass {
    public int b(String[] a) {
        return 0;
    }
}
