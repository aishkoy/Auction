package ProductState;

import Exceptions.StateException;
import models.Product;

public interface State {
    void startSale(Product product) throws StateException;
    void raisePrice(Product product) throws StateException;
    void withdraw(Product product) throws StateException;
    void giveToTheWinner(Product product) throws StateException;
}
