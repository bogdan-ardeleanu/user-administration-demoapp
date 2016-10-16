package x.y.z.demo.app.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by Anastasia cea micuta on 10/15/2016.
 */
public class LoginForm {
    @Size(min = 4, max = 10)
    @Pattern(regexp = "^(0|[1-9][0-9]*)$")
    @NotNull
    private String accountNo;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
}
