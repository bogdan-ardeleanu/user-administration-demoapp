package x.y.z.demo.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import x.y.z.demo.app.entity.CustomerEntity;

/**
 * Created by Anastasia cea micuta on 10/15/2016.
 */
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
    CustomerEntity findByAccountNo(String accountNo);
}
