package enset.ma.projetcustomermicroservice;

import enset.ma.projetcustomermicroservice.entities.Customer;
import enset.ma.projetcustomermicroservice.services.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ProjetCustomerMicroServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjetCustomerMicroServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerService customerService){
        return args -> {
            List<String> names=List.of("mohamed","imane","yassine");
            names.forEach(name->{
                customerService.saveCustomer(Customer.builder()
                                .name( name)
                                .email(name+"@gmail.com")
                        .build());
            });
        };
    }
}
