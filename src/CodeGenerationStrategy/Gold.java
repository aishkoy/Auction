package CodeGenerationStrategy;

public class Gold implements Strategy {
    @Override
    public String generateCode(String productId) {
        return cd.makeCode("Gold-" + productId);
    }
}
