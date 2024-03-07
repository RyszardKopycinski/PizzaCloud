package pl.researchkit.pizzas.data;

import pl.researchkit.pizzas.Order;

public interface OrderRepository {

    Order save(Order order);
}
