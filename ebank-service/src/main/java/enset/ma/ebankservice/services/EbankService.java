package enset.ma.ebankservice.services;

import enset.ma.ebankservice.entity.BankAccount;
import enset.ma.ebankservice.feign.CustomerRestClient;
import enset.ma.ebankservice.model.Customer;
import enset.ma.ebankservice.repository.BankAccountRepos;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class EbankService {
    private BankAccountRepos bankAccountRepos;
    private CustomerRestClient customerRestClient;

    public EbankService(CustomerRestClient customerRestClient, BankAccountRepos bankAccountRepos) {
        this.customerRestClient = customerRestClient;
        this.bankAccountRepos = bankAccountRepos;
    }

    public List<BankAccount> getAllBankAccount(){
        return bankAccountRepos.findAll();
    }
    public BankAccount getBankAccountById(String id){
        BankAccount bankAccount=bankAccountRepos.findById(id).orElseThrow(()->new RuntimeException("BankAcount not found"));
        bankAccount.setCustomer(customerRestClient.getCustomerById(bankAccount.getCustomerId()));
        return bankAccount;
    }
    public BankAccount save(BankAccount bankAccount){
        try {
            Customer customer=customerRestClient.getCustomerById(bankAccount.getCustomerId());
            bankAccount.setId(UUID.randomUUID().toString());
            bankAccount.setCreatedAt(new Date());
            return bankAccountRepos.save(bankAccount);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}
