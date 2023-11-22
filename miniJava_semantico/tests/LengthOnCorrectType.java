class MainClass {
    public static void main(String[] a) {
        System.out.println(0);
    }
}

class TestLengthOnCorrectType {
    public int a() {
        int[] arr;
        arr = new int[5];
        int len = arr.length; // valid
        return 0;
    }
}
