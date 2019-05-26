package irene.web;

import irene.entity.Customer;
import irene.service.CustomerService;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Log4j
@RestController
public class controller {

  @Autowired private CustomerService customerService;

  @RequestMapping("/root")
  public Map root(@RequestParam(name = "name", defaultValue = "Gest") String name) {
    // double brace for initialization
    return new HashMap<String, String>() {
      {
        put("name", name);
        put("id", "1");
      }
    };
  }

  // TODO fix the cross origin annotation, put it into config profile or bean
  @CrossOrigin
  @PostMapping("/login")
  public ResponseEntity<Customer> login(@RequestParam String account, @RequestParam String password) {

    Customer customer = customerService.loginWithAccount(account, password).orElse(null);
    if (ObjectUtils.isEmpty(customer)) {
      return new ResponseBuilder<>().status(HttpStatus.UNAUTHORIZED).message("login failure ").build();
    } else {
      return new ResponseBuilder<>().ok(customer);
    }
  }
  // TODO fix the cross origin annotation, put it into config profile or bean
  @CrossOrigin
  @PostMapping("/customer-add")
  public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {

    try {


      return new ResponseBuilder<Customer>()
          .ok(customerService.addCustomer(customer));

    } catch (Exception e) {
      log.error("unexpected error occur when saving customer into database :{}", e);
      return new ResponseBuilder<>().badRequest(e.getMessage());
    }
  }


}
