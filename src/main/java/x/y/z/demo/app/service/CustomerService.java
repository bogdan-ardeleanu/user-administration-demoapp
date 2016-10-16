package x.y.z.demo.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import x.y.z.demo.app.entity.AccountEntity;
import x.y.z.demo.app.entity.CustomerEntity;
import x.y.z.demo.app.repository.AccountRepository;
import x.y.z.demo.app.repository.CustomerRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Anastasia cea micuta on 10/16/2016.
 */
@Service
@Transactional
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository accountRepository;

    public CustomerEntity getDetails(Integer customerIdentifier) {
        CustomerEntity customer = customerRepository.findByIdentifier(customerIdentifier);
        List<AccountEntity> accounts = accountRepository.findByCustomer_IdOrderByNameAsc(customer.getId());
        customer.setAccounts(accounts);
        return customer;
    }
}
