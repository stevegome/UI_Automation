package Tests.Web.Regression;

@java.lang.FunctionalInterface
interface AddNumbers {
    void add();
}

class LambdaExpression {

    public static void main(String[] args) {

        // implement add() method
        AddNumbers ref = () -> {int x = 1;int y = 2;System.out.println(x + y);};
        ref.add(); // 3
    }
}