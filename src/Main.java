import Exceptions.StateException;
import models.Store;
import utils.ProductsReader;

public class Main {
    public static void main(String[] args) throws StateException {
        ProductsReader pr = new ProductsReader("products");
        Store store = new Store(pr);
        Store.ConsoleManager consoleManager = store.new ConsoleManager();
        consoleManager.startInteraction();
    }
}