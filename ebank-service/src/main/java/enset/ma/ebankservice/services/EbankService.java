package enset.ma.ebankservice.services;

import enset.ma.ebankservice.entity.BankAccount;
import enset.ma.ebankservice.repository.BankAccountRepos;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class EbankService {
    private BankAccountRepos bankAccountRepos;

    public EbankService(BankAccountRepos bankAccountRepos) {
        this.bankAccountRepos = bankAccountRepos;
    }
    public List<BankAccount> getAllBankAccount(){
        return bankAccountRepos.findAll();
    }
    public BankAccount getBankAccountById(Long id){
        return bankAccountRepos.findById(id).orElseThrow(()->new RuntimeException("BankAcount not found"));
    }
    public BankAccount save(BankAccount bankAccount){
        bankAccount.setId(UUID.randomUUID().toString());
        bankAccount.setCreatedAt(new Date());
        return bankAccountRepos.save(bankAccount);
    }
}
