class MainClass {
    public static void main(String[] a) {
        System.out.println(0);
    }
}


class ArrayAssignmentCorrectIndexType {
    public int foo() {
        int[] arr;
        arr = new int[5];
        arr[true] = 42; // valid
        return 0;
    }
}
