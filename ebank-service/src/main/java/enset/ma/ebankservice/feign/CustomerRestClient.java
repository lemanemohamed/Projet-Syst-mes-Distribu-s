package enset.ma.ebankservice.feign;

import enset.ma.ebankservice.model.Customer;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PROJET-CUSTOMER-MICRO-SERVICE")
public interface CustomerRestClient {
    @GetMapping("customers/{id}")
    @CircuitBreaker(name = "customerservice",fallbackMethod = "getdefaultcustomer")
    Customer getCustomerById(@PathVariable Long id);
    default Customer getdefaultcustomer(Long id, Exception e){
        return new Customer(id,"not available","not available");
    }
}
