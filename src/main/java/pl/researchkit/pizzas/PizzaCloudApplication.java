package pl.researchkit.pizzas;

import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootApplication
public class PizzaCloudApplication {

    public static void main(String[] args) {
        SpringApplication.run(PizzaCloudApplication.class, args);
    }
}