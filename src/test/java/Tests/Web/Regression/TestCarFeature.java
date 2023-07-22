package Tests.Web.Regression;

public class TestCarFeature {
    public static void main(String[] args) {
        Engine engine = new Car();
        // Car class implements Engine Interface
        engine.start();
        engine.stop();
        engine.sound();
        //engine.color("Red");

    }


}
