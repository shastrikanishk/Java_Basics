
/*
public class Person {
    String name;
    int percentage;



    public static void main(String[] args) {
        //create first person object
        Person Person1 = new Person();

        // assign values
        Person1.name="Ayan";
        Person1.percentage=99;

        //print assigned values using Person class object
        System.out.println("Name: " + Person1.name + "\n Percentage= " + Person1.percentage + "%");

        //create second person object
        Person Person2 = new Person();
        // assign value calling class vaiables thru created object
        Person2.name="Ryan";
        Person2.percentage=8;
        //print values
       System.out.println("Name: " + Person2.name + "\n Percentage= " + Person2.percentage + "%");


    }
}

 */

//Create a program to implement multiple objects.
//import class scanner which is part of util package in order to take inputs from user
import java.util.Scanner;

class Person {
    //Create a class named Person with a field name.
    String name;


    //Inside the main() method, get two input values for variables name1 and name2.
    public static void main(String[] args) {

        //Person name1 = new Person();
        //name1.name="test";

        //System.out.println(name1.name);

        Scanner Scan = new Scanner(System.in);
        String test1=Scan.nextLine();
        //System.out.println(test1);

        Person name1 = new Person();
        name1.name=test1;
        System.out.println(name1.name);




    }
}