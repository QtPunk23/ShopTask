package org.example.fistAtt;

import java.util.List;
import java.util.stream.Collectors;

public class StoreService {
private Store store;

public StoreService(Store store) {
        this.store = store;
        }

public void printProductCountByDepartment() {
        store.getProducts().stream()
        .collect(Collectors.groupingBy(p -> p.getDepartment().getNameOfDepartment(), Collectors.counting()))
        .forEach((department, count) -> System.out.println("Отдел: " + department + " - Количество товаров: " + count));
        }

public void printTotalPriceByDepartment() {
        store.getProducts().stream()
        .collect(Collectors.groupingBy(p -> p.getDepartment().getNameOfDepartment(), Collectors.summingDouble(Product::getPrice)))
        .forEach((department, total) -> System.out.println("Отдел: " + department + " - Сумма товаров: " + total));
        }

public void printTotalProductCount() {
        long totalCount = store.getProducts().size();
        System.out.println("Общее количество товаров: " + totalCount);
        }

public void printTotalPrice() {
        double totalPrice = store.getProducts().stream().mapToDouble(Product::getPrice).sum();
        System.out.println("Общая сумма товаров: " + totalPrice);
        }

public void printProductsInDepartment(int departmentIndex, List<Department> departments) {
        if (departmentIndex < 0 || departmentIndex >= departments.size()) {
        System.out.println("Некорректный номер отдела.");
        return;
        }

        String departmentName = departments.get(departmentIndex).getNameOfDepartment();
        List<Product> productsInDepartment = store.getProducts().stream()
        .filter(p -> p.getDepartment().getNameOfDepartment().equals(departmentName))
        .collect(Collectors.toList());

        if (productsInDepartment.isEmpty()) {
        System.out.println("В отделе " + departmentName + " нет товаров.");
        } else {
        System.out.println("Товары в отделе " + departmentName + ":");
        productsInDepartment.forEach(p -> System.out.println("- " + p.getName() + ": " + p.getPrice() + " руб."));
        }
        }

public List<Department> getAllDepartments() {
        return store.getProducts().stream()
        .map(Product::getDepartment)
        .distinct()
        .collect(Collectors.toList());
        }
        }