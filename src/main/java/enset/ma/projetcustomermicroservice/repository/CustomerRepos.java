package enset.ma.projetcustomermicroservice.repository;

import enset.ma.projetcustomermicroservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepos extends JpaRepository<Customer,Long> {
}
