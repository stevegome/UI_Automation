package Tests.Web.Regression;

class Autoboxing {
    //converting primitive type to wrapper class object
    public static void main(String[] args) {

        int x = 25;

        // autoboxing
        Integer xObj = x;

        System.out.println(xObj); // 25

    }
}