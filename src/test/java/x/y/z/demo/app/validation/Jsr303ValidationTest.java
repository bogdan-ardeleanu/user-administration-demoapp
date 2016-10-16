package x.y.z.demo.app.validation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import x.y.z.demo.app.model.LoginForm;
import x.y.z.demo.config.web.WebConfig;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

/**
 * Created by Anastasia cea micuta on 10/16/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class,
        classes = {
                WebConfig.class
        })
@WebAppConfiguration
public class Jsr303ValidationTest {
    @Autowired
    private Validator validator;

    @Test
    public void testFormLoginValidation() throws Exception {
        LoginForm loginForm = new LoginForm();
        loginForm.setIdentifier("a");

        // validate the input
//        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//        Validator validator = factory.getValidator();
        Set<ConstraintViolation<LoginForm>> violations = validator.validate(loginForm);

        System.out.print("violation = " + violations);
    }
}
