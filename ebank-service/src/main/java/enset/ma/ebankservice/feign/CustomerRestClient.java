package enset.ma.ebankservice.feign;

import enset.ma.ebankservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "PROJET-CUSTOMER-MICRO-SERVICE")
public interface CustomerRestClient {
    @GetMapping("customers/{id}")
    Customer getCustomerById(@PathVariable Long id);
}
