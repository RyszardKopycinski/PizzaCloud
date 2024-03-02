package pl.researchkit.pizzas.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.researchkit.pizzas.Ingredient;
import pl.researchkit.pizzas.Pizza;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static pl.researchkit.pizzas.Ingredient.Type;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignPizzaController {

    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(new Ingredient("SZYN", "szynka", Type.PROTEIN),
                                                     new Ingredient("WOLO", "wołowina", Type.PROTEIN),
                                                     new Ingredient("KURC", "kurczak", Type.PROTEIN),
                                                     new Ingredient("GRKU", "grillowany kurczak", Type.PROTEIN),
                                                     new Ingredient("PEPP", "pepperoni", Type.PROTEIN),
                                                     new Ingredient("SZPA", "szynka parmeńska", Type.PROTEIN),
                                                     new Ingredient("BOCZ", "boczek", Type.PROTEIN),
                                                     new Ingredient("SPPI", "spinata picante", Type.PROTEIN),
                                                     new Ingredient("CHOR", "chorizo", Type.PROTEIN),
                                                     new Ingredient("PAPR", "papryka", Type.VEGGIE),
                                                     new Ingredient("PCHI", "papryczki chilli", Type.VEGGIE),
                                                     new Ingredient("CEBU", "cebula", Type.VEGGIE),
                                                     new Ingredient("CCEB", "czerwona cebula", Type.VEGGIE),
                                                     new Ingredient("POMC", "pomidorki cherry", Type.VEGGIE),
                                                     new Ingredient("KUKU", "kukurydza", Type.VEGGIE),
                                                     new Ingredient("BAZY", "bazylia", Type.VEGGIE),
                                                     new Ingredient("ANAN", "ananas", Type.VEGGIE),
                                                     new Ingredient("COLI", "czarne oliwki", Type.VEGGIE),
                                                     new Ingredient("ZOLI", "zielone oliwki", Type.VEGGIE),
                                                     new Ingredient("PIEC", "pieczarki", Type.VEGGIE),
                                                     new Ingredient("JALA", "jalapeno", Type.VEGGIE),
                                                     new Ingredient("PEST", "pesto", Type.VEGGIE),
                                                     new Ingredient("RUKO", "rukola", Type.VEGGIE),
                                                     new Ingredient("SZCZ", "szczypior", Type.VEGGIE),
                                                     new Ingredient("MOZZ", "mozzarella", Type.CHEESE),
                                                     new Ingredient("RICO", "ricotta", Type.CHEESE),
                                                     new Ingredient("GORG", "gorgonzola", Type.CHEESE),
                                                     new Ingredient("FETA", "feta", Type.CHEESE),
                                                     new Ingredient("PARM", "parmezan", Type.CHEESE),
                                                     new Ingredient("S---", "brak sosu", Type.SAUCE),
                                                     new Ingredient("SPOM", "sos pomidorowy", Type.SAUCE),
                                                     new Ingredient("SBBQ", "sos BBQ", Type.SAUCE),
                                                     new Ingredient("SSMI", "sos śmietanowy", Type.SAUCE));
        Type[] types = Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString()
                                   .toLowerCase(), filterByType(ingredients, type));
        }
    }

    @GetMapping
    public String showDesignForm(Model model) {
        model.addAttribute("design", new Pizza());
        return "design";
    }

    @PostMapping
    public String processDesign(Pizza pizzaDesign) {

        log.info("Przetwarzanie projektu pizza: " + pizzaDesign);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
        return ingredients.stream()
                          .filter(t -> t.getType()
                                        .equals(type))
                          .collect(Collectors.toList());
    }
}
