package Tests.Web.Regression;

class Unboxing {
    public static void main(String[] args) {
        //converting wrapper object to primitive data type
        // assign value to Integer object
        Integer xObj = new Integer(25);
        String yObj = new String("Stevan");

        // unboxing
        int x = xObj;
        String y = yObj;

        System.out.println(x); // 25
        System.out.println(y);

    }
}