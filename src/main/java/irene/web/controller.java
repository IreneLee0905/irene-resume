package irene.web;

import irene.entity.Customer;
import irene.service.CustomerService;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
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
  public ResponseEntity<Customer> login(
      @RequestParam String account, @RequestParam String password) {

    Customer customer = customerService.loginWithAccount(account, password).orElse(null);
    if (ObjectUtils.isEmpty(customer)) {
      return new ResponseBuilder<>()
          .status(HttpStatus.UNAUTHORIZED)
          .message("login failure ")
          .build();
    } else {
      return new ResponseBuilder<>().ok(customer);
    }
  }
  // TODO fix the cross origin annotation, put it into config profile or bean
  @CrossOrigin
  @PostMapping("/customer-add")
  public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {

    Optional<Customer> optional = customerService.addCustomer(customer);
    if (optional.isPresent()) {
      return new ResponseBuilder<>().ok(optional.get());
    } else {
      return new ResponseBuilder<>()
          .badRequest("The account is already exist, please try to use other account");
    }
  }
}
