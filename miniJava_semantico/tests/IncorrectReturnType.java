class MainClass {
    public static void main(String[] a) {
        System.out.println(0);
    }
}

class IncorrectReturnType {
    public int a(String[] a) {
        int result = myMethod(); // Error: Incompatible types, int cannot be assigned a void value
        return 0;
    }

    public String myMethod() {
        return "hello";
    }
}