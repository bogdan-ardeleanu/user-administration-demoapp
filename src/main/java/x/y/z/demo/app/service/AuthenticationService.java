package x.y.z.demo.app.service;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import x.y.z.demo.app.entity.CustomerEntity;
import x.y.z.demo.app.repository.CustomerRepository;

/**
 * Created by Anastasia cea micuta on 10/16/2016.
 */
@Service
public class AuthenticationService implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        if(!NumberUtils.isNumber(username)) {
            throw new InternalAuthenticationServiceException("ID-ul trebuie sa fie numeric");
        }
        CustomerEntity customer = customerRepository.findByAccountNo(username);
        UserDetails userDetails = new User(username, username, null);
        return userDetails;
    }
}