class DeclaredBefore {
    int value;
    TestBefore(int val) {
        value = val;
    }
}

class MainClass {
    public static void main(String[] args) {
        DeclaredBefore obj;
        obj = new DeclaredBefore(5);
        System.out.println(obj.value);
    }
}