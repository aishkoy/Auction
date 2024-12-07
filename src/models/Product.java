package models;

import CodeGenerationStrategy.HonoraryCode;
import Exceptions.StateException;
import ProductState.ForSale;
import ProductState.InStock;
import ProductState.Sold;
import ProductState.State;

public class Product {
    private final String id;
    private final String name;
    private double price;
    private String honoraryCode;
    private String state;

    private transient double initialPrice;
    private transient State stateObj;

    public Product(String id, String name, double price, String honoraryCode, String state) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.honoraryCode = honoraryCode;
        this.state = state;
        this.initialPrice = price;
        initializeState();
    }

    public void setInitialPrice() {
        this.initialPrice = this.price;
    }

    public void initializeState() {
        switch (state.toLowerCase()) {
            case "на складе":
                this.stateObj = new InStock();
                break;
            case "на торгах":
                this.stateObj = new ForSale();
                break;
            case "продан":
                this.stateObj = new Sold();
                break;
            default:
                this.stateObj = new InStock();
                this.state = "на складе";
        }
        setInitialPrice();
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setStateObj(State stateObj) {
        this.stateObj = stateObj;
    }

    public double getPrice() {
        return price;
    }

    public double getInitialPrice() {
        return initialPrice;
    }

    public void increasePrice() {
        System.out.println("Фиксированное значение подъема цены: 100");
        this.price += 100;
    }

    public String getShortInfo(){
        return String.format("%-8s  ║  %-30s ║", this.id, this.name);
    }

    @Override
    public String toString() {
        return String.format("║ %-8s ║  %-30s ║  %-8s ║ %-10s ║ %-15s║", this.id, this.name, this.price, this.state ,this.honoraryCode);
    }


    public void startSale() {
        try{
            stateObj.startSale(this);
        } catch (StateException e) {
            System.out.println(e.getMessage());
        }
    }

    public void raisePrice() {
        try{
            stateObj.raisePrice(this);
        } catch (StateException e) {
            System.out.println(e.getMessage());
        }
    }

    public void withdraw() {
        try{
            stateObj.withdraw(this);
        } catch (StateException e) {
            System.out.println(e.getMessage());
        }
    }

    public void giveToTheWinner() {
        try{
            stateObj.giveToTheWinner(this);
            if (state.equalsIgnoreCase("продан")) {
                HonoraryCode context = new HonoraryCode();
                context.setStrategy(this.price);
                this.honoraryCode = context.generateCode(this.id);
            }
        } catch (StateException e) {
            System.out.println(e.getMessage());
        }
    }

}
