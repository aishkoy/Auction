package ProductState;

import Exceptions.StateException;
import models.Product;

public class ForSale implements State {

    @Override
    public void startSale(Product product) throws StateException {
        throw new StateException("Ошибка: Товар уже участвует в торгах!");
    }

    @Override
    public void raisePrice(Product product){
        product.increasePrice();
        System.out.println("Цена товара было успешно увеличена!\n" +
                "Новая цена товара: " + product.getPrice());
    }

    @Override
    public void withdraw(Product product) throws StateException {
        if(product.getInitialPrice() == product.getPrice()){
            System.out.println("Так как изначальная цена товара не была изменена, он будет возращен на склад.");
            product.setState("на складе");
            product.setStateObj(new InStock());
        } else{
            throw new StateException("Ошибка: Так как товар уже в резерве, его можно только выдать!");
        }
    }

    @Override
    public void giveToTheWinner(Product product) throws StateException {
        if(product.getPrice() == 0){
            throw new StateException("Ошибка: Нельзя выдать товар бесплатно!");
        } else{
            product.setState("продан");
            product.setStateObj(new Sold());
            System.out.println("Товар был успешно продан!");
        }
    }
}
