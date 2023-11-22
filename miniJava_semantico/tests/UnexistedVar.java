class MainClass {
    public static void main(String[] a) {
        System.out.println(0);
    }
}

class UnexistedVar {
    public int a(String[] a) {
        int x = y; // Error: Variable y not found
        return x;
    }
}
