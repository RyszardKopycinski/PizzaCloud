package pl.researchkit.pizzas;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Ingredient {

    private final String id;
    private final String name;
    private final Type   type;

    public static enum Type {
       PROTEIN, VEGGIE, CHEESE, SAUCE
    }
}
