// Incorrect Return Type:

class Err7 {
    public static void main(String[] a) {
        int result = myMethod(); // Error: Incompatible types, int cannot be assigned a void value
    }

    public static void myMethod() {
        // Method definition here
    }
}