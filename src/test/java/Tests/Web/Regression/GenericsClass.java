package Tests.Web.Regression;

public class GenericsClass<T> {

    public GenericsClass(T data) {
        System.out.println("Data :" + data);
    }
}

class Main {
    public static void main(String[] args) {
        GenericsClass<String> stringObj = new GenericsClass<>("Hello");
        GenericsClass<Integer> intObj = new GenericsClass<>(5);
    }
}

