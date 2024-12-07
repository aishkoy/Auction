package ProductState;

import Exceptions.StateException;
import models.Product;

public class Sold implements State {

    @Override
    public void startSale(Product product) throws StateException {
        throw new StateException("Ошибка: Нельзя начать продажу, так как товар уже продан");
    }

    @Override
    public void raisePrice(Product product) throws StateException {
        throw new StateException("Ошибка: Нельзя повысить стоимость товара, так ка он уже продан! ");
    }

    @Override
    public void withdraw(Product product) throws StateException {
        throw new StateException("Ошибка: Нельзя снять с торгов товарБ который уже продан!");
    }

    @Override
    public void giveToTheWinner(Product product) throws StateException {
        throw new StateException("Ошибка: Нельзя выдать покупателю, так ка товар уже продан!");
    }
}