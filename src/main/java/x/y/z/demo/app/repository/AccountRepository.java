package x.y.z.demo.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import x.y.z.demo.app.entity.AccountEntity;

import java.util.List;

/**
 * Created by Anastasia cea micuta on 10/16/2016.
 */
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    List<AccountEntity> findByCustomer_IdOrderByAccountNoAsc(Long idCustomer);

    @Query(value = "select a from AccountEntity a where a.id = :id and a.customer.identifier = :identifier")
    AccountEntity getAccount(@Param("id") Long idAccount, @Param("identifier") Integer customerIdentifier);
}
