package CodeGenerationStrategy;

public class Bronze implements Strategy {

    @Override
    public String generateCode(String productId) {
        return cd.makeCode("Bronze-" + productId);
    }
}
