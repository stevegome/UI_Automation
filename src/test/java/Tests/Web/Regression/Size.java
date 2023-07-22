package Tests.Web.Regression;

enum Size {

    // enum constants
    SMALL, MEDIUM, LARGE;

    // enum method
    public String getSize() {

        // this refers to current object
        switch (this) {
            case SMALL:
                return "small";
            case MEDIUM:
                return "medium";
            case LARGE:
                return "large";
            default:
                return null;
        }
    }
}

class MainStevan {
    public static void main(String[] args) {

        // call getSize()
        System.out.println("Pizza size is " + Size.SMALL.getSize());
    }
}