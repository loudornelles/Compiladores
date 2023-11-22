class Main {
    public static void main(String[] args) {
        DeclaredAfter obj;
        obj = new DeclaredAfter(5);
        System.out.println(obj.value);
    }
}

class DeclaredAfter {
    int value;
    DeclaredAfter(int val) {
        value = val;
    }
}