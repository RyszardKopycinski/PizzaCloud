package pl.researchkit.pizzas;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Order {

    private Long   id;
    private Date   placedAt;
    @NotBlank(message = "Podanie imienia i nazwiska obowiązkowe.")
    private String deliveryName;
    @NotBlank(message = "Podanie ulicy obowiązkowe.")
    private String deliveryStreet;
    @NotBlank(message = "Podanie miejscowości obowiązkowe.")
    private String deliveryCity;
    @NotBlank(message = "Podanie województwa obowiązkowe.")
    private String deliveryState;
    @NotBlank(message = "Podanie kodu pocztowego obowiązkowe")
    @Pattern(regexp = "^([0-9][0-9])(-)([0-9][0-9][0-9])", message = "nieprawidłowy format kodu pocztowego: '99-999")
    private String deliveryZip;
    @CreditCardNumber(message = "Nieprawidłowy numer karty kredytowej.")
    private String ccNumber;
    @Pattern(regexp = "^(0[1-9]|1[0-2])(/)(2[4-9]|3[0-])$", message = "Data ważności karty musi być w formacie MM/RR")
    private String ccExpiration;
    @Digits(integer = 3, fraction = 0, message = "Nieprawidłowy kod CVV.")
    private String ccCVV;
    List<Pizza> pizzas = new ArrayList<>();

    public void addDesign(Pizza design) {
        this.pizzas.add(design);
    }
}
