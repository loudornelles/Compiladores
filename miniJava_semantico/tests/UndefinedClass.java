class MainClass {
    public static void main(String[] a) {
        System.out.println(0);
    }
}

class UndefinedClass {
    public int a() {
        UndefinedClass obj;
        obj = new UndefinedClass1(); // Error: Class UndefinedClass not found
        return 0;
    }
}
