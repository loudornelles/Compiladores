class Test {
    public static void main(String[] args){
        System.out.println(0);
    }
}

class A {
    boolean b;
    public int foo() {
        int a;
        a = 10;
        return this.bar(a, b);
    }

    public int bar(int i, boolean j) {
        int ret;
        if (j) {
            ret = i;
        } else {
            ret = 0;
        }

        return ret;
    }
}