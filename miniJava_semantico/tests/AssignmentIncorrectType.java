class MainClass {
    public static void main(String[] a) {
        System.out.println(0);
    }
}

class AssignmentIncorrectType {
    public int foo() {
        int x;
        x = true; // Error: Incompatible types
        return 0;
    }
}
