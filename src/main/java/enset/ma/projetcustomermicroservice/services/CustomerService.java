package enset.ma.projetcustomermicroservice.services;

import enset.ma.projetcustomermicroservice.entities.Customer;
import enset.ma.projetcustomermicroservice.repository.CustomerRepos;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private CustomerRepos customerRepos;

    public CustomerService(CustomerRepos customerRepos) {
        this.customerRepos = customerRepos;
    }
    public List<Customer> getAllCustomer(){
        return customerRepos.findAll();
    }
    public Customer getCustomerById(Long id){
        return customerRepos.findById(id).orElseThrow(()->new RuntimeException("Customer not found"));
    }
    public Customer saveCustomer(Customer customer){
        return customerRepos.save(customer);
    }
}
