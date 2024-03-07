package pl.researchkit.pizzas.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import pl.researchkit.pizzas.Ingredient;
import pl.researchkit.pizzas.Pizza;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcPizzaRepository implements PizzaRepository {

    //private JdbcTemplate     jdbc;
    private SimpleJdbcInsert pizzaInsert;
    private SimpleJdbcInsert pizzaIngredientInsert;
    private ObjectMapper     objectMapper;

    /*@Autowired
    public JdbcPizzaRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }*/

    @Autowired
    public JdbcPizzaRepository(JdbcTemplate jdbc) {
        this.pizzaInsert = new SimpleJdbcInsert(jdbc).withTableName("Pizza")
                                                     .usingGeneratedKeyColumns("id");
        pizzaIngredientInsert = new SimpleJdbcInsert(jdbc).withTableName("Pizza_Ingredients");
        objectMapper = new ObjectMapper();
    }

    @Override
    public Pizza save(Pizza pizza) {
        pizza.setCreatedAt(new Date());
        long pizzaId = savePizzaInfo2(pizza);
        pizza.setId(pizzaId);
        List<Ingredient> ingredients = pizza.getIngredients();
        for (Ingredient ingredient : ingredients) {
            saveIngredientsToPizza2(ingredient, pizzaId);
        }
        /*long pizzaId = savePizzaInfo(pizza);
        pizza.setId(pizzaId);
        for (Ingredient ingredient : pizza.getIngredients()) {
            saveIngredientToPizza(ingredient, pizzaId);
        }*/
        return pizza;
    }

    /*private long savePizzaInfo(Pizza pizza) {
        pizza.setCreatedAt(new Date());
        PreparedStatementCreator psc = new PreparedStatementCreatorFactory("insert into Pizza (name, createdAt) values (?, ?)",
                                                                           Types.VARCHAR,
                                                                           Types.TIMESTAMP).newPreparedStatementCreator(Arrays.asList(pizza.getName(),
                                                                                                                                      new Timestamp(pizza.getCreatedAt()
                                                                                                                                                         .getTime())));
        System.out.println(pizza.getName() + " # " + pizza.getIngredients() + " # " + pizza.getCreatedAt());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        System.out.println(psc.toString());
        jdbc.update(psc, keyHolder);
        return keyHolder.getKey()
                        .longValue();
    }

    private void saveIngredientToPizza(Ingredient ingredient, long pizzaId) {
        jdbc.update("insert into Pizza_Ingredients (pizza, ingredient) values (?, ?)", pizzaId, ingredient.getId());
    }*/

    private long savePizzaInfo2(Pizza pizza) {
        @SuppressWarnings("unchecked") Map<String, Object> values = objectMapper.convertValue(pizza, Map.class);
        values.put("createdAt", pizza.getCreatedAt());
        long pizzaId = pizzaInsert.executeAndReturnKey(values)
                                  .longValue();
        return pizzaId;
    }

    private void saveIngredientsToPizza2(Ingredient ingredient, long pizzaId) {
        Map<String, Object> values = new HashMap<>();
        values.put("pizza", pizzaId);
        values.put("ingredient", ingredient.getId());
        pizzaIngredientInsert.execute(values);
    }
}
