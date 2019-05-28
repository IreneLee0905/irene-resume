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

  public Optional<Customer> addCustomer(Customer customer) {
    Optional<Customer> optional = findByAccount(customer.getAccount());
    if (!optional.isPresent()) {

      return Optional.of(customerDao.save(customer));
    } else {
      return Optional.empty();
    }
  }

  public Optional<Customer> findByAccount(String account) {
    return customerDao.findByAccount(account);
  }
}
