package enset.ma.projetcustomermicroservice.services;

import enset.ma.projetcustomermicroservice.entities.Customer;
import enset.ma.projetcustomermicroservice.repository.CustomerRepos;
import org.springframework.ai.mcp.annotation.McpTool;
import org.springframework.ai.mcp.annotation.McpToolParam;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private CustomerRepos customerRepos;

    public CustomerService(CustomerRepos customerRepos) {
        this.customerRepos = customerRepos;
    }
    @McpTool(description = "Get All Customers")
    public List<Customer> getAllCustomer(){
        return customerRepos.findAll();
    }
    @McpTool(description = "Find customer by his Id")
    public Customer getCustomerById(@McpToolParam(description = "The customer Id") Long id){
        return customerRepos.findById(id).orElseThrow(()->new RuntimeException("Customer not found"));
    }
    @McpTool(description = "save a new customer")
    public Customer saveCustomer(@McpToolParam(description = "The Customer to save(name,email)") Customer customer){
        return customerRepos.save(customer);
    }
}
