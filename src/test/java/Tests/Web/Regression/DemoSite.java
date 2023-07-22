package Tests.Web.Regression;

public class DemoSite implements TestInterface {
    public static void main(String[] args)
    {
        System.out.println("The value of a is :"+TestInterface.great.a);
        System.out.println("The value of b is :"+TestInterface.great.b);
    }
}
