import Exceptions.StateException;
import models.Store;

public class Main {
    public static void main(String[] args) throws StateException {
        Store store = new Store();
        Store.ConsoleManager consoleManager = store.new ConsoleManager();
        consoleManager.startInteraction();
    }
}