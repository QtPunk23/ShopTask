package org.example.fistAtt;

public class Product {
    private String name;
    private double price;

    private Department department;

    public Product(String name, double price, Department department) {
        this.name = name;
        this.price = price;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Department getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "    {\n" +
                "        \"name\": \"" + name + "\",\n" +
                "        \"price\": " + price + ",\n" +
                "        \"department\": \"" + department.getNameOfDepartment() + "\"\n" +
                "    }";
    }
}
