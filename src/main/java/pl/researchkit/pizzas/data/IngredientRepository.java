package pl.researchkit.pizzas.data;

import pl.researchkit.pizzas.Ingredient;

public interface IngredientRepository {

    Iterable<Ingredient> findAll();
    Ingredient findById(String id);
    Ingredient save(Ingredient ingredient);
}
