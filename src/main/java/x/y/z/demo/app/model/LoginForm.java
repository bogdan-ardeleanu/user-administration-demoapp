package x.y.z.demo.app.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by Anastasia cea micuta on 10/15/2016.
 */
public class LoginForm {
    @Size(min = 4, max = 10, message = "{Size.LoginForm.accountNo}")
    @Pattern(regexp = "^(0|[1-9][0-9]*)$", message = "{Pattern.LoginForm.accountNo}")
//    @NotNull(message = "{NotNull.LoginForm.accountNo}")
//    @NotEmpty(message = "{NotEmpty.LoginForm.accountNo}")
    private String accountNo;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
}
