package x.y.z.demo.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import x.y.z.demo.app.entity.CustomerEntity;
import x.y.z.demo.app.model.LoginForm;
import x.y.z.demo.app.repository.CustomerRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

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
        LoginForm loginForm = new LoginForm();
        loginForm.setAccountNo(username);

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<LoginForm>> violations = validator.validate(loginForm);
        if (null != violations && !violations.isEmpty()) {
            StringBuilder sb = new StringBuilder(50);
            for (ConstraintViolation<LoginForm> violation : violations) {
                sb.append(violation.getMessage()).append('\n');
            }

            throw new InternalAuthenticationServiceException(sb.toString());
        }
        CustomerEntity customer = customerRepository.findByAccountNo(username);
        UserDetails userDetails = new User(username, username, null);
        return userDetails;
    }
}