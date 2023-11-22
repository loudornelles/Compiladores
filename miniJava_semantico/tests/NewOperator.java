class MainClass {
    public static void main(String[] a) {
        System.out.println(0);
    }
}

class A {}

class B {
    public int foo() {
        A a;
        C c;
        a = new A();
        c = new C();
        return 0;
    }
}

class C {}