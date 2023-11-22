class MainClass {
    public static void main(String[] a) {
        System.out.println(0);
    }
}
class DuplicatedVarName {
    public int foo() {
        int x;
        int x; // Error: Variable x is already defined in this scope
        return 0;
    }
}
