package pl.researchkit.pizzas.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pl.researchkit.pizzas.Ingredient;
import pl.researchkit.pizzas.Order;
import pl.researchkit.pizzas.Pizza;
import pl.researchkit.pizzas.data.IngredientRepository;
import pl.researchkit.pizzas.data.PizzaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static pl.researchkit.pizzas.Ingredient.Type;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignPizzaController {

    private final IngredientRepository ingredientRepo;
    private       PizzaRepository      designRepo;

    @Autowired
    public DesignPizzaController(IngredientRepository ingredientRepo, PizzaRepository designRepo) {
        this.ingredientRepo = ingredientRepo;
        this.designRepo = designRepo;
    }

    @ModelAttribute(name = "order")
    public Order order() {
        return new Order();
    }

    @ModelAttribute(name = "pizza")
    public Pizza pizza() {
        return new Pizza();
    }

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll()
                      .forEach(i -> ingredients.add(i));
        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString()
                                   .toLowerCase(), filterByType(ingredients, type));
        }
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid Pizza design, Errors errors, @ModelAttribute Order order) {
        if (errors.hasErrors()) {
            log.info("Błędy design: " + errors);
            return "design";
        }
        //zapis Pizzy
        Pizza saved = designRepo.save(design);
        order.addDesign(saved);
        log.info("Przetwarzanie projektu pizza: " + design);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                          .filter(t -> t.getType()
                                        .equals(type))
                          .collect(Collectors.toList());
    }
}
