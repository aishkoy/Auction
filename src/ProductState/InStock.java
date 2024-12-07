package ProductState;

import Exceptions.StateException;
import models.Product;

public class InStock implements State {

    @Override
    public void startSale(Product product){
        System.out.println("Вы успешно выставили товар на торги!");
        product.setState("на торгах");
        product.setStateObj(new ForSale());
    }

    @Override
    public void raisePrice(Product product) throws StateException {
        throw new StateException("Ошибка: Вы не можете повысить цену товара, так как он еще не участвует в торгах!");
    }

    @Override
    public void withdraw(Product product) throws StateException {
        throw new StateException("Ошибка: Вы не можете снять с торгов товар, который в них не участвует!");
    }

    @Override
    public void giveToTheWinner(Product product) throws StateException {
        throw new StateException("Ошибка: Нельзя отдать товар сразу со склада!");
    }
}
