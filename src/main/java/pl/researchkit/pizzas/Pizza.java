package pl.researchkit.pizzas;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Pizza {

    private Long         id;
    private Date         createdAt;
    @NotNull
    @Size(min = 5, message = "Nazwa musi składać się z minimim 5 znaków.")
    private String       name;
    @NotEmpty(message = "Musisz wybrać conajmniej jeden składnik.")
    @Size(max = 8, message = "Maksymalnie możesz wybrać 8 składników.")
    private List<String> ingredients;
}
