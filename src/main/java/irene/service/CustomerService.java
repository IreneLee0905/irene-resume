package irene.service;

import irene.dao.CustomerDao;
import irene.entity.Customer;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

  @Autowired private CustomerDao customerDao;

  public Optional<Customer> loginWithAccount(String account, String password) {

    return customerDao.findByAccountAndPassword(account, password);
  }

  public Customer addCustomer(Customer customer) {
    return customerDao.save(customer);
  }
}
