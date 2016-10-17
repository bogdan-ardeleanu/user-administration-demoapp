package x.y.z.demo.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import x.y.z.demo.app.entity.AccountEntity;
import x.y.z.demo.app.entity.CustomerEntity;
import x.y.z.demo.app.repository.AccountRepository;
import x.y.z.demo.app.repository.CustomerRepository;

import java.util.List;

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

    @Autowired
    @Qualifier(value = "messageSource")
    private MessageSource messageSource;

    public CustomerEntity getDetails(Integer customerIdentifier) {
        CustomerEntity customer = customerRepository.findByIdentifier(customerIdentifier);
        List<AccountEntity> accounts = accountRepository.findByCustomer_IdOrderByAccountNoAsc(customer.getId());
        customer.setAccounts(accounts);
        return customer;
    }

    public AccountEntity getAccount(Long idAccount, Integer customerIdentifier) {
        AccountEntity account = accountRepository.getAccount(idAccount, customerIdentifier);
        return account;
    }

    public void deposit(Long idAccount, Integer customerIdentifier, Integer amount) {
        if (amount < 1) {
            String msg = messageSource.getMessage("Pozitive.amount", null, LocaleContextHolder.getLocale());
            throw new IllegalArgumentException(msg);
        }

        AccountEntity account = accountRepository.getAccount(idAccount, customerIdentifier);
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }

    public void withdrawal(Long idAccount, Integer customerIdentifier, Integer amount) {
        if (amount < 1) {
            String msg = messageSource.getMessage("Pozitive.amount", null, LocaleContextHolder.getLocale());
            throw new IllegalArgumentException(msg);
        }

        AccountEntity account = accountRepository.getAccount(idAccount, customerIdentifier);
        if (amount > account.getBalance()) {
            String msg = messageSource.getMessage("Negative.amount", null, LocaleContextHolder.getLocale());
            throw new IllegalArgumentException(msg);
        }

        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
    }
}
