package x.y.z.demo.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
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
import javax.validation.Validator;
import java.util.Locale;
import java.util.Set;

/**
 * Created by Anastasia cea micuta on 10/16/2016.
 */
@Service
public class AuthenticationService implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    @Qualifier(value = "messageSource")
    private MessageSource messageSource;

    @Autowired
    @Qualifier(value = "validator")
    private Validator validator;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        LoginForm loginForm = new LoginForm();
        loginForm.setIdentifier(username);

        Set<ConstraintViolation<LoginForm>> violations = validator.validate(loginForm);
        if (null != violations && !violations.isEmpty()) {
            StringBuilder sb = new StringBuilder(50);
            for (ConstraintViolation<LoginForm> violation : violations) {
                sb.append(violation.getMessage()).append(". ");
            }

            throw new InternalAuthenticationServiceException(sb.toString());
        }
        CustomerEntity customer = customerRepository.findByIdentifier(Integer.parseInt(username));
        if (null == customer) {
            Locale locale = LocaleContextHolder.getLocale();
            String msgException = messageSource.getMessage("NotFound.auth.identifier", new Object[]{username}, locale);
            throw new InternalAuthenticationServiceException(msgException);
        }
        UserDetails userDetails = new User(username, username, null);
        return userDetails;
    }
}