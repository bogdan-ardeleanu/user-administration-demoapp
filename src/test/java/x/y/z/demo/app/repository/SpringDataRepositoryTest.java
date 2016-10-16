package x.y.z.demo.app.repository;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import x.y.z.demo.app.entity.AccountEntity;
import x.y.z.demo.app.entity.CustomerEntity;
import x.y.z.demo.config.datasource.DataSourceConfig;
import x.y.z.demo.config.datasource.PersistenceLayerConfig;

import java.util.List;
import java.util.Random;

/**
 * Created by Anastasia cea micuta on 10/15/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class,
        classes = {
                DataSourceConfig.class,
                PersistenceLayerConfig.class
        })
public class SpringDataRepositoryTest {
    private static final Logger logger = LoggerFactory.getLogger(SpringDataRepositoryTest.class);

    private static final Random random = new Random();

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    public void testCustomerRepository() throws Exception {

        CustomerEntity customer = new CustomerEntity();
        customer.setIdentifier(random.nextInt());
        customer.setFirstName(RandomStringUtils.randomAlphanumeric(20).toUpperCase());
        customer.setLastName(RandomStringUtils.randomAlphanumeric(10).toUpperCase());
        customer = customerRepository.save(customer);

        logger.debug("inserted ID=" + customer.getId());
    }

    @Test
    public void getCustomer() throws Exception {
        CustomerEntity customer = customerRepository.findOne(1l);

        logger.debug("inserted ID=" + customer.getId());
    }

    @Test
    public void testAccountRepository() throws Exception {
        List<AccountEntity> accounts = accountRepository.findByCustomer_IdOrderByNameAsc(1l);
        logger.debug("accounts no = "+ accounts.size());
    }
}
