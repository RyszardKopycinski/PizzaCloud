package pl.researchkit.pizzas.data;

import pl.researchkit.pizzas.Pizza;

public interface PizzaRepository {

    Pizza save(Pizza design);
}
