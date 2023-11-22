class MainClass {
    public static void main(String[] a) {
        System.out.println(0);
    }
}

class LengthOnCorrectType {
    public int a() {
        int[] arr;
        int len;
        arr = new int[5];
        len = arr.length; // valid
        return 0;
    }
}
