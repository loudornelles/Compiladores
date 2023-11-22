class MainClass {
    public static void main(String[] a) {
        System.out.println(0);
    }
}

class LengthReturnType {
    public int a() {
        int[] arr;
        boolean len;
        arr = new int[5];
        len = arr.length; // Error: Incompatible types
        return 0;
    }
}
