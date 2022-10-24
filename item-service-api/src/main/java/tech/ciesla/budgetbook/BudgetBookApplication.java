package tech.ciesla.budgetbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class BudgetBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(BudgetBookApplication.class, args);
    }
}
