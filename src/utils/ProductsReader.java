package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ProductsReader {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    private static Path PATH;
    private final Product[] products;

    public ProductsReader(String filename) {
        PATH = Paths.get("src/data/" + filename + ".json");
        this.products = readFile();
    }

    public Product[] readFile() {
        try {
            String str = Files.readString(PATH);
            return GSON.fromJson(str, Product[].class);
        } catch (IOException e) {
            System.out.println("Error reading file" + e.getMessage());
            return new Product[0];
        }
    }

    public Product[] getGoods(){
        return products;
    }

    public void writeFile(Product[] products) {
        String newJson = GSON.toJson(products);
        try {
            Files.write(PATH, newJson.getBytes());
        } catch (IOException e) {
            System.out.println("Error writing file" + e.getMessage());
        }
    }
}
