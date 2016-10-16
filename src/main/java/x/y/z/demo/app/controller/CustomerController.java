package x.y.z.demo.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import x.y.z.demo.app.entity.AccountEntity;
import x.y.z.demo.app.entity.CustomerEntity;
import x.y.z.demo.app.service.CustomerService;

import java.util.HashMap;
import java.util.List;
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
