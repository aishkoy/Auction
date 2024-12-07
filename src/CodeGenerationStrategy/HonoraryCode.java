package CodeGenerationStrategy;

public class HonoraryCode {
    private Strategy strategy;

    public void setStrategy(double price) {
        if(price < 500){
            this.strategy = new Bronze();
        } else if(price < 1000){
            this.strategy = new Silver();
        } else{
            this.strategy = new Gold();
        }
    }

    public String generateCode(String productId) {
        return strategy.generateCode(productId);
    }
}
