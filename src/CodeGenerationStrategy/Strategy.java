package CodeGenerationStrategy;

import utils.CodeGenerator;

public interface Strategy {
    CodeGenerator cd = new CodeGenerator();
    String generateCode(String productId);
}
