package x.y.z.demo.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import x.y.z.demo.app.entity.AccountEntity;
import x.y.z.demo.app.entity.CustomerEntity;
import x.y.z.demo.app.service.CustomerService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anastasia cea micuta on 10/16/2016.
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {
    private static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/details", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Map<String, Object> getDetails() {
        Map<String, Object> result = new HashMap<>();

        String principal = getPrincipal();
        CustomerEntity customer = customerService.getDetails(Integer.parseInt(principal));

        result.put("customer", customer);

        return result;
    }

    @RequestMapping(value = "/account/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public AccountEntity getAccount(@PathVariable("id") Long idAccount) {
        String principal = getPrincipal();
        AccountEntity account = customerService.getAccount(idAccount, Integer.parseInt(principal));
        return account;
    }

    @RequestMapping(value = "/account/{id}/withdrawal", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Map<String, Object> withdrawal(@PathVariable("id") Long idAccount, @RequestParam Integer amount) {
        Map<String, Object> result = new HashMap<>();
        try {
            String principal = getPrincipal();
            customerService.withdrawal(idAccount, Integer.parseInt(principal), amount);

            result.put("success", true);
        } catch (Throwable t) {
            logger.error("deposit error", t);

            result.put("success", false);
            result.put("msg", t.getMessage());
        }


        return result;
    }

    @RequestMapping(value = "/account/{id}/deposit", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Map<String, Object> deposit(@PathVariable("id") Long idAccount, @RequestParam Integer amount) {
        Map<String, Object> result = new HashMap<>();
        try {
            String principal = getPrincipal();
            customerService.deposit(idAccount, Integer.parseInt(principal), amount);

            result.put("success", true);
        } catch (Throwable t) {
            logger.error("deposit error", t);

            result.put("success", false);
            result.put("msg", t.getMessage());
        }


        return result;
    }

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

}
