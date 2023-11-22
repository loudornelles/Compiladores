class MainClass {
    public static void main(String[] a) {
        System.out.println(0);
    }
}

class IncorrectParams {
    public int a() {
        MyClass obj;
        obj = new MyClass();
        return obj.someMethod(0, 123);
    }
}

class MyClass {
    public int someMethod(int param) {
        return 0;
    }
}
