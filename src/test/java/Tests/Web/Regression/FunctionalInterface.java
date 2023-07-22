package Tests.Web.Regression;

@java.lang.FunctionalInterface
interface AddNumber {
    void add();
}

class FunctionalInterface {
    public static void main(String[] args) {

        AddNumber ref = new AddNumber() {

            public void add() {
                System.out.println(4 + 5);
            }
        };

        ref.add();    // 9
    }
}