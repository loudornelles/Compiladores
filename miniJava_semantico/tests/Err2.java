// Incorrect Parameters:

class Err2 {
    public static void main(String[] a) {
        MyClass obj = new MyClass();
        obj.someMethod("string", 123);
    }
}

class MyClass {
    public void someMethod(String param) {
    }
}
