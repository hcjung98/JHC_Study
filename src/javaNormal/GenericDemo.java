package javaNormal;

public class GenericDemo {
    public static void main(String[] args) {
        Person<EmployeeInfo> p1 = new Person<EmployeeInfo>(new EmployeeInfo(1));
        Person p3 = new Person(new EmployeeInfo(1));
//        javaNormal.Person<String> p2 = new javaNormal.Person<String>("부장");
    }
}
