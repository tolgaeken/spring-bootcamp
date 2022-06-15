package dev.spring.models;

import java.text.DecimalFormat;

public class TestArea {
    public static void main(String[] args) {
        Circle circle = new Circle(10.0, "Black");
        System.out.println(circle.getColor() + " circle area is : " + new DecimalFormat("##.##").format(circle.getArea()));

        Cylinder cylinder = new Cylinder(10.0, "Blue", 15);
        System.out.println(cylinder.getColor() + " cylinder area is : " +  new DecimalFormat("##.##").format(cylinder.getArea()));
        System.out.println(cylinder.getColor() + " cylinder volume is : " +  new DecimalFormat("##.##").format(cylinder.getVoluma()));
    }
}

