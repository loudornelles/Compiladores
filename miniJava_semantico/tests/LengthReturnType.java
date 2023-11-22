class MainClass {
    public static void main(String[] a) {
        System.out.println(0);
    }
}

class TestLengthReturnType {
    public int a() {
        int[] arr;
        arr = new int[5];
        boolean len = arr.length; // Error: Incompatible types
        return 0;
    }
}
