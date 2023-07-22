package Tests.Web.Regression;

public class Car implements Engine {


    @Override
    public void start() {
        System.out.println("Engine starts");

    }

    @Override
    public void stop() {
        System.out.println("Engine stops");
    }

    @Override
    public void sound() {
        System.out.println("Engine sound");
    }
}
