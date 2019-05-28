package irene.dao;

import irene.entity.Customer;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface CustomerDao extends CrudRepository<Customer, Long> {

  List<Customer> findByLastName(String lastName);

  Optional<Customer> findByAccountAndPassword(String account, String password);

  Optional<Customer> findByAccount(String account);
}
