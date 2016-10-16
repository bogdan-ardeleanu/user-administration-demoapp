package x.y.z.demo.app.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by Anastasia cea micuta on 10/15/2016.
 */
public class LoginForm {
    @Size(min = 4, max = 10, message = "{Size.LoginForm.identifier}")
    @Pattern(regexp = "^(0|[1-9][0-9]*)$", message = "{Pattern.LoginForm.identifier}")
//    @NotNull(message = "{NotNull.LoginForm.identifier}")
//    @NotEmpty(message = "{NotEmpty.LoginForm.identifier}")
    private String identifier;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
}
