package x.y.z.demo.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import x.y.z.demo.app.entity.AccountEntity;
import x.y.z.demo.app.entity.CustomerEntity;

import java.util.List;

/**
 * Created by Anastasia cea micuta on 10/16/2016.
 */
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    List<AccountEntity> findByCustomer_IdOrderByNameAsc(Long idCustomer);
}
