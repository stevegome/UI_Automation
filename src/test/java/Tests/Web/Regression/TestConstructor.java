package Tests.Web.Regression;

class Student {
    int rollno;
    String name;
    float fee;

    public Student(int rollNo, String name, float fee) {
        this.rollno = rollNo;
        this.name = name;
        this.fee = fee;
    }

    void display() {
        System.out.println(this.rollno + " " + this.name + " " + this.fee);
    }
}

class TestConstructor {
    public static void main(String[] args) {
        Student s1 = new Student(111, "ankit", 5000f);
        s1.display();
        Student s2 = new Student(111, "ankit", 6000f);
        s2.display();

    }
}    