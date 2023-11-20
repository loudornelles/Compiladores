// Calling Methods That Don't Exist:

class Err1 {
    public static void main() {
        MyClass obj = new MyClass();
        obj.nonExistentMethod();
    }
}

class MyClass {
}
