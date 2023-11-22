class MainClass {
    public static void main(String[] a) {
        System.out.println(0);
    }
}

class IncorrectReturnType {
    public int a() {
        return this.myMethod();
    }

    public boolean myMethod() {
        return false;
    }
}