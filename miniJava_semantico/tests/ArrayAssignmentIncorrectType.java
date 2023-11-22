class MainClass {
    public static void main(String[] a) {
        System.out.println(0);
    }
}

class ArrayAssignmentIncorrectType {
    public int foo() {
        int[] arr;
        arr = new int[5];
        arr[0] = true; // Error: Incompatible types
        return 0;
    }
}
