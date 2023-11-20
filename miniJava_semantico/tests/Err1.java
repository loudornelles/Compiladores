// Calling Methods That Don't Exist:

class Err1 {
    public static void main(String[] a) {
        MyClass obj = new MyClass();
        obj.someMethod("string", 123);
    }
}

class MyClass {
    public static void main(String[] a) {

    }
}
