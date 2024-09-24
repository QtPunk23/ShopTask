package org.example.fistAtt;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Product> products;

    public Store() {
        this.products = new ArrayList<>();
    }

    public void addProducts(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void saveToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("{");
            writer.println("    \"products\": [");
            for (int i = 0; i < products.size(); i++) {
                writer.println(products.get(i));
                if (i < products.size() - 1) {
                    writer.println(",");
                }
            }
            writer.println("    ]");
            writer.println("}");
            System.out.println("Данные сохранены в файл: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("Файл не найден, создаю новый файл: " + filename);
            saveToFile(filename);  // Сохраняем пустой магазин в файл
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            StringBuilder jsonString = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                jsonString.append(line).append("\n");
            }
            // Парсим данные
            String data = jsonString.toString();
            String productsString = data.substring(data.indexOf("[") + 1, data.indexOf("]"));
            String[] productLines = productsString.split("},");
            for (String productLine : productLines) {
                String name = productLine.split("\"name\": \"")[1].split("\"")[0];
                double price = Double.parseDouble(productLine.split("\"price\": ")[1].split(",")[0]);
                String departmentName = productLine.split("\"department\": \"")[1].split("\"")[0];
                Department department = new Department(departmentName);
                addProducts(new Product(name, price, department));
            }
            System.out.println("Данные загружены из файла: " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ошибка при загрузке данных. Проверьте формат файла.");
        }
    }

}
