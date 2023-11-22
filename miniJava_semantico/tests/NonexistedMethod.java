class MainClass {
    public static void main(String[] a) {
        System.out.println(0);
    }
}

class MyClass {
    public int c() {
        return 0;
    }
}

class NonexistedMethod {
    public int a() {
        MyClass obj;
        obj = new MyClass();
        return obj.b();
    }
}
