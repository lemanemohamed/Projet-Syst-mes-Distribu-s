package enset.ma.ebankservice.repository;

import enset.ma.ebankservice.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BankAccountRepos extends JpaRepository<BankAccount,Long> {
    List<BankAccount> findCustomerById(Long id);
}
