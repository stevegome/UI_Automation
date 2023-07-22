package Tests.Web.Regression;

@java.lang.FunctionalInterface
interface AddNumbersNew {
    int add(int x, int y);
}

class LambdaExpressionWithReturn {

    public static void main(String[] args) {

        AddNumbersNew obj = (x, y) -> {return x + y;};
        int sum = obj.add(2, 2);
        System.out.println(sum); // 4
    }
}
