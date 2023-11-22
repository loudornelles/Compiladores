class MainClass {
    public static void main(String[] a) {
        System.out.println(0);
    }
}

class NoLocalVariableWithSameNameAsParameter {
    public int a(int x) {
        int x; // should error
        return 0;
    }
}
