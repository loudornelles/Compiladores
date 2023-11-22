class MainClass {
    public static void main(String[] a) {
        System.out.println(0);
    }
}

class ArrayAssignmentCorrectType {
    public int foo() {
        int[] arr;
        arr = new int[5];
        arr[0] = 42; // valid
        return 0;
    }
}
