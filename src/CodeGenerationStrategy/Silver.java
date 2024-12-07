package CodeGenerationStrategy;

public class Silver implements Strategy {

    @Override
    public String generateCode(String productId) {
        return cd.makeCode("Silver-" + productId);
    }
}
