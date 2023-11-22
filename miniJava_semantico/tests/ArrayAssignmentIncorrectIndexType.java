class MainClass {
    public static void main(String[] a) {
        System.out.println(0);
    }
}

class ArrayAssignmentIncorrectIndexType {
    public int foo() {
        int[] arr;
        arr = new int[5];
        arr[true] = 42; // Error: Incompatible types for array index
        return 0;
    }
}
