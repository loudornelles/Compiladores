class MainClass {
    public static void main(String[] a) {
        System.out.println(0);
    }
}


class IfWhileNonBoolean {
    public int foo() {
        if (42) {
            // Error: Condition must be boolean
        } else {
            
        }

        while (0) {
            // Error: Condition must be boolean
        }

        return 0;
    }
}
