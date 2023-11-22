class ReturnType {
    int value;
    ReturnType(int val) {
        value = val;
    }
    int getValue() {
        return value;
    }
}

class Main {
    public static void main(String[] args) {
        ReturnType obj;
        int value;
        obj = new ReturnType(5);
        value = obj.getValue();
        System.out.println(value);
    }
}